package org.example.businessmodule.exception.tools;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.io.Serializable;

@JsonRootName("CustomErrorResponse")
public class CustomErrorResponse implements Serializable {
    @JsonProperty("title")
    private String title;

    @JsonProperty("detail")
    private String detail;

    @JsonProperty("instance")
    private String instance;

    public String getTitle() {
        return title;
    }

    public String getDetail() {
        return detail;
    }

    public String getInstance() {
        return instance;
    }

    public CustomErrorResponse(String title, String detail, String instance) {
        this.title = title;
        this.detail = detail;
        this.instance = instance;
    }

    public CustomErrorResponse() {
    }
}
