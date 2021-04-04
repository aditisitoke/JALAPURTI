package com.aditi.hkp.ModelClasses;

/**
 * Created by LENOVO on 24-03-2018.
 */

public class Login {

    String Message,messageType,Redirect,Id,Info,TabNo;
    LoginObject Object;

    public LoginObject getObject() {
        return Object;
    }

    public void setObject(LoginObject object) {
        Object = object;
    }

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

    public String getTabNo() {
        return TabNo;
    }

    public void setTabNo(String tabNo) {
        TabNo = tabNo;
    }

    public class LoginObject{
        String login_id,adhar,password,role_id,adhar_id;

        public String getLogin_id() {
            return login_id;
        }

        public void setLogin_id(String login_id) {
            this.login_id = login_id;
        }

        public String getAdhar() {
            return adhar;
        }

        public void setAdhar(String adhar) {
            this.adhar = adhar;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getRole_id() {
            return role_id;
        }

        public void setRole_id(String role_id) {
            this.role_id = role_id;
        }

        public String getAdhar_id() {
            return adhar_id;
        }

        public void setAdhar_id(String adhar_id) {
            this.adhar_id = adhar_id;
        }
    }

}
