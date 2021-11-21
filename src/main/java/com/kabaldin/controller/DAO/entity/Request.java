package com.kabaldin.controller.DAO.entity;

import java.util.Objects;

public class Request {

    private int id;
    private String description;
    private String master;
    private String date;
    private int totalCost;
    private String userLogin;
    private int compilationStatusId;
    private int paymentStatusId;

    public Request() {
    }

    public Request(String description, int totalCost, String userLogin) {
        this.description = description;
        this.totalCost = totalCost;
        this.userLogin = userLogin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public int getCompilationStatusId() {
        return compilationStatusId;
    }

    public void setCompilationStatusId(int compilationStatusId) {
        this.compilationStatusId = compilationStatusId;
    }

    public int getPaymentStatusId() {
        return paymentStatusId;
    }

    public void setPaymentStatusId(int paymentStatusId) {
        this.paymentStatusId = paymentStatusId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Request)) return false;
        Request request = (Request) obj;
        return id == request.id &&
                Objects.equals(description, request.description) &&
                Objects.equals(master, request.master) &&
                Objects.equals(date, request.date) &&
                Objects.equals(compilationStatusId, request.compilationStatusId) &&
                Objects.equals(paymentStatusId, request.paymentStatusId) &&
                Objects.equals(totalCost, request.totalCost) &&
                Objects.equals(userLogin, request.userLogin);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((description == null) ? 0 : description.hashCode()) +
                ((date == null) ? 0 : date.hashCode()) +
                compilationStatusId +
                paymentStatusId +
                totalCost +
                ((userLogin == null) ? 0 : userLogin.hashCode());
        return result;
    }
}
