import java.io.File;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String pathname = "C:\\Users"; //needed to enter the correct path
        ProcessDir processDir = new ProcessDir(null,new File(pathname));
        processDir.invoke();

        Map<String, Integer> map = processDir.getRawResult();

        map.entrySet().stream()
                .filter(e -> e.getValue() > 0)
                .sorted((e1, e2) -> e2.getValue() - e1.getValue())
                .forEach(System.out::println);


    }
}