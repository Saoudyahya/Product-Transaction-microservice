package com.example.Productmicroservice.MessageModel;


import java.util.Date;

public class SupplierMessage {


    private String MessageId;
    private String Message;
    private Date MassageDate ;

    public SupplierMessage(String messageId, String message, Date massageDate) {
        MessageId = messageId;
        Message = message;
        MassageDate = massageDate;
    }

    public SupplierMessage() {
    }

    @Override
    public String toString() {
        return "SupplierMessage{" +
                "MessageId='" + MessageId + '\'' +
                ", Message='" + Message + '\'' +
                ", MassageDate=" + MassageDate +
                '}';
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
