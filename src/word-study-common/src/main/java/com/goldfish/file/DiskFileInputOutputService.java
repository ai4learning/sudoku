package com.goldfish.file;

import com.goldfish.enumtype.FieldInfoType;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

/**
 * 磁盘文件读写服务
 * Created by david on 2015/10/21.
 */
public class DiskFileInputOutputService implements FileInputOutputService {

    /**
     * 基本根路径
     */
    private String basePath;
    private String defaultEncoding="UTF-8";


    @Override
    public boolean write(String fileId, String fileGroup, Object fileBean) throws IOException {
        if(StringUtils.isEmpty(fileId) || StringUtils.isEmpty(fileGroup)
                || fileBean==null){
            return false;
        }

        String filePath=FilenameUtils.concat(basePath,fileGroup+"/"+fileId);
        File file=new File(filePath);

        Class fileBeanClass = fileBean.getClass();
        if(fileBeanClass== String.class){  //直接作为文件写入
             FileUtils.writeStringToFile(file, (String) fileBean, defaultEncoding);
        }else if(BeanUtils.isByteArray(fileBeanClass)){
            FileUtils.writeByteArrayToFile(file, (byte[]) fileBean);
        }else{
            this.writeToGroupData(file, fileBean);
        }
        return true;
    }


    /**
     * 将文件对象写入文件组，其中parentFile是追加了根路径+文件分组+文件id的路径，其后跟上成员变量值即可
     * @param parentFile
     * @param fileBean
     */
    private void writeToGroupData(File parentFile, Object fileBean) {

        Class clazz=fileBean.getClass();
        Field[] fields=clazz.getDeclaredFields();
        for(Field field:fields){
            FileField fileField=field.getAnnotation(FileField.class);
            if(fileField!=null){
                String fieldName=BeanUtils.getFieldName(fileField, field);
                //如果父路径不存在，创建父路径
                if(!parentFile.exists()){
                    parentFile.mkdirs();
                }

                try {
                    //Class fieldType=field.getType();
                    BeanFieldInfo beanFieldInfo=BeanUtils.getFiledInfo(clazz, field);
                    if(beanFieldInfo.getFieldInfoType()== FieldInfoType.STRING || beanFieldInfo.getFieldInfoType()==FieldInfoType.OTHER){
                        String fieldValStr=beanFieldInfo.getStringValue(fileBean);
                        if(fieldValStr!=null){
                            FileUtils.writeStringToFile(new File(parentFile,fieldName),fieldValStr,defaultEncoding);
                        }else{
                            FileUtils.deleteQuietly(new File(parentFile,fieldName));
                        }

                    }else{ //为byte数组
                        byte[] byteValue=beanFieldInfo.getByteArrayValue(fileBean);
                        if(byteValue!=null){
                            FileUtils.writeByteArrayToFile(new File(parentFile, fieldName), byteValue);
                        }else{
                            FileUtils.deleteQuietly(new File(parentFile,fieldName));
                        }
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public <T>  T  read(String fileId, String fileGroup, Class<T> fileClass) throws IOException{
        if(StringUtils.isEmpty(fileId) || StringUtils.isEmpty(fileGroup)
                || fileClass==null){
            return null;
        }

        String filePath=FilenameUtils.concat(basePath,fileGroup+"/"+fileId);
        File file=new File(filePath);
        if(!file.exists()){
            return null;
        }
        if(fileClass==String.class){
            if(!file.isFile()){
                return null;
            }
            return (T) FileUtils.readFileToString(file,defaultEncoding);
        }else if(BeanUtils.isByteArray(fileClass)){
            if(!file.isFile()){
                return null;
            }
            return (T) FileUtils.readFileToByteArray(file);
        }


        return readFromGroupFile(file,fileClass);

    }




    private <T> T readFromGroupFile(File parentFile, Class<T> fileClass) throws IOException {
        T t=null;
        try {
            t=fileClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Field[] fields=fileClass.getDeclaredFields();
        for(Field field:fields){
            FileField fileField=field.getAnnotation(FileField.class);
            if(fileField!=null){
                String fieldName=BeanUtils.getFieldName(fileField, field);
                File fieldFile=new File(parentFile,fieldName);
                if(fieldFile.exists()){
                    BeanFieldInfo beanFieldInfo=BeanUtils.getFiledInfo(fileClass, field);
                    if(beanFieldInfo.getFieldInfoType()== FieldInfoType.STRING || beanFieldInfo.getFieldInfoType()==FieldInfoType.OTHER){
                        String content=FileUtils.readFileToString(fieldFile, defaultEncoding);
                        if(content!=null){
                            beanFieldInfo.setStringValue(t, content);
                        }
                    }else{ //为byte数组
                        byte[] byteValue=FileUtils.readFileToByteArray(fieldFile);
                        if(byteValue!=null){
                            beanFieldInfo.setByteValue(t, byteValue);
                        }
                    }
                }

            }
        }
        return t;
    }


    /**
     * 删除文件内容
     *
     * @param fileId
     * @param fileGroup
     * @return
     */
    @Override
    public boolean deleteFile(String fileId, String fileGroup) {
        if(StringUtils.isEmpty(fileId) || StringUtils.isEmpty(fileGroup)){
            return false;
        }

        String filePath=FilenameUtils.concat(basePath,fileGroup+"/"+fileId);
        File file=new File(filePath);
        return  FileUtils.deleteQuietly(file);
    }




    /**
     * 文件是否存在
     *
     * @param fileId
     * @param fileGroup
     * @return
     */
    @Override
    public boolean fileExist(String fileId, String fileGroup) {
        if(StringUtils.isEmpty(fileId) || StringUtils.isEmpty(fileGroup)){
            return false;
        }
        String filePath=FilenameUtils.concat(basePath,fileGroup+"/"+fileId);
        File file=new File(filePath);
        return file.exists();
    }



    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public String getDefaultEncoding() {
        return defaultEncoding;
    }

    public void setDefaultEncoding(String defaultEncoding) {
        this.defaultEncoding = defaultEncoding;
    }
}
