package com.goldfish.vo.statistics;

import com.goldfish.vo.BasicVO;

import java.util.List;

/**
 * @author zhangjingtao
 * @date 2018/7/7 0007.
 */
public class SituationVO extends BasicVO{
    private Long classNO;
    private String date;
    private Integer dayNO;
    private List<PersonalAchievementVO> achievements;
    private Boolean realTime;

    public Long getClassNO() {
        return classNO;
    }

    public void setClassNO(Long classNO) {
        this.classNO = classNO;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getDayNO() {
        return dayNO;
    }

    public void setDayNO(Integer dayNO) {
        this.dayNO = dayNO;
    }

    public List<PersonalAchievementVO> getAchievements() {
        return achievements;
    }

    public void setAchievements(List<PersonalAchievementVO> achievements) {
        this.achievements = achievements;
    }

    public Boolean getRealTime() {
        return realTime;
    }

    public void setRealTime(Boolean realTime) {
        this.realTime = realTime;
    }

    @Override
    public String toString() {
        return "SituationVO{" +
                "classNO=" + classNO +
                ", date='" + date + '\'' +
                ", dayNO=" + dayNO +
                ", achievements=" + achievements +
                ", realTime=" + realTime +
                '}';
    }
}
