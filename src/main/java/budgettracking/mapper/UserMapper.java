package budgettracking.mapper;

import budgettracking.dtos.UserDTO;
import budgettracking.entities.UserAccount;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {BudgetMapper.class})
public interface UserMapper {
    UserDTO toDTO(UserAccount entity);
    UserAccount toEntity(UserDTO dto);
}
