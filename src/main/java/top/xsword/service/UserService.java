package top.xsword.service;

import top.xsword.interfaces.Callback;

public class UserService implements Comparable<UserService> {
    private int age;

    public UserService() {
    }

    public UserService(int age) {
        this.age = age;
    }

    public void start(String username, String password, Callback callback){
        System.out.println("====start====");
        System.out.println(callback.callback(username, password));
        System.out.println("=====end=====");
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int compareTo(UserService o) {
        return age-o.getAge();
    }

    @Override
    public String toString() {
        return "UserService{" +
                "age=" + age +
                '}';
    }
}
