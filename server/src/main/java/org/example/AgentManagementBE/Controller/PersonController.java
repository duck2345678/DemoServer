package org.example.AgentManagementBE.Controller;

import org.example.AgentManagementBE.Model.Person;
import org.example.AgentManagementBE.Service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.List;
import java.util.HashMap;

@RestController
@RequestMapping("/user") 
public class PersonController {
    @Autowired
    private final PersonService personService;
    
    public PersonController(PersonService personService) {
        this.personService = personService;
    }
    
    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> createPerson(@RequestBody Person newPerson) {
        return personService.createPerson(newPerson);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> getUserByEmail(@RequestBody Map<String, String> loginRequest) {
        String personEmail = loginRequest.get("personEmail");
        String personPassword = loginRequest.get("personPassword");
        return personService.getUserByEmail(personEmail, personPassword);
    }

    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> findByNameSimilarity(@RequestParam String personName) {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Person> users = personService.findByNameSimilarity(personName);
            if (!users.isEmpty()) {
                response.put("code", 200);
                response.put("status", "success");
                response.put("message", "Tìm kiếm người dùng thành công!");
                response.put("data", users);
                return ResponseEntity.ok(response);
            } else {
                response.put("code", 404);
                response.put("status", "error");
                response.put("message", "Không tìm thấy người dùng nào!");
                response.put("data", null);
                return ResponseEntity.status(404).body(response);
            }
        } catch (Exception e) {
            response.put("code", 500);
            response.put("status", "error");
            response.put("message", "Lỗi khi tìm kiếm người dùng: " + e.getMessage());
            response.put("data", null);
            return ResponseEntity.status(500).body(response);
        }
    }
}
