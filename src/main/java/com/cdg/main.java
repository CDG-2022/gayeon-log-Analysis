package com.cdg;

import java.io.*;
import java.util.*;

import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.ls.LSOutput;

public class main
{
    static HashMap<String, Integer> apiKeyMap = new HashMap<>();
    static HashMap<String, Integer> stateCodeMap = new HashMap<>();
    static HashMap<String, Integer> apiServiceMap = new HashMap<>();
    static HashMap<String, Integer> peakTimeMap = new HashMap<>();
    static HashMap<String, Integer> webBrowserMap = new HashMap<>();
    private static void logAnalysis(String line) {
        for (int i = 0; i < line.length(); i++) {
            String[] splitLog = StringUtils.substringsBetween(line, "[", "]");
            stateCodeMap.put(splitLog[0], stateCodeMap.getOrDefault(splitLog[0], 0) + 1);
            apiServiceMap.put(StringUtils.substringBetween(splitLog[1], "search/", "?"), apiServiceMap.getOrDefault(StringUtils.substringBetween(splitLog[1], "search/", "?"), 0) + 1);
            apiKeyMap.put(StringUtils.substringBetween(splitLog[1], "apikey=", "&"), apiKeyMap.getOrDefault(StringUtils.substringBetween(splitLog[1], "apikey=", "&"), 0) + 1);
            webBrowserMap.put(splitLog[2], webBrowserMap.getOrDefault(splitLog[2], 0) + 1);
            peakTimeMap.put(splitLog[3].substring(0, splitLog[3].length()-3), peakTimeMap.getOrDefault(splitLog[3].substring(0, splitLog[3].length()-3), 0) + 1); // 1 증가
        }
    }

    private static String maxValue(Map<String, Integer> map) {
        int max = Collections.max(map.values());
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() == max) {
                return entry.getKey();
            }
        }
        return "";
    }

    private static void sortKeyPrint(Map<String, Integer> map) { // key 기준 정렬
        List<String> keySet = new ArrayList<>(map.keySet());
        Collections.sort(keySet); // 오름차순 정렬
        for (String key : keySet) {
            System.out.println(key + " : " + map.get(key));
        }
        System.out.printf("\n\n");
    }

    private static void sortKeyWriteFile(Map<String, Integer> map, PrintWriter printWriter) { // key 기준 정렬
        List<String> keySet = new ArrayList<>(map.keySet());
        Collections.sort(keySet); // 오름차순 정렬
        for (String key : keySet) {
            printWriter.write(key + " : " + map.get(key) + "\n");
        }
        printWriter.write("\n\n");
    }

    private static void sortValuePrint(Map<String, Integer> map, int rank, boolean persent) { // value 기준 정렬
        List<String> keySet = new ArrayList<>(map.keySet());
        keySet.sort((o1, o2) -> map.get(o2).compareTo(map.get(o1)));
        for (int i = 0; i < rank; i++) {
            if (persent) { //  비율로 표시
                int total = map.values().stream().mapToInt(Integer::intValue).sum();
                System.out.println(keySet.get(i) + " : " + String.format("%.1f", ((double)map.get(keySet.get(i)) / (double)total * 100)) + "%");
            } else {
                System.out.println(keySet.get(i) + " : " + map.get(keySet.get(i)));
            }
        }
        System.out.printf("\n\n");
    }

    private static void sortValueWriteFile(Map<String, Integer> map, int rank, boolean persent, PrintWriter printWriter) { // value 기준 정렬
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

    private static void printLog() {
        System.out.println("최다호출 APIKEY\n");
        System.out.println(maxValue(apiKeyMap) + "\n\n");
        System.out.println("상태코드 별 횟수\n");
        sortKeyPrint(stateCodeMap);
        System.out.println("상위 3개의 API ServiceID와 각각의 요청 수\n");
        sortValuePrint(apiServiceMap, 3, false);
        System.out.println("피크 시간대\n");
        System.out.println(maxValue(peakTimeMap) + "\n\n");
        System.out.println("웹 브라우저 별 사용비율\n");
        sortValuePrint(webBrowserMap, 5, true);
    }

    private static void writeLog() throws IOException {
        File file = new File("output.log");
        if (!file.exists()) file.createNewFile();
        FileWriter fileWriter = new FileWriter(file);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        printWriter.write("최다호출 APIKEY\n\n");
        printWriter.write(maxValue(apiKeyMap) + "\n\n\n");
        printWriter.write("상태코드 별 횟수\n\n");
        sortKeyWriteFile(stateCodeMap, printWriter);
        printWriter.write("상위 3개의 API ServiceID와 각각의 요청 수\n\n");
        sortValueWriteFile(apiServiceMap, 3, false, printWriter);
        printWriter.write("피크 시간대\n\n");
        printWriter.write(maxValue(peakTimeMap) + "\n\n\n");
        printWriter.write("웹 브라우저 별 사용비율\n\n");
        sortValueWriteFile(webBrowserMap, 5, true, printWriter);
        printWriter.close();
    }

    public static void main( String[] args ) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("input.log"));
        while (true) {
            String line = bufferedReader.readLine();
            if (line == null) break;
            logAnalysis(line);
        }
//        printLog();
        writeLog();
        bufferedReader.close();
    }
}