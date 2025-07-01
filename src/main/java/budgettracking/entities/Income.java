package budgettracking.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
public class Income {
    @Id @GeneratedValue
    private Long id;
    private BigDecimal amount;
    private String description;
    private LocalDate date;
    private String category;

    @ManyToOne
    @JoinColumn(name = "budget_id")
    private Budget budget;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Income)) return false;
        Income income = (Income) o;
        return getId().equals(income.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
