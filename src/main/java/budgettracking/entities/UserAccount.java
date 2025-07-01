package budgettracking.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class UserAccount {
    @Id @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;

    @OneToMany(mappedBy = "userAccount", cascade = CascadeType.ALL)
    private List<Budget> budgets;
}
