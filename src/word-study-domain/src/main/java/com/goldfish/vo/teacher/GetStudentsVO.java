package com.goldfish.vo.teacher;

import com.goldfish.domain.User;
import com.goldfish.vo.BasicVO;

import java.util.List;

/**
 * @author zhangjingtao
 * @date 2018/10/21 0021.
 */
public class GetStudentsVO extends BasicVO {
    private List<User> data;

    @Override
    public String toString() {
        return "GetStudentsVO{" +
                "data=" + data +
                ", success=" + success +
                ", msg='" + msg + '\'' +
                '}';
    }

    public List<User> getData() {
        return data;
    }

    public void setData(List<User> data) {
        this.data = data;
    }
}
