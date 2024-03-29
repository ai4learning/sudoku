from flask import Flask, render_template, jsonify
import json

app = Flask(__name__)


@app.route('/api/Ajax/AjaxGetErrorUnit',methods=['GET','POST'])
def ajax_get_error_unit():
    data = {"data": [{"Id": 1391, "vocCode": "8a108cb7-42aa-d911-0142-ad812043003a", "spelling": "gap", "soundMark": "",
                      "meaning": "缺口;", "soundMarkUs": "[ɡæp]", "UnitId": 0, "unitNbr": 1, "vocIndex": 0,
                      "isCollected": false},
                     {"Id": 1392, "vocCode": "8a108cb7-42aa-d911-0142-ad812043003b", "spelling": "map", "soundMark": "",
                      "meaning": "地图", "soundMarkUs": "[mæp]", "UnitId": 0, "unitNbr": 1, "vocIndex": 1,
                      "isCollected": false},
                     {"Id": 1393, "vocCode": "8a108cb7-42aa-d911-0142-ad81208d003c", "spelling": "nap", "soundMark": "",
                      "meaning": "午睡;打盹", "soundMarkUs": "[næp]", "UnitId": 0, "unitNbr": 1, "vocIndex": 2,
                      "isCollected": false},
                     {"Id": 1411, "vocCode": "8a108cb7-42aa-d911-0142-ad8120d50055", "spelling": "pan", "soundMark": "",
                      "meaning": "平底锅", "soundMarkUs": "[pæn]", "UnitId": 0, "unitNbr": 1, "vocIndex": 3,
                      "isCollected": false},
                     {"Id": 1415, "vocCode": "8a108cb7-42aa-d911-0142-ad8120d70059", "spelling": "beg", "soundMark": "",
                      "meaning": "请求", "soundMarkUs": "[beɡ]", "UnitId": 0, "unitNbr": 1, "vocIndex": 4,
                      "isCollected": false},
                     {"Id": 1417, "vocCode": "8a108cb7-42aa-d911-0142-ad8120d8005b", "spelling": "bed", "soundMark": "",
                      "meaning": "床", "soundMarkUs": "[bed]", "UnitId": 0, "unitNbr": 1, "vocIndex": 5,
                      "isCollected": false},
                     {"Id": 1418, "vocCode": "8a108cb7-42aa-d911-0142-ad8120d8005c", "spelling": "fed", "soundMark": "",
                      "meaning": "喂养（过去式）", "soundMarkUs": "[fed]", "UnitId": 0, "unitNbr": 1, "vocIndex": 6,
                      "isCollected": false},
                     {"Id": 1422, "vocCode": "8a108cb7-42aa-d911-0142-ad8120e30060", "spelling": "wet", "soundMark": "",
                      "meaning": "湿的", "soundMarkUs": "[wet]", "UnitId": 0, "unitNbr": 1, "vocIndex": 7,
                      "isCollected": false},
                     {"Id": 1426, "vocCode": "8a108cb7-42aa-d911-0142-ad81210f0064", "spelling": "dress",
                      "soundMark": "",
                      "meaning": "n.服装;女装;v.打扮;穿衣;", "soundMarkUs": "[dres]", "UnitId": 0, "unitNbr": 1, "vocIndex": 8,
                      "isCollected": false},
                     {"Id": 1427, "vocCode": "8a108cb7-42aa-d911-0142-ad8121100065", "spelling": "bell",
                      "soundMark": "",
                      "meaning": "钟;", "soundMarkUs": "[bel]", "UnitId": 0, "unitNbr": 1, "vocIndex": 9,
                      "isCollected": false},
                     {"Id": 1430, "vocCode": "8a108cb7-42aa-d911-0142-ad812113006a", "spelling": "tell",
                      "soundMark": "",
                      "meaning": "告诉", "soundMarkUs": "[tel]", "UnitId": 0, "unitNbr": 1, "vocIndex": 10,
                      "isCollected": false},
                     {"Id": 1438, "vocCode": "8a108cb7-42aa-d911-0142-ad81211c0072", "spelling": "spend",
                      "soundMark": "",
                      "meaning": "花费;度过", "soundMarkUs": "[spend]", "UnitId": 0, "unitNbr": 1, "vocIndex": 11,
                      "isCollected": false},
                     {"Id": 1439, "vocCode": "8a108cb7-42aa-d911-0142-ad81211d0073", "spelling": "spent",
                      "soundMark": "",
                      "meaning": "花费;度过（过去式）", "soundMarkUs": "[spent]", "UnitId": 0, "unitNbr": 1, "vocIndex": 12,
                      "isCollected": false},
                     {"Id": 1440, "vocCode": "8a108cb7-42aa-d911-0142-ad81211e0074", "spelling": "fence",
                      "soundMark": "",
                      "meaning": "围栏篱笆;购买赃物的人", "soundMarkUs": "[fens]", "UnitId": 0, "unitNbr": 1, "vocIndex": 13,
                      "isCollected": false},
                     {"Id": 1441, "vocCode": "8a108cb7-42aa-d911-0142-ad81211e0075", "spelling": "hence",
                      "soundMark": "",
                      "meaning": "因此;从此后", "soundMarkUs": "[hens]", "UnitId": 0, "unitNbr": 1, "vocIndex": 14,
                      "isCollected": false},
                     {"Id": 1442, "vocCode": "8a108cb7-42aa-d911-0142-ad81211f0076", "spelling": "then",
                      "soundMark": "",
                      "meaning": "adv.那么;然后;当时;而且", "soundMarkUs": "[ðen]", "UnitId": 0, "unitNbr": 1, "vocIndex": 15,
                      "isCollected": false},
                     {"Id": 1443, "vocCode": "8a108cb7-42aa-d911-0142-ad8121200077", "spelling": "pen", "soundMark": "",
                      "meaning": "钢笔", "soundMarkUs": "[pen]", "UnitId": 0, "unitNbr": 1, "vocIndex": 16,
                      "isCollected": false},
                     {"Id": 1449, "vocCode": "8a108cb7-42aa-d911-0142-ad81213e0080", "spelling": "lent",
                      "soundMark": "",
                      "meaning": "借出（过去式）;（基督教）打斋节", "soundMarkUs": "[lent]", "UnitId": 0, "unitNbr": 1, "vocIndex": 17,
                      "isCollected": false},
                     {"Id": 1450, "vocCode": "8a108cb7-42aa-d911-0142-ad81213f0081", "spelling": "mind",
                      "soundMark": "",
                      "meaning": "头脑;精神;心;理智", "soundMarkUs": "[maɪnd]", "UnitId": 0, "unitNbr": 1, "vocIndex": 18,
                      "isCollected": false},
                     {"Id": 1451, "vocCode": "8a108cb7-42aa-d911-0142-ad81213f0082", "spelling": "rent",
                      "soundMark": "",
                      "meaning": "租借租用;租金", "soundMarkUs": "[rent]", "UnitId": 0, "unitNbr": 1, "vocIndex": 19,
                      "isCollected": false},
                     {"Id": 1452, "vocCode": "8a108cb7-42aa-d911-0142-ad8121400083", "spelling": "left",
                      "soundMark": "",
                      "meaning": "左边;离开（过去式）", "soundMarkUs": "[left]", "UnitId": 0, "unitNbr": 1, "vocIndex": 20,
                      "isCollected": false},
                     {"Id": 1453, "vocCode": "8a108cb7-42aa-d911-0142-ad8121430084", "spelling": "theft",
                      "soundMark": "",
                      "meaning": "n. 偷盗，偷窃; 被盗，失窃; 盗窃之物，赃物; 失窃案例（注意：不是thief）", "soundMarkUs": "[θeft]", "UnitId": 0,
                      "unitNbr": 1,
                      "vocIndex": 21, "isCollected": false},
                     {"Id": 1511, "vocCode": "8a108cb7-42aa-d911-0142-ad8121f200cf", "spelling": "cape",
                      "soundMark": "",
                      "meaning": "海角;披肩", "soundMarkUs": "[keɪp]", "UnitId": 0, "unitNbr": 1, "vocIndex": 22,
                      "isCollected": false},
                     {"Id": 1512, "vocCode": "8a108cb7-42aa-d911-0142-ad8121f300d0", "spelling": "gape",
                      "soundMark": "",
                      "meaning": "裂口;张嘴;打哈欠", "soundMarkUs": "[ɡeɪp]", "UnitId": 0, "unitNbr": 1, "vocIndex": 23,
                      "isCollected": false},
                     {"Id": 1513, "vocCode": "8a108cb7-42aa-d911-0142-ad8121f300d1", "spelling": "tape",
                      "soundMark": "",
                      "meaning": "磁带;录音;用胶布粘", "soundMarkUs": "[teɪp]", "UnitId": 0, "unitNbr": 1, "vocIndex": 24,
                      "isCollected": false},
                     {"Id": 1514, "vocCode": "8a108cb7-42aa-d911-0142-ad8121f400d2", "spelling": "blame",
                      "soundMark": "",
                      "meaning": "责备", "soundMarkUs": "[bleɪm]", "UnitId": 0, "unitNbr": 1, "vocIndex": 25,
                      "isCollected": false},
                     {"Id": 1515, "vocCode": "8a108cb7-42aa-d911-0142-ad8121f400d3", "spelling": "came",
                      "soundMark": "",
                      "meaning": "来到（过去式）", "soundMarkUs": "[kʌm]", "UnitId": 0, "unitNbr": 1, "vocIndex": 26,
                      "isCollected": false},
                     {"Id": 1516, "vocCode": "8a108cb7-42aa-d911-0142-ad8121f500d4", "spelling": "dame",
                      "soundMark": "",
                      "meaning": "夫人（女爵）", "soundMarkUs": "[deɪm]", "UnitId": 0, "unitNbr": 1, "vocIndex": 27,
                      "isCollected": false},
                     {"Id": 1518, "vocCode": "8a108cb7-42aa-d911-0142-ad8121f500d6", "spelling": "game",
                      "soundMark": "",
                      "meaning": "游戏;赌博", "soundMarkUs": "[ɡeɪm]", "UnitId": 0, "unitNbr": 1, "vocIndex": 28,
                      "isCollected": false},
                     {"Id": 1522, "vocCode": "8a108cb7-42aa-d911-0142-ad8121f700da", "spelling": "bake",
                      "soundMark": "",
                      "meaning": "烘烤", "soundMarkUs": "[beɪk]", "UnitId": 0, "unitNbr": 1, "vocIndex": 29,
                      "isCollected": false},
                     {"Id": 1523, "vocCode": "8a108cb7-42aa-d911-0142-ad8121f800db", "spelling": "cake",
                      "soundMark": "",
                      "meaning": "蛋糕", "soundMarkUs": "[keɪk]", "UnitId": 0, "unitNbr": 1, "vocIndex": 30,
                      "isCollected": false},
                     {"Id": 1524, "vocCode": "8a108cb7-42aa-d911-0142-ad81221100dc", "spelling": "fake",
                      "soundMark": "",
                      "meaning": "假的;伪造的", "soundMarkUs": "[feɪk]", "UnitId": 0, "unitNbr": 1, "vocIndex": 31,
                      "isCollected": false},
                     {"Id": 1525, "vocCode": "8a108cb7-42aa-d911-0142-ad81221100dd", "spelling": "lake",
                      "soundMark": "",
                      "meaning": "湖泊", "soundMarkUs": "[leɪk]", "UnitId": 0, "unitNbr": 1, "vocIndex": 32,
                      "isCollected": false},
                     {"Id": 1531, "vocCode": "8a108cb7-42aa-d911-0142-ad81221400e3", "spelling": "ate", "soundMark": "",
                      "meaning": "吃(过去式）", "soundMarkUs": "[et]", "UnitId": 0, "unitNbr": 1, "vocIndex": 33,
                      "isCollected": false},
                     {"Id": 1532, "vocCode": "8a108cb7-42aa-d911-0142-ad81221400e4", "spelling": "late",
                      "soundMark": "",
                      "meaning": "晚的;迟到", "soundMarkUs": "[leɪt]", "UnitId": 0, "unitNbr": 1, "vocIndex": 34,
                      "isCollected": false},
                     {"Id": 1533, "vocCode": "8a108cb7-42aa-d911-0142-ad81221500e5", "spelling": "gate",
                      "soundMark": "",
                      "meaning": "大门", "soundMarkUs": "[ɡeɪt]", "UnitId": 0, "unitNbr": 1, "vocIndex": 35,
                      "isCollected": false},
                     {"Id": 1534, "vocCode": "8a108cb7-42aa-d911-0142-ad81221500e6", "spelling": "fate",
                      "soundMark": "",
                      "meaning": "命运", "soundMarkUs": "[feɪt]", "UnitId": 0, "unitNbr": 1, "vocIndex": 36,
                      "isCollected": false},
                     {"Id": 1535, "vocCode": "8a108cb7-42aa-d911-0142-ad81221600e7", "spelling": "mate",
                      "soundMark": "",
                      "meaning": "伙伴;同事", "soundMarkUs": "[meɪt]", "UnitId": 0, "unitNbr": 1, "vocIndex": 37,
                      "isCollected": false},
                     {"Id": 1540, "vocCode": "8a108cb7-42aa-d911-0142-ad81221900ec", "spelling": "paste",
                      "soundMark": "",
                      "meaning": "面团;粘贴", "soundMarkUs": "[peɪst]", "UnitId": 0, "unitNbr": 1, "vocIndex": 38,
                      "isCollected": false},
                     {"Id": 1541, "vocCode": "8a108cb7-42aa-d911-0142-ad81221a00ed", "spelling": "vase",
                      "soundMark": "",
                      "meaning": "花瓶", "soundMarkUs": "[vɑːz]", "UnitId": 0, "unitNbr": 1, "vocIndex": 39,
                      "isCollected": false},
                     {"Id": 1543, "vocCode": "8a108cb7-42aa-d911-0142-ad81221b00ef", "spelling": "base",
                      "soundMark": "",
                      "meaning": "基地;基础", "soundMarkUs": "[beɪs]", "UnitId": 0, "unitNbr": 1, "vocIndex": 40,
                      "isCollected": false},
                     {"Id": 1547, "vocCode": "8a108cb7-42aa-d911-0142-ad81224300fe", "spelling": "knee",
                      "soundMark": "",
                      "meaning": "膝盖", "soundMarkUs": "[niː]", "UnitId": 0, "unitNbr": 1, "vocIndex": 41,
                      "isCollected": false},
                     {"Id": 1548, "vocCode": "8a108cb7-42aa-d911-0142-ad81224300ff", "spelling": "sweets",
                      "soundMark": "",
                      "meaning": "糖果", "soundMarkUs": "[swiːts]", "UnitId": 0, "unitNbr": 1, "vocIndex": 42,
                      "isCollected": false},
                     {"Id": 1549, "vocCode": "8a108cb7-42aa-d911-0142-ad8122440100", "spelling": "green",
                      "soundMark": "",
                      "meaning": "绿色的", "soundMarkUs": "[ɡriːn]", "UnitId": 0, "unitNbr": 1, "vocIndex": 43,
                      "isCollected": false},
                     {"Id": 1550, "vocCode": "8a108cb7-42aa-d911-0142-ad8122440101", "spelling": "queen",
                      "soundMark": "",
                      "meaning": "女王", "soundMarkUs": "[kwiːn]", "UnitId": 0, "unitNbr": 1, "vocIndex": 44,
                      "isCollected": false},
                     {"Id": 1552, "vocCode": "8a108cb7-42aa-d911-0142-ad8122450103", "spelling": "bike",
                      "soundMark": "",
                      "meaning": "自行车;骑自行车", "soundMarkUs": "[baɪk]", "UnitId": 0, "unitNbr": 1, "vocIndex": 45,
                      "isCollected": false},
                     {"Id": 1553, "vocCode": "8a108cb7-42aa-d911-0142-ad81225c0104", "spelling": "hike",
                      "soundMark": "",
                      "meaning": "徒步旅游", "soundMarkUs": "[haɪk]", "UnitId": 0, "unitNbr": 1, "vocIndex": 46,
                      "isCollected": false},
                     {"Id": 1555, "vocCode": "8a108cb7-42aa-d911-0142-ad81225e0107", "spelling": "bite",
                      "soundMark": "",
                      "meaning": "咬;钓饵", "soundMarkUs": "[baɪt]", "UnitId": 0, "unitNbr": 1, "vocIndex": 47,
                      "isCollected": false},
                     {"Id": 1556, "vocCode": "8a108cb7-42aa-d911-0142-ad81225e0108", "spelling": "kite",
                      "soundMark": "",
                      "meaning": "风筝", "soundMarkUs": "[kaɪt]", "UnitId": 0, "unitNbr": 1, "vocIndex": 48,
                      "isCollected": false},
                     {"Id": 1558, "vocCode": "8a108cb7-42aa-d911-0142-ad81225f010a", "spelling": "fine",
                      "soundMark": "",
                      "meaning": "好的;罚款", "soundMarkUs": "[faɪn]", "UnitId": 0, "unitNbr": 1, "vocIndex": 49,
                      "isCollected": false}], "success": true, "condition": 0, "msg": "搞定！",
            "studytoken": "af593e2c-36d6-4d4d-8860-685800a0dec0",
            "bookInfo": {"Id": 0, "moduleCode": "00000000-0000-0000-0000-000000000000", "bookName": "错词强化学...",
                         "totalUnitNbr": 0,
                         "outDate": false, "startFrom": 0, "studyMode": 0}, "totalNbr": 0}
    return jsonify(data)


