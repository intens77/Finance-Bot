package WorkingClasses;


import Patterns.Command;

public class User {

    private final String userId;
    private float monthBudget;
    private Command lastCalledCommand;

    public User(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public boolean setMonthBudget(float budget) {
        if (budget > 0) {
            monthBudget = budget;
            return true;
        }
        return false;
    }

    public boolean resetMonthBudget(float budget) {
        return setMonthBudget(budget);
    }

    public boolean increaseMonthBudget(float sum) {
        if (sum > 0) {
            monthBudget += sum;
            return true;
        }
        return false;
    }

    public boolean decreaseMonthBudget(float sum) {
        if (sum > 0 & monthBudget >= sum) {
            monthBudget -= sum;
            return true;
        }
        return false;
    }

    public float checkMonthBudget() {
        return this.monthBudget;
    }

    public void saveLastUserCommand(Command lastCalledCommand) {
        this.lastCalledCommand = lastCalledCommand;
    }

    public Command getLastUserCommand() {
        var result = lastCalledCommand;
        lastCalledCommand = null;
        return result;
    }
}