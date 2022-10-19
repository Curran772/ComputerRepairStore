CREATE DATABASE IF NOT EXISTS inventory_db;

use inventory_db;

CREATE TABLE IF NOT EXISTS product(
    item_name VARCHAR(50) PRIMARY KEY,
    item_amount DOUBLE,
    item_qty DOUBLE
);