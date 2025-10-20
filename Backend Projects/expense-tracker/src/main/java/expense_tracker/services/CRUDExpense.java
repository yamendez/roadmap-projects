package expense_tracker.services;

import expense_tracker.model.Expense;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CRUDExpense implements CRUDService<Expense>{

    private static List<Expense> expenses = new ArrayList<>();
    private CSVHandler csv = new CSVHandler();

    public CRUDExpense() {

        if(csv.file != null) {
            if (expenses.isEmpty()) {
                expenses.addAll(csv.readExpense());
            } else {
                expenses.clear();
                expenses.addAll(csv.readExpense());
            }
        }
    }

    @Override
    public List<Expense> list() {
        return expenses;
    }

    @Override
    public void add(LocalDate date, String description, Float amount) {
        Expense expense = null;
        if(!expenses.isEmpty()) {
            Long id = expenses.get(expenses.size() - 1).getId() + 1;
            expense = new Expense(id, date, description, amount);
        } else {
            expense = new Expense(description, amount, date);
        }
        expenses.add(expense);
        System.out.println("Expense added successfully (ID: "+expense.getId()+")");
        save();
    }

    @Override
    public void delete(Long id) {
        Optional<Expense> e = expenses.stream().
                filter(expense -> expense.getId().equals(id))
                .findAny();
        if(e.isPresent()) {
            expenses.remove(e.get());
            System.out.println("Expense deleted successfully");
        } else {
            System.out.println("Could not find expense (ID: "+id+")");
        }
        save();
    }

    @Override
    public void update(Long id, LocalDate date, String description, Float amount) {

        Optional<Expense> e = expenses.stream().
                filter(expense -> expense.getId().equals(id))
                .findAny();

        if (description!=null && amount != null) {
            if (e.isPresent()){
                int index = expenses.indexOf(e.get());
                e.get().setDate(date);
                e.get().setDescription(description);
                e.get().setAmount(amount);
                expenses.set(index, e.get());
            }
        } else if (description != null) {
            if (e.isPresent()){
                int index = expenses.indexOf(e.get());
                e.get().setDate(date);
                e.get().setDescription(description);
                expenses.set(index, e.get());
            }
        } else {
            if (e.isPresent()){
                int index = expenses.indexOf(e.get());
                e.get().setDate(date);
                e.get().setAmount(amount);
                expenses.set(index, e.get());
            }
        }
        save();
    }

    public void save() {
        csv.writeExpense(expenses);
    }
}
