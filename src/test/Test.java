package test;


import java.util.Scanner;

class Number {              // lop Number
    int num;

    public Number(int num) {        // ham khoi tao
        this.num = num;
    }

    public int getNum() {           // ham tra ve gia tri num
        return num;
    }

    public void setNum(int num) {   // ham set gia tri num
        this.num = num;
    }
}

public class Test {
    private void swap(Number a, Number b) {     // ham hoan vi
        int temp = a.getNum();                  // gan num cua a cho temp
        a.setNum(b.getNum());                   // gan num cua b cho a
        b.setNum(temp);                         // gan temp cho num cua b
    }

    public static void main(String[] args) {
        Number a, b;                                // hai doi tuong Number a va b
        Test sw = new Test();                   // doi tuong sw thuoc lop MySwap
        Scanner scan = new Scanner(System.in);

        System.out.print("Nhap a = ");
        a = new Number(scan.nextInt());             // khoi tao a voi num la so nhap vao
        System.out.print("Nhap b = ");
        b = new Number(scan.nextInt());             // khoi tao b voi num la so nhap vao
        scan.close();
        System.out.println("Befor swap: a = " + a.getNum() + " b = " + b.getNum());
        sw.swap(a, b);                              // su dung ham swap
        System.out.println("After swap: a = " + a.getNum() + " b = " + b.getNum());
    }
}
