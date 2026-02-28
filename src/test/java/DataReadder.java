
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;


public class DataReadder {
    public List<HashMap<String, String>> getJSONData(String filepath) throws IOException {
        // Read JSON to String
        String jsonContent = FileUtils.readFileToString(
                new File(System.getProperty("user.dir") + "/src/test/TestData.JSON"),
                StandardCharsets.UTF_8
        );
        // Convert String to Map and paste in 1 List , so it is convert List like this
        //List<HashMap<String, String>>

        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data =
                mapper.readValue(jsonContent,
                        new TypeReference<List<HashMap<String, String>>>() {
                        });

        return data;
    }
}
