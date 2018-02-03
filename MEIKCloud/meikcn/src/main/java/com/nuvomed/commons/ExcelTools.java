package com.nuvomed.commons;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.springframework.web.multipart.MultipartFile;
 

 
public class ExcelTools {
    private static String EXCEL_2003 = ".xls";
    private static String EXCEL_2007 = ".xlsx";
    private static String EXCEL_CSV = ".csv";
    public static void readExcelJXL() {
 
    }
 
    /**
     * 通过POI方式读取Excel
     * 
     * @param excelFile
     */
    public static List<String[]> readExcelPOI(MultipartFile excelFile, Integer cons) throws Exception {
      if (excelFile != null) {
            String fileName = excelFile.getOriginalFilename();
            fileName = fileName.toLowerCase();
            if (fileName.toLowerCase().endsWith(EXCEL_2003)) {
            	List<String[]> data = readExcelPOI2003(excelFile, cons);
               return data;
            }
            if (fileName.toLowerCase().endsWith(EXCEL_2007)||fileName.toLowerCase().endsWith(EXCEL_CSV)) {
            	List<String[]> data = readExcelPOI2007(excelFile, cons);
                return data;
            }
        }
        return null;
    }
 
    /**
     * 读取Excel2003的表单
     * 
     * @param excelFile
     * @return
     * @throws Exception
     */
    @SuppressWarnings("static-access")
	private static List <String[]> readExcelPOI2003(MultipartFile excelFile, Integer rCons)
            throws Exception {
        List<String[]> datasList = new ArrayList<String[]>();
        
        InputStream input = excelFile.getInputStream();
        HSSFWorkbook workBook = new HSSFWorkbook(input);
        HSSFSheet sheet = workBook.getSheetAt(0);
        // 获取Sheet里面的Row数量
        Integer rowNum = sheet.getLastRowNum() + 1;
            for (int j = 0; j < rowNum; j++) {
                 if (j>rCons) {
                   
                    HSSFRow row = sheet.getRow(j);
                    if (row == null) {
                        continue;
                    }
 
                    Integer cellNum = row.getLastCellNum() + 0;
                    String[] datas = new String[cellNum];
                    for (int k = 0; k < cellNum; k++) {
                        HSSFCell cell = row.getCell(k);
                        if (cell == null) {
                            continue;
                        }
                        if (cell != null) {
                            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                            String cellValue = "";
                            int cellValueType = cell.getCellType();
                            if (cellValueType == cell.CELL_TYPE_STRING) {
                                cellValue = cell.getStringCellValue();
                            }
                            if (cellValueType == cell.CELL_TYPE_NUMERIC) {
                                //Double number = cell.getNumericCellValue();                                 
                             
                                cellValue = cell.getNumericCellValue() + "";
                            }
 
                          datas[k] = cellValue;
                        }
                    }
                    datasList.add(datas);
                }
            }
        
      
        return datasList;
    }
   
    /**
     * 读取Excel2007的表单
     * 
     * @param excelFile
     * @return
     * @throws Exception
     */
    @SuppressWarnings("static-access")
	private static List<String[]> readExcelPOI2007(MultipartFile excelFile, Integer rCons) throws Exception {
        List<String[]> datasList = new ArrayList<String[]>();
        InputStream input = excelFile.getInputStream();
        org.apache.poi.ss.usermodel.Workbook workBook = WorkbookFactory.create(input);
        org.apache.poi.ss.usermodel.Sheet sheet = workBook.getSheetAt(0);
        // 获取行值
        Integer rowNum = sheet.getLastRowNum() + 1;
        for (int j = 0; j < rowNum; j++) {
               if (j > rCons) {
                    
                    Row row = sheet.getRow(j);
                    if (row == null) {
                        continue;
                    }
                    Integer cellNum = row.getLastCellNum()+0;    
                    String datas[]=new String[cellNum];
                    for (int k = 0; k < cellNum; k++) {
                    	
                        Cell cell = row.getCell(k);
                        if (cell==null) {
                            continue;
                        }
                        if (cell != null) {
                            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                            String cellValue = "";
                            int cellValueType = cell.getCellType();
                            if (cellValueType == cell.CELL_TYPE_STRING) {
                                cellValue = cell.getStringCellValue();
                            }
                            if (cellValueType == cell.CELL_TYPE_NUMERIC) {
                                //Double number = cell.getNumericCellValue();
                                cellValue = cell.getNumericCellValue() + "";
                            }
                            datas[k] = cellValue;
                            }
                    }
                    
                    datasList.add(datas);
                }
            }
        
        
        return datasList;
    }
    
}