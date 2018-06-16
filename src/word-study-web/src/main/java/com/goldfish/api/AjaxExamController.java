package com.goldfish.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.goldfish.common.log.LogTypeEnum;
import com.goldfish.constant.QuestionTypes;
import com.goldfish.constant.State;
import com.goldfish.constant.TestArea;
import com.goldfish.dao.cache.local.CourseContext;
import com.goldfish.domain.*;
import com.goldfish.service.*;
import com.goldfish.vo.BasicVO;
import com.goldfish.vo.exam.*;
import com.goldfish.domain.Paper;
import com.goldfish.service.PaperService;
import com.goldfish.service.QuestionService;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by John on 2018/5/20 0020.
 */
public class AjaxExamController extends AjaxErrorBookController{

    /**
     * 单元测试每种题型都是10个题目
     */
    private static final int UNIT_EXAM_QUESTION_NUMBER=10;

    @Resource
    private PaperService paperService;
    @Resource
    private QuestionService questionService;
    @Resource
    private ExamService examService;
    @Resource
    private WordStudyService wordStudyService;
    @Resource
    private CourseContext courseContext;

    /**
     * 生成自主测试试卷
     * @param testArea 0
     * @param questionNbr 20
     * @param questionTypes 0,1,2,3
     * @param context
     * @return
     *
     * {
        "dataEn2Ch":[
            {
                "answerIndex":"A",
                "choices":{
                    "A":"柠檬",
                    "B":"笑话,玩笑",
                    "C":"根本不;单单除……之外",
                    "D":"v.发嘟嘟声，吼叫"
                },
                "spelling":"lemon",
                "vocCode":"8a108cb7-42aa-d911-0142-ad8121430085",
                "question":"lemon"
            },
            {
                "answerIndex":"C",
                "choices":{
                    "A":"vt.1.与...相对,面临,遭遇 2.使面对,使面临,使当面作证",
                    "B":"a.听觉的",
                    "C":"第二;秒钟",
                    "D":"（去）买东西"
                },
                "spelling":"second",
                "vocCode":"8a108cb7-42aa-d911-0142-ad8121450088",
                "question":"second"
            },
            {
                "answerIndex":"C",
                "choices":{
                    "A":"请问/劳驾。",
                    "B":"n.超级市场",
                    "C":"大头针",
                    "D":"n. 惠灵顿（新西兰）"
                },
                "spelling":"pin",
                "vocCode":"8a108cb7-42aa-d911-0142-ad8121ac00a0",
                "question":"pin"
            },
            {
                "answerIndex":"A",
                "choices":{
                    "A":"狐狸;时髦女郎",
                    "B":"n.钥匙;关键;答案",
                    "C":"农民",
                    "D":"n.流行病,迅速的传播、生长或发展 adj.流行性的,极为盛行的"
                },
                "spelling":"fox",
                "vocCode":"8a108cb7-42aa-d911-0142-ad8121b500b3",
                "question":"fox"
            },
            {
                "answerIndex":"A",
                "choices":{
                    "A":"牛;公牛",
                    "B":"ad.合理地",
                    "C":"船遇难(失事)",
                    "D":"架子"
                },
                "spelling":"ox",
                "vocCode":"8a108cb7-42aa-d911-0142-ad8121d200bd",
                "question":"ox"
            },
            {
                "answerIndex":"B",
                "choices":{
                    "A":"n. 保护;庇护;支持;赞助",
                    "B":"叔叔;伯伯",
                    "C":"n.未来;期货;前途;将来时 adj.将来的，未来的",
                    "D":"n. 旅游业"
                },
                "spelling":"uncle",
                "vocCode":"8a108cb7-42aa-d911-0142-ad8121d500c2",
                "question":"uncle"
            },
            {
                "answerIndex":"C",
                "choices":{
                    "A":"n.残余, 遗迹, 遗体",
                    "B":"n.熟练,有技巧,灵巧",
                    "C":"咬;钓饵",
                    "D":"n.牛津"
                },
                "spelling":"bite",
                "vocCode":"8a108cb7-42aa-d911-0142-ad81225e0107",
                "question":"bite"
            },
            {
                "answerIndex":"B",
                "choices":{
                    "A":"缝;干针线活",
                    "B":"好的;罚款",
                    "C":"n. 保护;庇护;支持;赞助",
                    "D":"n.士兵"
                },
                "spelling":"fine",
                "vocCode":"8a108cb7-42aa-d911-0142-ad81225f010a",
                "question":"fine"
            },
            {
                "answerIndex":"C",
                "choices":{
                    "A":"adv. 几乎,差不多 adj. 几乎",
                    "B":"n.地铁",
                    "C":"骑 ;乘",
                    "D":"牙疼"
                },
                "spelling":"ride",
                "vocCode":"8a108cb7-42aa-d911-0142-ad8122630113",
                "question":"ride"
            },
            {
                "answerIndex":"C",
                "choices":{
                    "A":"n.外国人",
                    "B":"adj.不可预知的",
                    "C":"战斗;打架",
                    "D":"律师"
                },
                "spelling":"fight",
                "vocCode":"8a108cb7-42aa-d911-0142-ad8122db015a",
                "question":"fight"
            },
            {
                "answerIndex":"B",
                "choices":{
                    "A":"n.情况，情形",
                    "B":"兄弟",
                    "C":"n.会;集会;会见;汇合点",
                    "D":"在城市"
                },
                "spelling":"brother",
                "vocCode":"8a108cb7-42aa-d911-0142-ad8123350196",
                "question":"brother"
            },
            {
                "answerIndex":"C",
                "choices":{
                    "A":"蓝色的",
                    "B":"n. 激怒，愤怒",
                    "C":"划圈;包围;盘旋;环绕",
                    "D":"n. 节目，节目单"
                },
                "spelling":"circle",
                "vocCode":"8a108cb7-42aa-d911-0142-ad81233901a0",
                "question":"circle"
            },
            {
                "answerIndex":"B",
                "choices":{
                    "A":"让路,让步",
                    "B":"污垢;泥土;灰尘",
                    "C":"收集物，收藏品",
                    "D":"n. 修理，修补v. 补救，纠正;修理"
                },
                "spelling":"dirt",
                "vocCode":"8a108cb7-42aa-d911-0142-ad81233901a1",
                "question":"dirt"
            },
            {
                "answerIndex":"A",
                "choices":{
                    "A":"早晨",
                    "B":"n.安逸, 安心, 不费力, 悠闲vt.使悠闲, 使安心, 减轻, 放松vi.减弱, 减轻, 放松, 灵活地移动",
                    "C":"adj.公民的；国内的",
                    "D":"adj. 立方体的"
                },
                "spelling":"morning",
                "vocCode":"8a108cb7-42aa-d911-0142-ad81235801b5",
                "question":"morning"
            },
            {
                "answerIndex":"B",
                "choices":{
                    "A":"n. 手杖,藤条,甘蔗v.杖击,藤编",
                    "B":"棕色的",
                    "C":"n. 病毒",
                    "D":"vt.1.与...相对,面临,遭遇 2.使面对,使面临,使当面作证"
                },
                "spelling":"brown",
                "vocCode":"8a108cb7-42aa-d911-0142-ad81237601c7",
                "question":"brown"
            },
            {
                "answerIndex":"B",
                "choices":{
                    "A":"n.证据;试验",
                    "B":"口哨;汽笛;警笛",
                    "C":"头;头脑;才智;去;驶向",
                    "D":"便宜"
                },
                "spelling":"whistle",
                "vocCode":"8a108cb7-42aa-d911-0142-ad8123e00213",
                "question":"whistle"
            },
            {
                "answerIndex":"B",
                "choices":{
                    "A":"n.(附有停车场的)汽车旅馆",
                    "B":"对虾;明虾",
                    "C":"n.轨道;vt.使沿轨道运行;使进入轨道运行;vi.沿轨道运行，环行",
                    "D":"互相，彼此"
                },
                "spelling":"prawn",
                "vocCode":"8a108cb7-42aa-d911-0142-ad8124670282",
                "question":"prawn"
            },
            {
                "answerIndex":"C",
                "choices":{
                    "A":"担心的，烦恼的",
                    "B":"平坦的;扁平的;住公寓",
                    "C":"新闻",
                    "D":"n.1.轻易,不费力 2.安逸"
                },
                "spelling":"news",
                "vocCode":"8a108cb7-42aa-d911-0142-ad8124ca02d2",
                "question":"news"
            },
            {
                "answerIndex":"A",
                "choices":{
                    "A":"干燥",
                    "B":"adj.无线的",
                    "C":"n.钥匙;关键;答案",
                    "D":"v.按"
                },
                "spelling":"dry",
                "vocCode":"8a108cb7-42aa-d911-0142-ad8124cc02dd",
                "question":"dry"
            },
            {
                "answerIndex":"B",
                "choices":{
                    "A":"adj.黑色的",
                    "B":"高兴的",
                    "C":"n.笔记本;笔记本电脑;笔记簿;手册",
                    "D":"n.1.轻易,不费力 2.安逸"
                },
                "spelling":"happy",
                "vocCode":"8a108cb7-42aa-d911-0142-ad8124e202e8",
                "question":"happy"
            }
        ],
        "dataCh2En":[
            {
                "answerIndex":"A",
                "choices":{
                    "A":"fat",
                    "B":"skillfulness",
                    "C":"miscreant",
                    "D":"concrete"
                },
                "spelling":"fat",
                "vocCode":"8a108cb7-42aa-d911-0142-ad812032002b",
                "question":"肥的;油腻的"
            },
            {
                "answerIndex":"A",
                "choices":{
                    "A":"tenth",
                    "B":"love",
                    "C":"wireless",
                    "D":"cake"
                },
                "spelling":"tenth",
                "vocCode":"8a108cb7-42aa-d911-0142-ad81213d007e",
                "question":"第十;十分之一"
            },
            {
                "answerIndex":"C",
                "choices":{
                    "A":"definitely",
                    "B":"catch (a) cold",
                    "C":"rent",
                    "D":"storm"
                },
                "spelling":"rent",
                "vocCode":"8a108cb7-42aa-d911-0142-ad81213f0082",
                "question":"租借租用;租金"
            },
            {
                "answerIndex":"C",
                "choices":{
                    "A":"choral",
                    "B":"blue",
                    "C":"pink",
                    "D":"picnic"
                },
                "spelling":"pink",
                "vocCode":"8a108cb7-42aa-d911-0142-ad8121ad00a3",
                "question":"粉红色的"
            },
            {
                "answerIndex":"C",
                "choices":{
                    "A":"give a helping hand",
                    "B":"science fiction",
                    "C":"Miss",
                    "D":"collapse"
                },
                "spelling":"Miss",
                "vocCode":"8a108cb7-42aa-d911-0142-ad8121b400b0",
                "question":"小姐;女士"
            },
            {
                "answerIndex":"B",
                "choices":{
                    "A":"cent",
                    "B":"frog",
                    "C":"toothache",
                    "D":"Friday"
                },
                "spelling":"frog",
                "vocCode":"8a108cb7-42aa-d911-0142-ad8121cf00b6",
                "question":"青蛙"
            },
            {
                "answerIndex":"A",
                "choices":{
                    "A":"run",
                    "B":"while",
                    "C":"riot",
                    "D":"call for"
                },
                "spelling":"run",
                "vocCode":"8a108cb7-42aa-d911-0142-ad8121d300be",
                "question":"跑步;经营;开办"
            },
            {
                "answerIndex":"B",
                "choices":{
                    "A":"compaction",
                    "B":"gate",
                    "C":"shelf",
                    "D":"redress"
                },
                "spelling":"gate",
                "vocCode":"8a108cb7-42aa-d911-0142-ad81221500e5",
                "question":"大门"
            },
            {
                "answerIndex":"C",
                "choices":{
                    "A":"various",
                    "B":"redress",
                    "C":"paste",
                    "D":"stamp"
                },
                "spelling":"paste",
                "vocCode":"8a108cb7-42aa-d911-0142-ad81221900ec",
                "question":"面团;粘贴"
            },
            {
                "answerIndex":"A",
                "choices":{
                    "A":"base",
                    "B":"can",
                    "C":"star",
                    "D":"blue"
                },
                "spelling":"base",
                "vocCode":"8a108cb7-42aa-d911-0142-ad81221b00ef",
                "question":"基地;基础"
            },
            {
                "answerIndex":"B",
                "choices":{
                    "A":"cheap",
                    "B":"queen",
                    "C":"pregnant",
                    "D":"festive"
                },
                "spelling":"queen",
                "vocCode":"8a108cb7-42aa-d911-0142-ad8122440101",
                "question":"女王"
            },
            {
                "answerIndex":"B",
                "choices":{
                    "A":"some",
                    "B":"dose",
                    "C":"sparrow",
                    "D":"prize"
                },
                "spelling":"dose",
                "vocCode":"8a108cb7-42aa-d911-0142-ad81227b0119",
                "question":"剂量;一剂;一服"
            },
            {
                "answerIndex":"A",
                "choices":{
                    "A":"nail",
                    "B":"offer",
                    "C":"worried",
                    "D":"ease"
                },
                "spelling":"nail",
                "vocCode":"8a108cb7-42aa-d911-0142-ad812283012a",
                "question":"指甲;钉;爪"
            },
            {
                "answerIndex":"A",
                "choices":{
                    "A":"hay",
                    "B":"level",
                    "C":"sick",
                    "D":"worried"
                },
                "spelling":"hay",
                "vocCode":"8a108cb7-42aa-d911-0142-ad81229b0131",
                "question":"干草"
            },
            {
                "answerIndex":"A",
                "choices":{
                    "A":"seel",
                    "B":"south",
                    "C":"parity",
                    "D":"worn"
                },
                "spelling":"seel",
                "vocCode":"8a108cb7-42aa-d911-0142-ad8122a0013c",
                "question":"以线缝合;使闭眼"
            },
            {
                "answerIndex":"A",
                "choices":{
                    "A":"garden",
                    "B":"humorous",
                    "C":"subjective",
                    "D":"definitely"
                },
                "spelling":"garden",
                "vocCode":"8a108cb7-42aa-d911-0142-ad8123330193",
                "question":"花园;园艺"
            },
            {
                "answerIndex":"B",
                "choices":{
                    "A":"clean up",
                    "B":"circus",
                    "C":"soldier",
                    "D":"What's your name?"
                },
                "spelling":"circus",
                "vocCode":"8a108cb7-42aa-d911-0142-ad812337019d",
                "question":"马戏团;马戏表演;竞技场;广场"
            },
            {
                "answerIndex":"C",
                "choices":{
                    "A":"bike",
                    "B":"enjoy",
                    "C":"wrist",
                    "D":"eloquent"
                },
                "spelling":"wrist",
                "vocCode":"8a108cb7-42aa-d911-0142-ad81240b0232",
                "question":"手腕;腕部"
            },
            {
                "answerIndex":"A",
                "choices":{
                    "A":"crayon",
                    "B":"south",
                    "C":"Oxford",
                    "D":"defeat"
                },
                "spelling":"crayon",
                "vocCode":"8a108cb7-42aa-d911-0142-ad8124500271",
                "question":"蜡笔;蜡笔画"
            },
            {
                "answerIndex":"C",
                "choices":{
                    "A":"Oxford",
                    "B":"cake",
                    "C":"trunk",
                    "D":"shortcoming"
                },
                "spelling":"trunk",
                "vocCode":"8a108cb7-42aa-d911-0142-ad81246b0291",
                "question":"树干;(汽车后部)行李箱;大箱子;象鼻;躯干"
            }
        ],
        "dataListen2Ch":[
            {
                "answerIndex":"B",
                "choices":{
                    "A":"让路,让步",
                    "B":"账单",
                    "C":"农民",
                    "D":"愚蠢的"
                },
                "spelling":"bill",
                "vocCode":"8a108cb7-42aa-d911-0142-ad8121ae00a6",
                "question":"bill"
            },
            {
                "answerIndex":"B",
                "choices":{
                    "A":"n.小虾",
                    "B":"袜子",
                    "C":"n.超级市场",
                    "D":"麻雀"
                },
                "spelling":"sock",
                "vocCode":"8a108cb7-42aa-d911-0142-ad8121cf00b7",
                "question":"sock"
            },
            {
                "answerIndex":"A",
                "choices":{
                    "A":"相同的",
                    "B":"（两者）都不",
                    "C":"v.按",
                    "D":"母亲节"
                },
                "spelling":"same",
                "vocCode":"8a108cb7-42aa-d911-0142-ad8121f600d8",
                "question":"same"
            },
            {
                "answerIndex":"A",
                "choices":{
                    "A":"湖泊",
                    "B":"n.监视,监督",
                    "C":"adj.不可预知的",
                    "D":"v./n.倒塌;崩溃;(价格)暴跌;倒闭，破产"
                },
                "spelling":"lake",
                "vocCode":"8a108cb7-42aa-d911-0142-ad81221100dd",
                "question":"lake"
            },
            {
                "answerIndex":"B",
                "choices":{
                    "A":"一些，若干;有些;某一",
                    "B":"命运",
                    "C":"n.轨道;vt.使沿轨道运行;使进入轨道运行;vi.沿轨道运行，环行",
                    "D":"adv. 忠实地;亲爱地"
                },
                "spelling":"fate",
                "vocCode":"8a108cb7-42aa-d911-0142-ad81221500e6",
                "question":"fate"
            },
            {
                "answerIndex":"A",
                "choices":{
                    "A":"绿色的",
                    "B":"天使，守护神",
                    "C":"v.私语；咕哝；潺潺 n..私语；咕哝；耳语",
                    "D":"手"
                },
                "spelling":"green",
                "vocCode":"8a108cb7-42aa-d911-0142-ad8122440100",
                "question":"green"
            },
            {
                "answerIndex":"C",
                "choices":{
                    "A":"adj.每一个，每个",
                    "B":"收集物，收藏品",
                    "C":"领带;系领带;栓绳子",
                    "D":"英俊的"
                },
                "spelling":"tie",
                "vocCode":"8a108cb7-42aa-d911-0142-ad8122d90155",
                "question":"tie"
            },
            {
                "answerIndex":"C",
                "choices":{
                    "A":"头;头脑;才智;去;驶向",
                    "B":"n. 网络空间;赛博空间",
                    "C":"害羞的",
                    "D":"n.妻子"
                },
                "spelling":"shy",
                "vocCode":"8a108cb7-42aa-d911-0142-ad8124cb02d9",
                "question":"shy"
            }
        ],
        "dataListen2Write":[
            {
                "answerIndex":"A",
                "spelling":"lent",
                "vocCode":"8a108cb7-42aa-d911-0142-ad81213e0080",
                "question":"借出（过去式）;（基督教）打斋节"
            },
            {
                "answerIndex":"A",
                "spelling":"think",
                "vocCode":"8a108cb7-42aa-d911-0142-ad8121ae00a5",
                "question":"思考;认为"
            },
            {
                "answerIndex":"A",
                "spelling":"on",
                "vocCode":"8a108cb7-42aa-d911-0142-ad8121d000b8",
                "question":"在...上"
            },
            {
                "answerIndex":"A",
                "spelling":"dumpling",
                "vocCode":"8a108cb7-42aa-d911-0142-ad8121f100cc",
                "question":"饺子"
            },
            {
                "answerIndex":"A",
                "spelling":"came",
                "vocCode":"8a108cb7-42aa-d911-0142-ad8121f400d3",
                "question":"来到（过去式）"
            },
            {
                "answerIndex":"A",
                "spelling":"late",
                "vocCode":"8a108cb7-42aa-d911-0142-ad81221400e4",
                "question":"晚的;迟到"
            },
            {
                "answerIndex":"A",
                "spelling":"these",
                "vocCode":"8a108cb7-42aa-d911-0142-ad81224100fb",
                "question":"这些"
            },
            {
                "answerIndex":"A",
                "spelling":"fine",
                "vocCode":"8a108cb7-42aa-d911-0142-ad81225f010a",
                "question":"好的;罚款"
            },
            {
                "answerIndex":"A",
                "spelling":"mice",
                "vocCode":"8a108cb7-42aa-d911-0142-ad8122620110",
                "question":"老鼠（复数）"
            },
            {
                "answerIndex":"A",
                "spelling":"nice",
                "vocCode":"8a108cb7-42aa-d911-0142-ad8122620111",
                "question":"好的，漂亮"
            },
            {
                "answerIndex":"A",
                "spelling":"cute",
                "vocCode":"8a108cb7-42aa-d911-0142-ad81227f0123",
                "question":"可爱的"
            },
            {
                "answerIndex":"A",
                "spelling":"bay",
                "vocCode":"8a108cb7-42aa-d911-0142-ad81229a012f",
                "question":"海湾;河湾"
            },
            {
                "answerIndex":"A",
                "spelling":"jockey",
                "vocCode":"8a108cb7-42aa-d911-0142-ad812315017e",
                "question":"赛马的骑师; 驾驶员; 操作工;骗人"
            },
            {
                "answerIndex":"A",
                "spelling":"bird",
                "vocCode":"8a108cb7-42aa-d911-0142-ad812338019f",
                "question":"小鸟"
            },
            {
                "answerIndex":"A",
                "spelling":"horse",
                "vocCode":"8a108cb7-42aa-d911-0142-ad81235601b1",
                "question":"马"
            },
            {
                "answerIndex":"A",
                "spelling":"owl",
                "vocCode":"8a108cb7-42aa-d911-0142-ad81237701c9",
                "question":"猫头鹰"
            },
            {
                "answerIndex":"A",
                "spelling":"wheat",
                "vocCode":"8a108cb7-42aa-d911-0142-ad8123df0210",
                "question":"小麦"
            },
            {
                "answerIndex":"A",
                "spelling":"play",
                "vocCode":"8a108cb7-42aa-d911-0142-ad812424024e",
                "question":"玩;打（篮球）踢（足球）"
            },
            {
                "answerIndex":"A",
                "spelling":"clock",
                "vocCode":"8a108cb7-42aa-d911-0142-ad812438025b",
                "question":"时钟"
            },
            {
                "answerIndex":"A",
                "spelling":"crab",
                "vocCode":"8a108cb7-42aa-d911-0142-ad8124510274",
                "question":"蟹;阴虱;爱争吵的人"
            }
        ],
        "msg":"成功！",
        "success":true,
        "condition":0
    }
     */
    @RequestMapping(value="GetExam",method={RequestMethod.GET,RequestMethod.POST})
    public @ResponseBody
    ExamVO doGetExam(Integer testArea, Integer questionNbr, String questionTypes, ModelMap context) {
        ExamVO examVO = new ExamVO();
        List<QuestionVO> ecList = new ArrayList<>(questionNbr);
        List<QuestionVO> ceList = new ArrayList<>(questionNbr);
        List<QuestionVO> lcList = new ArrayList<>(questionNbr);
        List<Listen2WriteVO> lwList = new ArrayList<>(questionNbr);
        // 1.装填用户信息
        User user = this.getUserInfo();
        if (user == null) {
            examVO.setCondition(-1);
            return examVO;
        }
        //2.根据用户信息和testArea查询单词列表
        WordStudy wordStudyQuery = new WordStudy();
        TestArea testArea1 = TestArea.getTestArea(testArea);
        wordStudyQuery.setMemoryLevel(testArea1.getCorrespondingMemoryLevel().getLevel());
        wordStudyQuery.setStudentId(user.getId().intValue());  //TODO 到底是什么ID
        wordStudyQuery.setUserCode(user.getUserCode());
        List<WordStudy> wordStudyList = wordStudyService.getListByExample(wordStudyQuery).getDefaultModel();
        //3.根据questionTypes和questionNbr出题
        //testArea=0&questionNbr=10&questionTypes=0,1,2
        String[] questionTypesArray = questionTypes.split(",");
        int questionNbrCount = questionNbr * questionTypesArray.length;
        int questionTypeCount = 0;
        for (WordStudy wordStudy : wordStudyList)
        {
            if (questionTypeCount >= questionNbrCount)
                break;
            Question questionQuery = new Question();
            QuestionTypes qt = QuestionTypes.getQuestionTypesByNumber
                    (Integer.valueOf(questionTypesArray[questionTypeCount/questionNbr]));
            questionQuery.setType(qt.getFullName());
            questionQuery.setWordId(wordStudy.getWordId());
            Question question = questionService.getUnique(questionQuery).getDefaultModel();
            if(question == null)
            {
                //如果没查到，说明这个单词没有对应的题，就下一个单词
                continue;
            }

            if (qt.equals(QuestionTypes.LISTEN2WRITE))
            {
                Listen2WriteVO vo = new Listen2WriteVO();
                vo.setAnswerIndex(question.getAnswerIndex());
                vo.setQuestion(question.getQuestion());
                vo.setSpelling(question.getSpelling());
                vo.setVocCode(question.getVocCode());
                lwList.add(vo);
            }
            else
            {
                ChoicesVO choicesVO = new ChoicesVO(question.getChoices());
                QuestionVO questionVO = new QuestionVO(question.getAnswerIndex(),question.getSpelling()
                        ,question.getVocCode(),question.getQuestion(),choicesVO,question.getId());
                if (qt.equals(QuestionTypes.EN2CH))
                {

                    ecList.add(questionVO);
                }
                else if (qt.equals(QuestionTypes.CH2EN))
                {
                    ceList.add(questionVO);
                }
                else if (qt.equals(QuestionTypes.LISTEN2CH))
                {
                    lcList.add(questionVO);
                }
            }
            questionTypeCount++;
        }
        examVO.setDataEn2Ch(ecList);
        examVO.setDataCh2En(ceList);
        examVO.setDataListen2Ch(lcList);
        examVO.setDataListen2Write(lwList);
        examVO.setCondition(0); //TODO 这是什么
        examVO.setMsg("success");
        examVO.setSuccess(true);
        return examVO;
    }

