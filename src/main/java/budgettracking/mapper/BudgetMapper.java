package budgettracking.mapper;

import budgettracking.dtos.BudgetDTO;
import budgettracking.entities.Budget;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ExpenseMapper.class, IncomeMapper.class})
public interface BudgetMapper {

    @Mapping(source = "userAccount.id", target = "userId")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "expenses", target = "expenseDTOs")
    @Mapping(source = "incomes", target = "incomeDTOs")
    BudgetDTO toDTO(Budget entity);

    @InheritInverseConfiguration
    @Mapping(target = "userAccount.id", source = "userId")
    @Mapping(target = "expenses", source = "expenseDTOs")
    @Mapping(target = "incomes", source = "incomeDTOs")
    Budget toEntity(BudgetDTO dto);
}
