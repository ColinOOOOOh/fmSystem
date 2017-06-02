package com.fmSystem.Controller;

import com.fmSystem.Bean.Po.CommodityPo;
import com.fmSystem.Bean.Vo.CommodityVo;
import com.fmSystem.Bean.Vo.ShopVo;
import com.fmSystem.Bean.Vo.YearMonthVo;
import com.fmSystem.Service.IShopService;
import com.fmSystem.Utility.ReturnMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by 74551 on 2017/5/31.
 */
@Controller
@RequestMapping("/shopController")
public class ShopController {
    @Autowired
    IShopService shopService;
    Logger logger = Logger.getLogger("shopController");

    @ResponseBody
    @RequestMapping(value = "/uploadForm",  method = RequestMethod.POST, produces={"application/json;charset=UTF-8"})
    public ReturnMessage uploadFrom(@RequestParam("file")CommonsMultipartFile file, YearMonthVo yearMonthVo, HttpSession httpSession) throws IOException{
        int shopId = (int)httpSession.getAttribute("SHOP_ID");

        File newFile = new File("E:/fmSystemFile");
        file.transferTo(newFile);
        ReturnMessage returnMessage = shopService.uploadRecordFile(newFile, shopId, yearMonthVo);
        return returnMessage;
    }

    @ResponseBody
    @RequestMapping(value = "/addEmployee",  method = RequestMethod.POST, produces={"application/json;charset=UTF-8"})
    public ReturnMessage addEmployee(@RequestBody ShopVo shopVo, HttpSession httpSession){
        int userId = shopVo.getUserId();
        int shopId = (int)httpSession.getAttribute("SHOP_ID");

        ReturnMessage returnMessage = shopService.addEmployee(userId, shopId);
        return returnMessage;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteEmployee",  method = RequestMethod.POST, produces={"application/json;charset=UTF-8"})
    public ReturnMessage removeEmployee(@RequestBody ShopVo shopVo, HttpSession httpSession){
        int userId = shopVo.getUserId();
        int shopId = (int)httpSession.getAttribute("SHOP_ID");

        ReturnMessage returnMessage = shopService.removeEmployee(userId, shopId);
        return returnMessage;
    }

    @ResponseBody
    @RequestMapping(value = "/searchEmployeeUser",  method = RequestMethod.POST, produces={"application/json;charset=UTF-8"})
    public ReturnMessage searchEmployeeUser(@RequestBody ShopVo shopVo){
        String userPhone = shopVo.getUserPhone();
        ReturnMessage returnMessage = shopService.searchEmployeeUser(userPhone);
        return returnMessage;
    }

    @ResponseBody
    @RequestMapping(value = "/getEmployeeList",  method = RequestMethod.POST, produces={"application/json;charset=UTF-8"})
    public ReturnMessage getEmployeeList(HttpSession httpSession){
        int shopId = (int)httpSession.getAttribute("SHOP_ID");
        ReturnMessage returnMessage = shopService.getEmployeeList(shopId);
        return returnMessage;
    }
//2.6
    @ResponseBody
    @RequestMapping(value = "/modifyShopInfoOnload",  method = RequestMethod.POST, produces={"application/json;charset=UTF-8"})
    public ReturnMessage modifyShopInfoOnload(HttpSession httpSession){
        int shopId = (int)httpSession.getAttribute("SHOP_ID");
        ReturnMessage returnMessage = shopService.modifyShopInfoOnload(shopId);
        return returnMessage;
    }
//2.7
    @ResponseBody
    @RequestMapping(value = "/modifyShopInfo",  method = RequestMethod.POST, produces={"application/json;charset=UTF-8"})
    public ReturnMessage modifyShopInfoOnload(@RequestBody List<CommodityPo> commodityPos, HttpSession httpSession) {

        int shopId = (int) httpSession.getAttribute("SHOP_ID");
        ReturnMessage returnMessage = shopService.modifyShopInfo(commodityPos, shopId);
        return returnMessage;
    }
//2.8
    @ResponseBody
    @RequestMapping(value = "/getMonthlySales",  method = RequestMethod.POST, produces={"application/json;charset=UTF-8"})
    public ReturnMessage getMonthlySales(@RequestBody YearMonthVo yearMonthVo, HttpSession httpSession) {

        int shopId = (int) httpSession.getAttribute("SHOP_ID");
        ReturnMessage returnMessage = shopService.getMonthlySales(shopId, yearMonthVo);
        return returnMessage;
    }


}
