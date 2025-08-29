CREATE TABLE IF NOT EXISTS loan_application (
    id BIGSERIAL PRIMARY KEY,

    full_name VARCHAR(255) NOT NULL CHECK (char_length(full_name) BETWEEN 2 AND 255),

    monthly_income NUMERIC(15,2) NOT NULL
        CHECK (monthly_income >= 5000.00 AND monthly_income <= 5000000.00),

    loan_amount NUMERIC(15,2) NOT NULL
        CHECK (loan_amount >= 1000.00 AND loan_amount <= 5000000.00),

    loan_purpose VARCHAR(50) NOT NULL,

    age INT NOT NULL CHECK (age >= 1),

    phone_number VARCHAR(10) NOT NULL
        CHECK (phone_number ~ '^\d{10}$'),

    email VARCHAR(320) NOT NULL
        CHECK (email ~ '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$')
);

-- Sample data inserts
INSERT INTO loan_application (full_name, monthly_income, loan_amount, loan_purpose, age, phone_number, email)
VALUES
  ('Alice Johnson', 55000.00, 200000.00, 'CAR', 30, '0123456789', 'alice.johnson@example.com'),
  ('Bob Smith', 120000.00, 800000.00, 'HOME', 42, '0987654321', 'bob.smith@example.com'),
  ('Charlie Nguyen', 75000.00, 50000.00, 'EDUCATION', 25, '0112233445', 'charlie.nguyen@example.com'),
  ('Diana Lee', 300000.00, 1500000.00, 'BUSINESS', 38, '0223344556', 'diana.lee@example.com'),
  ('Ethan Clark', 65000.00, 120000.00, 'PERSONAL', 28, '0334455667', 'ethan.clark@example.com');
