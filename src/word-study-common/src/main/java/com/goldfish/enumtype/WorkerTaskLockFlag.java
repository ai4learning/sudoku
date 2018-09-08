package com.goldfish.enumtype;

/**
 * User:cdtangxi
 * Date:2014/11/20 0020
 * Time:20:54
 */
public enum WorkerTaskLockFlag {
    LOCKED(1, "锁定"),
    UNLOCK(2, "未锁定");

    private final int type;
    private final String typeName;

    private WorkerTaskLockFlag(int type, String typeName) {
        this.type = type;
        this.typeName = typeName;
    }

    public static WorkerTaskLockFlag getType(int type) {
        for (WorkerTaskLockFlag t : values()) {
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
