package com.ashutosh.expense_tracker.service;

import com.ashutosh.expense_tracker.custom_exceptions.ResourceNotFoundException;
import com.ashutosh.expense_tracker.entity.Expense;
import com.ashutosh.expense_tracker.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseServiceImpl implements ExpenseService{
    @Autowired
    private ExpenseRepository expenseRepository;

    @Override
    public Page<Expense> getAllExpenses(Pageable page){
        return expenseRepository.findAll(page);
    }

    @Override
    public Expense getExpenseById(Long id){
        Optional<Expense> expense = expenseRepository.findById(id);
        if(expense.isPresent()){
            return expense.get();
        }
        throw new ResourceNotFoundException("Expense is not found for the given id "+id);
    }

    @Override
    public void deleteExpenseById(Long id){
        Expense expense = getExpenseById(id);
        expenseRepository.delete(expense);
    }

    @Override
    public Expense saveExpense(Expense expense){
        return expenseRepository.save(expense);
    }

    @Override
    public List<Expense> saveMultipleExpenses(List<Expense> expenses){
        return expenseRepository.saveAll(expenses);
    }

    @Override
    public Expense updateExpense(Expense expense, Long id){
       Expense existing = getExpenseById(id);
       existing.setName(expense.getName() != null ? expense.getName() : existing.getName());
       existing.setCategory(expense.getCategory() != null ? expense.getCategory() : existing.getCategory());
       existing.setAmount(expense.getAmount() != null ? expense.getAmount() : existing.getAmount());
       existing.setDescription(expense.getDescription() != null ? expense.getDescription() : existing.getDescription());
       return expenseRepository.save(existing);
    }

    @Override
    public List<Expense> readByCategory(String category, Pageable page){
        return expenseRepository.findByCategory(category, page).toList();
    }

    @Override
    public List<Expense> findByKeywordName(String keyword, Pageable page){
        return expenseRepository.findByNameContaining(keyword, page).toList();
    }
}
