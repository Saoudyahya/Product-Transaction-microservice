package com.example.Productmicroservice.MessageModel;


import java.util.Date;

public class MessageModel {


    private String MessageId;
    private String Message;
    private Date MassageDate ;
    private String action;



    public MessageModel(String messageId, String message, Date massageDate, String action) {
        MessageId = messageId;
        Message = message;
        MassageDate = massageDate;
        this.action = action;

    }


    @Override
    public String toString() {
        return "ProductMessage{" +
                "MessageId='" + MessageId + '\'' +
                ", Message='" + Message + '\'' +
                ", MassageDate=" + MassageDate +
                ", action='" + action + '\'' +
                '}';
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public MessageModel() {
    }



    public String getMessageId() {
        return MessageId;
    }

    public void setMessageId(String messageId) {
        MessageId = messageId;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public Date getMassageDate() {
        return MassageDate;
    }

    public void setMassageDate(Date massageDate) {
        MassageDate = massageDate;
    }
}
