package expense_tracker.commands;

import expense_tracker.model.Expense;
import expense_tracker.services.CRUDExpense;
import expense_tracker.services.CRUDService;
import picocli.CommandLine.Option;
import picocli.CommandLine.Command;

import java.time.LocalDate;

@Command(name = "update", description = "update an expense")
public class UpdateCommand implements Runnable{

    @Option(names = "--id", description = "expense id", required = true)
    private Long id;

    @Option(names = "--description", description = "expense description")
    private String description;
    @Option(names = "--amount", description = "expense amount")
    private Float amount;

    @Override
    public void run() {
        CRUDService<Expense> service = new CRUDExpense();
        service.update(id, LocalDate.now(), description, amount);
    }
}
