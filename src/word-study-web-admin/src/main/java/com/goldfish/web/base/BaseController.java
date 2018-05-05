package com.goldfish.web.base;
import com.goldfish.common.CommonResult;
import com.google.gson.Gson;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wangyong on 2015/10/9.
 */
public class BaseController {

    @InitBinder
    //此处的参数也可以是ServletRequestDataBinder类型
    public void initBinder(ServletRequestDataBinder binder) throws Exception {
        //注册自定义的属性编辑器
        //1、日期
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        CustomDateEditor dateEditor = new CustomDateEditor(df, true);
        //表示如果命令对象有Date类型的属性，将使用该属性编辑器进行类型转换
        binder.registerCustomEditor(Date.class, dateEditor);
    }

    public void toVm(CommonResult result,ModelMap context){
        context.putAll(result.getReturnMap());
    }

    public  void sendJsonBack(Object object,HttpServletResponse response){
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        try {
            String jsonResult=null;
            if(object instanceof String){
                jsonResult= (String) object;
            }else{
                jsonResult= new Gson().toJson(object);
            }
            response.getWriter().print(jsonResult);
        } catch (IOException e) {
//            LogTypeEnum.DEFAULT.error(e,"");
        }
    }


    public int getPageSize(HttpServletRequest request, int min, int max) {
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        return pageSize;
    }
}
