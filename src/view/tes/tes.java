package view.tes;

import model.SinhVien;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class tes {
    public static void main(String[] args) throws IOException {
        List<SinhVien> sinhVienList1 = new ArrayList<>();
        FileInputStream file = new FileInputStream("svdk.xlsx");
        XSSFWorkbook wb = new XSSFWorkbook(file);
        XSSFSheet sheet = wb.getSheetAt(0);
        FormulaEvaluator formulaEvaluator = wb.getCreationHelper().createFormulaEvaluator();
        for(Row row: sheet){
            SinhVien s = new SinhVien();
            s.setMSSV(String.valueOf(row.getCell(1)));
            s.setMaUuTien(String.valueOf(row.getCell(2)));
            s.setMaLop(String.valueOf(row.getCell(3)));
            s.setMaKhoa(String.valueOf(row.getCell(4)));
            s.setMaTrangThai(0);
            s.setTenSV(String.valueOf(row.getCell(5)));
            s.setNgaySinh(Date.valueOf(String.valueOf(row.getCell(6))));
            s.setGioiTinh(String.valueOf(row.getCell(7)));
            s.setCMND(String.valueOf(row.getCell(8)));
            s.setSDT(String.valueOf(row.getCell(9)));
            s.setEmail(String.valueOf(row.getCell(10)));
            s.setQueQuan(String.valueOf(row.getCell(11)));
            s.setNgayDangKy(Date.valueOf(String.valueOf(row.getCell(12))));
            sinhVienList1.add(s);
        }
        wb.close();
        file.close();
    }
}
