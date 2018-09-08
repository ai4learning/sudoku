package com.goldfish.common;

import com.goldfish.common.log.LogBusinessId;

import java.io.Serializable;
import java.util.*;

/**
 * 公用返还结果
 * @author david
 *
 * @param <T>
 */
public class CommonResult<T> implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public  String modelKey="value";

	private boolean success;

	private String returnCode;

	private String message;

	private Map<String,Object> resultMap ;



	public CommonResult() {
		this(false);
	}


	public CommonResult(boolean success) {
		super();
		this.success = success;
		resultMap=new HashMap<String, Object>();
	}


	public CommonResult(boolean success, Map<String,Object> resultMap) {
		super();
		this.success = success;
		this.resultMap=resultMap;
	}

	public void addDefaultModel(T value){
		checkResultMapSafe();
		resultMap.put(modelKey, value);
	}

	@SuppressWarnings("unchecked")
	public T getDefaultModel(){
		return  resultMap==null ? null:(T)resultMap.get(modelKey);
	}

	public void addDefaultModel(String key, T value) {
		this.modelKey=key;
		checkResultMapSafe();
		resultMap.put(modelKey, value);
	}

	public void addModel(String key,Object value){
		checkResultMapSafe();
		resultMap.put(key, value);
	}

	public Object getModel(String key){
		return resultMap==null? null: resultMap.get(key);
	}


	public boolean isSuccess() {
		return success;
	}


	public void setSuccess(boolean success) {
		this.success = success;
	}





	public String getReturnCode() {
		return returnCode;
	}





	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}





	public String getMessage() {
		return message;
	}





	public void setMessage(String message) {
		this.message = message;
	}


	/**
	 * 设置结果信息
	 * @param isSuccess
	 * @param message
	 */
	public CommonResult setResultInfo(boolean isSuccess,String message){
		this.success=isSuccess;
		this.message=message;
		return this;
	}


	public CommonResult setResultInfo(boolean success,String message,String returnCode){
		this.success=success;
		this.message=message;
		this.returnCode=returnCode;
		return this;
	}


	public Map<String,Object> getReturnMap(){
		return getReturnMap(false);
	}


	//获取结果对象Map 用于json转换
	public Map<String,Object> getReturnMap(boolean isNew){
		Map<String,Object> returnMap=null;
		if(isNew || resultMap==null){
			returnMap=new HashMap<String, Object>();
			if(resultMap!=null){
				returnMap.putAll(this.resultMap);
			}
		}else{
			returnMap=this.resultMap;
		}
		returnMap.put("success", this.success);
		returnMap.put("returnCode", this.returnCode);
		returnMap.put("message", this.message);

		return returnMap;
	}


	/**
	 * 检查map是否为空
	 */
	private void checkResultMapSafe(){
		if(resultMap==null){
			synchronized (this){
				//获取锁定后，多验证一次
				if(resultMap==null){
					resultMap=new HashMap<String, Object>();
				}
			}
		}
	}


	/**
	 * 没有MAP的result,节约内存空间
	 * @return
	 */
	public static CommonResult newSimpleResult(){
		return new CommonResult(false,null);
	}
	public CommonResult<T> failed(String message){

		return result(false,message);
	}

	public CommonResult<T> result(boolean b, String message) {
		this.success = b;
		this.message = message;
		return this;
	}

	public CommonResult<T> succeed() {
		return succeed(null);
	}
	public CommonResult<T> succeed(String message) {
		return result(true, message);
	}



	/**
	 * 获取Raw map数据
	 */
	public Map<String,Object> getRawMap(){
		return resultMap;
	}

	/**
	 * 合并两个结果
	 * @param result
	 */
	public void mergeResult(CommonResult result){
		if(result!=null && result.resultMap!=null){
			this.resultMap.putAll(result.resultMap);
		}
	}
	public CommonResult<T> listAdd(String key, Object item) {
		Object obj = this.resultMap.get(key);
		List<Object> list = null;
		if(obj == null){
			list = new ArrayList<Object>();
			resultMap.put(key,list);
		} else if(obj instanceof  List){
			list = (List<Object>)obj;
		} else {
			throw new IllegalArgumentException(key+"已存在，但不是一个list!.obj="+obj);
		}
		list.add(item);
		return this;
	}

	public CommonResult<T> put(String key, Object value) {
		if(key != null && key.length() > 0){
			this.resultMap.put(key, value);
		}
		return this;
	}

	public static <T> CommonResult<T> create() {
		CommonResult<T> result = new CommonResult<T>();
		result.put("requestId", LogBusinessId.requestId());
		return result;
	}

	@Override
	public String toString() {
		return "CommonResult{" +
				"modelKey='" + modelKey + '\'' +
				", success=" + success +
				", returnCode='" + returnCode + '\'' +
				", message='" + message + '\'' +
				", resultMap=" + resultMap +
				'}';
	}

	public boolean isDefaultModelEmpty() {
		T t = getDefaultModel();
		if(t == null){
			return true;
		}
		if(t instanceof Collection){
			Collection c = (Collection)t;
			return c.isEmpty();
		}
		return false;
	}

	public CommonResult<T> addWarn(String message) {
		return listAdd("warn",message);
	}
}