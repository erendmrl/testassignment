package dataProvider;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class JsonFileParser {

    private final Logger logger = Logger.getLogger(this.getClass());


    public String parseJsonFileAndReturnRequestedDAta(String dataGroup, String requestedData) throws FileNotFoundException {
        String path = "testdata//testdata.json";
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(bufferedReader, JsonObject.class);
        return jsonObject.getAsJsonObject(dataGroup).get(requestedData).getAsString();
    }

}
