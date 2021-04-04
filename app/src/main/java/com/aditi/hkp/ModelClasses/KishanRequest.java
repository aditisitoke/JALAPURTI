package com.aditi.hkp.ModelClasses;

/**
 * Created by HP on 3/30/2018.
 */

public class KishanRequest {

    String Message,messageType,Redirect,Id,Info,Object,TabNo;

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getRedirect() {
        return Redirect;
    }

    public void setRedirect(String redirect) {
        Redirect = redirect;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getInfo() {
        return Info;
    }

    public void setInfo(String info) {
        Info = info;
    }

    public String getObject() {
        return Object;
    }

    public void setObject(String object) {
        Object = object;
    }

    public String getTabNo() {
        return TabNo;
    }

    public void setTabNo(String tabNo) {
        TabNo = tabNo;
    }
}
