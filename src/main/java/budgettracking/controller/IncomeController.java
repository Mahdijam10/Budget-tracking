package budgettracking.controller;

import budgettracking.dtos.IncomeDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import budgettracking.services.IncomeService;

import java.util.List;

@RestController
@RequestMapping("/api/incomes")
public class IncomeController {
    private static final Logger logger = LoggerFactory.getLogger(IncomeController.class);
    private final IncomeService incomeService;

    public IncomeController(IncomeService incomeService){
        this.incomeService = incomeService;
    }

    @PostMapping
    public ResponseEntity<IncomeDTO> createIncome(@RequestBody IncomeDTO incomeDTO) {
        IncomeDTO createdExpense = this.incomeService.createIncome(incomeDTO);
        return ResponseEntity.ok(createdExpense);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IncomeDTO> updateIncome(@PathVariable Long id, @RequestBody IncomeDTO incomeDTO){
        IncomeDTO updateIncome = this.incomeService.updateIncome(id, incomeDTO);
        return ResponseEntity.ok(updateIncome);
    }


    @GetMapping("/{id}")
    public ResponseEntity<IncomeDTO> getIncome(@PathVariable Long id) {
        IncomeDTO income = this.incomeService.getIncomeById(id);
        return ResponseEntity.ok(income);
    }

    @GetMapping
    public ResponseEntity<List<IncomeDTO>> getAllIncomes() {
        List<IncomeDTO> incomes = this.incomeService.getAllIncomes();
        return ResponseEntity.ok(incomes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIncome(@PathVariable Long id) {
        this.incomeService.deleteIncome(id);
        return ResponseEntity.noContent().build();
    }
}
