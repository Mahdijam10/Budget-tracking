package budgettracking.mapper;

import budgettracking.dtos.ExpenseDTO;
import budgettracking.entities.Expense;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ExpenseMapper {

    @Mapping(source = "budget.id", target = "budgetId")
    ExpenseDTO toDTO(Expense entity);

    //ignore = true --> meaning that the object with name "budget" has to be set by yourself
    @Mapping(target = "budget", ignore = true)
    Expense toEntity(ExpenseDTO dto);
}
