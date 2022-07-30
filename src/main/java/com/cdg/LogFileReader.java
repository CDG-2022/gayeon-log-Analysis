package com.cdg;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LogFileReader {
    private BufferedReader bufferedReader;

    public void logFileOpen() throws FileNotFoundException {
        bufferedReader = new BufferedReader(new FileReader("input.log"));
    }
    public String logFileRead() throws IOException {
        String line = bufferedReader.readLine();
        return line;
    }
    public void logFileClose() throws IOException {
        bufferedReader.close();
    }
}
