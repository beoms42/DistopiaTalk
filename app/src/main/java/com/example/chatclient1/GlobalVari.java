package com.example.chatclient1;

import android.app.Application;

public class GlobalVari extends Application {

    private String SendMsg;

    public String getData()
    {
        return SendMsg;
    }

    public void setData(String SendMsg)
    {
        this.SendMsg = SendMsg;
    }
}