    /**
     * 获取单元测试试卷
     * @param moduleCode 8a108cb7-42ad-314f-0142-ad33a0a70001
     * @param unitNbr 11
     * @param context
     * @return
     *
     * {
        "data":{
            "dataEn2Ch":Array[15],
            "dataCh2En":Array[15],
            "dataListen2Ch":Array[10],
            "msg":"成功！",
            "success":true,
            "condition":0
        },
        "success":true,
        "condition":2,
        "studytoken":"00000000-0000-0000-0000-000000000000",
        "totalNbr":0
    }

    {
        "data":{
            "dataEn2Ch":[
                {
                    "answerIndex":"C",
                    "choices":{
                        "A":"天气，气候",
                        "B":"n.[亦作rumor] 谣言, 传闻v.谣传",
                        "C":"海龟",
                        "D":"任何;每一"
                    },
                    "spelling":"turtle",
                    "vocCode":"8a108cb7-42aa-d911-0142-ad81235301aa",
                    "question":"turtle"
                },
                {
                    "answerIndex":"A",
                    "choices":{
                        "A":"冲浪",
                        "B":"n.王国",
                        "C":"蓝色",
                        "D":"试穿"
                    },
                    "spelling":"surfing",
                    "vocCode":"8a108cb7-42aa-d911-0142-ad81235301a9",
                    "question":"surfing"
                },
                {
                    "answerIndex":"A",
                    "choices":{
                        "A":"棕色的",
                        "B":"adj.诚实的,老实的",
                        "C":"n.阿根廷(南美洲南部国家)n.阿根廷(南美洲国家)",
                        "D":"身份证"
                    },
                    "spelling":"brown",
                    "vocCode":"8a108cb7-42aa-d911-0142-ad81237601c7",
                    "question":"brown"
                },
                {
                    "answerIndex":"C",
                    "choices":{
                        "A":"n.纽扣,电铃等的按钮v.扣（纽扣）",
                        "B":"n. 书，书籍 /预定 书",
                        "C":"熊;忍受;负荷",
                        "D":"adj.含糊的, 不清楚的, 茫然的, 暧昧的"
                    },
                    "spelling":"bear",
                    "vocCode":"8a108cb7-42aa-d911-0142-ad81235401ad",
                    "question":"bear"
                },
                {
                    "answerIndex":"B",
                    "choices":{
                        "A":"n.野生生物;野生的动物和植物",
                        "B":"谷物;玉米;伤感平庸作品",
                        "C":"有危险，有风险",
                        "D":"n.汽车, 小汽车, 车辆, 客车, [铁]车厢"
                    },
                    "spelling":"corn",
                    "vocCode":"8a108cb7-42aa-d911-0142-ad81235701b2",
                    "question":"corn"
                },
                {
                    "answerIndex":"C",
                    "choices":{
                        "A":"n.努力",
                        "B":"n.三明治",
                        "C":"划圈;包围;盘旋;环绕",
                        "D":"可爱的;机灵的"
                    },
                    "spelling":"circle",
                    "vocCode":"8a108cb7-42aa-d911-0142-ad81233901a0",
                    "question":"circle"
                },
                {
                    "answerIndex":"B",
                    "choices":{
                        "A":"n.花岗岩，花岗石",
                        "B":"毛皮;软毛;毛皮制品;皮衣",
                        "C":"n. 书，书籍 /预定 书",
                        "D":"六"
                    },
                    "spelling":"fur",
                    "vocCode":"8a108cb7-42aa-d911-0142-ad81235101a5",
                    "question":"fur"
                },
                {
                    "answerIndex":"B",
                    "choices":{
                        "A":"变胖",
                        "B":"老鼠",
                        "C":"出生",
                        "D":"n.成员，会员"
                    },
                    "spelling":"mouse",
                    "vocCode":"8a108cb7-42aa-d911-0142-ad81237101bc",
                    "question":"mouse"
                },
                {
                    "answerIndex":"C",
                    "choices":{
                        "A":"a.突出的,显著的,杰出的",
                        "B":"n. 俄罗斯人",
                        "C":"猪肉",
                        "D":"无性的"
                    },
                    "spelling":"pork",
                    "vocCode":"8a108cb7-42aa-d911-0142-ad81235501ae",
                    "question":"pork"
                },
                {
                    "answerIndex":"A",
                    "choices":{
                        "A":"小鸟",
                        "B":"n.阿根廷(南美洲南部国家)n.阿根廷(南美洲国家)",
                        "C":"树",
                        "D":"编写;创作"
                    },
                    "spelling":"bird",
                    "vocCode":"8a108cb7-42aa-d911-0142-ad812338019f",
                    "question":"bird"
                },
                {
                    "answerIndex":"B",
                    "choices":{
                        "A":"n. 形状",
                        "B":"塔;高楼;高耸;胜过",
                        "C":"a.率直的;钝的 v.(使)钝;(使)迟钝",
                        "D":"在…上方prep."
                    },
                    "spelling":"tower",
                    "vocCode":"8a108cb7-42aa-d911-0142-ad81237401c3",
                    "question":"tower"
                },
                {
                    "answerIndex":"A",
                    "choices":{
                        "A":"喇叭;触角;角质;号",
                        "B":"a.非宗教的;民事的",
                        "C":"鼓",
                        "D":"试穿"
                    },
                    "spelling":"horn",
                    "vocCode":"8a108cb7-42aa-d911-0142-ad81235801b4",
                    "question":"horn"
                },
                {
                    "answerIndex":"B",
                    "choices":{
                        "A":"n.签名，署名，信号",
                        "B":"梨子;梨树",
                        "C":"n. 课;一节课",
                        "D":"编写;创作"
                    },
                    "spelling":"pear",
                    "vocCode":"8a108cb7-42aa-d911-0142-ad81235401ac",
                    "question":"pear"
                },
                {
                    "answerIndex":"B",
                    "choices":{
                        "A":"v.不在",
                        "B":"怎么;怎样",
                        "C":"adj.心理的,精神上的",
                        "D":"n.旋转式机器 adj.旋转的"
                    },
                    "spelling":"how",
                    "vocCode":"8a108cb7-42aa-d911-0142-ad81237301c1",
                    "question":"how"
                },
                {
                    "answerIndex":"B",
                    "choices":{
                        "A":"n.王国",
                        "B":"运动",
                        "C":"在...上搜索",
                        "D":"n. 形状"
                    },
                    "spelling":"sport",
                    "vocCode":"8a108cb7-42aa-d911-0142-ad81235901b7",
                    "question":"sport"
                }
            ],
            "dataCh2En":[
                {
                    "answerIndex":"C",
                    "choices":{
                        "A":"car",
                        "B":"recent",
                        "C":"mouse",
                        "D":"regard"
                    },
                    "spelling":"mouse",
                    "vocCode":"8a108cb7-42aa-d911-0142-ad81237101bc",
                    "question":"老鼠"
                },
                {
                    "answerIndex":"B",
                    "choices":{
                        "A":"this morning",
                        "B":"hurt",
                        "C":"recent",
                        "D":"useless"
                    },
                    "spelling":"hurt",
                    "vocCode":"8a108cb7-42aa-d911-0142-ad81235201a6",
                    "question":"伤害"
                },
                {
                    "answerIndex":"C",
                    "choices":{
                        "A":"outstanding",
                        "B":"search sth.",
                        "C":"corn",
                        "D":"seal"
                    },
                    "spelling":"corn",
                    "vocCode":"8a108cb7-42aa-d911-0142-ad81235701b2",
                    "question":"谷物;玉米;伤感平庸作品"
                },
                {
                    "answerIndex":"A",
                    "choices":{
                        "A":"nurse",
                        "B":"Argentina",
                        "C":"transparent",
                        "D":"survival kit"
                    },
                    "spelling":"nurse",
                    "vocCode":"8a108cb7-42aa-d911-0142-ad81235201a7",
                    "question":"护士"
                },
                {
                    "answerIndex":"A",
                    "choices":{
                        "A":"pear",
                        "B":"sibilant",
                        "C":"tree",
                        "D":"save one's life"
                    },
                    "spelling":"pear",
                    "vocCode":"8a108cb7-42aa-d911-0142-ad81235401ac",
                    "question":"梨子;梨树"
                },
                {
                    "answerIndex":"A",
                    "choices":{
                        "A":"clown",
                        "B":"honest",
                        "C":"successful",
                        "D":"hover"
                    },
                    "spelling":"clown",
                    "vocCode":"8a108cb7-42aa-d911-0142-ad81237601c6",
                    "question":"小丑;粗鲁愚蠢的人"
                },
                {
                    "answerIndex":"B",
                    "choices":{
                        "A":"frontier",
                        "B":"morning",
                        "C":"important",
                        "D":"tomorrow"
                    },
                    "spelling":"morning",
                    "vocCode":"8a108cb7-42aa-d911-0142-ad81235801b5",
                    "question":"早晨"
                },
                {
                    "answerIndex":"A",
                    "choices":{
                        "A":"towel",
                        "B":"moon",
                        "C":"book",
                        "D":"marry"
                    },
                    "spelling":"towel",
                    "vocCode":"8a108cb7-42aa-d911-0142-ad81237501c4",
                    "question":"毛巾;手巾"
                },
                {
                    "answerIndex":"B",
                    "choices":{
                        "A":"gymnastics",
                        "B":"pork",
                        "C":"cactus",
                        "D":"leave my jacket behind"
                    },
                    "spelling":"pork",
                    "vocCode":"8a108cb7-42aa-d911-0142-ad81235501ae",
                    "question":"猪肉"
                },
                {
                    "answerIndex":"C",
                    "choices":{
                        "A":"gray-haired",
                        "B":"psychological",
                        "C":"purse",
                        "D":"moon"
                    },
                    "spelling":"purse",
                    "vocCode":"8a108cb7-42aa-d911-0142-ad81235201a8",
                    "question":"钱包"
                },
                {
                    "answerIndex":"B",
                    "choices":{
                        "A":"book",
                        "B":"house",
                        "C":"athlete",
                        "D":"sibilant"
                    },
                    "spelling":"house",
                    "vocCode":"8a108cb7-42aa-d911-0142-ad81237001ba",
                    "question":"房子"
                },
                {
                    "answerIndex":"A",
                    "choices":{
                        "A":"sport",
                        "B":"wont",
                        "C":"tributary",
                        "D":"ninety"
                    },
                    "spelling":"sport",
                    "vocCode":"8a108cb7-42aa-d911-0142-ad81235901b7",
                    "question":"运动"
                },
                {
                    "answerIndex":"B",
                    "choices":{
                        "A":"what time",
                        "B":"surfing",
                        "C":"ID card",
                        "D":"hurt"
                    },
                    "spelling":"surfing",
                    "vocCode":"8a108cb7-42aa-d911-0142-ad81235301a9",
                    "question":"冲浪"
                },
                {
                    "answerIndex":"A",
                    "choices":{
                        "A":"girl",
                        "B":"sibilant",
                        "C":"thousand",
                        "D":"frontier"
                    },
                    "spelling":"girl",
                    "vocCode":"8a108cb7-42aa-d911-0142-ad81233901a2",
                    "question":"女生"
                },
                {
                    "answerIndex":"A",
                    "choices":{
                        "A":"dirty",
                        "B":"survival kit",
                        "C":"steak",
                        "D":"thirsty"
                    },
                    "spelling":"dirty",
                    "vocCode":"8a108cb7-42aa-d911-0142-ad812338019e",
                    "question":"脏的;肮脏的;下流的;卑鄙的;腐败的;不正当的"
                }
            ],
            "dataListen2Ch":[
                {
                    "answerIndex":"B",
                    "choices":{
                        "A":"在…上方prep.",
                        "B":"猪肉",
                        "C":"num./n./a.一千;[pl.]许许多多，成千上万",
                        "D":"adv. 通常地;一般地"
                    },
                    "spelling":"pork",
                    "vocCode":"8a108cb7-42aa-d911-0142-ad81235501ae",
                    "question":"pork"
                },
                {
                    "answerIndex":"B",
                    "choices":{
                        "A":"任何;每一",
                        "B":"大喊;大叫",
                        "C":"a.正确的，精确的",
                        "D":"邀请;邀请函"
                    },
                    "spelling":"shout",
                    "vocCode":"8a108cb7-42aa-d911-0142-ad81237001b9",
                    "question":"shout"
                },
                {
                    "answerIndex":"C",
                    "choices":{
                        "A":"总的来说;大体上",
                        "B":"n. 俄罗斯人",
                        "C":"护士",
                        "D":"天气，气候"
                    },
                    "spelling":"nurse",
                    "vocCode":"8a108cb7-42aa-d911-0142-ad81235201a7",
                    "question":"nurse"
                },
                {
                    "answerIndex":"A",
                    "choices":{
                        "A":"马戏团;马戏表演;竞技场;广场",
                        "B":"n.主意",
                        "C":"vt.(巧妙地)处理",
                        "D":"n.汽车, 小汽车, 车辆, 客车, [铁]车厢"
                    },
                    "spelling":"circus",
                    "vocCode":"8a108cb7-42aa-d911-0142-ad812337019d",
                    "question":"circus"
                },
                {
                    "answerIndex":"C",
                    "choices":{
                        "A":"n.汽车, 小汽车, 车辆, 客车, [铁]车厢",
                        "B":"adj.性的, 性别的, [生]有性的",
                        "C":"外边",
                        "D":"受欢迎的;普遍的"
                    },
                    "spelling":"out",
                    "vocCode":"8a108cb7-42aa-d911-0142-ad81236f01b8",
                    "question":"out"
                },
                {
                    "answerIndex":"C",
                    "choices":{
                        "A":"n.救生包;应急箱",
                        "B":"a.泥泞的",
                        "C":"谷物;玉米;伤感平庸作品",
                        "D":"须鲸"
                    },
                    "spelling":"corn",
                    "vocCode":"8a108cb7-42aa-d911-0142-ad81235701b2",
                    "question":"corn"
                },
                {
                    "answerIndex":"B",
                    "choices":{
                        "A":"n.1.坚决,坚定,决心 2.解决,解答",
                        "B":"脏的;肮脏的;下流的;卑鄙的;腐败的;不正当的",
                        "C":"学习",
                        "D":"n.尿"
                    },
                    "spelling":"dirty",
                    "vocCode":"8a108cb7-42aa-d911-0142-ad812338019e",
                    "question":"dirty"
                },
                {
                    "answerIndex":"B",
                    "choices":{
                        "A":"总的来说;大体上",
                        "B":"怎么;怎样",
                        "C":"n. 俄罗斯人",
                        "D":"n. 形状"
                    },
                    "spelling":"how",
                    "vocCode":"8a108cb7-42aa-d911-0142-ad81237301c1",
                    "question":"how"
                },
                {
                    "answerIndex":"B",
                    "choices":{
                        "A":"n.预测,预报 vat.预言,预测,预报",
                        "B":"钱包",
                        "C":"大陆;洲",
                        "D":"a.非宗教的;民事的"
                    },
                    "spelling":"purse",
                    "vocCode":"8a108cb7-42aa-d911-0142-ad81235201a8",
                    "question":"purse"
                },
                {
                    "answerIndex":"B",
                    "choices":{
                        "A":"n.汽车, 小汽车, 车辆, 客车, [铁]车厢",
                        "B":"梨子;梨树",
                        "C":"n.发型;理发",
                        "D":"a.正确的，精确的"
                    },
                    "spelling":"pear",
                    "vocCode":"8a108cb7-42aa-d911-0142-ad81235401ac",
                    "question":"pear"
                }
            ],
            "msg":"成功！",
            "success":true,
            "condition":0
        },
        "success":true,
        "condition":2,
        "studytoken":"00000000-0000-0000-0000-000000000000",
        "totalNbr":0
    }
     *
     */

