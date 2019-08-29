import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

public class MetadataFileVisitor extends SimpleFileVisitor<Path> {

    private List<String> data = new LinkedList<>();

    public List<String> getMetaData() {return data;} //dohvaćanje liste

    private String fileExtension;

    public MetadataFileVisitor(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    // TO DO: nadjačavanjem definirajte potrebne metode FileVisitora
    @Override
    public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes bs) throws IOException {
        fillList(path, bs);
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path path, BasicFileAttributes bs) throws IOException{
        if(fileExtension.equals("*")){
            fillList(path, bs);
            //System.out.println(bs.creationTime().toString());
            return FileVisitResult.CONTINUE;
        }

        String name = path.getFileName().toString();
        int i = name.lastIndexOf('.');
        String exten = "";

        if(i > 0)
            exten = name.substring(i+1).toLowerCase();

        if(exten.equals(fileExtension))
            fillList(path, bs);

        return FileVisitResult.CONTINUE;
    }

    private void fillList(Path file, BasicFileAttributes attr) {
        StringBuilder sb = new StringBuilder();
//TO DO: napisati funkciju za pretvorbu formata vremena

        Function<String, String> f = new Function<String, String>(){
            @Override
            public String apply(String s) {
                return s.substring(0,10) + " " + s.substring(11, 16);
            }
        };

        sb.append(file.getFileName().toString() + ",");
        String time = attr.creationTime().toString();
        sb.append(f.apply(time) + ",");

        if(Files.isDirectory(file))
            sb.append(0);

        else
            sb.append(String.valueOf(attr.size()));

        data.add(sb.toString());
    }

}
