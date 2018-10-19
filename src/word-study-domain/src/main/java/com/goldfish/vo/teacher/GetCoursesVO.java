package com.goldfish.vo.teacher;

import com.goldfish.vo.BasicVO;

import java.util.List;

/**
 * @author zhangjingtao
 * @date 2018/10/19 0019.
 */
public class GetCoursesVO extends BasicVO {
    private List<GetCoursesElementVO> data;

    public List<GetCoursesElementVO> getData() {
        return data;
    }

    public void setData(List<GetCoursesElementVO> data) {
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
