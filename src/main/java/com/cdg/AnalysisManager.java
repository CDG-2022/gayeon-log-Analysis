package com.cdg;

import java.io.IOException;

public class AnalysisManager {
    private LogMap logMap = new LogMap();
    private LogParser logParser = new LogParser();
    private LogFileWriter logFileWriter = new LogFileWriter();

    public void analysisStart() throws IOException {
        logParser.logParse(logMap);
        logFileWriter.logFileWriter(logMap);
    }
}
