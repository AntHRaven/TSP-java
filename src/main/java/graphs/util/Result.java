package graphs.util;

import java.util.Map;
import java.util.TreeMap;

public class Result {

    private Map<String, Map<String, Integer>> resultMap = new TreeMap<>();

    public Map<String, Map<String, Integer>> getResultMap() {
        return resultMap;
    }

    public void setResultMap(Map<String, Map<String, Integer>> resultMap) {
        this.resultMap = resultMap;
    }
}
