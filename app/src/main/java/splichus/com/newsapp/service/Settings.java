package splichus.com.newsapp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import splichus.com.newsapp.Constants;

public class Settings {

    Map<String, Boolean> apis;

    public Settings() {
        apis = new HashMap<>();
        apis.put(Constants.NEWSAPI_URL, true);
    }

    public Map<String, Boolean> getApis() {
        return apis;
    }

    public void setApis(Map<String, Boolean> apis) {
        this.apis = apis;
    }

    public List<String> getActiveAPIs() {
        List<String> activeAPIs = new ArrayList<>();
        for (Map.Entry<String, Boolean> entry : apis.entrySet()) {
            if (entry.getValue()) {
                activeAPIs.add(entry.getKey());
            }
        }
        return activeAPIs;
    }

    public void changeEntry(String api, Boolean status) {
        apis.put(api, status);
    }

    public boolean getStatus(String API) {
        if (API != null && apis.containsKey(API)) {
            return apis.get(API);
        }
        return false;
    }
}
