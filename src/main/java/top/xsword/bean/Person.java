package top.xsword.bean;

public abstract class Person {
    public String name;
    public int age;
    public abstract void cat();
    public void eat(){
        System.out.println(this.name+"在吃东西");
    }
}
