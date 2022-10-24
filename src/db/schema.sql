CREATE DATABASE IF NOT EXISTS item_db;

use item_db;

CREATE TABLE IF NOT EXISTS product(
    item_name VARCHAR(50) PRIMARY KEY,
    item_qty INTEGER,
    item_amount DOUBLE
);