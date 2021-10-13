import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class FinanceBot extends TelegramLongPollingBot {

  private static HashMap<String, ICommand> commands;
  private static SendMessage messageSender;

  public FinanceBot() {
    messageSender = new SendMessage();
    commands = new HashMap<>();
    commands.put("/start", this::startProcess);
    commands.put("/get_strategies", this::getStrategies);
  }

  @Override
  public void onUpdateReceived(Update update) {
    if (update.hasMessage()) {
      messageSender.setChatId(update.getMessage().getChatId().toString());
      String botAnswer = processUserMessage(update.getMessage().getText());
      messageSender.setText(botAnswer);
      try {
        execute(messageSender);
      } catch (TelegramApiException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public String getBotUsername() {

    return "FinanceBot";
  }

  @Override
  public String getBotToken() {

    return "";
  }

  public static String processUserMessage(String message) {
    if (message != null) {
      if (commands.containsKey(message)) {
        return commands.get(message).myFunction();
      }
      return generateError();
    }
    return null;
  }

  public String startProcess() {
    String startMessage = "";
    try {
      Scanner scanner = new Scanner(new File(String.format("src%1$smain%1$sresources%1$sstart.txt",
          File.separator)));
      scanner.useDelimiter("\\Z");
      startMessage = scanner.next();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return startMessage;
  }

  public static String generateError() {
    return "Я не знаю такую команду:(";
  }

  public String getStrategies() {
    return "Стратегия 1\n Стратегия 2\n Стратегия 3";
  }
}
