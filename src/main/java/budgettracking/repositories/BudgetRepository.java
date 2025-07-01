package budgettracking.repositories;

import budgettracking.entities.Budget;
import budgettracking.entities.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
}
