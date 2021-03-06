package com.kabaldin.controller.DAO.entity;

public class CompilationStatus {
    private int id;
    private String compilationStatus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompilationStatus() {
        return compilationStatus;
    }

    public void setCompilationStatus(String compilationStatus) {
        this.compilationStatus = compilationStatus;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CompilationStatus)) return false;
        CompilationStatus that = (CompilationStatus) obj;
        return id == that.id && compilationStatus == that.compilationStatus;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((compilationStatus == null) ? 0 : compilationStatus.hashCode());
        return result;
    }
}
