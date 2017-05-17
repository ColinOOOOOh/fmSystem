package com.fmSystem.utilTest;

import com.fmSystem.Bean.Vo.SalesRecordVo;
import com.fmSystem.Utility.ExcelLoador;
import com.fmSystem.baseTest.SpringTestCase;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 74551 on 2017/4/26.
 */
public class LoadExcelTest extends SpringTestCase {
    @Test
    public void showFile(){
        ExcelLoador loadExcel = new ExcelLoador();
        try{
            InputStream is = new FileInputStream("E:\\test.xls");
            loadExcel.readExcelTitle(is);
        }catch (Exception e ){
            System.out.println("shit!!!");
        }

    }

    @Test
    public void loadRecordTest(){
        ExcelLoador loadExcel = new ExcelLoador();
        List<SalesRecordVo> salesRecordVos = new ArrayList<SalesRecordVo>();
        try{
            InputStream is = new FileInputStream("E:\\test.xls");

            salesRecordVos = loadExcel.loadRecordFile(is);
        }catch (Exception e ){
            e.printStackTrace();
        }
        System.out.println(salesRecordVos.toString());

    }
}
