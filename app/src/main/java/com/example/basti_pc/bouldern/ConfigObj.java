package com.example.basti_pc.bouldern;

public class ConfigObj {

    private String dataType;
    private String data;

    public ConfigObj(String dataType, String data){
        this.dataType = dataType;
        this.data = data;
    }

    public void setDataType(String dataType){
        this.dataType = dataType;
    }

    public void setData(String data){
        this.data = data;
    }

    public String getDataType(){
        return dataType;
    }

    public String getData(){
        return data;
    }
}
