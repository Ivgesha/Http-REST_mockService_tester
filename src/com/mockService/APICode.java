package com.mockService;

public class APICode {

    private int id;
    private int code;


    public APICode(int id, int code) {
        this.id = id;
        this.code = code;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


    @Override
    public String  toString(){
        return String.valueOf(getCode());
    }
}
