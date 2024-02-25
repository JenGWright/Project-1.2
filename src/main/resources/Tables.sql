DROP TABLE PRODUCT IF EXISTS;
DROP TABLE SELLER IF EXISTS;


CREATE TABLE SELLER (name varchar(255) primary key);

CREATE TABLE PRODUCT (
    productID long,
    productName varchar (255),
    sellerName varchar(255) references SELLER(name),
    productPrice double);



