package com.cdg;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class LogFileWriter {
    private MapSorter mapSorter = new MapSorter();
    public void logFileWriter(LogMap logMap) throws IOException {
        File file = new File("output.log");
        if (!file.exists()) file.createNewFile();
        FileWriter fileWriter = new FileWriter(file);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        printWriter.write("최다호출 APIKEY\n\n");
        printWriter.write(mapSorter.getMaxValue(logMap.apiKeyMap) + "\n\n\n");
        printWriter.write("상태코드 별 횟수\n\n");
        mapSorter.sortKeyWriteFile(logMap.stateCodeMap, printWriter);
        printWriter.write("상위 3개의 API ServiceID와 각각의 요청 수\n\n");
        mapSorter.sortValueWriteFile(logMap.apiServiceMap, 3, false, printWriter);
        printWriter.write("피크 시간대\n\n");
        printWriter.write(mapSorter.getMaxValue(logMap.peakTimeMap) + "\n\n\n");
        printWriter.write("웹 브라우저 별 사용비율\n\n");
        mapSorter.sortValueWriteFile(logMap.webBrowserMap, 5, true, printWriter);
        printWriter.close();
    }
}
