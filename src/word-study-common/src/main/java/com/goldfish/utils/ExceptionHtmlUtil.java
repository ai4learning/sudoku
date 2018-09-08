package com.goldfish.utils;

/**
 * 异常信息堆栈转换为html工具
 * @author cdtangzili
 * @since 2014-12-16 下午4:42:31
 * @version
 */
public class ExceptionHtmlUtil {
	/**
	 * 将异常信息转换为html显示
	 * @param e
	 * @return
	 */
	public static String getExceptionHtml(Exception e) {
		String msg="<a>"+e.getClass().getName()+"<a/> : "+e.getMessage()+"<br/>";
		for(StackTraceElement elem : e.getStackTrace()) {
		    msg+="&nbsp;&nbsp;&nbsp;&nbsp; at "+elem.toString()+"<br/>";
		}
		if (e.getCause()!=null) {
			msg+=getCausedByHtml(e.getCause());
		}
		return msg;
	}
	/**
	 * 将异常信息转换为html显示(递归显示causeby)
	 * 
	 */
	public static String getCausedByHtml(Throwable cause){
		String msg="Caused by : <a>"+cause.getClass().getName()+"<a/> : "+cause.getMessage()+"<br/>";
		for(StackTraceElement elem : cause.getStackTrace()) {
            msg+="&nbsp;&nbsp;&nbsp;&nbsp; at "+elem.toString()+"<br/>";
        }
		if (cause.getCause()!=null) {
			msg+=getCausedByHtml(cause.getCause());
		}
		return msg;
	}
}