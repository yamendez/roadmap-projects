package expense_tracker.commands;

import expense_tracker.model.Expense;
import expense_tracker.services.CRUDExpense;
import expense_tracker.services.CRUDService;
import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Command;

import java.time.LocalDate;
import java.util.Locale;

@Command(name = "add", description = "adds a new expense")

public class AddCommand implements Runnable {

    @Option(names = "--description", description = "expense description")
    private String description;

    @Option(names = "--amount", description = "amount spent")
    private Float amount;

    @Override
    public void run() {
        if(description != null && amount != null) {
            LocalDate date = LocalDate.now();
            CRUDService<Expense> service = new CRUDExpense();
            service.add(date, description, amount);
        }
    }
}
