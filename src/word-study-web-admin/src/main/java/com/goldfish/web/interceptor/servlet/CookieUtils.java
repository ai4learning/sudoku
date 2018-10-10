package com.goldfish.web.interceptor.servlet;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * cookie工具:
 * 1.从quest中获取登录相关cookie
 * 2.向response中写入登录cookie
 * Time: 10:57:49
 */
public class CookieUtils {
    private final static Log log = LogFactory.getLog(CookieUtils.class);
    // 保存cookie上下文
    private Map<String, GCookie> cookieMap;

    /**
     * 获取cookie值
     * @param servletRequest
     * @param name
     * @return
     */
    public String getCookieValue(HttpServletRequest servletRequest, String name) {
        Cookie[] cookies = servletRequest.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                String cookieName = cookie.getName();
                if (cookieName.equals(name)) {
                    if (cookieMap != null && cookieMap.containsKey(name)) {
                        GCookie gCookie = cookieMap.get(name);
                        return gCookie.getValue(cookie.getValue());
                    }
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    /**
     * 从response中删除cookie
     *
     * @param servletResponse
     * @param name
     */
    public void deleteCookie(HttpServletResponse servletResponse, String name) {
        Cookie cookie;
        if (cookieMap != null && cookieMap.containsKey(name)) {
            GCookie gCookie = cookieMap.get(name);
            cookie = gCookie.newCookie(null);
        } else {
            cookie = new Cookie(name, null);
        }
        cookie.setMaxAge(0);
        servletResponse.addCookie(cookie);
    }

    /**
     * 设置cookie
     *
     * @param servletResponse
     * @param name
     * @param value
     */
    public void setCookie(HttpServletResponse servletResponse, String name, String value) {
        if (cookieMap != null && cookieMap.containsKey(name)) {
            GCookie gCookie = cookieMap.get(name);

            Cookie cookie = gCookie.newCookie(value);
            servletResponse.addCookie(cookie);
        } else {
            throw new RuntimeException("Cookie " + name + " is undefined!");
        }
    }

    /**
     * 初始化CookieMap，用于对Cookie的key和域名做限制
     *
     * @param gCookieList
     */
    public void setGCookie(List<GCookie> gCookieList) {
        if (gCookieList != null) {
            HashMap<String, GCookie> jdCookieHashMap = new HashMap<String, GCookie>(gCookieList.size());
            for (GCookie jdCookie : gCookieList) {
                jdCookieHashMap.put(jdCookie.getName(), jdCookie);
            }
            cookieMap = jdCookieHashMap;
        }
    }

    /**
     * cookie超期无效则从response中清除cookie
     *
     * @param request
     * @param response
     */
    public void invalidate(HttpServletRequest request, HttpServletResponse response) {
        if (cookieMap != null && cookieMap.size() > 0) {
            for (Map.Entry<String, GCookie> entry : cookieMap.entrySet()) {
                String key = entry.getKey();
                GCookie gCookie = entry.getValue();
                if (gCookie.getExpiry() < 1) {
                    if (StringUtils.isNotEmpty(getCookieValue(request, key))) {
                        deleteCookie(response, key);
                    }
                }
            }
        }
    }

}