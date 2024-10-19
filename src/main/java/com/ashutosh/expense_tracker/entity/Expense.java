package com.ashutosh.expense_tracker.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "expenses")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @NotBlank(message = "Expense name cannot be null")
    @Size(min = 3)
    @Column(name="expense_name")
    private String name;
    private String description;
    @Column(name="expense_amount")
    @NotNull
    private BigDecimal amount;
    private String category;
    private Date date;
    @CreationTimestamp
    private Timestamp created_at;
    @UpdateTimestamp
    private Timestamp updated_at;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE) // delete the expenses when the user is deleted.
    @JsonIgnore //not to fetch the user while fetching the expense.
    private User user;
}
