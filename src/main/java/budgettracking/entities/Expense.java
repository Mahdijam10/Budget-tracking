package budgettracking.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Expense {
    @Id @GeneratedValue
    private Long id;
    private BigDecimal amount;
    private String source;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "budget_id")
    private Budget budget;
}
