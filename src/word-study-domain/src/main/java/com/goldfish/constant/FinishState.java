package com.goldfish.constant;

/**
 * Created by John on 2018/5/6 0006.
 */
public enum FinishState {
    NOT_START(-1,"未开始"),
    NOT_COMPLETE(0,"未完成"),
    COMPLETE(1,"已完成"),
    DELETE(2,"删除");

    private int state;
    private String desc;

    FinishState(int state, String desc) {
        this.state = state;
        this.desc = desc;
    }

    public int getState() {
        return state;
    }
}
