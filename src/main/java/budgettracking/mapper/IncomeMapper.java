package budgettracking.mapper;


import budgettracking.dtos.IncomeDTO;
import budgettracking.entities.Income;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IncomeMapper {
    @Mapping(source = "budget.id", target = "budgetId")
    IncomeDTO toDTO(Income entity);

    //ignore = true --> meaning that the object with name "budget" has to be set by yourself
    @Mapping(target = "budget", ignore = true)
    Income toEntity(IncomeDTO dto);
}