    /**
     * /**
     * {
     "ec": [1, 2, 3, 4, 5],
     "ce": [1, 2, 3, 4, 5],
     "lc": [1, 2, 3, 4, 5]
     }
     */
    @RequestMapping(value="GetUnitExam",method={RequestMethod.GET,RequestMethod.POST})
    public @ResponseBody
    UnitExamVO doGetUnitExam(String moduleCode, Integer unitNbr, ModelMap context) {
        Paper paperCondition = new Paper();
        paperCondition.setUnitNbr(unitNbr);
        paperCondition.setModuleCode(moduleCode);
        Paper paperResult = paperService.getUnique(paperCondition).getDefaultModel();
        DataVO dataVO = new DataVO();
        dataVO.setMsg("");
        dataVO.setSuccess(true);
        dataVO.setCondition(0);
        if (paperResult != null) {
            JSONObject questionsJson = JSON.parseObject(paperResult.getQuestions());
            if (questionsJson.containsKey(QuestionTypes.EN2CH.getForShort())) {
                dataVO.setDataEn2Ch(getQuestionVOfromPaper(questionsJson, QuestionTypes.EN2CH.getForShort()));
            }
            if (questionsJson.containsKey(QuestionTypes.CH2EN.getForShort())) {
                dataVO.setDataCh2En(getQuestionVOfromPaper(questionsJson, QuestionTypes.CH2EN.getForShort()));
            }
            if (questionsJson.containsKey(QuestionTypes.LISTEN2CH.getForShort())) {
                dataVO.setDataListen2Ch(getQuestionVOfromPaper(questionsJson, QuestionTypes.LISTEN2CH.getForShort()));
            }
        }
        else
        {
            Question questionQuery = new Question();
            questionQuery.setUnitNbr(unitNbr);
            questionQuery.setLessonId(courseContext.getLessonIdByCode(moduleCode));
            dataVO.setDataEn2Ch(getVoFromQuestions(questionQuery,QuestionTypes.EN2CH));
            dataVO.setDataCh2En(getVoFromQuestions(questionQuery,QuestionTypes.CH2EN));
            dataVO.setDataListen2Ch(getVoFromQuestions(questionQuery,QuestionTypes.LISTEN2CH));
            // 持久化paper
            paperService.addPaper(generatePaper(moduleCode,unitNbr,dataVO));
        }
        UnitExamVO unitExamVO = new UnitExamVO();
        unitExamVO.setSuccess(true);
        unitExamVO.setCondition(0);
        unitExamVO.setTotalNbr(0);
        unitExamVO.setData(dataVO);
        return unitExamVO;
    }

