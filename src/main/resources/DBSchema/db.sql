SET SQL_SAFE_UPDATES = 0;
-- MySQL Workbench Forward Engineering
-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `db` ;

-- -----------------------------------------------------
-- Table `mydb`.`role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`role` ;

CREATE TABLE IF NOT EXISTS `mydb`.`role` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `role` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`));

INSERT INTO role (id, role) VALUES (default, 'manager');
INSERT INTO role (id, role) VALUES (default, 'master');
INSERT INTO role (id, role) VALUES (default, 'user');
-- -----------------------------------------------------
-- Table `mydb`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`user` ;

CREATE TABLE IF NOT EXISTS `mydb`.`user` (
    `login` VARCHAR(45) NOT NULL,
    `email` VARCHAR(255) NOT NULL,
    `password` VARCHAR(50) NOT NULL,
    `first_name` VARCHAR(45) NOT NULL,
    `last_name` VARCHAR(45) NOT NULL,
    `phone_number` VARCHAR(12) NOT NULL,
    `account` INT NULL,
    `role_id` INT NOT NULL,
    PRIMARY KEY (`login`),
    UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE,
    INDEX `fk_user_role1_idx` (`role_id` ASC) VISIBLE,
    UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
    CONSTRAINT `fk_user_role1`
    FOREIGN KEY (`role_id`)
    REFERENCES `mydb`.`role` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
INSERT INTO user(login, email, password, first_name, last_name, phone_number, account, role_id)
VALUES ('MainManeger', 'maneger@gmail.com', 12345, 'Sasha', 'Kabaldin', 380111111111, 0, 1);
INSERT INTO user(login, email, password, first_name, last_name, phone_number, account, role_id)
VALUES ('MasterOne', 'masterone@gmail.com', 'master1', 'Denis', 'Denisovich', 380888888888, 0, 2);
INSERT INTO user(login, email, password, first_name, last_name, phone_number, account, role_id)
VALUES ('MasterTwo', 'mastertwo@gmail.com', 'master2', 'Pavlo', 'Pavlovich', 380999999999, 0, 2);

-- -----------------------------------------------------
-- Table `mydb`.`compilation_status`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`compilation_status` ;

CREATE TABLE IF NOT EXISTS `mydb`.`compilation_status` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `compilation_status` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`));
INSERT INTO compilation_status(id, compilation_status) VALUES (default, 'In work');
INSERT INTO compilation_status(id, compilation_status) VALUES (default, 'Finish');

-- -----------------------------------------------------
-- Table `mydb`.`payment_status`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`payment_status` ;

CREATE TABLE IF NOT EXISTS `mydb`.`payment_status` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `payment_status` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`));

INSERT INTO payment_status(id, payment_status) VALUES (default, 'Waiting for pay');
INSERT INTO payment_status(id, payment_status) VALUES (default, 'Paid');
INSERT INTO payment_status(id, payment_status) VALUES (default, 'Canceled');

-- -----------------------------------------------------
-- Table `mydb`.`request`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`request` ;

CREATE TABLE IF NOT EXISTS `mydb`.`request` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `description` VARCHAR(10000) NOT NULL,
    `date` DATETIME NULL,
    `total_cost` DECIMAL(9) NOT NULL,
    `user_login` VARCHAR(45) NOT NULL,
    `compilation_status_id` INT NOT NULL,
    `payment_status_id` INT NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_request_user1_idx` (`user_login` ASC) VISIBLE,
    INDEX `fk_request_compilation_status1_idx` (`compilation_status_id` ASC) VISIBLE,
    INDEX `fk_request_payment_status1_idx` (`payment_status_id` ASC) VISIBLE,
    CONSTRAINT `fk_request_user1`
    FOREIGN KEY (`user_login`)
    REFERENCES `mydb`.`user` (`login`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    CONSTRAINT `fk_request_compilation_status1`
    FOREIGN KEY (`compilation_status_id`)
    REFERENCES `mydb`.`compilation_status` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    CONSTRAINT `fk_request_payment_status1`
    FOREIGN KEY (`payment_status_id`)
    REFERENCES `mydb`.`payment_status` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);


-- -----------------------------------------------------
-- Table `mydb`.`feedback`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`feedback` ;

CREATE TABLE IF NOT EXISTS `mydb`.`feedback` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    `datatime` DATETIME NULL,
    `feedback` VARCHAR(10000) NULL,
    `rating` INT NOT NULL,
    `request_id` INT NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_feedback_request1_idx` (`request_id` ASC) VISIBLE,
    CONSTRAINT `fk_feedback_request1`
    FOREIGN KEY (`request_id`)
    REFERENCES `mydb`.`request` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);