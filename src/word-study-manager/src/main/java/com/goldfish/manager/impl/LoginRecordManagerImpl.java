package com.goldfish.manager.impl;

import com.goldfish.dao.cache.local.LoginRecordContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import com.goldfish.common.PageQuery;
import com.goldfish.domain.LoginRecord;
import com.goldfish.dao.LoginRecordDao;
import com.goldfish.manager.LoginRecordManager;

/**
 * @author hellosscat
 * @since 2018-5-8
 * 登录Manager实现类
 */
@Component("loginRecordManager")
public class LoginRecordManagerImpl implements LoginRecordManager {
    @Resource(name = "loginRecordDao")
    private LoginRecordDao loginRecordDao;
    @Resource(name = "loginRecordContext")
    private LoginRecordContext localCache;


    public LoginRecord addLoginRecord(LoginRecord loginRecord) {
        loginRecordDao.addLoginRecord(loginRecord);
        // 写入本地缓存
        localCache.addByTraning(loginRecord.getWordTrainingId(),
                loginRecord.getWordTrainingCode(),
                loginRecord);
        return loginRecord;
    }

    public void updateLoginRecord(LoginRecord loginRecord) {
        loginRecordDao.updateLoginRecord(loginRecord);
    }


    public void deleteLoginRecord(Long id) {
        loginRecordDao.deleteLoginRecord(id);
    }


    public LoginRecord getLoginRecordById(Long id) {
        return loginRecordDao.getLoginRecordById(id);
    }


    public LoginRecord getUnique(LoginRecord loginRecord) {
        // 从缓存获取User信息
        LoginRecord loginInfo = localCache.getByTraning(loginRecord.getWordTrainingId(), loginRecord.getWordTrainingCode());
        if (loginInfo != null) {
            return loginInfo;
        }
        loginInfo = loginRecordDao.getUnique(loginRecord);
        // 写入本地缓存
        localCache.addByTraning(loginInfo.getWordTrainingId(),
                loginInfo.getWordTrainingCode(),
                loginInfo);
        return loginInfo;
    }


    public List<LoginRecord> getListByExample(LoginRecord loginRecord) {
        return loginRecordDao.getListByExample(loginRecord);
    }


    public List<LoginRecord> getLoginRecordByPage(PageQuery pageQuery) {
        return loginRecordDao.getLoginRecordByPage(pageQuery.getParams());
    }

    public int count(PageQuery pageQuery) {
        return loginRecordDao.count(pageQuery.getParams());
    }

    @Override
    public void changeLoginRecordState(Map<String, Object> params) {
        loginRecordDao.changeState(params);
    }

    /*******
     * getter and setter
     ***/

    public LoginRecordDao getLoginRecordDao() {
        return loginRecordDao;
    }

    public void setLoginRecordDao(LoginRecordDao loginRecordDao) {
        this.loginRecordDao = loginRecordDao;
    }
}