    /**
     * 保存测试结果
     * @param moduleCode 8a108cb7-42ad-314f-0142-ad33a0a70001
     * @param resultScore 100
     * @param testType 1（单元测试）   8（每日强化训练）
     * @param unitNbr 1 （当type为8时，该字段为0）
     * @param realDuration 117
     * @param standardDuration 240
     * @param errorNbr 0
     * @param cashPoint 10
     * @param cashPointType 4  6 （当为强化训练时，cashType为6）
     * @param context
     * @return
     *{"success":true,"msg":"记忆已经保存"}
     *
     */
    @RequestMapping(value="AjaxSaveMutiTest",method={RequestMethod.GET,RequestMethod.POST})
    public @ResponseBody
    BasicVO doAjaxSaveMutiTest(String moduleCode, Integer resultScore,
                               Integer testType, Integer unitNbr,
                               Long realDuration, Long standardDuration,
                               Integer errorNbr,
                               Integer cashPoint, Integer cashPointType, ModelMap context) {
        Exam examQuery = new Exam();
        examQuery.setModuleCode(moduleCode);
        examQuery.setResultScore(resultScore);
        examQuery.setTestType(testType);
        examQuery.setUnitNbr(unitNbr);
        examQuery.setRealDuration(realDuration);
        examQuery.setStandardDuration(standardDuration);
        examQuery.setErrorNbr(errorNbr);
        examQuery.setCashPoint(cashPoint);
        examQuery.setCashPointType(cashPointType);

        BasicVO basicVO = new BasicVO();
        try {
            examService.addExam(examQuery);
            basicVO.setSuccess(true);
            basicVO.setMsg("记忆已经保存");
        }catch (Exception e)
        {
            LogTypeEnum.EXCEPTION.error("学生关联课程为空");
            LogTypeEnum.EXCEPTION.error(e.toString());
            basicVO.setSuccess(false);
            basicVO.setMsg("记忆保存失败");
        }
        return basicVO;
    }


