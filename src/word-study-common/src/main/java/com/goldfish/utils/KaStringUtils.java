package com.goldfish.utils;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;


/**
 * 数据处理工具类
 * @author zhangshibin
 * @since 2014-11-13
 */
public class KaStringUtils {


	
	/**
	 * skuId数据转换,需要去重复
	 * @param
	 * @return
	 */
	public static String joinCollection(Collection collection,String seperator){
		if(CollectionUtils.isEmpty(collection)){
			return "";
		}
		StringBuilder sb=new StringBuilder();
		for (Object object : collection) {
			sb.append(object).append(seperator);
		}
		sb.delete(sb.length()-seperator.length(), sb.length());
		return sb.toString();
	}


	
	/**
	 * 将long的数据转换为String
	 * @param idList
	 * @return
	 */
	public static List<String> long2StringCollection(List<Long> idList) {
		List<String> strList=new ArrayList<String>(idList.size());
		for (Long l: idList) {
			strList.add(String.valueOf(l));
		}
		return strList;
	}


	/**
	 * 将long的数据转换为String
	 * @param strList
	 * @return
	 */
	public static List<Long> string2LongCollection(List<String> strList) {
		List<Long> longList=new ArrayList<Long>(strList.size());
		for (String str: strList) {
			longList.add(Long.valueOf(str));
		}
		return longList;
	}


	/**
	 * 获取UUID
	 * @return
	 */
	public static String getUUID(){
		String uuid=UUID.randomUUID().toString().replace("-", "");
		return uuid;
	}



}
