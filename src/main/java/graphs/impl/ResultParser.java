package graphs.impl;

import graphs.Graph;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ResultParser {


    public void saveResultToJson(Map<String, Map<String, Integer>> resultMap, File file) {
        BufferedWriter bf = null;
        try {
            // create new BufferedWriter for the output file
            bf = new BufferedWriter(new FileWriter(file));
            try {
                for(Map.Entry<String, Map<String, Integer>> entry : resultMap.entrySet()) {
                    bf.write("{" + entry.getKey() + ":");
                    for (Map.Entry<String, Integer> subEntry : entry.getValue().entrySet()) {
                       bf.write("{" + subEntry.getKey() + ":" + entry.getValue() + "},");
                    }
                    bf.write("},\n");

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            bf.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {

            try {

                // always close the writer
                if (bf != null) {
                    bf.close();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private Map<String, Map<String, Integer>> resultToMap(Node node, Graph graph) {
        Map<String, Integer> subResultMap = new HashMap<>();
        return null;
    }
}
