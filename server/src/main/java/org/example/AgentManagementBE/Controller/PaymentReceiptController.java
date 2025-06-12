package org.example.AgentManagementBE.Controller;

import org.example.AgentManagementBE.Model.PaymentReceipt;
import org.example.AgentManagementBE.Service.PaymentReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/paymentReceipt")
public class PaymentReceiptController {
    @Autowired
    private final PaymentReceiptService paymentReceiptService;

    public PaymentReceiptController(PaymentReceiptService paymentReceiptService) {
        this.paymentReceiptService = paymentReceiptService;
    }

    @GetMapping("/getAllPaymentReceipts")
    public ResponseEntity<Map<String, Object>> getAllPaymentReceipts() {
        Map<String, Object> response = new HashMap<>();
        try {
            Iterable<PaymentReceipt> paymentReceipts = paymentReceiptService.getAllPaymentReceipt();
            response.put("code", 200);
            response.put("status", "success");
            response.put("message", "Lấy danh sách phiếu thu tiền thành công");
            response.put("data", paymentReceipts);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("code", 500);
            response.put("status", "error");
            response.put("message", "Lỗi khi lấy danh sách phiếu thu tiền: " + e.getMessage());
            response.put("data", null);
            return ResponseEntity.status(500).body(response);
        }
    }

    @GetMapping("/getPaymentReceiptById")
    public ResponseEntity<Map<String, Object>> getPaymentReceiptById(@RequestParam int paymentReceiptId) {
        Map<String, Object> response = new HashMap<>();
        try {
            Iterable<PaymentReceipt> paymentReceipts = paymentReceiptService.getPaymentReceiptByID(paymentReceiptId);
            if (paymentReceipts.iterator().hasNext()) {
                response.put("code", 200);
                response.put("status", "success");
                response.put("message", "Tìm thấy phiếu thu tiền thành công");
                response.put("data", paymentReceipts);
                return ResponseEntity.ok(response);
            } else {
                response.put("code", 404);
                response.put("status", "error");
                response.put("message", "Không tìm thấy phiếu thu tiền");
                response.put("data", null);
                return ResponseEntity.status(404).body(response);
            }
        } catch (Exception e) {
            response.put("code", 500);
            response.put("status", "error");
            response.put("message", "Lỗi khi tìm phiếu thu tiền: " + e.getMessage());
            response.put("data", null);
            return ResponseEntity.status(500).body(response);
        }
    }

    @GetMapping("/getPaymentReceiptsByAgentId")
    public ResponseEntity<Map<String, Object>> getPaymentReceiptsByAgentId(@RequestParam int agentId) {
        Map<String, Object> response = new HashMap<>();
        try {
            Iterable<PaymentReceipt> paymentReceipts = paymentReceiptService.getPaymentReceiptByAgentID(agentId);
            if (paymentReceipts.iterator().hasNext()) {
                response.put("code", 200);
                response.put("status", "success");
                response.put("message", "Lấy danh sách phiếu thu tiền theo đại lý thành công");
                response.put("data", paymentReceipts);
                return ResponseEntity.ok(response);
            } else {
                response.put("code", 404);
                response.put("status", "error");
                response.put("message", "Không tìm thấy phiếu thu tiền của đại lý");
                response.put("data", null);
                return ResponseEntity.status(404).body(response);
            }
        } catch (Exception e) {
            response.put("code", 500);
            response.put("status", "error");
            response.put("message", "Lỗi khi lấy danh sách phiếu thu tiền: " + e.getMessage());
            response.put("data", null);
            return ResponseEntity.status(500).body(response);
        }
    }

    @PostMapping("/addPaymentReceipt")
    public ResponseEntity<Map<String, Object>> createPaymentReceipt(@RequestBody PaymentReceipt paymentReceipt) {
        Map<String, Object> response = new HashMap<>();
        try {
            if (paymentReceipt == null) {
                response.put("code", 400);
                response.put("status", "error");
                response.put("message", "Dữ liệu phiếu thu tiền không được để trống");
                response.put("data", null);
                return ResponseEntity.badRequest().body(response);
            }

            if (paymentReceiptService.insertPaymentReceipt(paymentReceipt)) {
                response.put("code", 201);
                response.put("status", "success");
                response.put("message", "Tạo phiếu thu tiền thành công");
                response.put("data", "Đã tạo phiếu thu tiền mới");
                return ResponseEntity.status(201).body(response);
            } else {
                response.put("code", 400);
                response.put("status", "error");
                response.put("message", "Tạo phiếu thu tiền thất bại. Số tiền thu vượt quá số tiền nợ");
                response.put("data", null);
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            response.put("code", 500);
            response.put("status", "error");
            response.put("message", "Lỗi khi tạo phiếu thu tiền: " + e.getMessage());
            response.put("data", null);
            return ResponseEntity.status(500).body(response);
        }
    }
}
