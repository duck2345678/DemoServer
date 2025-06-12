public static class ImportDetailID implements Serializable {
    private int importReceiptID;
    private int productID;

    public ImportDetailID() {}

    public ImportDetailID(int importReceiptID, int productID) {
        this.importReceiptID = importReceiptID;
        this.productID = productID;
    }

    public int getImportReceiptID() {
        return importReceiptID;
    }

    public void setImportReceiptID(int importReceiptID) {
        this.importReceiptID = importReceiptID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    // ✅ Override equals()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ImportDetailID)) return false;
        ImportDetailID that = (ImportDetailID) o;
        return importReceiptID == that.importReceiptID &&
               productID == that.productID;
    }

    // ✅ Override hashCode()
    @Override
    public int hashCode() {
        return java.util.Objects.hash(importReceiptID, productID);
    }
}
