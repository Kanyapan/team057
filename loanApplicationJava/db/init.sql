CREATE TABLE IF NOT EXISTS loan_applications (
    id SERIAL PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    monthly_income NUMERIC NOT NULL,
    loan_amount NUMERIC NOT NULL,
    loan_purpose VARCHAR(50) NOT NULL,
    age INT NOT NULL,
    phone_number VARCHAR(10) UNIQUE NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    eligible BOOLEAN,
    reason TEXT,
    timestamp TIMESTAMP
);
