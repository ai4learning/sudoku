package com.goldfish.file;

import java.io.IOException;

/**
 * 文件读写工具类
 * （分为文件系统和mongoDB等，为接口，方便之后进行相关的扩展实现）
 * Created by david on 2015/10/21.
 */
public interface FileInputOutputService {

    /**
     * 写到文件系统中
     * @param fileId
     * @param fileGroup
     * @param fileBean
     */
    public boolean write(String fileId, String fileGroup, Object fileBean) throws IOException;


    /**
     * 读取文件内容为Java对象
     * @param fileId
     * @param fileGroup
     * @param fileClass
     */
    public <T>  T  read(String fileId, String fileGroup, Class<T> fileClass) throws IOException;


    /**
     * 删除文件内容
     * @param fileId
     * @param fileGroup
     * @return
     */
    public boolean deleteFile(String fileId, String fileGroup);



    /**
     * 文件是否存在
     *
     * @param fileId
     * @param fileGroup
     * @return
     */
    public boolean fileExist(String fileId, String fileGroup);
}
