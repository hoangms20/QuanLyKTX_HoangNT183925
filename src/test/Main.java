package test;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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
            s.setEnglish(Integer.parseInt(String.valueOf(row.getCell(3))));
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

    public static void printListDulicated(List<Student> studentList){
        for (Student s: studentList) {
            System.out.println(s.getDetail()+" ------ so lan trung: "+s.getNum());
        }
    }

    public static List<Student> removeDuplicates(List<Student> studentList){
        int l = studentList.size();
        int j = 0;
        int k = 0 ;

        List<Student> students = new ArrayList<>();
        students.add(new Student("0", "0",0));

        for (int i = 0; i < l-1; i++) {
            j = i + 1;
            String s = studentList.get(i).getMssv();
            int eng = studentList.get(i).getEnglish();
            while (j < l && eng == studentList.get(j).getEnglish()){
                if(s.equals(studentList.get(j).getMssv())){
                    if (!students.get(k).getMssv().equals(s)){
                        students.add(studentList.get(i));
                        k++;
                    }
                    students.get(k).setNum(students.get(k).getNum() + 1);
                    studentList.remove(j);
                    l--;
                }else {
                    j++;
                }
            }
        }

        students.remove(0);
        
        return students;
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

        System.out.println("\n************************");
        System.out.println("Danh sach sinh vien sap xep theo diem la:");
        printList(studentList);

        List<Student> studentList2 = removeDuplicates(studentList);
        System.out.println("\n************************");
        System.out.println("Danh sach sinh vien bi trung la:");
        printListDulicated(studentList2);

        System.out.println("\n************************");
        System.out.println("Danh sach sinh vien cuoi cung la:");
        printList(studentList);


    }
}
