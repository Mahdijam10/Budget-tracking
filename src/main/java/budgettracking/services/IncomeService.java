package budgettracking.services;


import budgettracking.dtos.IncomeDTO;
import budgettracking.entities.Budget;
import budgettracking.entities.Income;
import budgettracking.exceptions.BudgetException;
import budgettracking.exceptions.IncomeException;
import jakarta.transaction.Transactional;
import budgettracking.mapper.IncomeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import budgettracking.repositories.BudgetRepository;
import budgettracking.repositories.IncomeRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class IncomeService {
    private static final Logger logger = LoggerFactory.getLogger(IncomeService.class);
    private final IncomeRepository incomeRepository;
    private final IncomeMapper incomeMapper;
    private final BudgetRepository budgetRepository;

    public IncomeService(IncomeRepository incomeRepository, IncomeMapper incomeMapper, BudgetRepository budgetRepository) {
        this.incomeRepository = incomeRepository;
        this.incomeMapper = incomeMapper;
        this.budgetRepository = budgetRepository;
    }

    @Transactional
    public IncomeDTO createIncome(IncomeDTO incomeDTO){
        Budget budget = budgetRepository.findById(incomeDTO.getBudgetId())
                .orElseThrow(() -> new BudgetException("Budget not found"));

        Income income = incomeMapper.toEntity(incomeDTO);
        income.setBudget(budget);

        budget.getIncomes().add(income);

        Income savedIncome = incomeRepository.save(income);
        return incomeMapper.toDTO(savedIncome);
    }

    @Transactional
    public IncomeDTO updateIncome(Long id, IncomeDTO incomeDTO){
        Income existingIncome = incomeRepository.findById(id)
                .orElseThrow(() -> new IncomeException("Income not found"));

        Budget newBudget = budgetRepository.findById(incomeDTO.getBudgetId())
                .orElseThrow(() -> new BudgetException("Budget not found"));

        Budget oldBudget = existingIncome.getBudget();

        existingIncome.setAmount(incomeDTO.getAmount());
        existingIncome.setDate(incomeDTO.getDate());
        existingIncome.setDescription(incomeDTO.getDescription());
        existingIncome.setBudget(newBudget);

        //bidirectional
        if (oldBudget != null && !oldBudget.equals(newBudget)) {
            oldBudget.getIncomes().remove(existingIncome);
        }

        if (!newBudget.getIncomes().contains(existingIncome)) {
            newBudget.getIncomes().add(existingIncome);
        }

        incomeRepository.save(existingIncome);

        return incomeMapper.toDTO(existingIncome);
    }

    public List<IncomeDTO> getAllIncomes(){
        return this.incomeRepository.findAll()
                .stream()
                .map(incomeMapper::toDTO)
                .collect(Collectors.toList());
    }

    public IncomeDTO getIncomeById(Long id){
        return this.incomeRepository.findById(id)
                .map(incomeMapper::toDTO)
                .orElseThrow(() -> new IncomeException("Income not found"));
    }

    @Transactional
    public void deleteIncome(Long id){
        Income income = this.incomeRepository.findById(id)
                .orElseThrow(() -> new IncomeException("Income not found"));

        Budget budget = income.getBudget();
        if (budget != null) {
            budget.getIncomes().remove(income);
            income.setBudget(null);
        }

        incomeRepository.delete(income);
    }
}


