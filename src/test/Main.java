package test;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static List<Student> taiLenTuTep() throws IOException {
        List<Student> studentList = new ArrayList<>();

        FileInputStream file = new FileInputStream("dssinhvien.xlsx");
        XSSFWorkbook wb = new XSSFWorkbook(file);
        XSSFSheet sheet = wb.getSheetAt(0);

        for(Row row: sheet){
            Student s = new Student();
            s.setName(String.valueOf(row.getCell(1)));
            s.setMssv(String.valueOf(row.getCell(2)));
            s.setMath(Integer.parseInt(String.valueOf(row.getCell(3))));
            s.setEnglish(Integer.parseInt(String.valueOf(row.getCell(4))));
            studentList.add(s);
        }

        wb.close();
        file.close();

        return studentList;
    }

    public static void printList(List<Student> studentList){
        for (Student s: studentList) {
            System.out.println(s.getDetail());
        }
    }

    public static void main(String[] args) {
        List<Student> studentList = null;
        try {
            studentList = taiLenTuTep();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Danh sach sinh vien la:");
        printList(studentList);

        Collections.sort(studentList, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o2.getEnglish() - o1.getEnglish();
            }
        });

        System.out.println("************************");
        System.out.println("Danh sach sinh vien theo diem la:");
        printList(studentList);

    }
}
