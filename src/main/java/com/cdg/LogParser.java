package com.cdg;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

public class LogParser {
    private LogFileReader logFileReader = new LogFileReader();

    public void logParse(LogMap logMap) throws IOException {
        logFileReader.logFileOpen();
        while (true) {
            String[] splitLog = StringUtils.substringsBetween(logFileReader.logFileRead(), "[", "]");
            if (splitLog == null) break;
            logMap.stateCodeMap.put(splitLog[0], logMap.stateCodeMap.getOrDefault(splitLog[0], 0) + 1);
            logMap.apiServiceMap.put(StringUtils.substringBetween(splitLog[1], "search/", "?"), logMap.apiServiceMap.getOrDefault(StringUtils.substringBetween(splitLog[1], "search/", "?"), 0) + 1);
            logMap.apiKeyMap.put(StringUtils.substringBetween(splitLog[1], "=", "&"), logMap.apiKeyMap.getOrDefault(StringUtils.substringBetween(splitLog[1], "apikey=", "&"), 0) + 1);
            logMap.webBrowserMap.put(splitLog[2], logMap.webBrowserMap.getOrDefault(splitLog[2], 0) + 1);
            logMap.peakTimeMap.put(splitLog[3].substring(0, splitLog[3].length()-3), logMap.peakTimeMap.getOrDefault(splitLog[3].substring(0, splitLog[3].length()-3), 0) + 1);
        }
        logFileReader.logFileClose();
    }
}
