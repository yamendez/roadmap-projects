package expense_tracker.cli_app;

import expense_tracker.commands.*;
import picocli.CommandLine;

@CommandLine.Command(name = "expense-tracker")
public class Expense_Tracker {
    public static void main(String[] args) {
        System.out.println("CLI Expense Tracker");
        CommandLine commandLine = new CommandLine(new Expense_Tracker());
        commandLine.addSubcommand("add", new AddCommand());
        commandLine.addSubcommand("list", new ListCommand());
        commandLine.addSubcommand("summary", new SummaryCommand());
        commandLine.addSubcommand("delete", new DeleteCommand());
        commandLine.addSubcommand("update", new UpdateCommand());
        commandLine.execute(args);
    }

}
