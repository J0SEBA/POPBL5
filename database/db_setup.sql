drop database if exists amuzon;
create database amuzon;
use amuzon;

create table users(
userID               INT AUTO_INCREMENT,
name            varchar(25)	 NOT NULL,
surname            varchar(25)	 NOT NULL,
userName            varchar(25)	 NOT NULL,
email            varchar(50)	 NOT NULL,
password               char(9),
gender			varchar(25),
born_date				date,
PRIMARY KEY(userID)
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
userID                INT ,
datee                 date,
statee             VARCHAR(25)     NOT NULL,
total                decimal(7,2),
FOREIGN KEY (userID) REFERENCES users(userID)
);



create table category(
categoryID  		VARCHAR(25)   NOT NULL
);

create table product(
productID               TINYINT UNSIGNED,
categoryID               VARCHAR(25) NOT NULL,
description            varchar(65)	 NOT NULL,
stock            SMALLINT UNSIGNED,
price                decimal(7,2),
warehouseID               TINYINT UNSIGNED
);

create table orders_product(
orderID  			TINYINT UNSIGNED not null,
productID	 		TINYINT UNSIGNED not null,
quantity			TINYINT UNSIGNED not null,
price				TINYINT UNSIGNED not null,
remaining			TINYINT UNSIGNED not null				
);


	
	
ALTER TABLE warehouseOperator 
	ADD  CONSTRAINT PK_warehouseOperator PRIMARY KEY warehouseOperator(operatorID);
	
ALTER TABLE vehicles 
	ADD  CONSTRAINT PK_vehicles PRIMARY KEY vehicles(vehicleID);
	
ALTER TABLE orders 
	ADD  CONSTRAINT PK_orders PRIMARY KEY orders(orderID);
	
	
ALTER TABLE category 
	ADD  CONSTRAINT PK_category PRIMARY KEY category(categoryID);	
	
	
ALTER TABLE product 
	ADD  CONSTRAINT PK_product PRIMARY KEY product(productID),
	ADD CONSTRAINT FK_product FOREIGN KEY product (categoryID) REFERENCES category(categoryID);
	

ALTER TABLE orders_product 
	ADD CONSTRAINT PK_orders_product PRIMARY KEY orders_product (orderID,productID),
	ADD CONSTRAINT FK_orders_product1 FOREIGN KEY orders_product (orderID) REFERENCES orders(orderID),
	ADD CONSTRAINT FK_orders_product2 FOREIGN KEY orders_product (productID) REFERENCES product(productID);