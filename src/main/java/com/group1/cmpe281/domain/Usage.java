package com.group1.cmpe281.domain;

import org.bson.types.ObjectId;

/**
 * Created by WU on 17/11/2015.
 */
public class Usage {
    private String id = new ObjectId().toString();

    public Usage() {};
    public Usage(int count, int credit) {
        this.dataCount = count;
        this.credit = credit;
        this.balance = credit - count;
    }

    int dataCount;
    int credit;
    int balance;
    public int getDataCount() {
        return dataCount;
    }

    public void setDataCount(int dataCount) {
        this.dataCount = dataCount;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
