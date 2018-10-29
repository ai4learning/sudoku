package com.goldfish.vo.teacher;

import com.goldfish.domain.Course;
import com.goldfish.vo.BasicVO;

import java.util.List;

/**
 * @author zhangjingtao
 * @date 2018/10/19 0019.
 */
public class GetCoursesVO extends BasicVO {
    private List<Course> data;

    public List<Course> getData() {
        return data;
    }

    public void setData(List<Course> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "GetCoursesVO{" +
                "data=" + data +
                ", success=" + success +
                ", msg='" + msg + '\'' +
                '}';
    }
}
