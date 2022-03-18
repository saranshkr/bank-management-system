package com.contrefairebank.DataManagement.entities;

public class FundTransferRequestBody {
    
    private int fromAccountNo;
    private int toAccountNo;
    private double amount;

    public FundTransferRequestBody() {}

    public FundTransferRequestBody(int fromAccountNo, int toAccountNo, double amount) {
        this.fromAccountNo = fromAccountNo;
        this.toAccountNo = toAccountNo;
        this.amount = amount;
    }

    public int getFromAccountNo() {
        return fromAccountNo;
    }

    public void setFromAccountNo(int fromAccountNo) {
        this.fromAccountNo = fromAccountNo;
    }

    public int getToAccountNo() {
        return toAccountNo;
    }

    public void setToAccountNo(int toAccountNo) {
        this.toAccountNo = toAccountNo;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    };

    
}
