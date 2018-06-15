package com.goldfish.vo.course;

import com.goldfish.vo.CurrentStudyPositionVO;

/**
 *  *         {
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
 * Created by John on 2018/5/21 0021.
 */
public class PositonedCourseStudyVO extends CourseStudyVO {

    private CurrentStudyPositionVO currentPosition;

    public CurrentStudyPositionVO getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(CurrentStudyPositionVO currentPosition) {
        this.currentPosition = currentPosition;
    }
}
