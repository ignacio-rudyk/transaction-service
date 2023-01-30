package com.accenture.transactionservice.model;

public class ErrorResponse {

    private Integer code;

    private String desc;

    private Object data;

    public ErrorResponse() {
        super();
    }

    public ErrorResponse(Object data) {
        this.code = 0;
        this.desc = new String();
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "code=" + code +
                ", desc='" + desc + '\'' +
                ", data=" + data +
                '}';
    }
}
