package com.cdg;

import java.util.HashMap;
import java.util.Map;

public class LogMap {
    private Map<String, Integer> apiKeyMap = new HashMap<>();
    private Map<String, Integer> stateCodeMap = new HashMap<>();
    private Map<String, Integer> apiServiceMap = new HashMap<>();
    private Map<String, Integer> peakTimeMap = new HashMap<>();
    private Map<String, Integer> webBrowserMap = new HashMap<>();

    public Map<String, Integer> getApiKeyMap() {
        return apiKeyMap;
    }

    public void setApiKeyMap(Map<String, Integer> apiKeyMap) {
        this.apiKeyMap = apiKeyMap;
    }

    public Map<String, Integer> getStateCodeMap() {
        return stateCodeMap;
    }

    public void setStateCodeMap(Map<String, Integer> stateCodeMap) {
        this.stateCodeMap = stateCodeMap;
    }

    public Map<String, Integer> getApiServiceMap() {
        return apiServiceMap;
    }

    public void setApiServiceMap(Map<String, Integer> apiServiceMap) {
        this.apiServiceMap = apiServiceMap;
    }

    public Map<String, Integer> getPeakTimeMap() {
        return peakTimeMap;
    }

    public void setPeakTimeMap(Map<String, Integer> peakTimeMap) {
        this.peakTimeMap = peakTimeMap;
    }

    public Map<String, Integer> getWebBrowserMap() {
        return webBrowserMap;
    }

    public void setWebBrowserMap(Map<String, Integer> webBrowserMap) {
        this.webBrowserMap = webBrowserMap;
    }
}
