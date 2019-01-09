drop database if exists amuzon;
create database amuzon;
use amuzon;

create table users(
userID               TINYINT UNSIGNED,
name            varchar(25)	 NOT NULL,
surname            varchar(25)	 NOT NULL,
userName            varchar(25)	 NOT NULL,
email            varchar(50)	 NOT NULL,
password               char(9)
);

create table warehouseOperator(
operatorID               TINYINT UNSIGNED,
name            varchar(25)	 NOT NULL,
surname            varchar(25)	 NOT NULL,
userName            varchar(25)	 NOT NULL,
password               char(9),
isManager         boolean
);

create table vehicles(
vehicleID  		TINYINT UNSIGNED,
statee  	boolean,
position    VARCHAR(25)     NOT NULL,
velocity 	decimal(7,2)
);

create table orders(
orderID               TINYINT UNSIGNED,
userID                TINYINT UNSIGNED,
vehicleID                TINYINT UNSIGNED,
datee                 date,
statee             VARCHAR(25)     NOT NULL,
total                decimal(7,2)
);



create table category(
categoryID  		VARCHAR(25)   NOT NULL
);

create table product(
productID               TINYINT UNSIGNED,
categoryID               VARCHAR(25) NOT NULL,
description            varchar(65)	 NOT NULL,
stock            SMALLINT UNSIGNED,
prize                decimal(7,2),
warehouseID               TINYINT UNSIGNED
);

create table item(
orderID  			TINYINT UNSIGNED not null,
productID	 		TINYINT UNSIGNED not null,
vehicleID  			TINYINT UNSIGNED not null,
statee				varchar(10) not null				
);


ALTER TABLE users 
	ADD  CONSTRAINT PK_users PRIMARY KEY users(userID);
	
	
ALTER TABLE warehouseOperator 
	ADD  CONSTRAINT PK_warehouseOperator PRIMARY KEY warehouseOperator(operatorID);
	
ALTER TABLE vehicles 
	ADD  CONSTRAINT PK_vehicles PRIMARY KEY vehicles(vehicleID);
	
ALTER TABLE orders 
	ADD  CONSTRAINT PK_orders PRIMARY KEY orders(orderID),
	ADD CONSTRAINT FK_orders1 FOREIGN KEY orders (userID) REFERENCES users(userID),
	ADD CONSTRAINT FK_orders2 FOREIGN KEY orders (vehicleID) REFERENCES vehicles(vehicleID);
	
ALTER TABLE category 
	ADD  CONSTRAINT PK_category PRIMARY KEY category(categoryID);	
	
	
ALTER TABLE product 
	ADD  CONSTRAINT PK_product PRIMARY KEY product(productID),
	ADD CONSTRAINT FK_product FOREIGN KEY product (categoryID) REFERENCES category(categoryID);
	

ALTER TABLE item 
	ADD CONSTRAINT PK_item PRIMARY KEY item (orderID,productID,vehicleID),
	ADD CONSTRAINT FK_item1 FOREIGN KEY item (orderID) REFERENCES orders(orderID),
	ADD CONSTRAINT FK_item2 FOREIGN KEY item (productID) REFERENCES product(productID),
	ADD CONSTRAINT FK_item3 FOREIGN KEY item (vehicleID) REFERENCES vehicles(vehicleID);