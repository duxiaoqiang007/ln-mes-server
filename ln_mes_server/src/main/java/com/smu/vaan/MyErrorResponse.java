package com.smu.vaan;

import com.smu.vaan.exception.ApiException;

import java.util.Objects;

/**
 * Created by vaan on 2017/2/23.
 */
public class MyErrorResponse {
    private long errCode;
    private String errMessage;
    private String path;

    public MyErrorResponse(long errCode, String errMessage, String path) {
        this.errCode = errCode;
        this.errMessage = errMessage;
        this.path = path;
    }

    public MyErrorResponse(ApiException e, String path) {
        this.errCode = e.getErrCode();
        this.errMessage = e.getMessage();
        this.path = path;
    }

    public long getErrCode() {
        return errCode;
    }

    public void setErrCode(long errCode) {
        this.errCode = errCode;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MyErrorResponse)) return false;
        MyErrorResponse that = (MyErrorResponse) o;
        return errCode == that.errCode &&
                Objects.equals(errMessage, that.errMessage) &&
                Objects.equals(path, that.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(errCode, errMessage, path);
    }
}
