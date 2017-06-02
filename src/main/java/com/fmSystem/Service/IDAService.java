package com.fmSystem.Service;

import com.fmSystem.Utility.ReturnMessage;

import java.util.List;

/**
 * Created by 74551 on 2017/5/29.
 */
public interface IDAService {
    ReturnMessage associationAnalysis(List<String> items, int shopId);

    ReturnMessage warehouseCal(String commodityName, int shopId);

    ReturnMessage getCommodityList(int shopId);

    ReturnMessage layout(int shopId);
}
