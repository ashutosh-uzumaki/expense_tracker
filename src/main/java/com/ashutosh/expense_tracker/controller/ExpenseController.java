package com.ashutosh.expense_tracker.controller;

import com.ashutosh.expense_tracker.entity.Expense;
import com.ashutosh.expense_tracker.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;
    @GetMapping("/expenses")
    public List<Expense> getAllExpenses(Pageable page){
        return expenseService.getAllExpenses(page).toList();
    }
    @GetMapping("expenses/{id}")
    public Expense getExpenseById(@PathVariable("id") Long id){
        return expenseService.getExpenseById(id);
    }
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("expenses/{id}")
    public String deleteExpenseById(@PathVariable("id") Long id){
        expenseService.deleteExpenseById(id);
        return "Expense has been deleted";
    }
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("expenses/save")
    public Expense saveExpense(@RequestBody Expense expense){
        return expenseService.saveExpense(expense);
    }
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("expenses/save/multiple")
    public List<Expense> saveMultipleExpense(@RequestBody List<Expense> expenses){
        return expenseService.saveMultipleExpenses(expenses);
    }

    @PutMapping("expenses/update/{id}")
    public Expense updateExpense(@RequestBody Expense expense, @PathVariable("id") Long id){
        return expenseService.updateExpense(expense, id);
    }
}
