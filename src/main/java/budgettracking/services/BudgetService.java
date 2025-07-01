package budgettracking.services;


import budgettracking.dtos.BudgetDTO;
import budgettracking.entities.Budget;
import budgettracking.entities.Expense;
import budgettracking.entities.Income;
import budgettracking.entities.UserAccount;
import budgettracking.exceptions.BudgetException;
import budgettracking.exceptions.UserException;
import jakarta.transaction.Transactional;
import budgettracking.mapper.BudgetMapper;
import budgettracking.mapper.ExpenseMapper;
import budgettracking.mapper.IncomeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import budgettracking.repositories.BudgetRepository;
import budgettracking.repositories.ExpenseRepository;
import budgettracking.repositories.IncomeRepository;
import budgettracking.repositories.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BudgetService {
    private static final Logger logger = LoggerFactory.getLogger(BudgetService.class);
    private final BudgetRepository budgetRepository;
    private final UserRepository userRepository;
    private final IncomeRepository incomeRepository;
    private final ExpenseRepository expenseRepository;
    private final BudgetMapper budgetMapper;
    private final ExpenseMapper expenseMapper;
    private final IncomeMapper incomeMapper;


    public BudgetService(BudgetRepository budgetRepository, UserRepository userRepository, IncomeRepository incomeRepository, ExpenseRepository expenseRepository, BudgetMapper budgetMapper, ExpenseMapper expenseMapper, IncomeMapper incomeMapper) {
        this.budgetRepository = budgetRepository;
        this.userRepository = userRepository;
        this.incomeRepository = incomeRepository;
        this.expenseRepository = expenseRepository;
        this.budgetMapper = budgetMapper;
        this.expenseMapper = expenseMapper;
        this.incomeMapper = incomeMapper;
    }

    @Transactional
    public BudgetDTO createBudget(BudgetDTO budgetDTO) {
        UserAccount user = this.userRepository.findById(budgetDTO.getUserId())
                .orElseThrow(() -> new UserException("User not found"));

        List<Expense> updatedExpenses = budgetDTO.getExpenseDTOs() != null ?
                budgetDTO.getExpenseDTOs().stream()
                        .map(expenseMapper::toEntity)
                        .collect(Collectors.toList()) : List.of();

        List<Income> updatedIncomes = budgetDTO.getIncomeDTOs() != null ?
                budgetDTO.getIncomeDTOs().stream()
                        .map(incomeMapper::toEntity)
                        .collect(Collectors.toList()) : List.of();

        // First map the basic budget structure
        Budget budget = this.budgetMapper.toEntity(budgetDTO);

        // Link budget to user
        budget.setUserAccount(user);
        if (!user.getBudgets().contains(budget)) {
            user.getBudgets().add(budget);
        }

        // Link expenses to budget
        updatedExpenses.forEach(e -> e.setBudget(budget));
        budget.setExpenses(updatedExpenses);

        // Link incomes to budget
        updatedIncomes.forEach(i -> i.setBudget(budget));
        budget.setIncomes(updatedIncomes);

        // Persist all
        this.budgetRepository.save(budget); // this should cascade to incomes and expenses if set up correctly
        this.userRepository.save(user);     // not strictly necessary if cascade is used, but okay

        return this.budgetMapper.toDTO(budget);
    }


    @Transactional
    public BudgetDTO updateBudget(Long id, BudgetDTO budgetDTO){
        Budget existingBudget = this.budgetRepository.findById(id)
                .orElseThrow(() -> new BudgetException("Budget not found"));

        UserAccount user = this.userRepository.findById(budgetDTO.getUserId())
                .orElseThrow(() -> new UserException("User not found"));

        existingBudget.setCategory(budgetDTO.getCategory());
        existingBudget.setBudgetLimit(budgetDTO.getBudgetLimit());
        existingBudget.setStartDate(budgetDTO.getStartDate());
        existingBudget.setEndDate(budgetDTO.getEndDate());

        if(!existingBudget.getUserAccount().getId().equals(user.getId())){
            existingBudget.setUserAccount(user);
            if(!user.getBudgets().contains(existingBudget)){
                user.getBudgets().add(existingBudget);
            }
        }

        //map and replace expenses
        List<Expense> updatedExpenses = budgetDTO.getExpenseDTOs() != null ?
                budgetDTO.getExpenseDTOs().stream()
                        .map(expenseMapper::toEntity)
                        .peek(e -> e.setBudget(existingBudget))
                        .collect(Collectors.toList()) : List.of();

        existingBudget.getExpenses().clear();
        existingBudget.getExpenses().addAll(updatedExpenses);

        //map and replace incomes
        List<Income> updatedIncomes = budgetDTO.getExpenseDTOs() != null ?
                budgetDTO.getIncomeDTOs().stream()
                        .map(incomeMapper::toEntity)
                        .peek(e -> e.setBudget(existingBudget))
                        .collect(Collectors.toList()) : List.of();

        existingBudget.getIncomes().clear();
        existingBudget.getIncomes().addAll(updatedIncomes);

        // Save related budgettracking.entities
        this.budgetRepository.save(existingBudget);
        this.expenseRepository.saveAll(updatedExpenses);
        this.incomeRepository.saveAll(updatedIncomes);
        this.userRepository.save(user);

        return budgetMapper.toDTO(existingBudget);
    }

    public BudgetDTO getBudgetById(Long id) {
        Budget budget = budgetRepository.findById(id)
                .orElseThrow(() -> new BudgetException("Budget not found"));

        return budgetMapper.toDTO(budget);
    }

    @Transactional
    public void deleteBudget(Long id) {
        Budget budget = budgetRepository.findById(id)
                .orElseThrow(() -> new BudgetException("Budget not found"));

        budgetRepository.delete(budget);
    }

    public List<BudgetDTO> getAllBudgets(){
        return this.budgetRepository.findAll()
                .stream()
                .map(budgetMapper::toDTO)
                .collect(Collectors.toList());
    }
}
