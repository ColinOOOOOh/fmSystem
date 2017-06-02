package com.fmSystem.Utility;


import com.fmSystem.Bean.Vo.SalesRecordVo;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.stereotype.Component;

import java.io.*;
import java.sql.Time;
import java.util.ArrayList;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import static java.lang.Integer.parseInt;

/**
 * Created by 74551 on 2017/4/26.
 */
@Component
public class ExcelLoador {
    private POIFSFileSystem fs;
    private HSSFWorkbook wb;
    private HSSFSheet sheet;
    private HSSFRow row;

    /**
     * 读取Excel表格表头的内容
     * @param  is
     * @return String 表头内容的数组
     */
    public void readExcelTitle(InputStream is) {
        try {
            fs = new POIFSFileSystem(is);
            wb = new HSSFWorkbook(fs);
        } catch (IOException e) {
            e.printStackTrace();
        }

        sheet = wb.getSheetAt(0);

        for(int i = 0; i < sheet.getLastRowNum(); i++){
            row = sheet.getRow(i);
            for(int j = 0; j < row.getLastCellNum(); j++){
                System.out.print(row.getCell(j));
            }
            System.out.println();
        }
    }
        public List<SalesRecordVo> loadRecordFile(File file){
            try {
                InputStream inputStream = new FileInputStream(file);

                fs = new POIFSFileSystem(inputStream);
                wb = new HSSFWorkbook(fs);
            } catch (Exception e) {
                e.printStackTrace();
            }
            List<SalesRecordVo> salesRecordVos = new ArrayList<SalesRecordVo>() ;
            //读取表头
            sheet = wb.getSheetAt(0);
            HSSFRow headerRow = sheet.getRow(0);

            HashMap<String, Integer> headerSequenceMap = new HashMap<String, Integer>();
            for (int i = 0; i < headerRow.getLastCellNum(); i++){
                headerSequenceMap.put(headerRow.getCell(i).toString(), i);
            }

            //读取数据
            HSSFCell cell;
            for (int i = 1; i <= sheet.getLastRowNum(); i++){
                row = sheet.getRow(i);

                SalesRecordVo salesRecordVo = new SalesRecordVo();
                int index;
                index = headerSequenceMap.get("商品名称");
                String commodityName = row.getCell(index).toString();
                salesRecordVo.setCommodityName(commodityName);
                index = headerSequenceMap.get("日期");
                cell = row.getCell(index);
                java.util.Date utilDate = cell.getDateCellValue();
                Date sqlDate = new Date(utilDate.getTime());
                salesRecordVo.setrDate(sqlDate);
                index = headerSequenceMap.get("时间");
                cell = row.getCell(index);
                java.util.Date utilTime = cell.getDateCellValue();
                Time sqlTime = new Time(utilTime.getTime());
                salesRecordVo.setTime(sqlTime);
                index = headerSequenceMap.get("数量");
                cell = row.getCell(index);
                int number = (int)cell.getNumericCellValue();
                salesRecordVo.setNumber(number);
                salesRecordVos.add(salesRecordVo);
            }
            return  salesRecordVos;
    }

    }
