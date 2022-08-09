package com.cdg;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class LogFileWriter {
    FileWriter fileWriter;
    PrintWriter printWriter;
    File file;
    private MapSorter mapSorter = new MapSorter();
    private static final String OUTPUT_FILE_NAME = "output.log"; // 쓰려는 file name
    public LogFileWriter() throws IOException{
        file = new File(OUTPUT_FILE_NAME);
        if (!file.exists()) {
            file.createNewFile();
        }
        fileWriter = new FileWriter(file);
        printWriter = new PrintWriter(fileWriter);
    }
    public void logFileWriter(LogMap logMap) {
        printWriter.write("최다호출 APIKEY\n\n");
        if (mapSorter.getMaxValues(logMap.getApiKeyMap()).size() != 0) {
            for (int i = 0; i < mapSorter.getMaxValues(logMap.getApiKeyMap()).size(); i++) {
                printWriter.write((String) mapSorter.getMaxValues(logMap.getApiKeyMap()).get(i) + "\n\n");
            }
        }
        printWriter.println();
        printWriter.write("상태코드 별 횟수\n\n");
        if (mapSorter.sortOrderByKeyAsc(logMap.getStateCodeMap()).size() != 0) {
            for (int i = 0; i < mapSorter.sortOrderByKeyAsc(logMap.getStateCodeMap()).size(); i++) {
                printWriter.write( (String) mapSorter.sortOrderByKeyAsc(logMap.getStateCodeMap()).get(i) + "\n");
            }
        }
        printWriter.println(); printWriter.println();
        printWriter.write("상위 3개의 API ServiceID와 각각의 요청 수\n\n");
        if (mapSorter.sortOrderByValueDesc(logMap.getApiServiceMap(), 3, false).size() != 0 ) {
            for (int i = 0; i < mapSorter.sortOrderByValueDesc(logMap.getApiServiceMap(), 3, false).size(); i++) {
                printWriter.write((String) mapSorter.sortOrderByValueDesc(logMap.getApiServiceMap(), 3, false).get(i) + "\n");
            }
        }
        printWriter.println(); printWriter.println();
        printWriter.write("피크 시간대\n\n");
        if (mapSorter.getMaxValues(logMap.getPeakTimeMap()).size() != 0) {
            for (int i = 0; i < mapSorter.getMaxValues(logMap.getPeakTimeMap()).size(); i++) {
                printWriter.write((String) mapSorter.getMaxValues(logMap.getPeakTimeMap()).get(i) + "\n\n");
            }
        }
        printWriter.println();
        printWriter.write("웹 브라우저 별 사용비율\n\n");
        if (mapSorter.sortOrderByValueDesc(logMap.getWebBrowserMap(), 5, true).size() != 0) {
            for (int i = 0; i < mapSorter.sortOrderByValueDesc(logMap.getWebBrowserMap(), 5, true).size(); i++) {
                printWriter.write((String) mapSorter.sortOrderByValueDesc(logMap.getWebBrowserMap(), 5, true).get(i) + "\n");
            }
        }
        printWriter.close();
    }

    private void writeContent(boolean isProcessing, )
}
