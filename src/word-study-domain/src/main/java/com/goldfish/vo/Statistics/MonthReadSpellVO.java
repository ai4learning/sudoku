package com.goldfish.vo.statistics;

/**
 * Created by Administrator on 2018/6/20 0020.
 */

/**
 * {
 "date": "2018-5",
 "totalReadTimes": 129,
 "totalSpellTimes": 356
 }
 */
public class MonthReadSpellVO {
    private String date;
    private int totalReadTimes;
    private int totalSpellTimes;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTotalReadTimes() {
        return totalReadTimes;
    }

    public void setTotalReadTimes(int totalReadTimes) {
        this.totalReadTimes = totalReadTimes;
    }

    public int getTotalSpellTimes() {
        return totalSpellTimes;
    }

    public void setTotalSpellTimes(int totalSpellTimes) {
        this.totalSpellTimes = totalSpellTimes;
    }
}
