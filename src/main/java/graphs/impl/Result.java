package graphs.impl;

import java.util.HashMap;
import java.util.Map;

public class Result {

    private Map<String, Map<String, Integer>> resultMap = new HashMap<>();

    public Map<String, Map<String, Integer>> getResultMap() {
        return resultMap;
    }

    public void setResultMap(Map<String, Map<String, Integer>> resultMap) {
        this.resultMap = resultMap;
    }
}
