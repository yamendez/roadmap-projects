package expense_tracker.commands;

import expense_tracker.model.Expense;
import expense_tracker.services.CRUDExpense;
import expense_tracker.services.CRUDService;
import picocli.CommandLine.Option;
import picocli.CommandLine.Command;

@Command(name = "delete", description = "deletes expenses by id")
public class DeleteCommand implements Runnable{
    @Option(names = "--id", description = "expense id", required = true)
    private Long id;

    @Override
    public void run() {
        CRUDService<Expense> service = new CRUDExpense();
        service.delete(id);
    }
}
