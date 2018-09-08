package com.goldfish.web.interceptor.servlet.context;

import java.util.HashSet;
import java.util.Set;

/**
 * 无需做拦截的路径
 * Created by John on 2018/5/6 0006.
 */
public class ExcludeReqstPath {

    private static Set<String> pathSet = new HashSet<String>(20);

    public static boolean isInclude(String str) {
        return pathSet.contains(str);
    }

    public void setPathSet(Set<String> pathSet) {
        ExcludeReqstPath.pathSet = pathSet;
    }
}
