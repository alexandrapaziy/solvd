-- 10 statements for insertion.
INSERT `bank`.`banks` (`bank_id`, `bank_name`, `main_location`, `main_phone`) VALUES (1, 'MonoBank', 'Kyiv, Avtozavodska St, 54/19', '+380936178150');
INSERT `bank`.`bank_branches` (`bank_branch_id`, `bank_id`, `location`, `phone`) VALUES (1, 1, 'Kyiv, Avtozavodska St, 54/19', '+380936178150');
INSERT `bank`.`positions` (`position_id`, `position_name`) VALUES (1, 'Manager');
INSERT `bank`.`employees` (`employee_id`, `bank_branch_id`, `position_id`, `name`, `surname`, `patronymic`, `phone`, `email`) VALUES (1, 1, 1, 'Ivan', 'Karpenko', 'Stepanovich', '+380673459274', 'ivankarpenko@gmail.com');
INSERT `bank`.`customers` (`customer_id`, `name`, `surname`, `patronymic`, `phone`, `email`, `address`) VALUES (1, 'Mykola', 'Bilak', 'Oleksandrovich', '+380675462847', 'mykolabilak@gmail.com', 'Kyiv, Street Ivan Franko, 12');
INSERT `bank`.`account_types` (`account_type_id`, `account_type_name`) VALUES (1, 'current');
INSERT `bank`.`accounts` (`account_id`, `customer_id`, `account_type_id`, `bank_branch_id`, `balance`) VALUES (1, 1, 1, 1, 10000);
INSERT `bank`.`transaction_types` (`transaction_type_id`, `transaction_type_name`) VALUES (1, 'deposit'), (2, 'withdrawal');
INSERT `bank`.`transactions` (`transaction_id`, `accounts_account_id`, `transaction_type_id`, `amount`, `date`) VALUES (1, 1, 1, 15000, '2023-07-03 12:00:00'), (2, 1, 2, 5000, '2023-07-03 12:31:00');

-- 10 statements for updating.
UPDATE `bank`.`banks` SET `main_phone` = '+380987654321' WHERE `bank_id` = 1;
UPDATE `bank`.`banks` SET `main_location` = 'Kyiv, Avtozavodska St, 54/20' WHERE `bank_id` = 1;
UPDATE `bank`.`bank_branches` SET `phone` = '+380987654321' WHERE `bank_branch_id` = 1;
UPDATE `bank`.`bank_branches` SET `location` = 'Kyiv, Avtozavodska St, 54/20' WHERE `bank_branch_id` = 1;
UPDATE `bank`.`positions` SET `position_name` = 'Senior Manager' WHERE `position_id` = 1;
UPDATE `bank`.`employees` SET `name` = 'Ivanko' AND `email` = 'ivankokarpenko@gmail.com' WHERE `employee_id` = 1;
UPDATE `bank`.`customers` SET `patronymic` = 'Mykolayovich' WHERE `customer_id` = 1;
UPDATE `bank`.`account_types` SET `account_type_name` = 'savings' WHERE `account_type_id` = 1;
UPDATE `bank`.`accounts` SET `balance` = 12000 WHERE `account_id` = 1;
UPDATE `bank`.`transactions` SET `amount` = 2000 WHERE `transaction_id` = 1;

-- 10 statements for deletions.
DELETE FROM `bank`.`banks` WHERE `bank_id` = 1;
DELETE FROM `bank`.`banks` WHERE `bank_id` = 1;
DELETE FROM `bank`.`bank_branches` WHERE `bank_branch_id` = 1;
DELETE FROM `bank`.`bank_branches` WHERE `bank_branch_id` = 1;
DELETE FROM `bank`.`positions` WHERE `position_id` = 1;
DELETE FROM `bank`.`employees` WHERE `employee_id` = 1;
DELETE FROM `bank`.`customers` WHERE `customer_id` = 1;
DELETE FROM `bank`.`account_types` WHERE `account_type_id` = 1;
DELETE FROM `bank`.`accounts` WHERE `account_id` = 1;
DELETE FROM `bank`.`transactions` WHERE `transaction_id` = 1;

-- 5 alter table.
ALTER TABLE `bank`.`credit_agreements` ADD `insert` INT; -- add column
ALTER TABLE `bank`.`employees` DROP COLUMN  `patronymic`; -- drop column
ALTER TABLE `bank`.`credit_agreements` MODIFY COLUMN `insert` DOUBLE; -- modify datatype
ALTER TABLE `bank`.`credit_agreements` CHANGE `insert` `percent` DOUBLE; -- rename column
ALTER TABLE `bank`.`credit_agreements` ADD CONSTRAINT `pk_insert` PRIMARY KEY (`employee_id`); -- add constraint
ALTER TABLE `bank`.`credit_agreements` DROP CONSTRAINT `pk_insert`; -- drop constaint

