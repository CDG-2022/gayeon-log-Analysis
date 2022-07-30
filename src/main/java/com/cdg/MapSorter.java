package com.cdg;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class MapSorter {
    public String getMaxValue(Map<String, Integer> map) { // value 가 가장 큰 key 값 반환
        int max = Collections.max(map.values());
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() == max) {
                return entry.getKey();
            }
        }
        return "";
    }
    public void sortKeyWriteFile(Map<String, Integer> map, PrintWriter printWriter) { // key 값 기준 정렬
        List<String> keySet = new ArrayList<>(map.keySet());
        Collections.sort(keySet);
        for (String key : keySet) {
            printWriter.write(key + " : " + map.get(key) + "\n");
        }
        printWriter.write("\n\n");
    }
    public void sortValueWriteFile(Map<String, Integer> map, int rank, boolean persent, PrintWriter printWriter) { // value 기준 정렬
        List<String> keySet = new ArrayList<>(map.keySet());
        keySet.sort((o1, o2) -> map.get(o2).compareTo(map.get(o1)));
        for (int i = 0; i < rank; i++) {
            if (persent) { //  비율로 표시
                int total = map.values().stream().mapToInt(Integer::intValue).sum();
                printWriter.write(keySet.get(i) + " : " + String.format("%.1f", ((double)map.get(keySet.get(i)) / (double)total * 100)) + "%\n");
            } else {
                printWriter.write(keySet.get(i) + " : " + map.get(keySet.get(i)) + "\n");
            }
        }
        printWriter.write("\n\n");
    }
}
