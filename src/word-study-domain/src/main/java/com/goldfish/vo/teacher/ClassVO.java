package com.goldfish.vo.teacher;

/**
 * @author zhangjingtao
 * @date 2018/10/19 0019.
 */

/**
 * start: "2018-09-09",
 *     end: "2018-09-19",
 *     name: "班级名称",
 */
public class ClassVO {
    private String name;
    private String start;
    private String end;

    public ClassVO() {
    }

    public ClassVO(String name, String start, String end) {
        this.name = name;
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "ClassVO{" +
                "name='" + name + '\'' +
                ", start='" + start + '\'' +
                ", end='" + end + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
