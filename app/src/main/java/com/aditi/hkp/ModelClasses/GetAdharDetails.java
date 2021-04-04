package com.aditi.hkp.ModelClasses;

/**
 * Created by LENOVO on 27-03-2018.
 */

public class GetAdharDetails {

    String Message,messageType,Redirect,Id,Info,TabNo;
    GetAdharObject Object;

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

    public GetAdharObject getObject() {
        return Object;
    }

    public void setObject(GetAdharObject object) {
        Object = object;
    }

    public class GetAdharObject{
        String name,father_name,mail_id,parmanent_address,mobile_no,dob,adhar_id,adhar_no;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFather_name() {
            return father_name;
        }

        public void setFather_name(String father_name) {
            this.father_name = father_name;
        }

        public String getMail_id() {
            return mail_id;
        }

        public void setMail_id(String mail_id) {
            this.mail_id = mail_id;
        }

        public String getParmanent_address() {
            return parmanent_address;
        }

        public void setParmanent_address(String parmanent_address) {
            this.parmanent_address = parmanent_address;
        }

        public String getMobile_no() {
            return mobile_no;
        }

        public void setMobile_no(String mobile_no) {
            this.mobile_no = mobile_no;
        }

        public String getDob() {
            return dob;
        }

        public void setDob(String dob) {
            this.dob = dob;
        }

        public String getAdhar_id() {
            return adhar_id;
        }

        public void setAdhar_id(String adhar_id) {
            this.adhar_id = adhar_id;
        }

        public String getAdhar_no() {
            return adhar_no;
        }

        public void setAdhar_no(String adhar_no) {
            this.adhar_no = adhar_no;
        }
    }

}
