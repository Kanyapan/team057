package com.arise.model;

import com.arise.enums.LoanPurpose;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Entity
@Table(name = "loan_applications")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class LoanApplicationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    @Size(min = 2, max = 255, message = "Full name must be between 2 and 255 characters")
    @NotBlank(message = "Full name is required")
    private String fullName;

    @Column(nullable = false)
    @NotNull(message = "Monthly income is required")
    @Min(value = 5000, message = "Monthly income must be at least 5000")
    @Max(value = 5000000, message = "Monthly income must not exceed 5,000,000")
    private Double monthlyIncome;

    @Column(nullable = false)
    @NotNull(message = "Loan amount is required")
    @Min(value = 1000, message = "Loan amount must be at least 1000")
    @Max(value = 5000000, message = "Loan amount must not exceed 5,000,000")
    private Double loanAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull(message = "Loan purpose is required")
    private LoanPurpose loanPurpose;

    @Column(nullable = false)
    @NotNull(message = "Age is required")
    @Positive(message = "Age must be more than 0")
    private Integer age;

    @Column(nullable = false, length = 10, unique = true)
    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
    private String phoneNumber;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    private boolean eligible;

    private String reason;

    private OffsetDateTime timestamp;
}
