import java.io.File;
import java.util.*;
import java.util.concurrent.CountedCompleter;

public class ProcessDir extends CountedCompleter<Map<String, Integer>> {
    File dir;
    List<ProcessFile> filesMap = new ArrayList<>();
    List<ProcessDir> dirMap = new ArrayList<>();
    Map<String, Integer> map = new HashMap<>();

    public ProcessDir(CountedCompleter<?> completer, File dir) {
        super(completer);
        this.dir = dir;
    }

    @Override
    public void compute() {
        File[] files = dir.listFiles();
        if (files != null && files.length > 0) {
            for (File file : files) {
                if (file.isFile()) {
                    ProcessFile processFile = new ProcessFile(this, file);
                    addToPendingCount(1);
                    processFile.fork();
                    filesMap.add(processFile);
                } else if (file.isDirectory()) {
                    ProcessDir processDir = new ProcessDir(this, file);
                    addToPendingCount(1);
                    processDir.fork();
                    dirMap.add(processDir);
                }
            }
        }
        tryComplete();
    }

    @Override
    public Map<String, Integer> getRawResult() {
        return map;
    }

    @Override
    public void onCompletion(CountedCompleter<?> caller) {
        filesMap.forEach(f -> mergeWith(f.getRawResult()));
        dirMap.forEach(d -> mergeWith(d.getRawResult()));
    }
    public void mergeWith(Map<String, Integer> m) {
        if(!m.isEmpty())
            for( String key : m.keySet()){
                map.merge(key, m.get(key), Integer::sum);
            }
    }

}
