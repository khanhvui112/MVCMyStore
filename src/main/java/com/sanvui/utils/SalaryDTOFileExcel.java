package com.sanvui.utils;

import com.sanvui.dto.SalaryDTO;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: VuiSK
 * @created: 08/11/2021-11:58 AM
 * @mailto: sanvankhanh@gmail.com
 */
public class SalaryDTOFileExcel {
    public static List<SalaryDTO> readFile(String nameFile) throws IOException {
//        create List Employee
        List<SalaryDTO> salaryDTOList = new ArrayList<>();
        FileInputStream file = null;
        XSSFWorkbook workbook = null;
        try {
            file = new FileInputStream(new File(nameFile));

            workbook = new XSSFWorkbook(file);

            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);

            int countCell = 0;
            int count= 0;
            Row row1 = sheet.getRow(0);
            for (Cell cell : row1){
                if(cell.getCellType() == null){
                    break;
                }
                countCell++;
            }
            SalaryDTO salaryDTO;
            int check = 0;
            for (Row r : sheet) {

                if (check > 0){
                    salaryDTO = new SalaryDTO();
                    salaryDTO.setFullName(r.getCell(0).getStringCellValue());
                    salaryDTO.setStartDate( r.getCell(1).getLocalDateTimeCellValue().toLocalDate());
                    salaryDTO.setEndDate(r.getCell(2).getLocalDateTimeCellValue().toLocalDate());
                    salaryDTO.setSalary(r.getCell(3).getNumericCellValue());
                    salaryDTO.setBonus(r.getCell(4).getNumericCellValue());
                    salaryDTO.setMinusSalary(r.getCell(5).getNumericCellValue());
                    salaryDTO.setRealSalary(r.getCell(6).getNumericCellValue());
                    salaryDTO = new SalaryDTO();
                    salaryDTOList.add(salaryDTO);
                }
                check = 1;
            }
        }catch(Exception e){
            System.out.println("Read file fail");
        }finally {
            if (file != null){
                file.close();
            }
            if (workbook!=null){
                workbook.close();
            }
        }
        return salaryDTOList;
    }

    public static void writeToFile(String nameFile, List<SalaryDTO> list) throws IOException {
        try {
            FileInputStream file = new FileInputStream(nameFile);
            Workbook workbook = WorkbookFactory.create(file);
            Sheet sheet = workbook.getSheetAt(0);

//           get lastRow
            int lastRowCount = sheet.getLastRowNum();

            for (SalaryDTO t : list) {
                Row dataRow = sheet.createRow(++lastRowCount);
                dataRow.createCell(0).setCellValue(t.getFullName());
                dataRow.createCell(1).setCellValue(t.getStartDate().toString());
                dataRow.createCell(2).setCellValue(t.getEndDate().toString());
                dataRow.createCell(3).setCellValue(t.getSalary());
                dataRow.createCell(4).setCellValue(t.getBonus());
                dataRow.createCell(5).setCellValue(t.getMinusSalary());
                dataRow.createCell(6).setCellValue(t.getRealSalary());
            }
            FileOutputStream fileOutputStream = new FileOutputStream(nameFile);
            workbook.write(fileOutputStream);
            fileOutputStream.close();
        } catch (Exception e) {
            System.out.println("Write to file fail");
        }
    }
}
