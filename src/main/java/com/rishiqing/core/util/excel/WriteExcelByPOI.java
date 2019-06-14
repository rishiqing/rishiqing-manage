package com.rishiqing.core.util.excel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class WriteExcelByPOI {

    public static void writeInToExcel(File file, String sheetName, List list, Integer defaultRow) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(sheetName);
        if(defaultRow != null) {
            sheet.setDefaultColumnWidth(defaultRow);
        }
        //i为行，j为列
        for(int i=0;i<list.size();i++){
            shitCodeNarcNestedForLoopCheck(list,i,sheet);
        }

        OutputStream out = new FileOutputStream(file);
        workbook.write(out);
        out.close();
    }

    private static void shitCodeNarcNestedForLoopCheck(List list,int i,HSSFSheet sheet){
        List l = (List) list.get(i);
        for(int j=0;j<l.size();j++){
            HSSFRow row = sheet.getRow(j) != null ? sheet.getRow(j) : sheet.createRow(j);
            HSSFCell cellRow = row.createCell(i);
            cellRow.setCellValue((Boolean) l.get(j));
        }
    }
}
