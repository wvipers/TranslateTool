import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import com.google.gson.*;
import com.google.gson.internal.LinkedHashTreeMap;


/**
 * Created by Patryk on 21.05.2017.
 */
public class TranslationReader {




    public static LinkedHashTreeMap<String,String> readTranslationMap(String fileURI)  {
        String content=null;
        Path path= Paths.get(fileURI);

        try {
            content = new String(Files.readAllBytes(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        LinkedHashTreeMap translationMap = gson.fromJson(content , LinkedHashTreeMap.class);

        return translationMap;
    }

    public static void saveTranslationMap(TranslationMap map, String fileURI){
        Gson gson=new Gson();
        String mapInJson=gson.toJson(map.getMap(),map.getMap().getClass());
        mapInJson=mapInJson.replace(("\",\""),("\",\n\""));
        String[] lines = mapInJson.split("\\n");
        try (FileWriter fileWriter = new FileWriter(fileURI)) {
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for(String line:lines){
                bufferedWriter.write(line+"\n");
            }
            bufferedWriter.close();

        }catch (IOException e){
            e.printStackTrace();

        }

    }
}
