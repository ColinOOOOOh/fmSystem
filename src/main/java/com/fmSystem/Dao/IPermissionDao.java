package com.fmSystem.Dao;

import com.fmSystem.Bean.Po.PermissionPo;
import org.apache.ibatis.annotations.Param;

/**
 * Created by 74551 on 2017/5/31.
 */
public interface IPermissionDao {
    void setPermission(PermissionPo permission);

    PermissionPo getPermissionPoByUserId(int userId);

    void deletePermission(@Param("shopId") int shopId, @Param("userId") int userId);
}
