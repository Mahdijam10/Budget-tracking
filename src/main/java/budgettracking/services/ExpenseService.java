package budgettracking.services;

import budgettracking.dtos.ExpenseDTO;
import budgettracking.entities.Budget;
import budgettracking.entities.Expense;
import budgettracking.exceptions.BudgetException;
import budgettracking.exceptions.ExpenseException;
import jakarta.transaction.Transactional;
import budgettracking.mapper.ExpenseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import budgettracking.repositories.BudgetRepository;
import budgettracking.repositories.ExpenseRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final BudgetRepository budgetRepository;
    private final ExpenseMapper expenseMapper;
    private static final Logger logger = LoggerFactory.getLogger(ExpenseService.class);


    public ExpenseService(ExpenseRepository expenseRepository, BudgetRepository budgetRepository, ExpenseMapper expenseMapper) {
        this.expenseRepository = expenseRepository;
        this.budgetRepository = budgetRepository;
        this.expenseMapper = expenseMapper;
    }

    public List<ExpenseDTO> getAllExpenses(){
        return this.expenseRepository.findAll()
                .stream()
                .map(expenseMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ExpenseDTO getExpenseById(Long id){
        return this.expenseRepository.findById(id)
                .map(expenseMapper::toDTO)
                .orElseThrow(() -> new ExpenseException("Expense not found"));
    }

    @Transactional
    public ExpenseDTO createExpense(ExpenseDTO expenseDTO){
        Budget budget = budgetRepository.findById(expenseDTO.getBudgetId())
                .orElseThrow(() -> new BudgetException("Budget not found"));


        Expense expense = expenseMapper.toEntity(expenseDTO);
        expense.setBudget(budget);

        //bidirectional
        budget.getExpenses().add(expense);

        Expense savedExpense = expenseRepository.save(expense);
        return expenseMapper.toDTO(savedExpense);
    }

    @Transactional
    public ExpenseDTO updateExpense(Long id, ExpenseDTO expenseDTO){
        Expense existingExpense = expenseRepository.findById(id)
                .orElseThrow(() -> new ExpenseException("Expense not found"));

        Budget newBudget = budgetRepository.findById(expenseDTO.getBudgetId())
                .orElseThrow(() -> new BudgetException("Budget not found"));

        Budget oldBudget = existingExpense.getBudget();

        existingExpense.setAmount(expenseDTO.getAmount());
        existingExpense.setDate(expenseDTO.getDate());
        existingExpense.setSource(expenseDTO.getSource());
        existingExpense.setBudget(newBudget);

        //bidirectional
        if (oldBudget != null && !oldBudget.equals(newBudget)) {
            oldBudget.getExpenses().remove(existingExpense);
        }

        if (!newBudget.getExpenses().contains(existingExpense)) {
            newBudget.getExpenses().add(existingExpense);
        }

        expenseRepository.save(existingExpense);

        return expenseMapper.toDTO(existingExpense);
    }

    @Transactional
    public void deleteExpense(Long id){
        Expense expense = this.expenseRepository.findById(id)
                .orElseThrow(() -> new ExpenseException("Expense not found"));

        Budget budget = expense.getBudget();
        if (budget != null) {
            budget.getExpenses().remove(expense);
            expense.setBudget(null);
        }
        this.expenseRepository.delete(expense);
    }
}
