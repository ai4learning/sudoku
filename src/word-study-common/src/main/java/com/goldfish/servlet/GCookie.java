package com.goldfish.servlet;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;

/**
 * 不加密cookie
 * Created by John on 2018/5/5 0005.
 */
public class GCookie {

    /**
     * 暂不做加密
     */
//    private CookieCipherTools cookieCipherTools;
    /**
     * cookie 名
     */
    private String name;
    /**
     * cookie domain
     */
    private String domain;
    /**
     * cookie path
     */
    private String path;
    /**
     * cookie 超时时间
     */
    private int expiry;
    /**
     * cookie key
     *
     * @see #encrypt
     */
    private String key;
    /**
     * cookie 加密
     *
     * @see #key
     */
    private boolean encrypt;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getExpiry() {
        return expiry;
    }

    public void setExpiry(int expiry) {
        this.expiry = expiry;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public boolean isEncrypt() {
        return encrypt;
    }

    public void setEncrypt(boolean encrypt) {
        this.encrypt = encrypt;
    }

//    public void setCookieCipherTools(CookieCipherTools cookieCipherTools) {
//        this.cookieCipherTools = cookieCipherTools;
//    }

    public Cookie newCookie(String value) {
        String newValue;
        if (!StringUtils.isEmpty(value)) {
//            newValue = isEncrypt() ? cookieCipherTools.encrypt(value, getKey()) : value;
            newValue = value;
        } else {
            newValue = value;
        }
        Cookie cookie = new Cookie(name, newValue);
        if (!StringUtils.isBlank(domain)) {
            cookie.setDomain(domain);
        }
        if (!StringUtils.isBlank(path)) {
            cookie.setPath(path);
        }
        if (expiry > 0) {
            cookie.setMaxAge(expiry);
        }
        return cookie;
    }

    public String getValue(String value) {
        return value;
    }
}
