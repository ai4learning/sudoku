package com.goldfish.web.base;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by wcf on 2018/6/5.
 */
@Controller
@RequestMapping(value="/**", method= RequestMethod.GET)
public class IndexController {
    public String index() {
        return "/";
    }
}
