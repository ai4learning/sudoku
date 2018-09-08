package com.goldfish.constant;

import java.util.UUID;

public class WorkerConstant {

	
	/**
	 事务最小间隔时间 (秒)  1分钟
	 * */
	
	public static final int MIN_TASK_INTERVAL_SEC=1*60;


	/**
	 * 商品变动task name
	 */
	public static final String SKU_OPT_TASK_NAME = "sku_opt_task";
	
	/**
	 * 商品池变动task name
	 */
	public static final String SKU_POOL_OPT_TASK_NAME = "sku_pool_opt_task";

	/**
	 *  对外推送消息 task name
	 * */
	public static final String WARE_PLAT_OUT_MSG_TASK_NAME = "ware_plat_out_msg_task";
	
	/**
	 *  接收消息 task name
	 * */
	public static final String WARE_PLAT_IN_MSG_TASK_NAME = "ware_plat_in_msg_task";
	
	
	
	
	/** 机器实例ID	*/
	public static String INSTANCE_ID=null;

	
	/**
	 * TODO ,后期通过IP及端口号来生成instanceId
	 * 获取实例ID
	 * @return
	 */
	public static String getInstanceId() {
		if(INSTANCE_ID==null){
			UUID uuid=UUID.randomUUID();
			INSTANCE_ID=uuid.toString().replaceAll("\\-", "");
		}
		return INSTANCE_ID;
	}
	
}
