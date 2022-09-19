package graphs;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class ResultParser {

    public void saveResultToJson(Result resultMap, File file) throws IOException {
        new ObjectMapper().writeValue(file, resultMap);
    }

    public Result getMapFromJson(File json) throws IOException {
        return new ObjectMapper().readValue(json, Result.class);
    }

}
