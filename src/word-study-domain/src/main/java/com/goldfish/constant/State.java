package com.goldfish.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by John on 2018/5/6 0006.
 */
public enum State {
    VALID(1, "有效"),
    IN_VALID(2, "无效"),
    UNKNOW(-1,"未知"),
    YES(1,"是",Boolean.TRUE),
    NO(0,"否", Boolean.FALSE);

    private int state;
    private String desc;
    private Boolean result;
    private static Map<Integer,State> map = new HashMap<Integer,State>();

    static {
        map.put(1, State.YES);
        map.put(0, State.NO);
    }

    State(int state, String desc) {
        this.state = state;
        this.desc = desc;
    }

    State(int state, String desc, Boolean result) {
        this.state = state;
        this.desc = desc;
        this.result = result;
    }

    public int getState() {
        return state;
    }

    public Boolean getResult() {
        return result;
    }

    public static Map<Integer, State> getMap() {
        return map;
    }
}
