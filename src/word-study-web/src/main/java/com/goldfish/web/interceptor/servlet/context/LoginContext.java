package com.goldfish.web.interceptor.servlet.context;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 登录上下文:
 * 1.保存登录Cookie上下文；
 * 2.定义cookie 内容格式（value内容属性的格式可以业务自己确定，domain和path这些cookie属性可统一设置也可不设置）
 * 3.cookie内容的生成和解析；
 *
 * Created by John on 2018/5/6 0006.
 */
public class LoginContext {

    private static final ThreadLocal<LoginContext> holder = new ThreadLocal();
    private static final Logger log = LoggerFactory.getLogger(LoginContext.class);

    public static final String USER_KEY = "_user_";
    public static final String USER_TOKEN = "_token_";
    public static final String TRAINING_ID = "WordTrainingID";
    public static final String TRAINING_CODE = "WordTrainingCode";

    private String pin;
    private String userName;
    private String token;
    private String trainingId;
    private String trainingCode;

    // 创建时间
    private long created;
    // 过期时间
    private long expires;

    public LoginContext() {
    }


    public static void setLoginContext(LoginContext loginContext) {
        holder.set(loginContext);
    }

    public static LoginContext getLoginContext() {
        return (LoginContext)holder.get();
    }

    public static void remove() {
        holder.remove();
    }

    /**
     * 根据Value解析出LoginContext上下文
     *
     * @param key
     * @param value
     * @return
     */
    public static LoginContext parse(String key, String value) {
        LoginContext context = new LoginContext();
        setValue(key,value, context);
        return context;
    }

    /**
     * 解析cooKie并设置Value
     * Cookie格式： value=xxxx,created=12343434,expires=535324324
     * @param key
     * @param value
     * @param context
     */
    protected static void setValue(String key, String value, LoginContext context) {
        if(StringUtils.isNotEmpty(value)) {
            String[] fields = value.split("#");
            int len = fields.length;

            for(int i = 0; i < len; ++i) {
                String keyValues = fields[i];
                String[] keyValue = keyValues.split("=");
                if(keyValue.length == 2) {
                    try {
                        String subKeyName = keyValue[0];
                        if ("v_".equals(subKeyName)) {
                            if (USER_KEY.equals(key)) {
                                context.setPin(keyValue[1]);
                                context.setUserName(keyValue[1]);
                            }
                            if (USER_TOKEN.equals(key)) {
                                context.setToken(keyValue[1]);
                            }
                            if (TRAINING_ID.equals(key)) {
                                context.setTrainingId(keyValue[1]);
                            }
                            if (TRAINING_CODE.equals(key)) {
                                context.setTrainingCode(keyValue[1]);
                            }
                        }
                        if ("c_".equals(subKeyName)) {
                            context.setCreated(Long.parseLong(keyValue[1]));
                        }
                        if ("e_".equals(subKeyName)) {
                            context.setExpires(Long.parseLong(keyValue[1]));
                        }
                    } catch (Exception var9) {
                        log.warn("praser error!", var9);
                    }
                }
            }
        }
    }

    /**
     * 根据key获取Cookie Value
     * 写简单了，Cookie Value有一定的生成规则
     * @param key
     * @return
     */
    public String toCookieValue(String key) {
        StringBuilder sb = new StringBuilder("v_=");
        if (TRAINING_ID.equals(key)) {
            sb.append(trainingId);
        }
        if (TRAINING_CODE.contains(key)) {
            sb.append(trainingCode);
        }
        if (USER_KEY.contains(key)) {
            sb.append(userName);
        }
        if (USER_TOKEN.contains(key)) {
            sb.append(token);
        }
        sb.append("#c_=").append(created);
        sb.append("#e_=").append(expires);
        return sb.toString();
    }


    /**
     * 是否已登录
     * @return
     */
    public boolean isLogin() {
        // 学生端
        if (StringUtils.isNotEmpty(trainingId) && StringUtils.isNotEmpty(trainingCode)) {
            return true;
        }

        // admin端
        if (StringUtils.isNotEmpty(userName) && StringUtils.isNotEmpty(token)) {
            return true;
        }
        return false;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(String trainingId) {
        this.trainingId = trainingId;
    }

    public String getTrainingCode() {
        return trainingCode;
    }

    public void setTrainingCode(String trainingCode) {
        this.trainingCode = trainingCode;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public long getExpires() {
        return expires;
    }

    public void setExpires(long expires) {
        this.expires = expires;
    }
}
