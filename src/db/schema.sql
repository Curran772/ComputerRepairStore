CREATE DATABASE IF NOT EXISTS item_db;

use item_db;

CREATE TABLE IF NOT EXISTS inventory(
    item_id INT AUTO_INCREMENT PRIMARY KEY,
    item_name VARCHAR(50),
    item_qty INTEGER,
    item_amount DOUBLE
);