package budgettracking.exceptions;

public class ExpenseException extends RuntimeException {
    public ExpenseException(String msg){
        super(msg);
    }
}
