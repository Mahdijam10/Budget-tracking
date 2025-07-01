package budgettracking.dtos;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class BudgetDTO {
        private Long id;
        private String name;
        private String category;
        private BigDecimal budgetLimit;
        private LocalDate startDate;
        private LocalDate endDate;
        private Long userId;
        private List<ExpenseDTO> expenseDTOs;
        private List<IncomeDTO> incomeDTOs;
}
