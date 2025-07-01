package budgettracking.repositories;

import budgettracking.entities.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByBudgetId(Long budgetId);
    List<Expense> findBySourceAndBudgetId(String category, Long budgetId);
}
