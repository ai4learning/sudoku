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
    private Long id;
    private String name;
    private String start;
    private String end;

    public ClassVO() {
    }

    public ClassVO(Long id, String name, String start, String end) {
        this.id = id;
        this.name = name;
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "ClassVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
