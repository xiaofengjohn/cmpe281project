package com.springapp.mvc.Data;

/**
 * Created by WU on 17/11/2015.
 */
public class Usage {
    public Usage() {};
    public Usage(int count, int credit) {
        this.dataCount = count;
        this.credit = credit;
    }

    int dataCount;
    int credit;

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


}
