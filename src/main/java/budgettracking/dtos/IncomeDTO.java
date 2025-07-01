package budgettracking.dtos;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class IncomeDTO {
    private Long id;
    private BigDecimal amount;
    private String description;
    private LocalDate date;
    private String category;
    private Long budgetId;
}
