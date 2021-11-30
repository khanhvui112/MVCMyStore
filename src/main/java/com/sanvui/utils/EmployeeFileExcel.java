package com.sanvui.utils;

import com.sanvui.model.entity.Employee;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
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
public class EmployeeFileExcel {
    public static List<Employee> readFile(String nameFile) throws IOException {
//        create List Employee
        List<Employee> employeeList = new ArrayList<>();
        FileInputStream file = null;
        XSSFWorkbook workbook = null;
        try {
            file = new FileInputStream(new File(nameFile));

            workbook = new XSSFWorkbook(file);

            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);

            Employee employee;
            int check = 0;
            for (Row r : sheet) {

                if (check > 0) {
                    employee = new Employee();
                    employee.setFirstName(r.getCell(0).getStringCellValue());
                    employee.setLastName(r.getCell(1).getStringCellValue());
                    employee.setEmail(r.getCell(2).getStringCellValue());
                    employee.setPhone( r.getCell(3).getStringCellValue());
                    employee.setAddress(r.getCell(4).getStringCellValue());
                    employee.setUserName(r.getCell(5).getStringCellValue());
                    employee.setPassword(r.getCell(6).getStringCellValue());
                    employee.setDep_id((int) r.getCell(7).getNumericCellValue());
                    employeeList.add(employee);
                }
                check = 1;
            }
        } catch (Exception e) {
            System.out.println("Read Employee file fail");
        } finally {
            if (file != null) {
                file.close();
            }
            if (workbook != null) {
                workbook.close();
            }
        }
        return employeeList;
    }

    public static void writeToFile(String nameFile, List<Employee> list) throws IOException {
        try {
            FileInputStream file = new FileInputStream(nameFile);
            Workbook workbook = WorkbookFactory.create(file);
            Sheet sheet = workbook.getSheetAt(0);

//           get lastRow
            int lastRowCount = sheet.getLastRowNum();

            for (Employee t : list) {
                Row dataRow = sheet.createRow(++lastRowCount);
                dataRow.createCell(0).setCellValue(t.getEmpId());
                dataRow.createCell(1).setCellValue(t.getFirstName());
                dataRow.createCell(2).setCellValue(t.getLastName());
                dataRow.createCell(3).setCellValue(t.getEmail());
                dataRow.createCell(4).setCellValue(t.getPhone());
                dataRow.createCell(5).setCellValue(t.getAddress());
                dataRow.createCell(6).setCellValue(t.getDepartment().getDepName());
            }
            FileOutputStream fileOutputStream = new FileOutputStream(nameFile);
            workbook.write(fileOutputStream);
            fileOutputStream.close();
        } catch (Exception e) {
            System.out.println("Write Employee to file fail");
        }
    }
}
