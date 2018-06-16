package com.goldfish.api;

import com.goldfish.domain.SelfWords;
import com.goldfish.domain.User;
import com.goldfish.domain.Word;
import com.goldfish.service.SelfWordsService;
import com.goldfish.service.WordService;
import com.goldfish.vo.BasicVO;
import com.goldfish.web.base.BaseController;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2018/6/16 0016.
 * 收藏模块
 */
public class AjaxCollectionController extends BaseController {

    private static final String memorySuccess = "记忆已经保存";
    private static final String memoryFail = "记忆已经保存";
    private static final String loadSuccess = "记忆已经保存";
    private static final String loadFail = "记忆已经保存";

    @Resource
    private SelfWordsService selfWordsService;
    @Resource
    private WordService wordService;

    /**
     * /api/Ajax/AjaxPostCollect 收藏单词
     入参
     {
     vocCode: "8a108cb7-42aa-d911-0142-ad8120300028"
     }
     出参
     {
     "success": true,
     "msg": "记忆已经保存"
     }
     */
    @RequestMapping(value="AjaxPostCollect",method={RequestMethod.GET,RequestMethod.POST})
    public @ResponseBody Object doAjaxPostCollect(String vocCode, ModelMap context)
    {
        BasicVO vo = new BasicVO();
        // 1.装填用户信息
        User user = this.getUserInfo();
        if (user == null) {
            vo.setSuccess(false);
            vo.setMsg("记忆保存失败");
            return vo;
        }
        // 2.使用vocCode查询单词
        Word word = new Word();

        // 3.使用SelfWord保存收藏
        SelfWords selfWords = new SelfWords();
        selfWords.setVocCode(vocCode);
        selfWords.setStudentId(user.getId());
        selfWords.setUserCode(user.getUserCode());
        return null;
    }

    /**
     * /api/Ajax/GetVocabulary
     我的收藏单词信息

     入参
     orderType：0表示其他，1表示小学，2表示初中，3表示高中
     {
     orderType: '0,1,2,3'
     }
     出参
     {
     "data": [{
     "Id": 113620,
     "vocCode": "8a108cb7-4c56-483d-014c-564f271e065a",
     "spelling": "motor neurone disease",
     "meaning": "n.运动神经元病",
     "soundMarkUs": "",
     "vocIndex": 1,
     "UnitId": 0,
     "isCollected": false
     }, {
     "Id": 1384,
     "vocCode": "8a108cb7-42aa-d911-0142-ad812032002b",
     "spelling": "fat",
     "meaning": "肥的;油腻的",
     "soundMarkUs": "[fæt]",
     "vocIndex": 0,
     "UnitId": 0,
     "isCollected": false
     },
     ...
     ],
     "success": true,
     "msg": "完成加载"
     }
     */
    @RequestMapping(value="GetVocabulary",method={RequestMethod.GET,RequestMethod.POST})
    public @ResponseBody Object doGetVocabulary(String orderType, ModelMap context)
    {
        return null;
    }

    /**
     * /api/Ajax/GetVocabularyCount
     我的收藏单词总数

     入参
     orderType：0表示其他，1表示小学，2表示初中，3表示高中
     {
     orderType： '0,1,2,3'
     }
     出参
     {
     "success": true,
     "msg": "完成加载",
     "totalNbr": 45
     }
     */
    @RequestMapping(value="GetVocabularyCount",method={RequestMethod.GET,RequestMethod.POST})
    public @ResponseBody Object doGetVocabularyCount(String orderType, ModelMap context)
    {
        return null;
    }
}
