INSERT INTO `bank`.`banks`
(`bank_id`, `bank_name`, `main_location`, `main_phone`)
VALUES
    (1, 'MonoBank', 'Kyiv, Avtozavodska St, 54/19', '+380936178150'),
    (2, 'PrivatBank', 'Dnipro, Naberezhna Peremohy St, 50', '+380997654321'),
    (3, 'RaiffeisenBank', 'Kyiv, Leskova St, 9', '+380675637219');

INSERT INTO `bank`.`bank_branches`
(`bank_branch_id`, `bank_id`, `location`, `phone`)
VALUES
    (1, 1, 'Kyiv, Avtozavodska St, 54/19', '+380936178150'),
    (2, 2, 'Dnipro, Naberezhna Peremohy St, 50', '+380997654321'),
    (3, 2, 'Kyiv, Chokolivsky Boulevard, 22', '+380934567892'),
    (4, 3, 'Kyiv, Leskova St, 9', '+380675637219');

INSERT INTO `bank`.`positions`
(`position_id`, `position_name`)
VALUES
    (1, 'Manager'),
    (2, 'Cashier'),
    (3, 'Analyst');

INSERT INTO `bank`.`employees`
(`employee_id`, `bank_branch_id`, `position_id`, `name`, `surname`, `patronymic`, `phone`, `email`)
VALUES
    (1, 1, 1, 'Ivan', 'Karpenko', 'Stepanovich', '+380673459274', 'ivankarpenko@gmail.com'),
    (2, 2, 1, 'Olga', 'Shevchenko', 'Vitaliivna', '+380655782394', 'olgashevchenko@gmail.com'),
    (3, 3, 1, 'Vasyl', 'Kostenko', 'Maksymovich', '+380673854798', 'vasylkostenko@gmail.com' ),
    (4, 4, 1, 'Maria', 'Nestaiko', 'Olegivna', '+380668491251', 'marianestaiko@gmail.com');

INSERT INTO `bank`.`customers`
(`customer_id`, `name`, `surname`, `patronymic`, `phone`, `email`, `address`)
VALUES
    (1, 'Mykola', 'Bilak', 'Oleksandrovich', '+380675462847', 'mykolabilak@gmail.com', 'Kyiv, Street Ivan Franko, 12'),
    (2, 'Anna', 'Dovzhenko', 'Mykolaivna', '+380963746248', 'annadovzhenko@gmail.com', 'Kyiv, Street Taras Shevchenko, 24'),
    (3, 'Orest', 'Grinchenko', 'Pavlovych', '+380673857294', 'orestgrinchenko@gmail.com', 'Kyiv, Street Volodymyr Vynnychenko, 5'),
    (4, 'Svitlana', 'Kosach', 'Leonidivna', '+380986472862', 'svitlanakosach@gmail.com', 'Dnipro, Street Panasa Myrnoho, 18');

INSERT INTO `bank`.`account_types`
(`account_type_id`, `account_type_name`)
VALUES
    (1, 'current'),
    (2, 'salary'),
    (3, 'savings'),
    (4, 'deposit'),
    (5, 'business'),
    (6, 'credit');

INSERT INTO `bank`.`accounts`
(`account_id`, `customer_id`, `account_type_id`, `bank_branch_id`, `balance`)
VALUES
    (1, 1, 1, 1, 0),
    (2, 2, 6, 2, 85000),
    (3, 3, 3, 3, 4000),
    (4, 4, 2, 4, 3000);

INSERT INTO `bank`.`transaction_types`
(`transaction_type_id`, `transaction_type_name`)
VALUES
    (1, 'deposit'),
    (2, 'withdrawal'),
    (3, 'transfer'),
    (4, 'income'),
    (5, 'payment'),
    (6, 'credit disbursement'),
    (7, 'credit repayment');

INSERT INTO `bank`.`bank_operation_types`
(`bank_operation_type_id`, `bank_operation_type_name`)
VALUES
    (1, 'account opening'),
    (2, 'account closure'),
    (3, 'issuing a credit'),
    (4, 'credit closing');

INSERT INTO `bank`.`credit_application_statuses`
(`credit_application_status_id`, `credit_application_status_name_`)
VALUES
    (1, 'in review'),
    (2, 'approved'),
    (3, 'rejected');

INSERT INTO `bank`.`credit_agreement_statuses`
(`credit_agreement_status_id`, `credit_agreement_status_name`)
VALUES
    (1, 'in review'),
    (2, 'approved'),
    (3, 'rejected');

INSERT INTO `bank`.`bank_operations`
(`bank_operation_id`, `bank_operation_type_id`, `account_id`, `date`)
VALUES
    (1, 1, 1, '2022-03-20'),
    (2, 1, 2, '2022-12-15'),
    (3, 1, 3, '2022-07-02'),
    (4, 1, 4, '2022-09-25'),
    (5, 3, 2, '2022-09-05');

INSERT INTO `bank`.`employees_has_bank_operations`
(`employee_id`, `bank_operation_id`)
VALUES
    (1, 1),
    (2, 2),
    (3, 3),
    (4, 4),
    (2, 5);

INSERT INTO `bank`.`transactions`
(`transaction_id`, `account_id`, `transaction_type_id`, `amount`, `date`)
VALUES
    (1, 1, 3, 3000, '2023-07-03 12:30:00'),
    (2, 4, 4, 3000, '2023-07-03 12:31:00'),
    (3, 3, 1, 5500, '2023-09-05 10:11:00'),
    (4, 2, 6, 100000, '2023-09-05 10:11:00'),
    (5, 2, 2, 10000, '2023-09-06 10:11:00'),
    (6, 3, 2, 1500, '2023-10-04 16:23:00'),
    (7, 2, 7, 5000, '2023-10-05 10:44:00'),
    (8, 2, 7, 5000, '2023-11-05 17:38:00');

INSERT INTO `bank`.`credit_applications`
(`application_id`, `customer_id`, `credit_application_status_id`, `date`)
VALUES
    (1, 2, 2, '2023-09-05');

INSERT INTO `bank`.`credit_agreements`
(`agreement_id`, `application_id`, `credit_agreement_status_id`, `amount`, `interest`, `one_time_payment`)
VALUES
    (1, 1, 2, 100000, 20, 5000);

INSERT INTO `bank`.`credit_histories`
(`payment_id`, `agreement_id`, `amount`, `date`)
VALUES
    (1, 1, 5000, '2023-10-05'),
    (2, 1, 5000, '2023-11-05');