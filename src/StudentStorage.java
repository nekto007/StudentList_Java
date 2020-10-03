import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentStorage {
    public static final Logger logger = Logger.getLogger(Main.class.getName());
    private HashMap<String, Student> storage;

    public StudentStorage() {
        storage = new HashMap<>();
    }

    public void addStudent(String data) {
        logger.info("Добавляем студента");
        try {
            String[] components = data.split("\\s+");
            String name = components[0] + " " + components[1];
            storage.put(name, new Student(name, components[3], components[2]));
        } catch (ArrayIndexOutOfBoundsException e) {
            logger.log( Level.SEVERE, "In addStudent() array components[] is out of bound ! ", e );
            logger.info("addStudent() declined because of exception !");
        }
    }

    public void listStudent() {
        logger.info("Показываем список студентов.");
        storage.values().forEach(System.out::println);
    }

    public void removeStudent(String name) {
        logger.info("Удаляем студента.");
        try {
            storage.remove(name);
        }catch (NullPointerException e) {
            logger.log( Level.SEVERE, "In removeStudent() parameter name is null ! ", e );
            logger.info("removeStudent() declined because of exception !");
        }


    }

    public Student getStudentByName(String name) {
        logger.info("Находим студента по имени.");
        try {
            return storage.get(name);
        }catch (NullPointerException e) {
            logger.log( Level.SEVERE, "In getStudentByName() parameter name is null: ", e );
            logger.info("getStudentByName() declined because of exception !");
            return null;
        }

    }

    public int getCount() {
        logger.info("Выводим количество студентов.");
        return storage.size();
    }
}