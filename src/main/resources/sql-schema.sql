CREATE SCHEMA IF NOT EXISTS `logisticshackathon`;

USE `logisticshackathon` ;
CREATE TABLE IF NOT EXISTS `logisticshackathon`.`users` (
    `user_id` INT(11) NOT NULL AUTO_INCREMENT,
    `role` VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`user_id`)
);
USE `logisticshackathon` ;
CREATE TABLE IF NOT EXISTS `logisticshackathon`.`managers` (
    `fk_user_id` INT(11) NOT NULL AUTO_INCREMENT,
    `firstname` VARCHAR(40) DEFAULT NULL,
    `surname` VARCHAR(40) DEFAULT NULL,
    FOREIGN KEY (`fk_user_id`) REFERENCES users(`user_id`)
);
CREATE TABLE IF NOT EXISTS `logisticshackathon`.`drivers` (
    `fk_user_id` INT(11) NOT NULL AUTO_INCREMENT,
    `firstname` VARCHAR(40) DEFAULT NULL,
    `surname` VARCHAR(40) DEFAULT NULL,
    FOREIGN KEY (`fk_user_id`) REFERENCES users(`user_id`)
);

CREATE TABLE IF NOT EXISTS `logisticshackathon`.`products` (
    `product_id` INT(11) NOT NULL AUTO_INCREMENT,
    `product_name` VARCHAR(40) DEFAULT NULL,
     PRIMARY KEY (`product_id`)
);


CREATE TABLE IF NOT EXISTS `logisticshackathon`.`orders` (
    `order_id` INT(11) NOT NULL AUTO_INCREMENT,
    `fk_user_id` INT(11),	
    `quantity` INT(11) NOT NULL ,
    `customer_name` VARCHAR(40) DEFAULT NULL,
    `customer_address` VARCHAR(40) DEFAULT NULL,
     PRIMARY KEY (`order_id`),
FOREIGN KEY (`fk_user_id`) REFERENCES managers(`fk_user_id`)
);

CREATE TABLE IF NOT EXISTS `logisticshackathon`.`orderassignment` (
    `orderproduct_id` INT(11) NOT NULL AUTO_INCREMENT,
`fk_product_id` INT(11) NOT NULL ,
`fk_order_id` INT(11) NOT NULL ,
`fk_user_id` INT(11) NOT NULL ,
 `delivery_status` BOOLEAN DEFAULT FALSE,
     PRIMARY KEY (`orderproduct_id`),
FOREIGN KEY (`fk_product_id`) REFERENCES products(`product_id`),
FOREIGN KEY (`fk_order_id`) REFERENCES orders (`order_id`),
FOREIGN KEY (`fk_user_id`) REFERENCES drivers(`fk_user_id`)
);