@app.route('/api/Ajax/AjaxGetUserBook',methods=['GET','POST'])
def ajax_get_user_book():
    data = {"books": [
        {"Id": 5, "moduleCode": "8a108cb7-42ad-314f-0142-ad33a0a70001", "bookName": "零基础入门拼读", "coverImageUrl": "",
         "totalUnitNbr": 17,
         "currentPosition": {"Id": 510267, "studyPositionCode": "8ccdb66e-4daf-4ea0-9a99-83020a33d1df",
                             "positionType": 2, "vocCode": "8a108cb7-42aa-d911-0142-ad8120d60057", "unitNbr": 1,
                             "isCurrentPos": true, "isFinished": 1, "spelling": "van", "isAllFinished": true,
                             "IsTested": 0, "Status": 0}, "currentStudyBook": true, "outDate": false,
         "unitType": "Unit", "startFrom": 1, "studyMode": 1}], "condition": 0, "totalNbr": 0, "msg": "完成加载",
        "userState": 2, "totalLoginTimes": 0, "success": true}
    # return jsonify(data, ensure_ascii=False)
    return jsonify(data)


@app.route('/api/Ajax/AjaxGetUnit',methods=['GET','POST'])
def ajax_get_unit():
    data = {"data": [
        {"Id": 1381, "vocCode": "8a108cb7-42aa-d911-0142-ad8120300028", "spelling": "at", "meaning": "在….  在…的时候",
         "soundMarkUs": "[æt, ət]", "soundMarkUk": "", "UnitId": 0, "unitNbr": 1, "lessonNbr": 5, "fstClassId": 8,
         "vocIndex": 0, "isCollected": false},
        {"Id": 1382, "vocCode": "8a108cb7-42aa-d911-0142-ad8120310029", "spelling": "bat", "meaning": "蝙蝠;球拍",
         "soundMarkUs": "[bæt]", "soundMarkUk": "", "UnitId": 0, "unitNbr": 1, "lessonNbr": 5, "fstClassId": 8,
         "vocIndex": 1, "isCollected": false},
        {"Id": 1383, "vocCode": "8a108cb7-42aa-d911-0142-ad812031002a", "spelling": "cat", "meaning": "猫",
         "soundMarkUs": "[kæt]", "soundMarkUk": "", "UnitId": 0, "unitNbr": 1, "lessonNbr": 5, "fstClassId": 8,
         "vocIndex": 2, "isCollected": false},
        {"Id": 1384, "vocCode": "8a108cb7-42aa-d911-0142-ad812032002b", "spelling": "fat", "meaning": "肥的;油腻的",
         "soundMarkUs": "[fæt]", "soundMarkUk": "", "UnitId": 0, "unitNbr": 1, "lessonNbr": 5, "fstClassId": 8,
         "vocIndex": 3, "isCollected": false},
        {"Id": 1385, "vocCode": "8a108cb7-42aa-d911-0142-ad8120350030", "spelling": "hat", "meaning": "帽子，同义词：cap",
         "soundMarkUs": "[hæt]", "soundMarkUk": "", "UnitId": 0, "unitNbr": 1, "lessonNbr": 5, "fstClassId": 8,
         "vocIndex": 4, "isCollected": false},
        {"Id": 1386, "vocCode": "8a108cb7-42aa-d911-0142-ad8120380034", "spelling": "pat", "meaning": "轻拍;拍打",
         "soundMarkUs": "[pæt]", "soundMarkUk": "", "UnitId": 0, "unitNbr": 1, "lessonNbr": 5, "fstClassId": 8,
         "vocIndex": 5, "isCollected": false},
        {"Id": 1387, "vocCode": "8a108cb7-42aa-d911-0142-ad8120380035", "spelling": "sat", "meaning": "坐下（过去式）",
         "soundMarkUs": "[sæt]", "soundMarkUk": "", "UnitId": 0, "unitNbr": 1, "lessonNbr": 5, "fstClassId": 8,
         "vocIndex": 6, "isCollected": false},
        {"Id": 1388, "vocCode": "8a108cb7-42aa-d911-0142-ad8120410036", "spelling": "mat", "meaning": "小地垫;席子",
         "soundMarkUs": "[mæt]", "soundMarkUk": "", "UnitId": 0, "unitNbr": 1, "lessonNbr": 5, "fstClassId": 8,
         "vocIndex": 7, "isCollected": false},
        {"Id": 1389, "vocCode": "8a108cb7-42aa-d911-0142-ad8120420038", "spelling": "can", "meaning": "能够;罐",
         "soundMarkUs": "[kæn]", "soundMarkUk": "", "UnitId": 0, "unitNbr": 1, "lessonNbr": 5, "fstClassId": 8,
         "vocIndex": 8, "isCollected": false},
        {"Id": 1390, "vocCode": "8a108cb7-42aa-d911-0142-ad8120420039", "spelling": "cap", "meaning": "帽子",
         "soundMarkUs": "[kæp]", "soundMarkUk": "", "UnitId": 0, "unitNbr": 1, "lessonNbr": 5, "fstClassId": 8,
         "vocIndex": 9, "isCollected": false},
        {"Id": 1391, "vocCode": "8a108cb7-42aa-d911-0142-ad812043003a", "spelling": "gap", "meaning": "缺口;",
         "soundMarkUs": "[ɡæp]", "soundMarkUk": "", "UnitId": 0, "unitNbr": 1, "lessonNbr": 5, "fstClassId": 8,
         "vocIndex": 10, "isCollected": false},
        {"Id": 1392, "vocCode": "8a108cb7-42aa-d911-0142-ad812043003b", "spelling": "map", "meaning": "地图",
         "soundMarkUs": "[mæp]", "soundMarkUk": "", "UnitId": 0, "unitNbr": 1, "lessonNbr": 5, "fstClassId": 8,
         "vocIndex": 11, "isCollected": false},
        {"Id": 1393, "vocCode": "8a108cb7-42aa-d911-0142-ad81208d003c", "spelling": "nap", "meaning": "午睡;打盹",
         "soundMarkUs": "[næp]", "soundMarkUk": "", "UnitId": 0, "unitNbr": 1, "lessonNbr": 5, "fstClassId": 8,
         "vocIndex": 12, "isCollected": false},
        {"Id": 1394, "vocCode": "8a108cb7-42aa-d911-0142-ad81208d003d", "spelling": "rap", "meaning": "说唱，严厉批评",
         "soundMarkUs": "[ræp]", "soundMarkUk": "", "UnitId": 0, "unitNbr": 1, "lessonNbr": 5, "fstClassId": 8,
         "vocIndex": 13, "isCollected": false},
        {"Id": 1395, "vocCode": "8a108cb7-42aa-d911-0142-ad81208e003e", "spelling": "tap", "meaning": "栓;轻敲",
         "soundMarkUs": "[tæp]", "soundMarkUk": "", "UnitId": 0, "unitNbr": 1, "lessonNbr": 5, "fstClassId": 8,
         "vocIndex": 14, "isCollected": false},
        {"Id": 1396, "vocCode": "8a108cb7-42aa-d911-0142-ad81208e003f", "spelling": "wap", "meaning": "瓦片;无线上网协议",
         "soundMarkUs": "[wæp]", "soundMarkUk": "", "UnitId": 0, "unitNbr": 1, "lessonNbr": 5, "fstClassId": 8,
         "vocIndex": 15, "isCollected": false},
        {"Id": 1397, "vocCode": "8a108cb7-42aa-d911-0142-ad81208f0040", "spelling": "bag", "meaning": "包;袋",
         "soundMarkUs": "[bæɡ]", "soundMarkUk": "", "UnitId": 0, "unitNbr": 1, "lessonNbr": 5, "fstClassId": 8,
         "vocIndex": 16, "isCollected": false},
        {"Id": 1398, "vocCode": "8a108cb7-42aa-d911-0142-ad8120900042", "spelling": "hand", "meaning": "手;递交",
         "soundMarkUs": "[hænd]", "soundMarkUk": "", "UnitId": 0, "unitNbr": 1, "lessonNbr": 5, "fstClassId": 8,
         "vocIndex": 17, "isCollected": false},
        {"Id": 1399, "vocCode": "8a108cb7-42aa-d911-0142-ad8120900043", "spelling": "thank", "meaning": "感谢;谢谢",
         "soundMarkUs": "[θæŋk]", "soundMarkUk": "", "UnitId": 0, "unitNbr": 1, "lessonNbr": 5, "fstClassId": 8,
         "vocIndex": 18, "isCollected": false},
        {"Id": 1400, "vocCode": "8a108cb7-42aa-d911-0142-ad8120910044", "spelling": "pack", "meaning": "包裹;包装",
         "soundMarkUs": "[pæk]", "soundMarkUk": "", "UnitId": 0, "unitNbr": 1, "lessonNbr": 5, "fstClassId": 8,
         "vocIndex": 19, "isCollected": false},
        {"Id": 1401, "vocCode": "8a108cb7-42aa-d911-0142-ad8120930047", "spelling": "camp", "meaning": "营地",
         "soundMarkUs": "[kæmp]", "soundMarkUk": "", "UnitId": 0, "unitNbr": 1, "lessonNbr": 5, "fstClassId": 8,
         "vocIndex": 20, "isCollected": false},
        {"Id": 1402, "vocCode": "8a108cb7-42aa-d911-0142-ad812094004a", "spelling": "black", "meaning": "黑色的",
         "soundMarkUs": "[blæk]", "soundMarkUk": "", "UnitId": 0, "unitNbr": 1, "lessonNbr": 5, "fstClassId": 8,
         "vocIndex": 21, "isCollected": false},
        {"Id": 1403, "vocCode": "8a108cb7-42aa-d911-0142-ad81209d004b", "spelling": "dad", "meaning": "爹哋",
         "soundMarkUs": "[dæd]", "soundMarkUk": "", "UnitId": 0, "unitNbr": 1, "lessonNbr": 5, "fstClassId": 8,
         "vocIndex": 22, "isCollected": false},
        {"Id": 1404, "vocCode": "8a108cb7-42aa-d911-0142-ad81209d004c", "spelling": "and", "meaning": "和;又",
         "soundMarkUs": "[ənd, ænd]", "soundMarkUk": "", "UnitId": 0, "unitNbr": 1, "lessonNbr": 5, "fstClassId": 8,
         "vocIndex": 23, "isCollected": false},
        {"Id": 1405, "vocCode": "8a108cb7-42aa-d911-0142-ad81209e004e", "spelling": "land", "meaning": "土地;着陆;降落",
         "soundMarkUs": "[lænd]", "soundMarkUk": "", "UnitId": 0, "unitNbr": 1, "lessonNbr": 5, "fstClassId": 8,
         "vocIndex": 24, "isCollected": false},
        {"Id": 1406, "vocCode": "8a108cb7-42aa-d911-0142-ad81209f004f", "spelling": "sand", "meaning": "沙子 沙地",
         "soundMarkUs": "[sænd]", "soundMarkUk": "", "UnitId": 0, "unitNbr": 1, "lessonNbr": 5, "fstClassId": 8,
         "vocIndex": 25, "isCollected": false},
        {"Id": 1407, "vocCode": "8a108cb7-42aa-d911-0142-ad8120d20051", "spelling": "ladder", "meaning": "楼梯",
         "soundMarkUs": "['lædə(r)]", "soundMarkUk": "", "UnitId": 0, "unitNbr": 1, "lessonNbr": 5, "fstClassId": 8,
         "vocIndex": 26, "isCollected": false},
        {"Id": 1408, "vocCode": "8a108cb7-42aa-d911-0142-ad8120d30052", "spelling": "ban", "meaning": "禁止",
         "soundMarkUs": "[bæn]", "soundMarkUk": "", "UnitId": 0, "unitNbr": 1, "lessonNbr": 5, "fstClassId": 8,
         "vocIndex": 27, "isCollected": false},
        {"Id": 1409, "vocCode": "8a108cb7-42aa-d911-0142-ad8120d30053", "spelling": "fan", "meaning": "电扇;粉丝",
         "soundMarkUs": "[fæn]", "soundMarkUk": "", "UnitId": 0, "unitNbr": 1, "lessonNbr": 5, "fstClassId": 8,
         "vocIndex": 28, "isCollected": false},
        {"Id": 1410, "vocCode": "8a108cb7-42aa-d911-0142-ad8120d40054", "spelling": "man", "meaning": "人类;男人",
         "soundMarkUs": "[mæn]", "soundMarkUk": "", "UnitId": 0, "unitNbr": 1, "lessonNbr": 5, "fstClassId": 8,
         "vocIndex": 29, "isCollected": false},
        {"Id": 1411, "vocCode": "8a108cb7-42aa-d911-0142-ad8120d50055", "spelling": "pan", "meaning": "平底锅",
         "soundMarkUs": "[pæn]", "soundMarkUk": "", "UnitId": 0, "unitNbr": 1, "lessonNbr": 5, "fstClassId": 8,
         "vocIndex": 30, "isCollected": false},
        {"Id": 1412, "vocCode": "8a108cb7-42aa-d911-0142-ad8120d50056", "spelling": "ran", "meaning": "跑动（过去式）",
         "soundMarkUs": "[ræn]", "soundMarkUk": "", "UnitId": 0, "unitNbr": 1, "lessonNbr": 5, "fstClassId": 8,
         "vocIndex": 31, "isCollected": false},
        {"Id": 1413, "vocCode": "8a108cb7-42aa-d911-0142-ad8120d60057", "spelling": "van", "meaning": "小货车;用车搬运",
         "soundMarkUs": "[væn]", "soundMarkUk": "", "UnitId": 0, "unitNbr": 1, "lessonNbr": 5, "fstClassId": 8,
         "vocIndex": 32, "isCollected": false}], "success": true, "condition": 0, "msg": "搞定！",
        "studytoken": "4848df68-2593-42c1-bc6b-f881ccaaacca",
        "bookInfo": {"Id": 5, "moduleCode": "8a108cb7-42ad-314f-0142-ad33a0a70001", "bookName": "零基础入门拼读",
                     "coverImageUrl": "", "totalUnitNbr": 17, "outDate": false, "startFrom": 0, "studyMode": 0},
        "studyPos": {"Id": 0, "isCurrentPos": false, "isAllFinished": false, "IsTested": 0, "Status": 0},
        "totalNbr": 0}
    return jsonify(data)


