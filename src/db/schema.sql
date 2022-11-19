CREATE DATABASE IF NOT EXISTS item_db;

use item_db;

CREATE TABLE IF NOT EXISTS inventory(
    item_name VARCHAR(50) PRIMARY KEY,
    item_amount DOUBLE,
    item_qty INTEGER,
    item_image VARCHAR(250)
);

CREATE TABLE IF NOT EXISTS user_selection(
    item_name VARCHAR(50) PRIMARY KEY,
    item_amount DOUBLE,
    item_qty INTEGER
);