package test;


public class Student {
    private String name;
    private String mssv;
    private int english;
    private int num;

    public Student(){

    }

    public Student(String name, String mssv, int english) {
        this.name = name;
        this.mssv = mssv;
        this.english = english;
        this.num = 1;
    }

    public String getName() {
        return name;
    }

    public String getMssv() {
        return mssv;
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


    public void setEnglish(int english) {
        this.english = english;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getDetail(){
        String s = getName()+", "+getMssv()+", "+getEnglish();
        return s;
    }
}
