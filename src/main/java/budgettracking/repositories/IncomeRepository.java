package budgettracking.repositories;

import budgettracking.entities.Income;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IncomeRepository extends JpaRepository<Income, Long> {
    List<Income> findByBudgetId(Long budgetId);
}
