package com.fmSystem.Controller;

import com.fmSystem.Bean.Vo.RecordVo;
import com.fmSystem.Bean.Vo.SalesRecordVo;
import com.fmSystem.Bean.Vo.YearMonthVo;
import com.fmSystem.Service.IInTimeService;
import com.fmSystem.Utility.ReturnMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

import java.util.logging.Logger;

/**
 * Created by 74551 on 2017/6/1.
 */
@Controller
@RequestMapping("/inTimeController")
public class InTimeController {

    @Autowired
    IInTimeService inTimeService;
    Logger logger = Logger.getLogger("shopController");

    @ResponseBody
    @RequestMapping("/addRecord")
    public ReturnMessage addRecord(@RequestBody RecordVo recordVo, HttpSession httpSession){
        int shopId = (int)httpSession.getAttribute("SHOP_ID");
        ReturnMessage returnMessage;
        String dateAndTimeString = recordVo.getrDate() + " " + recordVo.getrTime();
        if (null != httpSession.getAttribute("LastAddDateAndTime")){
            //判断是否同一次交易
            if (dateAndTimeString.equals(httpSession.getAttribute("LastAddDateAndTime"))){
                int salesRecordId = (int)httpSession.getAttribute("SalesRecordId");
                returnMessage  = inTimeService.addRecordInfo(shopId, recordVo, salesRecordId);

            }else {
                returnMessage  = inTimeService.addSalesRecordAndInfo(shopId, recordVo);
            }
        }else {
            returnMessage  = inTimeService.addSalesRecordAndInfo(shopId, recordVo);
        }
        httpSession.setAttribute("LastAddDateAndTime", recordVo.getrDate() + " " + recordVo.getrTime());

        httpSession.setAttribute("SalesRecordId", returnMessage.getObject());
        return returnMessage;
    }

    @ResponseBody
    @RequestMapping("/getRecordList")
    public ReturnMessage getRecordList( HttpSession httpSession){
        int shopId = (int)httpSession.getAttribute("SHOP_ID");
        ReturnMessage returnMessage = inTimeService.getRecordList(shopId);
        return returnMessage;
    }

//    @ResponseBody
//    @RequestMapping("/modifyRecord")
//    public ReturnMessage modifyRecord(@RequestBody RecordVo recordVo, HttpSession httpSession){
//        int shopId = (int)httpSession.getAttribute("SHOP_ID");
//        ReturnMessage returnMessage = inTimeService.modifyRecord(shopId, recordVo);
//        return returnMessage;
//    }

    @ResponseBody
    @RequestMapping("/deleteRecord")
    public ReturnMessage deleteRecord(@RequestBody RecordVo recordVo, HttpSession httpSession){
        int shopId = (int)httpSession.getAttribute("SHOP_ID");
        inTimeService.deleteRecord(shopId, recordVo);
        ReturnMessage returnMessage = inTimeService.getRecordList(shopId);
        return returnMessage;
    }

}
