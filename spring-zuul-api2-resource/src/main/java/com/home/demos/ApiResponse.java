package com.home.demos;

public class ApiResponse {
    private long id;
    private String result;
    private String additionalInfo;

    public ApiResponse(long id, String result, String additionalInfo) {
        this.id = id;
        this.result = result;
        this.additionalInfo = additionalInfo;
    }


    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getResult() {
        return result;
    }

    public void setResult(final String result) {
        this.result = result;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
}
