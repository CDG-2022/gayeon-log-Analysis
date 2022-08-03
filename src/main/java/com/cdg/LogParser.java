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
            logMap.getStateCodeMap().put(splitLog[0], logMap.getStateCodeMap().getOrDefault(splitLog[0], 0) + 1);
            logMap.getApiServiceMap().put(StringUtils.substringBetween(splitLog[1], "search/", "?"), logMap.getApiServiceMap().getOrDefault(StringUtils.substringBetween(splitLog[1], "search/", "?"), 0) + 1);
            logMap.getApiKeyMap().put(StringUtils.substringBetween(splitLog[1], "=", "&"), logMap.getApiKeyMap().getOrDefault(StringUtils.substringBetween(splitLog[1], "=", "&"), 0) + 1);
            logMap.getWebBrowserMap().put(splitLog[2], logMap.getWebBrowserMap().getOrDefault(splitLog[2], 0) + 1);
            logMap.getPeakTimeMap().put(splitLog[3].substring(0, splitLog[3].length()-3), logMap.getPeakTimeMap().getOrDefault(splitLog[3].substring(0, splitLog[3].length()-3), 0) + 1);
        }
        logFileReader.logFileClose();
    }
}
