package com.cdg;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.Assert;

import java.io.IOException;

/**
 * Unit test for simple App.
 */
public class mainTest
{
    @DisplayName("logMap 에 올바른 값이 들어오는지 확인")
    @Test
    void parse() throws IOException {
        LogMap logMap = new LogMap();
        LogParser logParser = new LogParser();
        logParser.logParse(logMap);
    }
}
