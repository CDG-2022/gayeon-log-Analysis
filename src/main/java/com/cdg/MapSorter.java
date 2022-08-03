package com.cdg;

import java.io.PrintWriter;
import java.util.*;

public class MapSorter {
    public List getMaxValues(Map<String, Integer> map) {
        List<String> result = new ArrayList<String>();
        int max = Collections.max(map.values());
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() == max) {
                result.add(entry.getKey());
            }
        }
        return result;
    }
    public List sortOrderByKeyAsc(Map<String, Integer> map) {
        List<String> keySet = new ArrayList<>(map.keySet());
        Collections.sort(keySet);
        List<String> result = new ArrayList<String>();
        for (String key : keySet) {
            result.add(key + " : " + map.get(key));
        }
        return result;
    }
    public List sortOrderByValueDesc(Map<String, Integer> map, int rank, boolean percent) {
        List<String> keySet = new ArrayList<>(map.keySet());
        keySet.sort((o1, o2) -> map.get(o2).compareTo(map.get(o1)));
        List<String> result = new ArrayList<String>();
        for (int i = 0; i < rank; i++) { // 상위 몇 개
            if (percent) { //  비율로 표시
                int total = map.values().stream().mapToInt(Integer::intValue).sum();
                result.add(keySet.get(i) + " : " + String.format("%.1f", ((double)map.get(keySet.get(i)) / (double)total * 100)) + "%");
            } else {
                result.add(keySet.get(i) + " : " + map.get(keySet.get(i)));
            }
        }
        return result;
    }
    // not use
    public void sortKeyWriteFile(Map<String, Integer> map, PrintWriter printWriter) { // key 값 기준 정렬
        List<String> keySet = new ArrayList<>(map.keySet());
        Collections.sort(keySet);
        for (String key : keySet) {
            printWriter.write(key + " : " + map.get(key) + "\n");
        }
        printWriter.write("\n\n");
    }
    public void sortValueWriteFile(Map<String, Integer> map, int rank, boolean percent, PrintWriter printWriter) { // value 기준 정렬
        List<String> keySet = new ArrayList<>(map.keySet());
        keySet.sort((o1, o2) -> map.get(o2).compareTo(map.get(o1)));
        for (int i = 0; i < rank; i++) {
            if (percent) { //  비율로 표시
                int total = map.values().stream().mapToInt(Integer::intValue).sum();
                printWriter.write(keySet.get(i) + " : " + String.format("%.1f", ((double)map.get(keySet.get(i)) / (double)total * 100)) + "%\n");
            } else {
                printWriter.write(keySet.get(i) + " : " + map.get(keySet.get(i)) + "\n");
            }
        }
        printWriter.write("\n\n");
    }
}
