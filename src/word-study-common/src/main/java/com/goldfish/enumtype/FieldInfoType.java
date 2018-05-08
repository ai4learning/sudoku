package com.goldfish.enumtype;

/**
 * User:cdtangxi
 * Date:2014/11/20 0020
 * Time:20:54
 */
public enum FieldInfoType {
    STRING(1, "String字符"),
    BYTE_ARRAY(2, "byte数组"),
    OTHER(10,"其他，用JSON方式");

    private final int type;
    private final String typeName;

    private FieldInfoType(int type, String typeName) {
        this.type = type;
        this.typeName = typeName;
    }

    public static FieldInfoType getType(int type) {
        for (FieldInfoType t : values()) {
            if (type == t.getType()) {
                return t;
            }
        }
        return null;
    }

    public int getType() {
        return this.type;
    }

    public String getTypeName() {
        return this.typeName;
    }
}
