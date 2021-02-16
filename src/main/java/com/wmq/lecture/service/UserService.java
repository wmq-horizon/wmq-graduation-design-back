package com.wmq.lecture.service;

import com.wmq.lecture.entity.Users;
import com.wmq.lecture.mapper.UsersMapper;
import com.wmq.lecture.utils.ResultUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lenovo
 */
@Service
public class UserService {
    @Resource
    UsersMapper usersMapper;
    public Users getUserByUid(String uid){
        return usersMapper.selectUserByUid(uid);
    }
    public String getRoleByUid(String uid){
        return usersMapper.selectRoleByUid(uid);
    }

    public ResultUtil selectAllUsers(){
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setSetMessage("查询所有用户的信息");
        resultUtil.setCode(200);
        resultUtil.setData(usersMapper.selectAll());
        return resultUtil;
    }

    public ResultUtil uploadExcel(String fileName,MultipartFile file) {
        ResultUtil result = new ResultUtil();
        try {
            InputStream inputStream = file.getInputStream();
            Workbook wb;
            if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
                wb = new XSSFWorkbook(inputStream);
            } else {
                wb = new HSSFWorkbook(inputStream);
            }
            Sheet sheet = wb.getSheetAt(0);
            if (sheet == null) {
                result.setSetMessage("Excel数据为空！");
                return result;
            }
            List<Users> excelData = new ArrayList<>();
            Users temporaryUser;
            // 循环Excel
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) {
                    continue;
                }
                temporaryUser = new Users();
                if (row.getCell(0) != null) {
                    row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                    String uid = row.getCell(0).getStringCellValue();
                    if (uid == null || uid.isEmpty()) {
                        result.setSetMessage("Excel中用户编号称为必填项，不能为空，请填写后再进行上传！");
                        return result;
                    }
                    System.out.println(uid);
                    temporaryUser.setUid(uid);
                }
                if (row.getCell(1) != null) {
                    row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                    String name = row.getCell(1).getStringCellValue();
                    if (name == null || name.isEmpty()) {
                        result.setSetMessage("Excel中用户手机号为必填项，不能为空，请填写后再进行上传！");
                        return result;
                    }
                    System.out.println(name);
                    temporaryUser.setName(name);
                }
                if (row.getCell(2) != null) {
                    row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                    String password = row.getCell(2).getStringCellValue();
                    if (password == null || password.isEmpty()) {
                        result.setSetMessage("Excel中用户初始密码为必填项，不能为空，请填写后再进行上传！");
                        return result;
                    }
                    System.out.println(password);
                    temporaryUser.setPassword(password);
                }
                if (row.getCell(3) != null) {
                    row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                    String a = row.getCell(3).getStringCellValue();
                    System.out.println(a);
                    int integrity = Integer.parseInt(row.getCell(3).getStringCellValue());
                    System.out.println(integrity);
                    temporaryUser.setIntegrity(integrity);
                }
                if (row.getCell(4) != null) {
                    row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
                    String role = row.getCell(4).getStringCellValue();
                    temporaryUser.setRole(role);
                }
                if (row.getCell(5) != null) {
                    row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
                    double score = Double.parseDouble(row.getCell(5).getStringCellValue());
                    System.out.println(score);
                    temporaryUser.setScore(score);
                }
                if (row.getCell(6) != null) {
                    row.getCell(6).setCellType(Cell.CELL_TYPE_NUMERIC);
                    int status = (int)row.getCell(6).getNumericCellValue();
                    temporaryUser.setStatus(status);}

                //添加进list
                excelData.add(temporaryUser);
            }//for
            // 此处省略其他操作处理
            if (excelData.size() > 0) {
                // 将Excel数据插入数据库
                int i = usersMapper.insertExcelData(excelData);
                if (i == excelData.size()) {
                    // 数据全部插入成功
                    result.setSetMessage("success");
                }
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return result;
//            throw new Exception(e.getMessage());
        }
    }
}


