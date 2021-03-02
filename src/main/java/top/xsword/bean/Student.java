package top.xsword.bean;

public class Student extends Person {
    String stuID;
    public String classRoom;
    @Override
    public void cat() {
        System.out.println("学生在看什么");
    }

    void xiezuoye(){
        System.out.println("学生在写作业");
    }
}
