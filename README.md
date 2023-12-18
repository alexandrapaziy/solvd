Database Entity-Relationship model 


<img src="src/main/resources/ERdiagram/bank database.png" width="600" alt="ER Діаграма Бази Даних">

customers - stores information about bank customers, including basic personal details.

accounts - contains data on customer accounts (cards) with references to account types and customers.

account_types - defines various account types such as current, salary, savings, deposit, business, and credit.

transactions - stores data on financial transactions, including deposits, withdrawals, transfers, and payments.

transactions_types - defines different transaction types, such as deposit, withdrawal, transfer, and payment.

banks - holds information about banks, including their names and locations.

bank_branches - stores data about bank branches with references to specific banks.

employee - contains data about bank employees, including names and identification details.

positions - defines different positions for bank employees.

bank_operations - records data about bank operations, such as opening or closing accounts.

employee_has_bank_operations - establishes a relationship between employees and the types of bank operations they can
perform.

bank_operation_types - defines various types of bank operations, such as account opening, account closing, credit
opening, etc.

credit_applications - records data about credit applications as the initial step in obtaining a credit.

credit_application_statuses - defines different credit application statuses, such as in review, approved and rejected.

credit_agreements - stores information about credit agreements made after the approval of an application.

credit_agreement_statuses - defines different credit agreement statuses, such as in review, approved and rejected.

credit_history - tracks the payment history of a credit agreement, including amounts and dates.