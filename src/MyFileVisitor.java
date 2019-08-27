import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyFileVisitor extends SimpleFileVisitor<Path> {

    private Map<String, Integer> mapa = new HashMap<>();

    public Map<String, Integer> getMap(){
        return mapa;
    }

    @Override
    public FileVisitResult visitFile(Path path, BasicFileAttributes bfa) throws IOException {

        if(path.toString().endsWith(".txt")){

            List<String> lines = Files.readAllLines(path);
            int count = NumberCounter.countNumbers(lines);
            mapa.put(path.toString(), count);

        }
        return FileVisitResult.CONTINUE;
    }
}
