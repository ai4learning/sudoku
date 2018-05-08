package com.goldfish.utils;

import org.apache.commons.lang3.StringUtils;


/**
 * url工具；类
 * @author zhangshibin
 * @since 2014-12-24
 */
public class JdUrlUtil {

	/**
	 * 获取商品详情页url
	 * @param skuId
	 * @return
	 */
	public static String getItemPageUrl(String skuId){
		if(StringUtils.isNotEmpty(skuId)){
			return "http://item.jd.com/"+skuId+".html";
		}else{
			return "javascript:;";
		}
	}
	
	/**
	 * 获取商品详情页url
	 * @param skuId
	 * @return
	 */
	public static String getItemPageUrl(Long skuId){
		return getItemPageUrl(skuId==null ? null:String.valueOf(skuId));
	}
}
