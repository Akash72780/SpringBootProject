CREATE TABLE PRODUCT(
ID INT AUTO_INCREMENT,
NAME VARCHAR(255),
PRICE DOUBLE,
GENRE VARCHAR(255),
AUTHOR VARCHAR(255),
TYPE VARCHAR(255),
BRAND VARCHAR(255),
DESIGN VARCHAR(255)
);
CREATE TABLE USER(
ID INT AUTO_INCREMENT,
NAME VARCHAR(255),
PASSWORD VARCHAR(255),
EMAIL VARCHAR(255)
);
CREATE TABLE CART(
ID INT AUTO_INCREMENT,
USERID VARCHAR(255),
PRODID VARCHAR(255),
COUNT DOUBLE,
PRICE DOUBLE
);
