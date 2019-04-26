package com.goldfish.api;

import com.goldfish.common.log.LogTypeEnum;
import com.goldfish.importData.ImportCourseService;
import com.goldfish.vo.BasicVO;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.Date;

/**
 * @author zhangjingtao
 * @date 2019/2/12 0012.
 */
@Controller
@RequestMapping("/api/Ajax")
public class AjaxImportController {
    @Resource
    private ImportCourseService importCourseService;

    @RequestMapping(value = "courseUpload", method = {RequestMethod.POST})
    @Transactional
    public @ResponseBody
    BasicVO courseUpload(MultipartFile file) {
        try {
            LogTypeEnum.DEFAULT.info("获取到文件：" + file.getOriginalFilename());
            String path = "E:\\" + new Date().getTime() + file.getOriginalFilename();
            File newFile = new File(path);
            file.transferTo(newFile);
            this.importCourseService.importCourse(newFile);
            return new BasicVO(true, "导入课程成功");
        }catch (Exception e){
            LogTypeEnum.DEFAULT.warn(e.toString());
            return new BasicVO(false, "导入课程失败");
        }
    }
}
