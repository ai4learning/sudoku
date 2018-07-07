package com.goldfish.vo.statistics;

/**
 * @author zhangjingtao
 * @date 2018/7/7 0007.
 */
public class PersonalAchievementVO {
    private String name;
    private Integer achievement;

    public PersonalAchievementVO() {
    }

    public PersonalAchievementVO(String name, Integer achievement) {
        this.name = name;
        this.achievement = achievement;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAchievement() {
        return achievement;
    }

    public void setAchievement(Integer achievement) {
        this.achievement = achievement;
    }

    @Override
    public String toString() {
        return "PersonalAchievementVO{" +
                "name='" + name + '\'' +
                ", achievement=" + achievement +
                '}';
    }
}
