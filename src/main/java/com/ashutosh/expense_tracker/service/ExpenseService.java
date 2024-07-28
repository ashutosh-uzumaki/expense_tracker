package com.ashutosh.expense_tracker.service;

import com.ashutosh.expense_tracker.entity.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ExpenseService {
    Page<Expense> getAllExpenses(Pageable page);
    Expense getExpenseById(Long Id);
    void deleteExpenseById(Long Id);
    Expense saveExpense(Expense expense);
    List<Expense> saveMultipleExpenses(List<Expense> expenses);
    Expense updateExpense(Expense expense, Long id);
    List<Expense> readByCategory(String category, Pageable page);

    List<Expense> findByKeywordName(String keyword, Pageable page);
}
