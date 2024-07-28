package com.ashutosh.expense_tracker.repository;

import com.ashutosh.expense_tracker.entity.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository <Expense, Long>{
    Page<Expense> findByCategory(String category, Pageable page);
    Page<Expense> findByNameContaining(String name, Pageable page);
}
