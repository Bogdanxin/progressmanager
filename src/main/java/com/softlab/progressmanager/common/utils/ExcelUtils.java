package com.softlab.progressmanager.common.utils;

import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import com.softlab.progressmanager.common.ProException;
import com.softlab.progressmanager.core.model.Student;
import com.softlab.progressmanager.core.model.User;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author gwx
 * @version 1.0
 * @describe excel文件工具类
 * @date 2020/4/7 8:58
 */
public class ExcelUtils {


    /**
     * 获取文件类型
     */
    public static String excelType(MultipartFile file) {
        String suffix = file.getOriginalFilename()
            .substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        return suffix;
    }

    /**
     * 读取为xlsx后缀的表
     * @param inputStream
     * @return 返回一个学生列表
     */
    public static List<Student> readXlsxData(InputStream inputStream) throws IOException {
        List<Student> studentList = new ArrayList<>();
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        int num = workbook.getNumberOfSheets();
        // 遍历每个表
        for (int sheetNum = 0; sheetNum < num; sheetNum++) {
            XSSFSheet sheet = workbook.getSheetAt(sheetNum);
            if (sheet == null) {
                continue;
            }
            // 如果这个表不为空，就对每行进行遍历
            int lastRowNum = sheet.getLastRowNum();
            // 从第1行开始，越过标识行
            for (int i = 1; i <= lastRowNum; i++) {
                Student student = new Student();

                Row row = sheet.getRow(i);
                double studentId  = row.getCell(0).getNumericCellValue();
                String studentName = row.getCell(1).getStringCellValue();

                student.setStudentId((int) studentId);
                student.setStudentName(studentName);

                studentList.add(student);
            }
        }

        return studentList;
    }

    public static List<Student> readXlsData(InputStream inputStream) throws IOException {
        List<Student> studentList = new ArrayList<>();
        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
        int num = workbook.getNumberOfSheets();
        for (int sheetNum = 0; sheetNum < num; sheetNum++) {
            HSSFSheet sheet = workbook.getSheetAt(sheetNum);
            if (sheet == null) {
                continue;
            }

            int lastRowNum = sheet.getLastRowNum();
            for (int i = 0; i < lastRowNum; i++) {
                Student student = new Student();

                Row row = sheet.getRow(i);
                double studentId  = row.getCell(0).getNumericCellValue();
                String studentName = row.getCell(1).getStringCellValue();

                student.setStudentId((int) studentId);
                student.setStudentName(studentName);

                studentList.add(student);
            }
        }
        return studentList;
    }
}