-- 1 big statement to join all tables in the database.
SELECT
    `customers`.`name` AS `customer_name`,
    `customers`.`surname` AS `customer_surname`,
    `customers`.`patronymic` AS `customer_patronymic`,
    `accounts`.`balance`,
    `account_types`.`account_type_name`,
    `banks`.`bank_name`,
    `bank_branches`.`location` AS `branch_location`,
    `employees`.`name` AS `employee_name`,
    `employees`.`surname` AS `employee_surname`,
    `employees`.`patronymic` AS `employee_patronymic`,
    `positions`.`position_name` AS `employee_position`,
    COUNT(DISTINCT `credit_applications`.`application_id`) AS `credit_applications_count`,
    COUNT(DISTINCT `credit_agreements`.`agreement_id`) AS `credit_agreements_count`,
    COUNT(`transactions`.`transaction_id`) AS `transaction_count`
FROM `bank`.`customers`
         JOIN `bank`.`accounts` ON `customers`.`customer_id` = `accounts`.`customer_id`
         JOIN `bank`.`account_types` ON `accounts`.`account_type_id` = `account_types`.`account_type_id`
         JOIN `bank`.`bank_branches` ON `accounts`.`bank_branch_id` = `bank_branches`.`bank_branch_id`
         JOIN `bank`.`banks` ON `bank_branches`.`bank_id` = `banks`.`bank_id`
         JOIN `bank`.`bank_operations` ON `accounts`.`account_id` = `bank_operations`.`account_id`
         JOIN `bank`.`employees_has_bank_operations` ON `bank_operations`.`bank_operation_id` = `employees_has_bank_operations`.`bank_operation_id`
         JOIN `bank`.`employees` ON `employees_has_bank_operations`.`employee_id` = `employees`.`employee_id`
         JOIN `bank`.`positions` ON `employees`.`position_id` = `positions`.`position_id`
         LEFT JOIN `bank`.`transactions` ON `accounts`.`account_id` = `transactions`.`account_id`
         LEFT JOIN `bank`.`credit_applications` ON `customers`.`customer_id` = `credit_applications`.`customer_id`
         LEFT JOIN `bank`.`credit_agreements` ON `credit_applications`.`application_id` = `credit_agreements`.`application_id`
GROUP BY
    `accounts`.`account_id`;

-- 5 statements with left, right, inner, outer joins.
-- select balance for each customer
SELECT `customers`.`name`, `customers`.`surname`, `accounts`.`balance`
FROM `bank`.`customers`
         JOIN `bank`.`accounts` ON `customers`.`customer_id` = `accounts`.`customer_id`;

-- select work location for each employee
SELECT `employees`.`name`, `employees`.`surname`, `bank_branches`.`location`
FROM `bank`.`employees`
         LEFT JOIN `bank`.`bank_branches` ON `employees`.`bank_branch_id` = `bank_branches`.`bank_branch_id`;

-- select work location and position for each employee
SELECT `bank_branches`.`location`, `employees`.`name`, `employees`.`surname`, `positions`.`position_name`
FROM `bank`.`employees`
         RIGHT JOIN `bank`.`bank_branches` ON `employees`.`bank_branch_id` = `bank_branches`.`bank_branch_id`
         LEFT JOIN `bank`.`positions` ON `employees`.`position_id` = `positions`.`position_id`;

-- select customers and show their credit application status
SELECT  `customers`.`name`, `customers`.`surname`, `credit_application_statuses`.`credit_application_status_name_`
FROM  `bank`.`credit_applications`
          JOIN  `bank`.`customers` ON `credit_applications`.`customer_id` = `customers`.`customer_id`
          JOIN`bank`.`credit_application_statuses` ON `credit_applications`.`credit_application_status_id` = `credit_application_statuses`.`credit_application_status_id`;

-- select customers and show their bank name and location of branch
SELECT `bank_branches`.`location`, `banks`.`bank_name`, `customers`.`name`, `customers`.`surname`
FROM `bank`.`bank_branches`
         JOIN `bank`.`banks` ON `bank_branches`.`bank_id` = `banks`.`bank_id`
         JOIN `bank`.`accounts` ON `bank_branches`.`bank_branch_id` = `accounts`.`bank_branch_id`
         JOIN `bank`.`customers` ON `accounts`.`customer_id` = `customers`.`customer_id`;


-- 7 statements with aggregate functions and group by and without having.
-- select bank name and show how many accounts each of them have
SELECT `banks`.`bank_name`, COUNT(`customers`.`customer_id`) AS `customer_count`
FROM `bank`.`banks`
         JOIN `bank`.`bank_branches` ON `banks`.`bank_id` = `bank_branches`.`bank_id`
         JOIN `bank`.`accounts` ON `bank_branches`.`bank_branch_id` = `accounts`.`bank_branch_id`
         JOIN `bank`.`customers` ON `accounts`.`customer_id` = `customers`.`customer_id`
GROUP BY `banks`.`bank_id`;

-- select account types and show average amount of money for each type
SELECT `account_types`.`account_type_name`, AVG(`accounts`.`balance`) AS `average_balance`
FROM `bank`.`accounts`
         JOIN `bank`.`account_types` ON `accounts`.`account_type_id` = `account_types`.`account_type_id`
GROUP BY `account_types`.`account_type_id`;

