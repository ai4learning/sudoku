package com.goldfish.constant;

/**
 * Created by John on 2018/5/16 0016.
 */
public enum UserRoleType {
    USER(1, "用户"),ADMIN(2, "管理员");

    private Integer type;
    private String desc;

    UserRoleType(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
