package com.goldfish.vo.user;

import com.goldfish.vo.course.CourseStudyVO;

import java.util.List;

/**
 * 学生CourseVO
 *
 *      * {
 *     "books":[
 *         {
 *             "Id":5,
 *             "moduleCode":"8a108cb7-42ad-314f-0142-ad33a0a70001",
 *             "bookName":"零基础入门拼读",
 *             "coverImageUrl":"",
 *             "totalUnitNbr":17,
 *             "currentPosition":{
 *                 "Id":514981,
 *                 "studyPositionCode":"68c7bd11-5ebf-4c67-a8c8-e8d3c3b24840",
 *                 "positionType":0,
 *                 "vocCode":"8a108cb7-42aa-d911-0142-ad8123f50222",
 *                 "unitNbr":14,
 *                 "isCurrentPos":true,
 *                 "isFinished":0,
 *                 "spelling":"three",
 *                 "isAllFinished":true,
 *                 "IsTested":0,
 *                 "Status":0
 *             },
 *             "currentStudyBook":true,
 *             "outDate":false,
 *             "unitType":"Unit",
 *             "startFrom":1,
 *             "studyMode":1
 *         }
 *     ],
 *     "condition":0,
 *     "totalNbr":0,
 *     "msg":"完成加载",
 *     "userState":2,
 *     "totalLoginTimes":0,
 *     "success":true
 * }
 * Created by John on 2018/5/21 0021.
 */
public class UserBookVO extends UserVO {

    private List<CourseStudyVO> books;

    public List<CourseStudyVO> getBooks() {
        return books;
    }

    public void setBooks(List<CourseStudyVO> books) {
        this.books = books;
    }
}
