CREATE TABLE import_detail (
    import_receiptid INT NOT NULL,
    productid INT NOT NULL,
    quantity_import INT,
    import_price INT,
    into_money INT,
    unit INT,

    CONSTRAINT pk_import_detail PRIMARY KEY (import_receiptid, productid),

    CONSTRAINT fk_import_detail_import_receipt FOREIGN KEY (import_receiptid)
        REFERENCES import_receipt(import_receiptid)
        ON DELETE CASCADE,

    CONSTRAINT fk_import_detail_product FOREIGN KEY (productid)
        REFERENCES product(productid)
        ON DELETE CASCADE
);
