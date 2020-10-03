import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Main {
    public static final Logger logger = Logger.getLogger(Main.class.getName());
    private static String addCommand = "add Василий Петров " +
            "vasily.petrov@gmail.com +79787775747";
    private static String commandExamples = "\t" + addCommand + "\n" +
            "\tlist\n\tcount\n\tremove Василий Петров";
    private static String commandError = "Wrong command! Available command examples: \n" +
            commandExamples;
    private static String helpText = "Command examples:\n" + commandExamples;

    public static void main(String[] args) throws IOException {
        InputStream is = Main.class.getResourceAsStream("/logging.properties");
        LogManager.getLogManager().readConfiguration(is);

        logger.info("Приложение запускается");

        Scanner scanner = new Scanner(System.in);
        StudentStorage executor = new StudentStorage();
        logger.info("Хранилище создано.");
        System.out.println(helpText);
        try {
            while (true) {
                String command = scanner.nextLine();
                String[] tokens = command.split("\\s+", 2);
                if (tokens[0].equals("add")) {
                    executor.addStudent(tokens[1]);
                } else if (tokens[0].equals("list")) {
                    executor.listStudent();
                } else if (tokens[0].equals("get")) {
                    executor.getStudentByName(tokens[1]);
                } else if (tokens[0].equals("remove")) {
                    executor.removeStudent(tokens[1]);
                } else if (tokens[0].equals("count")) {
                    System.out.println("There are " + executor.getCount() + " customers");
                } else if (tokens[0].equals("help")) {
                    System.out.println(helpText);
                } else {
                    System.out.println(commandError);
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("In main() array tokens[] is out of bound: " + e);
            logger.log(Level.SEVERE, "In main() array tokens[] is out of bound: " + e);
        } finally {
            System.out.println("Приложение завершается");
        }
    }
}
