package com.fmSystem.Controller;

import com.fmSystem.Bean.Vo.CommodityVo;
import com.fmSystem.Bean.Vo.LayoutVo;
import com.fmSystem.Service.IDAService;
import com.fmSystem.Service.IUserService;
import com.fmSystem.Utility.ReturnMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by 74551 on 2017/6/2.
 */
@Controller
@RequestMapping("/DAController")
public class DAController {
    @Autowired
    private IDAService daService;
    Logger logger = Logger.getLogger("DAController");

    @ResponseBody
    @RequestMapping(value = "/layoutOnload", method = RequestMethod.POST, produces={"application/json;charset=UTF-8"})
    private ReturnMessage layoutOnload(HttpSession httpSession){
        int shopId = (int)httpSession.getAttribute("SHOP_ID");
        ReturnMessage returnMessage = daService.getCommodityList(shopId);
        return returnMessage;
    }

    @ResponseBody
    @RequestMapping(value = "/association", method = RequestMethod.POST, produces={"application/json;charset=UTF-8"})
    private ReturnMessage association(@RequestBody List<CommodityVo> commodityList, HttpSession httpSession){
        int shopId = (int)httpSession.getAttribute("SHOP_ID");
        List<String> nameList = new ArrayList<>();
        commodityList.forEach((CommodityVo e) ->{
            nameList.add(e.getCommodityName());
        });
        ReturnMessage returnMessage = daService.associationAnalysis(nameList, shopId);
        return returnMessage;
    }

    @ResponseBody
    @RequestMapping(value = "/warehouse", method = RequestMethod.POST, produces={"application/json;charset=UTF-8"})
    private ReturnMessage warehouse(@RequestBody CommodityVo commodityVo, HttpSession httpSession){
        int shopId = (int)httpSession.getAttribute("SHOP_ID");

        ReturnMessage returnMessage = daService.warehouseCal(commodityVo.getCommodityName(), shopId);
        return returnMessage;
    }

    @ResponseBody
    @RequestMapping(value = "/layout", method = RequestMethod.POST, produces={"application/json;charset=UTF-8"})
    private ReturnMessage layout(@RequestBody List<LayoutVo>layoutVos, HttpSession httpSession){
        int shopId = (int)httpSession.getAttribute("SHOP_ID");
        ReturnMessage returnMessage = daService.layout(shopId);
        return returnMessage;
    }


}
