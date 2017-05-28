package com.fmSystem.Controller;

import com.fmSystem.Bean.Po.TestPo;
import com.fmSystem.Service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.logging.Logger;

import static java.util.logging.Level.INFO;
import static java.util.logging.Level.WARNING;

/**
 * Created by 74551 on 2017/4/13.
 */
@Controller()
public class TestController {
    @Autowired
    private ITestService testService;

    Logger logger =  Logger.getLogger("testController");

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String  getIndex(){
        System.out.println("fuck");
        logger.log(WARNING,"fuck");
        return  "redirect:index.html";
    }
}
