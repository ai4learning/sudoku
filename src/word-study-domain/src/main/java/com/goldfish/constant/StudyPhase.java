package com.goldfish.constant;

/**
 * Created by John on 2018/5/19 0019.
 */
public enum StudyPhase {
    ENHANCE_STUDY(1,"强化练习"),
    OVERALL_MEMORY(2,"整体回忆"),
    UNIT_TEST(3,"单元测试");

    private int phase;
    private String desc;

    StudyPhase(int phase, String desc) {
        this.phase = phase;
        this.desc = desc;
    }

    public int getPhase() {
        return phase;
    }

    public String getDesc() {
        return desc;
    }
}