@app.route('/api/Ajax/AjaxLoadCourse',methods=['GET','POST'])
def ajax_load_course():
    data = {"books": [
        {"Id": 5, "moduleCode": "8a108cb7-42ad-314f-0142-ad33a0a70001", "bookName": "零基础入门拼读", "coverImageUrl": "",
         "bookState": 1, "bookType": 1, "bookPrice": 0.0000, "currentStudyBook": false, "difficultLevel": 1,
         "introduce": "0基础4个小时训练掌握拼读", "totalUnitNbr": 17, "CourseUnits": [
            {"Id": 38, "moduleCode": "8a108cb7-42ad-314f-0142-ad33a0a70001", "Unit": "Unit1", "IsFinished": 2,
             "IsTested": 1, "unitNbr": 1},
            {"Id": 39, "moduleCode": "8a108cb7-42ad-314f-0142-ad33a0a70001", "Unit": "Unit2", "IsFinished": 2,
             "IsTested": 1, "unitNbr": 2},
            {"Id": 40, "moduleCode": "8a108cb7-42ad-314f-0142-ad33a0a70001", "Unit": "Unit3", "IsFinished": 2,
             "IsTested": 1, "unitNbr": 3},
            {"Id": 41, "moduleCode": "8a108cb7-42ad-314f-0142-ad33a0a70001", "Unit": "Unit4", "IsFinished": 2,
             "IsTested": 1, "unitNbr": 4},
            {"Id": 42, "moduleCode": "8a108cb7-42ad-314f-0142-ad33a0a70001", "Unit": "Unit5", "IsFinished": 2,
             "IsTested": 1, "unitNbr": 5},
            {"Id": 43, "moduleCode": "8a108cb7-42ad-314f-0142-ad33a0a70001", "Unit": "Unit6", "IsFinished": 2,
             "IsTested": 1, "unitNbr": 6},
            {"Id": 44, "moduleCode": "8a108cb7-42ad-314f-0142-ad33a0a70001", "Unit": "Unit7", "IsFinished": 2,
             "IsTested": 1, "unitNbr": 7},
            {"Id": 45, "moduleCode": "8a108cb7-42ad-314f-0142-ad33a0a70001", "Unit": "Unit8", "IsFinished": 2,
             "IsTested": 1, "unitNbr": 8},
            {"Id": 46, "moduleCode": "8a108cb7-42ad-314f-0142-ad33a0a70001", "Unit": "Unit9", "IsFinished": 2,
             "IsTested": 1, "unitNbr": 9},
            {"Id": 47, "moduleCode": "8a108cb7-42ad-314f-0142-ad33a0a70001", "Unit": "Unit10", "IsFinished": 2,
             "IsTested": 1, "unitNbr": 10},
            {"Id": 48, "moduleCode": "8a108cb7-42ad-314f-0142-ad33a0a70001", "Unit": "Unit11", "IsFinished": 2,
             "IsTested": 2, "unitNbr": 11},
            {"Id": 49, "moduleCode": "8a108cb7-42ad-314f-0142-ad33a0a70001", "Unit": "Unit12", "IsFinished": 2,
             "IsTested": 1, "unitNbr": 12},
            {"Id": 50, "moduleCode": "8a108cb7-42ad-314f-0142-ad33a0a70001", "Unit": "Unit13", "IsFinished": 2,
             "IsTested": -1, "unitNbr": 13},
            {"Id": 51, "moduleCode": "8a108cb7-42ad-314f-0142-ad33a0a70001", "Unit": "Unit14", "IsFinished": 2,
             "IsTested": 1, "unitNbr": 14},
            {"Id": 52, "moduleCode": "8a108cb7-42ad-314f-0142-ad33a0a70001", "Unit": "Unit15", "IsFinished": 2,
             "IsTested": 1, "unitNbr": 15},
            {"Id": 53, "moduleCode": "8a108cb7-42ad-314f-0142-ad33a0a70001", "Unit": "Unit16", "IsFinished": 2,
             "IsTested": 1, "unitNbr": 16},
            {"Id": 54, "moduleCode": "8a108cb7-42ad-314f-0142-ad33a0a70001", "Unit": "Unit17", "IsFinished": 2,
             "IsTested": 1, "unitNbr": 17}], "outDate": false, "unitType": "Unit", "startFrom": 1, "studyMode": 1,
         "CompleteWordCount": 0}], "condition": 0, "totalNbr": 0, "msg": "完成加载", "userState": 2, "totalLoginTimes": 0,
            "success": true}
    # return jsonify(data, ensure_ascii=False)
    return jsonify(data)


@app.route('/')
def index():
    return  render_template('index.html')


if __name__ == '__main__':
    false = False
    true = True
    app.config['JSON_AS_ASCII'] = False
    app.run()
