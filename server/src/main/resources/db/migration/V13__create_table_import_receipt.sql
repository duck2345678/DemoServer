CREATE TABLE import_receipt (
    importreceiptid INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    datereceipt DATE,
    totalprice INT NOT NULL
);
