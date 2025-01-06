package org.example.businessmodule.exception.tools;

import java.util.List;


public class ErrorResponseArray {
    private String title;
    private List<String> details;
    private String instance;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }

    public String getInstance() {
        return instance;
    }

    public void setInstance(String instance) {
        this.instance = instance;
    }

    public ErrorResponseArray(String title, List<String> details, String instance) {
        this.title = title;
        this.details = details;
        this.instance = instance;
    }
}
