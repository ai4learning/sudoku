package com.goldfish.vo.teacher;

import com.goldfish.domain.ClassGrade;
import com.goldfish.vo.BasicVO;

import java.util.List;

/**
 * @author zhangjingtao
 * @date 2018/10/19 0019.
 */
public class GetClassListVO extends BasicVO {
    private List<ClassGrade> data;

    public List<ClassGrade> getData() {
        return data;
    }

    public void setData(List<ClassGrade> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "GetClassListVO{" +
                "data=" + data +
                ", success=" + success +
                ", msg='" + msg + '\'' +
                '}';
    }
}
