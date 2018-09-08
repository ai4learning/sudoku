package com.goldfish.file;

import com.goldfish.enumtype.FieldInfoType;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;

/**
 *
 * 通过反射对bean进行处理
 * Created by david on 2015/11/29.
 */
public class BeanUtils {


    /**
     * 获取域对象的信息
     * @param parentClass
     * @param field
     */
    public static BeanFieldInfo getFiledInfo(Class parentClass,Field field){
        BeanFieldInfo beanFieldInfo=new BeanFieldInfo(parentClass,field);
        
        Class fieldType=field.getType();
        //是否为字符串
        if(fieldType==String.class){
            beanFieldInfo.fieldInfoType= FieldInfoType.STRING;

        } //是否为byte[] ，用于存储二进制数组
        else if(isByteArray(fieldType)){
            beanFieldInfo.fieldInfoType= FieldInfoType.BYTE_ARRAY;
        }else{
            beanFieldInfo.fieldInfoType=FieldInfoType.OTHER;
        }

        return beanFieldInfo;
    }

    /**
     * 判断是否为byte数组
     * @param fileBeanClass
     * @return
     */
    public static boolean isByteArray(Class fileBeanClass) {
        return (fileBeanClass.isArray() && fileBeanClass.getComponentType()==byte.class);
    }


    /**
     * 获取后缀名称
     *
     * 如果Annotation 获取到的field值为空，就用成员变量名称
     * 如果有后缀，加上后缀名称
     * @param fileField
     * @param field
     * @return
     */
    public static String getFieldName(FileField fileField, Field field) {
        String fieldName=fileField.field();
        if(StringUtils.isEmpty(fieldName)){
            fieldName=field.getName();
        }
        String ext=fileField.ext();
        if(StringUtils.isNotEmpty(ext)){
            fieldName=fieldName+"."+ext;
        }
        return fieldName;
    }
}
