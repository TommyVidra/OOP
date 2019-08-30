import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LargeFileFinder extends SimpleFileVisitor<Path> {

    private List<String> allowedExt;
    private long minSize;
    private int numFiles = 0;
    private long totalSize = 0;
    private String longestFileName = "";
    private long longestSize = 0;

    public LargeFileFinder(List<String> allowedExt, long minSize) {
        this.allowedExt = allowedExt;
        this.minSize = minSize;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
            throws IOException {

        for(String ext : allowedExt){
            if(file.toString().endsWith(ext) && attrs.size() > minSize){
                if(numFiles == 0){
                    longestFileName = file.toString();
                    longestSize = attrs.size();
                }
                else{
                    if(longestSize < attrs.size()){
                        longestSize = attrs.size();
                        longestFileName = file.toString();
                    }
                }
                totalSize += attrs.size();
                ++numFiles;

            }
        }
        return FileVisitResult.CONTINUE;
    }

    public void writeSummary() {
        System.out.println("Ukupno pronađeno datoteka: " + this.numFiles);
        System.out.println("Ukupna velicina pronađenih datoteka: " +
                this.totalSize);
        System.out.println("Najveća datoteka je: " + this.longestFileName);
        System.out.println("Veličina najveće datoteke je: " + this.longestSize);
    }

    public static void main(String[] args) {
        List<String> extensions = new ArrayList<>(Arrays.asList("docx", "pdf"));
        LargeFileFinder finder = new LargeFileFinder(extensions,
                Integer.parseInt(args[1]));
        try {
            Files.walkFileTree(Paths.get(args[0]), finder);
            finder.writeSummary();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

