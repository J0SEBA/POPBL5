CREATE USER 'admin'@'localhost' IDENTIFIED BY 'admin';
GRANT ALL PRIVILEGES ON * . * TO 'admin'@'localhost';
FLUSH PRIVILEGES;

DROP SCHEMA IF EXISTS Automatic_Distribution_System;
CREATE SCHEMA IF NOT EXISTS Automatic_Distribution_System DEFAULT CHARACTER SET utf8;
USE Automatic_Distribution_System;


