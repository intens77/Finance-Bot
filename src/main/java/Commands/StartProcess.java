package Commands;

import Patterns.Command;
import WorkingClasses.ServiceFunctions;
import WorkingClasses.User;

public class StartProcess extends Command {
    @Override
    public String execute(User user, String message) {
        return ServiceFunctions.readFileContent("start.txt");
    }
}