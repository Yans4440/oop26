import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PlantUmlRunner {
    private static String jarPath;
    public static void setJarPath(String path){
        jarPath = path;
    }
    public static void generate(String data, String outputPath, String filename){
        File directory = new File(outputPath);
        directory.mkdirs();

        File file = new File(outputPath + "/" + filename);
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(data);
            writer.close();

            ProcessBuilder builder = new ProcessBuilder("java", "-jar", jarPath,file.getPath());
            builder.start();
        }catch (IOException e){
            throw new RuntimeException(e);
            }

    }
}