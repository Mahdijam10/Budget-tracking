package budgettracking.dtos;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

//careful names have to be exact the same as in the entity and the dto for mapping
@Data
public class ExpenseDTO {
    private Long id;
    private BigDecimal amount;
    private String source;
    private LocalDate date;
    private Long budgetId;
}

