USE `bank`;

CREATE TABLE IF NOT EXISTS `bank`.`customers` (
    `customer_id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    `surname` VARCHAR(45) NOT NULL,
    `patronymic` VARCHAR(45) NOT NULL,
    `phone` VARCHAR(13) NOT NULL,
    `email` VARCHAR(45) NOT NULL,
    `address` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`customer_id`));

CREATE TABLE IF NOT EXISTS `bank`.`account_types` (
    `account_type_id` INT NOT NULL AUTO_INCREMENT,
    `account_type_name` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`account_type_id`));

CREATE TABLE IF NOT EXISTS `bank`.`banks` (
    `bank_id` BIGINT NOT NULL AUTO_INCREMENT,
    `bank_name` VARCHAR(45) NOT NULL,
    `main_location` VARCHAR(45) NOT NULL,
    `main_phone` VARCHAR(13) NOT NULL,
    PRIMARY KEY (`bank_id`));

CREATE TABLE IF NOT EXISTS `bank`.`bank_branches` (
    `bank_branch_id` INT NOT NULL AUTO_INCREMENT,
    `bank_id` BIGINT NOT NULL,
    `location` VARCHAR(45) NOT NULL,
    `phone` VARCHAR(13) NOT NULL,
    PRIMARY KEY (`bank_branch_id`),
    FOREIGN KEY (`bank_id`)
    REFERENCES `bank`.`banks` (`bank_id`));

CREATE TABLE IF NOT EXISTS `bank`.`accounts` (
    `account_id` BIGINT NOT NULL AUTO_INCREMENT,
    `customer_id` BIGINT UNSIGNED NOT NULL,
    `account_type_id` INT NOT NULL,
    `bank_branch_id` INT NOT NULL,
    `balance` DOUBLE NULL,
    PRIMARY KEY (`account_id`),
    FOREIGN KEY (`customer_id`)
    REFERENCES `bank`.`customers` (`customer_id`),
    FOREIGN KEY (`account_type_id`)
    REFERENCES `bank`.`account_types` (`account_type_id`),
    FOREIGN KEY (`bank_branch_id`)
    REFERENCES `bank`.`bank_branches` (`bank_branch_id`));

CREATE TABLE IF NOT EXISTS `bank`.`transaction_types` (
    `transaction_type_id` INT NOT NULL AUTO_INCREMENT,
    `transaction_type_name` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`transaction_type_id`));

CREATE TABLE IF NOT EXISTS `bank`.`transactions` (
    `transaction_id` BIGINT NOT NULL AUTO_INCREMENT,
    `account_id` BIGINT NOT NULL,
    `transaction_type_id` INT NOT NULL,
    `amount` DOUBLE NOT NULL,
    `date` DATETIME NOT NULL,
    PRIMARY KEY (`transaction_id`),
    FOREIGN KEY (`account_id`)
    REFERENCES `bank`.`accounts` (`account_id`),
    FOREIGN KEY (`transaction_type_id`)
    REFERENCES `bank`.`transaction_types` (`transaction_type_id`));

CREATE TABLE IF NOT EXISTS `bank`.`bank_operation_types` (
    `bank_operation_type_id` INT NOT NULL AUTO_INCREMENT,
    `bank_operation_type_name` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`bank_operation_type_id`));

CREATE TABLE IF NOT EXISTS `bank`.`bank_operations` (
    `bank_operation_id` INT NOT NULL,
    `bank_operation_type_id` INT NOT NULL,
    `account_id` BIGINT NOT NULL,
    `date` DATETIME NOT NULL,
    PRIMARY KEY (`bank_operation_id`),
    FOREIGN KEY (`bank_operation_type_id`)
    REFERENCES `bank`.`bank_operation_types` (`bank_operation_type_id`),
    FOREIGN KEY (`account_id`)
    REFERENCES `bank`.`accounts` (`account_id`));

CREATE TABLE IF NOT EXISTS `bank`.`positions` (
    `position_id` INT NOT NULL AUTO_INCREMENT,
    `position_name` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`position_id`));

CREATE TABLE IF NOT EXISTS `bank`.`employees` (
    `employee_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `bank_branch_id` INT NOT NULL,
    `position_id` INT NOT NULL,
    `name` VARCHAR(45) NOT NULL,
    `surname` VARCHAR(45) NOT NULL,
    `patronymic` VARCHAR(45) NOT NULL,
    `phone` VARCHAR(13) NOT NULL,
    `email` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`employee_id`),
    FOREIGN KEY (`bank_branch_id`)
    REFERENCES `bank`.`bank_branches` (`bank_branch_id`),
    FOREIGN KEY (`position_id`)
    REFERENCES `bank`.`positions` (`position_id`));

CREATE TABLE IF NOT EXISTS `bank`.`credit_application_statuses` (
    `credit_application_status_id` INT NOT NULL AUTO_INCREMENT,
    `credit_application_status_name_` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`credit_application_status_id`));

CREATE TABLE IF NOT EXISTS `bank`.`credit_applications` (
    `application_id` BIGINT NOT NULL AUTO_INCREMENT,
    `customer_id` BIGINT UNSIGNED NOT NULL,
    `credit_application_status_id` INT NOT NULL,
    `date` DATE NOT NULL,
    PRIMARY KEY (`application_id`),
    FOREIGN KEY (`customer_id`)
    REFERENCES `bank`.`customers` (`customer_id`),
    FOREIGN KEY (`credit_application_status_id`)
    REFERENCES `bank`.`credit_application_statuses` (`credit_application_status_id`));

CREATE TABLE IF NOT EXISTS `bank`.`credit_agreement_statuses` (
    `credit_agreement_status_id` INT NOT NULL AUTO_INCREMENT,
    `credit_agreement_status_name` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`credit_agreement_status_id`));

CREATE TABLE IF NOT EXISTS `bank`.`credit_agreements` (
    `agreement_id` BIGINT NOT NULL AUTO_INCREMENT,
    `application_id` BIGINT NOT NULL,
    `credit_agreement_status_id` INT NOT NULL,
    `amount` DOUBLE NOT NULL,
    `interest` INT NOT NULL,
    `one_time_payment` DOUBLE NOT NULL,
    PRIMARY KEY (`agreement_id`),
    FOREIGN KEY (`application_id`)
    REFERENCES `bank`.`credit_applications` (`application_id`),
    FOREIGN KEY (`credit_agreement_status_id`)
    REFERENCES `bank`.`credit_agreement_statuses` (`credit_agreement_status_id`));

CREATE TABLE IF NOT EXISTS `bank`.`credit_histories` (
    `payment_id` BIGINT NOT NULL AUTO_INCREMENT,
    `agreement_id` BIGINT NOT NULL,
    `amount` DOUBLE NOT NULL,
    `date` DATETIME NOT NULL,
    PRIMARY KEY (`payment_id`),
    FOREIGN KEY (`agreement_id`)
    REFERENCES `bank`.`credit_agreements` (`agreement_id`));

CREATE TABLE IF NOT EXISTS `bank`.`employees_has_bank_operations` (
    `employee_id` INT UNSIGNED NOT NULL,
    `bank_operation_id` INT NOT NULL,
    PRIMARY KEY (`employee_id`),
    FOREIGN KEY (`employee_id`)
    REFERENCES `bank`.`employees` (`employee_id`),
    FOREIGN KEY (`bank_operation_id`)
    REFERENCES `bank`.`bank_operations` (`bank_operation_id`));