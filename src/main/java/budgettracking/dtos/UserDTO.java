package budgettracking.dtos;

import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private List<BudgetDTO> budgets;
}
