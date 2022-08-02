package com.cdg;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LogFileReader {
    private BufferedReader bufferedReader;
    private static final String READ_FILE_NAME = "input.log";

    public void logFileOpen() throws FileNotFoundException {
        bufferedReader = new BufferedReader(new FileReader(READ_FILE_NAME));
    }
    public String logFileRead() throws IOException {
        return bufferedReader.readLine();
    }
    public void logFileClose() throws IOException {
        bufferedReader.close();
    }
}
