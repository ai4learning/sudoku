package com.goldfish.file;


import com.alibaba.fastjson.JSON;
import com.goldfish.enumtype.FieldInfoType;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by david on 2015/11/29.
 */
public class BeanFieldInfo {


    Class parentClass;

    Field field;

    FieldInfoType fieldInfoType;


    public BeanFieldInfo(Class parentClass, Field field) {
        this.parentClass=parentClass;
        this.field=field;
    }


    public Class getParentClass() {
        return parentClass;
    }

    public void setParentClass(Class parentClass) {
        this.parentClass = parentClass;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public FieldInfoType getFieldInfoType() {
        return fieldInfoType;
    }

    public void setFieldInfoType(FieldInfoType fieldInfoType) {
        this.fieldInfoType = fieldInfoType;
    }


    public String getStringValue(Object object) {
        String getName=makeGetName(field.getName());
        Object resultObject=null;
        try {
          Method getMethod= parentClass.getMethod(getName);
          resultObject=getMethod.invoke(object);
        } catch (NoSuchMethodException e) {
            if(field.isAccessible()){
                try {
                    resultObject=field.get(object);
                } catch (IllegalAccessException e1) {}
            }
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        if(resultObject==null){
            return null;
        }
        //可能得到一个复杂对象
        if(fieldInfoType==FieldInfoType.OTHER){
            return JSON.toJSONString(resultObject);
        }else if(fieldInfoType==FieldInfoType.STRING){
            return (String) resultObject;
        }
        return null;
    }


    public byte[] getByteArrayValue(Object object) {
        String getName=makeGetName(field.getName());
        Object resultObject=null;
        try {
            Method getMethod= parentClass.getMethod(getName);
            resultObject=getMethod.invoke(object);
        } catch (NoSuchMethodException e) {
            //如果方法不存在，尝试通过直接获取对象值
            try {
                if(field.isAccessible()){
                    resultObject=field.get(object);
                }
            } catch (IllegalAccessException e1) {}
        } catch (InvocationTargetException e) {

        } catch (IllegalAccessException e) {

        }
        if(resultObject==null){
            return null;
        }
        return (byte[]) resultObject;

    }




    /**
     * 设置byte数组类型的值
     * @param object
     * @param byteValue
     */
    public  void setByteValue(Object object, byte[] byteValue) {
        String setName=makeSetName(field.getName());
        try {
            Method setMethod= parentClass.getMethod(setName);
            setMethod.invoke(object,byteValue);
        } catch (NoSuchMethodException e) {
            //如果方法不存在，尝试通过直接获取对象值
            try {
                if(field.isAccessible()){
                    field.set(object,byteValue);
                }
            } catch (IllegalAccessException e1) {}
        } catch (InvocationTargetException e) {

        } catch (IllegalAccessException e) {

        }

    }

    /**
     * 设置String类型的值
     * @param object
     * @param content
     */
    public  void setStringValue(Object object, String content) {
        String setName=makeSetName(field.getName());
        Object setObj=content;
        if(fieldInfoType==FieldInfoType.OTHER){
            setObj=JSON.parseObject(content);
        }

        try {
            Method setMethod= parentClass.getMethod(setName,field.getType());
            setMethod.invoke(object,setObj);
        } catch (NoSuchMethodException e) {
            //如果方法不存在，尝试通过直接获取对象值
            try {
                if(field.isAccessible()){
                    field.set(setObj,setObj);
                }
            } catch (IllegalAccessException e1) {}
        } catch (InvocationTargetException e) {

        } catch (IllegalAccessException e) {

        }

    }



    /**
     * To make the set function name
     * @param keyName
     * @return
     */
    public static String makeSetName(String keyName) {
        return "set"+keyName.substring(0,1).toUpperCase()+keyName.substring(1);
    }

    /**
     * To make the get function name
     * @param keyName
     * @return
     */
    public static String makeGetName(String keyName) {
        return "get"+keyName.substring(0,1).toUpperCase()+keyName.substring(1);
    }
}
