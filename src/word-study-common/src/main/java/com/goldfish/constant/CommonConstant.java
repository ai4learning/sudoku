package com.goldfish.constant;


/**
 * 常用常量类定义
 * @author zhangshibin
 *
 */
public class CommonConstant {

	/** 常用YN  1有效*/
	public static final int STD_YN_YES=1;
	/** 常用YN  0无效*/
	public static final int STD_YN_NO=0;
    
	
	
    /** 标准操作类型，新增*/
	public static final int STD_OPT_TYPE_ADD = 1;
    /** 标准操作类型，删除*/
	public static final int STD_OPT_TYPE_DELETE = 2;
    /** 标准操作类型，修改*/
	public static final int STD_OPT_TYPE_MODIFY = 3;
	

    
    
    /***************************Excel常量定义*******************************/
    /**
     * 导出商品池列表页商品列表	excel模板名称
     */
    public static final String EXPORT_PLIST_SKU= "export_skupool.xls";

    /**
     * 导出待上传的sku数据
     */
    public static final String EXPORT_SKU_UPLOAD= "export_sku_upload.xls";
	

    

	
	/**
	 * 导出列表最大条数，目前最大2000条
	 */
	public static final int EXPORT_LIST_MAX_SIZE = 2000;

    
    
    /**
     * 页面来源参数
     */
    public static final String PAGE_FROM_PARAM_NAME="from";


    public static  final  String REQUEST_USER_KEY="user_key";


	public static final String MEMORY_SUCCESS = "记忆已经保存";
	public static final String MEMORY_FAIL = "记忆保存失败";
	public static final String LOAD_SUCCESS = "完成加载";
	public static final String LOAD_FAIL = "加载失败";
	public static final String PARAMETER_ERROR = "入参错误";
	public static final String SUCCESS = "成功";
	public static final String FAIL = "失败";
	public static final String COMMA_SPLIT = ",";
}
