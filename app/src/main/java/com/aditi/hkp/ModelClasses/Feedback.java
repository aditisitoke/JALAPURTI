package com.aditi.hkp.ModelClasses;

/**
 * Created by HP on 3/30/2018.
 */

public class Feedback {

    String Message,messageType,Redirect,Id,Info,TabNo;
    FeedbackObject Object;

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

    public FeedbackObject getObject() {
        return Object;
    }

    public void setObject(FeedbackObject object) {
        Object = object;
    }

    public class FeedbackObject{
        String feedback_id,login_id,message,tbl_login;

        public String getFeedback_id() {
            return feedback_id;
        }

        public void setFeedback_id(String feedback_id) {
            this.feedback_id = feedback_id;
        }

        public String getLogin_id() {
            return login_id;
        }

        public void setLogin_id(String login_id) {
            this.login_id = login_id;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getTbl_login() {
            return tbl_login;
        }

        public void setTbl_login(String tbl_login) {
            this.tbl_login = tbl_login;
        }
    }

}
