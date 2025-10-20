package expense_tracker.commands;

import expense_tracker.model.Expense;
import expense_tracker.services.CRUDExpense;
import expense_tracker.services.CRUDService;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

@Command(name = "summary", description = "summary of all expenses")
public class SummaryCommand implements Runnable{

    @Option(names = "--month", description = "number of the month for the specific expense of current year")
    private int month = 0;
    @Override
    public void run() {
        CRUDService<Expense> service = new CRUDExpense();
        if(month == 0) {
            Float totalExpenses = service.list().stream()
                    .map(Expense::getAmount)
                    .reduce(Float::sum).get();
            System.out.println("Total expenses: $" + totalExpenses);
        } else {
            Optional<Float> totalExpenses = service.list().stream()
                    .filter(e -> e.getDate().getMonthValue() == month
                            && e.getDate().getYear() == LocalDate.now().getYear())
                    .map(Expense::getAmount)
                    .reduce(Float::sum);
            if(totalExpenses.isPresent()) {
                System.out.println("Total expenses for " + Month.of(month).toString() + ": $" + totalExpenses.get());
            } else {
                System.out.println("There are no expenses for " + Month.of(month));
            }
        }
    }
}
