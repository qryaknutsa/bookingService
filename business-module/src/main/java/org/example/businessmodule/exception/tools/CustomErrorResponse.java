package org.example.businessmodule.exception.tools;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CustomErrorResponse implements Serializable {
    @SerializedName("title")
    private String title;

    @SerializedName("detail")
    private String detail;

    @SerializedName("instance")
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
