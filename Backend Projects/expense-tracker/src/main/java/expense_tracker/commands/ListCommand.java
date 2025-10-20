package expense_tracker.commands;

import expense_tracker.model.Expense;
import expense_tracker.services.CRUDExpense;
import expense_tracker.services.CRUDService;
import picocli.CommandLine.Command;

@Command(name = "list", description = "lists all the expenses")
public class ListCommand implements Runnable{
    @Override
    public void run() {
        CRUDService<Expense> service = new CRUDExpense();
        System.out.println("ID\tDate\tDescription\tAmount");
        service.list().forEach(e -> {
            System.out.println(e.getId() + "\t" + e.getDate()
                    + "\t" + e.getDescription() + "\t" + e.getAmount());
        });
    }
}