    private List<QuestionVO> getQuestionVOfromPaper(JSONObject questionsJson,String questionTypeShort)
    {
        List<QuestionVO> questionVOList = new ArrayList<>();
        for (String questionId : questionsJson.getJSONArray(questionTypeShort).toJavaList(String.class))
        {
            Question question = questionService.getQuestionById(Long.valueOf(questionId)).getDefaultModel();
            ChoicesVO choicesVO = new ChoicesVO(question.getChoices());
            QuestionVO questionVO = new QuestionVO(question.getAnswerIndex(),question.getSpelling()
                    ,question.getVocCode(),question.getQuestion(),choicesVO,Long.valueOf(questionId));
            questionVOList.add(questionVO);
        }
        return questionVOList;
    }
    
    private List<QuestionVO> getVoFromQuestions(Question questionQuery,QuestionTypes questionType)
    {
        List<QuestionVO> questionVOList = new ArrayList<>(UNIT_EXAM_QUESTION_NUMBER);
        questionQuery.setType(questionType.getFullName());
        List<Question> questionList = questionService.getListByExample(questionQuery).getDefaultModel();
        Collections.shuffle(questionList);

        int questionLimit = questionList.size() <= UNIT_EXAM_QUESTION_NUMBER ? questionList.size() : UNIT_EXAM_QUESTION_NUMBER;
        for (int index = 0;index <= questionLimit; index++) {
            Question q = questionList.get(index);
            questionVOList.add(new QuestionVO(q.getAnswerIndex(), q.getSpelling(), q.getVocCode(), q.getQuestion()
                    , new ChoicesVO(q.getChoices()), q.getId()));
        }
        return questionVOList;
    }

