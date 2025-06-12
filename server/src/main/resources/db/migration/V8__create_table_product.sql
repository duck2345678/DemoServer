CREATE TABLE Product (
    productID SERIAL PRIMARY KEY,
    productName VARCHAR(255) NOT NULL,
    unit INTEGER NOT NULL,
    importPrice INTEGER NOT NULL,
    exportPrice INTEGER,
    inventoryQuantity INTEGER,

    CONSTRAINT fk_product_unit FOREIGN KEY (unit)
        REFERENCES Unit(unitID)
);
