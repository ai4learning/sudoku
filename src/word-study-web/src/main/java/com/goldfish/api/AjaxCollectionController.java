package com.goldfish.api;

import com.goldfish.web.base.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2018/6/16 0016.
 * 收藏模块
 */
public class AjaxCollectionController extends BaseController {

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
    public @ResponseBody Object doAjaxPostCollect()
    {
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
    public @ResponseBody Object doGetVocabulary()
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
    public @ResponseBody Object doGetVocabularyCount()
    {
        return null;
    }
}
