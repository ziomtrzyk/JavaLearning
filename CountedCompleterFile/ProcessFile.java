import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountedCompleter;

public class ProcessFile extends CountedCompleter<Map<String, Integer>> {

    File file;
    Map<String, Integer> map;

    public ProcessFile(CountedCompleter completer, File file) {
        super(completer);
        this.file = file;
        this.map = new HashMap<>();
        Arrays.asList("dog", "cat", "cow").forEach(s -> map.put(s, 0));
    }

    @Override
    public void compute() {
        try(FileReader fileReader = new FileReader(file)){
            StreamTokenizer st = new StreamTokenizer(fileReader);
            while(st.nextToken() != StreamTokenizer.TT_EOF){
                if(st.ttype == StreamTokenizer.TT_WORD){
                    Integer num = map.get(st.sval);
                    if(num != null) map.put(st.sval, num + 1);
                }
            }
            tryComplete();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<String, Integer> getRawResult() {
        return map;
    }
}
