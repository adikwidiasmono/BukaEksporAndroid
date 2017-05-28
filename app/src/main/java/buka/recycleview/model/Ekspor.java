package buka.recycleview.model;

import java.util.Date;

/**
 * Created by adikwidiasmono on 5/23/17.
 */

public class Ekspor {
    private int thumbnail;
    private String title;
    private String weightNeed;
    private Date dueDate;
    private String description;

    private Date sendDate;
    private String weightChoose;

    private Date proceedDate;
    private Integer status; // 1 : Deterima BE, 2 : Kirim, 3 : Sampai, 4 : Siap Bayar

    private String rejectWeight;
    private String price;
    private boolean paymentStatus;

    public Ekspor(int thumbnail, String title,
                  String weightNeed, Date dueDate,
                  String description, Date sendDate,
                  String weightChoose, Date proceedDate,
                  Integer status, String rejectWeight,
                  String price, boolean paymentStatus) {
        this.thumbnail = thumbnail;
        this.title = title;
        this.weightNeed = weightNeed;
        this.dueDate = dueDate;
        this.description = description;
        this.sendDate = sendDate;
        this.weightChoose = weightChoose;
        this.proceedDate = proceedDate;
        this.status = status;
        this.rejectWeight = rejectWeight;
        this.price = price;
        this.paymentStatus = paymentStatus;
    }

    public Ekspor(String title, String weightNeed, Date dueDate, String description, int thumbnail) {
        this.thumbnail = thumbnail;
        this.title = title;
        this.weightNeed = weightNeed;
        this.dueDate = dueDate;
        this.description = description;
    }

    public Ekspor(int thumbnail, String title, String description, Date sendDate, String weightChoose) {
        this.thumbnail = thumbnail;
        this.title = title;
        this.description = description;
        this.sendDate = sendDate;
        this.weightChoose = weightChoose;
    }

    public Ekspor(String title, String weightChoose, Date proceedDate, Integer status) {
        this.title = title;
        this.weightChoose = weightChoose;
        this.proceedDate = proceedDate;
        this.status = status;
    }

    public Ekspor(int thumbnail, String title, Date sendDate, String weightChoose, Date proceedDate, String rejectWeight, String price, boolean paymentStatus) {
        this.thumbnail = thumbnail;
        this.title = title;
        this.sendDate = sendDate;
        this.weightChoose = weightChoose;
        this.proceedDate = proceedDate;
        this.rejectWeight = rejectWeight;
        this.price = price;
        this.paymentStatus = paymentStatus;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWeightNeed() {
        return weightNeed;
    }

    public void setWeightNeed(String weightNeed) {
        this.weightNeed = weightNeed;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public String getWeightChoose() {
        return weightChoose;
    }

    public void setWeightChoose(String weightChoose) {
        this.weightChoose = weightChoose;
    }

    public Date getProceedDate() {
        return proceedDate;
    }

    public void setProceedDate(Date proceedDate) {
        this.proceedDate = proceedDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRejectWeight() {
        return rejectWeight;
    }

    public void setRejectWeight(String rejectWeight) {
        this.rejectWeight = rejectWeight;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public boolean isPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
