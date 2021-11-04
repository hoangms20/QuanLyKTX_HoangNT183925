package test;


public class Student {
    private String name;
    private String mssv;
    private int math;
    private int english;

    public Student(){

    }

    public Student(String name, String mssv, int math, int english) {
        this.name = name;
        this.mssv = mssv;
        this.math = math;
        this.english = english;
    }

    public String getName() {
        return name;
    }

    public String getMssv() {
        return mssv;
    }

    public int getMath() {
        return math;
    }

    public int getEnglish() {
        return english;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMssv(String mssv) {
        this.mssv = mssv;
    }

    public void setMath(int math) {
        this.math = math;
    }

    public void setEnglish(int english) {
        this.english = english;
    }

    public String getDetail(){
        String s = getName()+", "+getMssv()+", "+getMath()+", "+getEnglish();
        return s;
    }
}