-- select transaction types and show summary of amout for each type
SELECT `transaction_types`.`transaction_type_name`, SUM(`transactions`.`amount`) AS `total_amount`
FROM `bank`.`transactions`
         JOIN `bank`.`transaction_types` ON `transactions`.`transaction_type_id` = `transaction_types`.`transaction_type_id`
GROUP BY `transaction_types`.`transaction_type_id`;

-- select transaction types and show maximum of amout for each type
SELECT `transaction_types`.`transaction_type_name`, MAX(`transactions`.`amount`) AS `max_amount`
FROM `bank`.`transactions`
         JOIN `bank`.`transaction_types` ON `transactions`.`transaction_type_id` = `transaction_types`.`transaction_type_id`
GROUP BY `transaction_types`.`transaction_type_id`;

-- select and show how many employees and customers each bank branch have
SELECT `bank_branches`.`location`, COUNT(DISTINCT `employees`.`employee_id`) AS `employee_count`, COUNT(DISTINCT `customers`.`customer_id`) AS `customer_count`
FROM `bank`.`bank_branches`
         LEFT JOIN `bank`.`employees` ON `bank_branches`.`bank_branch_id` = `employees`.`bank_branch_id`
         LEFT JOIN `bank`.`accounts` ON `bank_branches`.`bank_branch_id` = `accounts`.`bank_branch_id`
         LEFT JOIN `bank`.`customers` ON `accounts`.`customer_id` = `customers`.`customer_id`
GROUP BY `bank_branches`.`bank_branch_id`;

-- select and show customers minimum account amount
SELECT `customers`.`name`, `customers`.`surname`, MIN(`accounts`.`balance`) AS `min_balance`
FROM `bank`.`customers`
         JOIN `bank`.`accounts` ON `customers`.`customer_id` = `accounts`.`customer_id`
GROUP BY `customers`.`customer_id`;

-- show how much money was paid each month to repay the credit
SELECT MONTH(`credit_histories`.`date`) AS `month`, SUM(`credit_histories`.`amount`) AS `total_credit_disbursement`
FROM `bank`.`credit_histories`
GROUP BY MONTH(`credit_histories`.`date`);

-- 7 statements with aggregate functions and group by and with having.
-- select and count how many employees are in each bank branch where employee id is more than 0
SELECT `bank_branches`.`location`, COUNT(`employees`.`employee_id`) AS `employee_count`
FROM `bank`.`bank_branches`
         LEFT JOIN `bank`.`employees` ON `bank_branches`.`bank_branch_id` = `employees`.`bank_branch_id`
GROUP BY `bank_branches`.`bank_branch_id`
HAVING COUNT(`employees`.`employee_id`) > 0;

-- select and show clients which accounts has more than 5000 balance
SELECT `customers`.`name`, `customers`.`surname`, AVG(`accounts`.`balance`) AS `average_balance`
FROM `bank`.`customers`
         JOIN `bank`.`accounts` ON `customers`.`customer_id` = `accounts`.`customer_id`
GROUP BY `customers`.`customer_id`
HAVING AVG(`accounts`.`balance`) > 5000;

-- select and show bank branch location with minimun account balance which equils 0
SELECT `bank_branches`.`location`, MIN(`accounts`.`balance`) AS `min_balance`
FROM `bank`.`bank_branches`
         JOIN `bank`.`accounts` ON `bank_branches`.`bank_branch_id` = `accounts`.`bank_branch_id`
GROUP BY `bank_branches`.`bank_branch_id`
HAVING MIN(`accounts`.`balance`) = 0;

-- select and show months where credit disbursement equils 5000
SELECT MONTH(`credit_histories`.`date`) AS `month`, SUM(`credit_histories`.`amount`) AS `total_credit_disbursement`
FROM `bank`.`credit_histories`
GROUP BY MONTH(`credit_histories`.`date`)
HAVING SUM(`credit_histories`.`amount`) = 5000;

-- select and show customers whom has at least one credit application
SELECT `customers`.`name`, `customers`.`surname`, COUNT(`credit_applications`.`application_id`) AS `application_count`
FROM `bank`.`customers`
         LEFT JOIN `bank`.`credit_applications` ON `customers`.`customer_id` = `credit_applications`.`customer_id`
GROUP BY `customers`.`customer_id`
HAVING COUNT(`credit_applications`.`application_id`) > 0;

-- select and show customer whom has maximum transaction amount more than 20000
SELECT `customers`.`name`, `customers`.`surname`, MAX(`transactions`.`amount`) AS `max_transaction_amount`
FROM `bank`.`customers`
         JOIN `bank`.`accounts` ON `customers`.`customer_id` = `accounts`.`customer_id`
         JOIN `bank`.`transactions` ON `accounts`.`account_id` = `transactions`.`account_id`
GROUP BY `customers`.`customer_id`
HAVING MAX(`transactions`.`amount`) > 20000;

-- select and show in which position are working four employees
SELECT `positions`.`position_name`, COUNT(`employees`.`employee_id`) AS `employee_count`
FROM `bank`.`employees`
         JOIN `bank`.`positions` ON `employees`.`position_id` = `positions`.`position_id`
GROUP BY `employees`.`position_id`
HAVING COUNT(`employees`.`employee_id`) = 4;