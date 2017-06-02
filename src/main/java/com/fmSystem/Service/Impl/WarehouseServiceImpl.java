package com.fmSystem.Service.Impl;

import com.fmSystem.Bean.Po.WarehousePo;
import com.fmSystem.Dao.IWarehouseDao;
import com.fmSystem.Service.IWarehouseService;
import com.fmSystem.Utility.ReturnMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 74551 on 2017/5/31.
 */
@Service
public class WarehouseServiceImpl implements IWarehouseService {
    @Autowired
    IWarehouseDao warehouseDao;
    @Override
    public void newWarehouse(int shopId) {
        WarehousePo warehousePo = new WarehousePo();
        warehousePo.setShopId(shopId);
        warehouseDao.setWarehouse(warehousePo);

    }
}
