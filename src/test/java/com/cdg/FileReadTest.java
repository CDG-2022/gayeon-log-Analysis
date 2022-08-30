package com.cdg;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Unit test for simple App.
 */
public class FileReadTest
{
    @DisplayName("읽고자 하는 파일이 없으면 익셉션 발생")
    @Test
    public void fileNonExist() throws FileNotFoundException
    {
        String READ_FILE_NAME = "input.log";
        BufferedReader bufferedReader = new BufferedReader(new FileReader(READ_FILE_NAME));
    }

    @DisplayName("파일이 한 줄씩 잘 읽힘")
    @Test
    public void fileOneLineRead() throws IOException {
        LogFileReader logFileReader = new LogFileReader();
        logFileReader.logFileOpen();
        String TEST_STRING = "[200][http://apis.daum.net/search/knowledge?apikey=23jf&q=daum][IE][2009-06-10 08:00:00]";
        Assertions.assertEquals(TEST_STRING, logFileReader.logFileRead());
        logFileReader.logFileClose();
    }
}
