package com.goldfish.vo.unit;

import com.goldfish.vo.BaseVO;
import com.goldfish.vo.CurrentStudyPositionVO;
import com.goldfish.vo.course.BookStudyVO;

import java.util.List;

/**
 *      * {
 *     "data":[
 *         {
 *             "Id":1460,
 *             "vocCode":"8a108cb7-42aa-d911-0142-ad812188008d",
 *             "spelling":"lick",
 *             "meaning":"舔;击败",
 *             "soundMarkUs":"[lɪk]",
 *             "soundMarkUk":"",
 *             "UnitId":0,
 *             "unitNbr":3,
 *             "lessonNbr":5,
 *             "fstClassId":8,
 *             "vocIndex":0,
 *             "isCollected":false
 *         },
 *         {
 *             "Id":1461,
 *             "vocCode":"8a108cb7-42aa-d911-0142-ad812188008e",
 *             "spelling":"pick",
 *             "meaning":"挑选;拾起",
 *             "soundMarkUs":"[pɪk]",
 *             "soundMarkUk":"",
 *             "UnitId":0,
 *             "unitNbr":3,
 *             "lessonNbr":5,
 *             "fstClassId":8,
 *             "vocIndex":1,
 *             "isCollected":false
 *         },
 *         {
 *             "Id":1462,
 *             "vocCode":"8a108cb7-42aa-d911-0142-ad812189008f",
 *             "spelling":"kick",
 *             "meaning":"踢;反冲",
 *             "soundMarkUs":"[kɪk]",
 *             "soundMarkUk":"",
 *             "UnitId":0,
 *             "unitNbr":3,
 *             "lessonNbr":5,
 *             "fstClassId":8,
 *             "vocIndex":2,
 *             "isCollected":false
 *         },
 *         {
 *             "Id":1463,
 *             "vocCode":"8a108cb7-42aa-d911-0142-ad8121890090",
 *             "spelling":"sick",
 *             "meaning":"有病的;恶心的",
 *             "soundMarkUs":"[sɪk]",
 *             "soundMarkUk":"",
 *             "UnitId":0,
 *             "unitNbr":3,
 *             "lessonNbr":5,
 *             "fstClassId":8,
 *             "vocIndex":3,
 *             "isCollected":false
 *         },
 *         {
 *             "Id":1464,
 *             "vocCode":"8a108cb7-42aa-d911-0142-ad81218a0092",
 *             "spelling":"thick",
 *             "meaning":"厚的;浓密的;粗的",
 *             "soundMarkUs":"[θɪk]",
 *             "soundMarkUk":"",
 *             "UnitId":0,
 *             "unitNbr":3,
 *             "lessonNbr":5,
 *             "fstClassId":8,
 *             "vocIndex":4,
 *             "isCollected":false
 *         },
 *         {
 *             "Id":1465,
 *             "vocCode":"8a108cb7-42aa-d911-0142-ad81218b0093",
 *             "spelling":"it",
 *             "meaning":"它",
 *             "soundMarkUs":"[ɪt]",
 *             "soundMarkUk":"",
 *             "UnitId":0,
 *             "unitNbr":3,
 *             "lessonNbr":5,
 *             "fstClassId":8,
 *             "vocIndex":5,
 *             "isCollected":false
 *         },
 *         {
 *             "Id":1466,
 *             "vocCode":"8a108cb7-42aa-d911-0142-ad81218b0094",
 *             "spelling":"sit",
 *             "meaning":"坐",
 *             "soundMarkUs":"[sɪt]",
 *             "soundMarkUk":"",
 *             "UnitId":0,
 *             "unitNbr":3,
 *             "lessonNbr":5,
 *             "fstClassId":8,
 *             "vocIndex":6,
 *             "isCollected":false
 *         },
 *         {
 *             "Id":1467,
 *             "vocCode":"8a108cb7-42aa-d911-0142-ad81218c0095",
 *             "spelling":"six",
 *             "meaning":"六",
 *             "soundMarkUs":"[sɪks]",
 *             "soundMarkUk":"",
 *             "UnitId":0,
 *             "unitNbr":3,
 *             "lessonNbr":5,
 *             "fstClassId":8,
 *             "vocIndex":7,
 *             "isCollected":false
 *         },
 *         {
 *             "Id":1468,
 *             "vocCode":"8a108cb7-42aa-d911-0142-ad81218c0096",
 *             "spelling":"fix",
 *             "meaning":"修理;固定",
 *             "soundMarkUs":"[fɪks]",
 *             "soundMarkUk":"",
 *             "UnitId":0,
 *             "unitNbr":3,
 *             "lessonNbr":5,
 *             "fstClassId":8,
 *             "vocIndex":8,
 *             "isCollected":false
 *         },
 *         {
 *             "Id":1469,
 *             "vocCode":"8a108cb7-42aa-d911-0142-ad81218d0097",
 *             "spelling":"mix",
 *             "meaning":"混合;搅拌",
 *             "soundMarkUs":"[mɪks]",
 *             "soundMarkUk":"",
 *             "UnitId":0,
 *             "unitNbr":3,
 *             "lessonNbr":5,
 *             "fstClassId":8,
 *             "vocIndex":9,
 *             "isCollected":false
 *         },
 *         {
 *             "Id":1470,
 *             "vocCode":"8a108cb7-42aa-d911-0142-ad81218d0099",
 *             "spelling":"with",
 *             "meaning":"和....",
 *             "soundMarkUs":"[wɪð]",
 *             "soundMarkUk":"",
 *             "UnitId":0,
 *             "unitNbr":3,
 *             "lessonNbr":5,
 *             "fstClassId":8,
 *             "vocIndex":10,
 *             "isCollected":false
 *         },
 *         {
 *             "Id":1471,
 *             "vocCode":"8a108cb7-42aa-d911-0142-ad81218e009a",
 *             "spelling":"him",
 *             "meaning":"他",
 *             "soundMarkUs":"[hɪm]",
 *             "soundMarkUk":"",
 *             "UnitId":0,
 *             "unitNbr":3,
 *             "lessonNbr":5,
 *             "fstClassId":8,
 *             "vocIndex":11,
 *             "isCollected":false
 *         },
 *         {
 *             "Id":1472,
 *             "vocCode":"8a108cb7-42aa-d911-0142-ad81218f009d",
 *             "spelling":"give",
 *             "meaning":"给出",
 *             "soundMarkUs":"[ɡɪv]",
 *             "soundMarkUk":"",
 *             "UnitId":0,
 *             "unitNbr":3,
 *             "lessonNbr":5,
 *             "fstClassId":8,
 *             "vocIndex":12,
 *             "isCollected":false
 *         },
 *         {
 *             "Id":1473,
 *             "vocCode":"8a108cb7-42aa-d911-0142-ad812190009e",
 *             "spelling":"dig",
 *             "meaning":"挖掘",
 *             "soundMarkUs":"[dɪɡ]",
 *             "soundMarkUk":"",
 *             "UnitId":0,
 *             "unitNbr":3,
 *             "lessonNbr":5,
 *             "fstClassId":8,
 *             "vocIndex":13,
 *             "isCollected":false
 *         },
 *         {
 *             "Id":1474,
 *             "vocCode":"8a108cb7-42aa-d911-0142-ad812190009f",
 *             "spelling":"pig",
 *             "meaning":"猪",
 *             "soundMarkUs":"[pɪɡ]",
 *             "soundMarkUk":"",
 *             "UnitId":0,
 *             "unitNbr":3,
 *             "lessonNbr":5,
 *             "fstClassId":8,
 *             "vocIndex":14,
 *             "isCollected":false
 *         },
 *         {
 *             "Id":1475,
 *             "vocCode":"8a108cb7-42aa-d911-0142-ad8121ac00a0",
 *             "spelling":"pin",
 *             "meaning":"大头针",
 *             "soundMarkUs":"[pɪn]",
 *             "soundMarkUk":"",
 *             "UnitId":0,
 *             "unitNbr":3,
 *             "lessonNbr":5,
 *             "fstClassId":8,
 *             "vocIndex":15,
 *             "isCollected":false
 *         },
 *         {
 *             "Id":1476,
 *             "vocCode":"8a108cb7-42aa-d911-0142-ad8121ac00a1",
 *             "spelling":"inn",
 *             "meaning":"旅馆;客栈",
 *             "soundMarkUs":"[ɪn]",
 *             "soundMarkUk":"",
 *             "UnitId":0,
 *             "unitNbr":3,
 *             "lessonNbr":5,
 *             "fstClassId":8,
 *             "vocIndex":16,
 *             "isCollected":false
 *         },
 *         {
 *             "Id":1477,
 *             "vocCode":"8a108cb7-42aa-d911-0142-ad8121ad00a2",
 *             "spelling":"ink",
 *             "meaning":"墨水",
 *             "soundMarkUs":"[ɪŋk]",
 *             "soundMarkUk":"",
 *             "UnitId":0,
 *             "unitNbr":3,
 *             "lessonNbr":5,
 *             "fstClassId":8,
 *             "vocIndex":17,
 *             "isCollected":false
 *         },
 *         {
 *             "Id":1478,
 *             "vocCode":"8a108cb7-42aa-d911-0142-ad8121ad00a3",
 *             "spelling":"pink",
 *             "meaning":"粉红色的",
 *             "soundMarkUs":"[pɪŋk]",
 *             "soundMarkUk":"",
 *             "UnitId":0,
 *             "unitNbr":3,
 *             "lessonNbr":5,
 *             "fstClassId":8,
 *             "vocIndex":18,
 *             "isCollected":false
 *         },
 *         {
 *             "Id":1479,
 *             "vocCode":"8a108cb7-42aa-d911-0142-ad8121ae00a4",
 *             "spelling":"link",
 *             "meaning":"联系;链接",
 *             "soundMarkUs":"[lɪŋk]",
 *             "soundMarkUk":"",
 *             "UnitId":0,
 *             "unitNbr":3,
 *             "lessonNbr":5,
 *             "fstClassId":8,
 *             "vocIndex":19,
 *             "isCollected":false
 *         },
 *         {
 *             "Id":1480,
 *             "vocCode":"8a108cb7-42aa-d911-0142-ad8121ae00a5",
 *             "spelling":"think",
 *             "meaning":"思考;认为",
 *             "soundMarkUs":"[θɪŋk]",
 *             "soundMarkUk":"",
 *             "UnitId":0,
 *             "unitNbr":3,
 *             "lessonNbr":5,
 *             "fstClassId":8,
 *             "vocIndex":20,
 *             "isCollected":false
 *         },
 *         {
 *             "Id":1481,
 *             "vocCode":"8a108cb7-42aa-d911-0142-ad8121ae00a6",
 *             "spelling":"bill",
 *             "meaning":"账单",
 *             "soundMarkUs":"[bɪl]",
 *             "soundMarkUk":"",
 *             "UnitId":0,
 *             "unitNbr":3,
 *             "lessonNbr":5,
 *             "fstClassId":8,
 *             "vocIndex":21,
 *             "isCollected":false
 *         },
 *         {
 *             "Id":1482,
 *             "vocCode":"8a108cb7-42aa-d911-0142-ad8121af00a7",
 *             "spelling":"hill",
 *             "meaning":"小山",
 *             "soundMarkUs":"[hɪl]",
 *             "soundMarkUk":"",
 *             "UnitId":0,
 *             "unitNbr":3,
 *             "lessonNbr":5,
 *             "fstClassId":8,
 *             "vocIndex":22,
 *             "isCollected":false
 *         },
 *         {
 *             "Id":1483,
 *             "vocCode":"8a108cb7-42aa-d911-0142-ad8121af00a8",
 *             "spelling":"kill",
 *             "meaning":"杀死",
 *             "soundMarkUs":"[kɪl]",
 *             "soundMarkUk":"",
 *             "UnitId":0,
 *             "unitNbr":3,
 *             "lessonNbr":5,
 *             "fstClassId":8,
 *             "vocIndex":23,
 *             "isCollected":false
 *         },
 *         {
 *             "Id":1484,
 *             "vocCode":"8a108cb7-42aa-d911-0142-ad8121b000a9",
 *             "spelling":"pill",
 *             "meaning":"药片;药丸",
 *             "soundMarkUs":"[pɪl]",
 *             "soundMarkUk":"",
 *             "UnitId":0,
 *             "unitNbr":3,
 *             "lessonNbr":5,
 *             "fstClassId":8,
 *             "vocIndex":24,
 *             "isCollected":false
 *         },
 *         {
 *             "Id":1485,
 *             "vocCode":"8a108cb7-42aa-d911-0142-ad8121b000aa",
 *             "spelling":"still",
 *             "meaning":"静止的;仍然",
 *             "soundMarkUs":"[stɪl]",
 *             "soundMarkUk":"",
 *             "UnitId":0,
 *             "unitNbr":3,
 *             "lessonNbr":5,
 *             "fstClassId":8,
 *             "vocIndex":25,
 *             "isCollected":false
 *         },
 *         {
 *             "Id":1486,
 *             "vocCode":"8a108cb7-42aa-d911-0142-ad8121b100ab",
 *             "spelling":"will",
 *             "meaning":"将要;决心;遗产",
 *             "soundMarkUs":"[wɪl]",
 *             "soundMarkUk":"",
 *             "UnitId":0,
 *             "unitNbr":3,
 *             "lessonNbr":5,
 *             "fstClassId":8,
 *             "vocIndex":26,
 *             "isCollected":false
 *         },
 *         {
 *             "Id":1487,
 *             "vocCode":"8a108cb7-42aa-d911-0142-ad8121b100ac",
 *             "spelling":"milk",
 *             "meaning":"牛奶;挤牛奶",
 *             "soundMarkUs":"[mɪlk]",
 *             "soundMarkUk":"",
 *             "UnitId":0,
 *             "unitNbr":3,
 *             "lessonNbr":5,
 *             "fstClassId":8,
 *             "vocIndex":27,
 *             "isCollected":false
 *         },
 *         {
 *             "Id":1488,
 *             "vocCode":"8a108cb7-42aa-d911-0142-ad8121b300ae",
 *             "spelling":"film",
 *             "meaning":"电影;拍电影",
 *             "soundMarkUs":"[fɪlm]",
 *             "soundMarkUk":"",
 *             "UnitId":0,
 *             "unitNbr":3,
 *             "lessonNbr":5,
 *             "fstClassId":8,
 *             "vocIndex":28,
 *             "isCollected":false
 *         },
 *         {
 *             "Id":1489,
 *             "vocCode":"8a108cb7-42aa-d911-0142-ad8121b300af",
 *             "spelling":"kiss",
 *             "meaning":"吻",
 *             "soundMarkUs":"[kɪs]",
 *             "soundMarkUk":"",
 *             "UnitId":0,
 *             "unitNbr":3,
 *             "lessonNbr":5,
 *             "fstClassId":8,
 *             "vocIndex":29,
 *             "isCollected":false
 *         },
 *         {
 *             "Id":1490,
 *             "vocCode":"8a108cb7-42aa-d911-0142-ad8121b400b0",
 *             "spelling":"Miss",
 *             "meaning":"小姐;女士",
 *             "soundMarkUs":"[mɪs]",
 *             "soundMarkUk":"",
 *             "UnitId":0,
 *             "unitNbr":3,
 *             "lessonNbr":5,
 *             "fstClassId":8,
 *             "vocIndex":30,
 *             "isCollected":false
 *         }
 *     ],
 *     "success":true,
 *     "condition":0,
 *     "msg":"搞定！",
 *     "studytoken":"c06616b5-4ca2-432a-ab94-50261c50baca",
 *     "bookInfo":{
 *         "Id":5,
 *         "moduleCode":"8a108cb7-42ad-314f-0142-ad33a0a70001",
 *         "bookName":"零基础入门拼读",
 *         "coverImageUrl":"",
 *         "totalUnitNbr":17,
 *         "outDate":false,
 *         "startFrom":0,
 *         "studyMode":0
 *     },
 *     "studyPos":{
 *         "Id":513527,
 *         "studyPositionCode":"e2b18f0b-bdd2-4413-9dc1-d305d3efaf7e",
 *         "positionType":0,
 *         "vocCode":"8a108cb7-42aa-d911-0142-ad81218b0093",
 *         "unitNbr":3,
 *         "isCurrentPos":true,
 *         "isFinished":0,
 *         "spelling":"it",
 *         "isAllFinished":false,
 *         "IsTested":0,
 *         "Status":0
 *     },
 *     "totalNbr":0
 * }
 * Created by John on 2018/6/2 0002.
 */
public class RichUnitStudyVO extends BaseVO {

    private String studyToken;
    private List<UnitStudyVO> data;
    private BookStudyVO bookInfo;
    private CurrentStudyPositionVO studyPos;

    public String getStudyToken() {
        return studyToken;
    }

    public void setStudyToken(String studyToken) {
        this.studyToken = studyToken;
    }

    public List<UnitStudyVO> getData() {
        return data;
    }

    public void setData(List<UnitStudyVO> data) {
        this.data = data;
    }

    public BookStudyVO getBookInfo() {
        return bookInfo;
    }

    public void setBookInfo(BookStudyVO bookInfo) {
        this.bookInfo = bookInfo;
    }

    public CurrentStudyPositionVO getStudyPos() {
        return studyPos;
    }

    public void setStudyPos(CurrentStudyPositionVO studyPos) {
        this.studyPos = studyPos;
    }
}
