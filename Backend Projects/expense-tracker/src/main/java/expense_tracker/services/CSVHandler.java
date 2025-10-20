package expense_tracker.services;

import expense_tracker.model.Expense;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CSVHandler {

    public File file;

    public CSVHandler() {
        Path newFilePath = Paths.get("Expenses.csv");
        try {
            file = new File(System.getProperty("user.dir"), "Expenses.csv");
            if(!file.exists()) {
                Files.createFile(newFilePath);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeExpense(List<Expense> expenses) {
        try {

            BufferedWriter writer = new BufferedWriter(new FileWriter("Expenses.csv"));
            StringBuilder message = new StringBuilder("ID,Date,Description,Amount\n");
            expenses.forEach(e -> {
                message.append(e.getId()).append(",")
                        .append(e.getDate()).append(",")
                        .append(e.getDescription()).append(",")
                        .append(e.getAmount()).append("\n");
            });
            writer.write(message.toString());
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Expense> readExpense() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Expenses.csv"));
            String line = reader.readLine();
            List<Expense> expenses = new ArrayList<>();
            String[] array = new String[4];
            int countLine = 0;
            while(line != null){
                if(countLine > 0) {
                    array = line.split(",");
                    Expense e = new Expense(Long.parseLong(array[0]),
                            LocalDate.parse(array[1]), array[2], Float.parseFloat(array[3]));
                    expenses.add(e);
                }
                line = reader.readLine();
                countLine++;
            }
            reader.close();
            return expenses;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