    private Paper generatePaper(String moduleCode, Integer unitNbr, DataVO dataVO)
    {
        Paper paper = new Paper();
        paper.setState(State.VALID.getState());
        paper.setUnitNbr(unitNbr);
        paper.setModuleCode(moduleCode);
        paper.setLessonId(courseContext.getLessonIdByCode(moduleCode));
        paper.setCreated(new Date());
        paper.setQuestionNbr(getDataVOQuestionNbr(dataVO));
        JSONObject questions = new JSONObject();
        questions.put(QuestionTypes.EN2CH.getForShort(),generateQuestionIdList(dataVO.getDataEn2Ch()));
        questions.put(QuestionTypes.CH2EN.getForShort(),generateQuestionIdList(dataVO.getDataCh2En()));
        questions.put(QuestionTypes.LISTEN2CH.getForShort(),generateQuestionIdList(dataVO.getDataListen2Ch()));
        paper.setQuestions(questions.toJSONString());
        LogTypeEnum.DEFAULT.info(paper.toString());
        return paper;
    }

    private int getDataVOQuestionNbr(DataVO dataVO)
    {
        return dataVO.getDataCh2En().size()+dataVO.getDataEn2Ch().size()+dataVO.getDataListen2Ch().size();
    }

    private List<Long> generateQuestionIdList(List<QuestionVO> questionVOList)
    {
        List<Long> list = new ArrayList<>();
        for (QuestionVO questionVO : questionVOList)
        {
            list.add(questionVO.getQuestionId());
        }
        return list;
    }
}
