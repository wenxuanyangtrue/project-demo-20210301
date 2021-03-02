import org.apache.logging.log4j.LogManager;
import org.junit.Test;
import top.xsword.bean.Student;
import top.xsword.exception.UserFindException;
import top.xsword.service.UserService;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Test01 {
    @Test
    public void test08(){
        Class<Student> studentClass = Student.class;
        Class<? super Student> superclass = studentClass.getSuperclass();
        Method[] methods = superclass.getMethods();
        for (Method method : methods) {
            System.out.println(method);
        }
    }

    @Test
    public void test07() throws IOException {
        FileHandler dh = new FileHandler();
        Logger log = Logger.getGlobal();
        log.addHandler(dh);
        log.setLevel(Level.CONFIG);
        log.info("两次老虎爱跳舞");
        log.info("小兔子乖乖拔萝卜");
    }

    @Test
    public void test06(){
        int x = -1;
        try{
            assert x >= 0 : "x的值不能小于等于0";
            System.out.println(x);
        }catch (AssertionError e){
            System.out.println(e);
        }
        System.out.println("hello");
    }

    @Test
    public void test05() throws IOException {
        try{
            String str = "av123";
            Integer.parseInt(str);
        }catch (NumberFormatException e){
            File file = new File("demo.log");
            if(!file.exists()){
                if(!file.createNewFile()){
                    throw new IllegalArgumentException("创建文件异常");
                }
            }
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(e+"\n");
            fileWriter.flush();
            fileWriter.close();
        }

    }

    @Test
    public void test04(){
        try{
            String str = "av123";
            Integer.parseInt(str);
            System.out.println("continue");
        }catch(NumberFormatException e){
            System.out.println(e);
        }catch (Exception e){
            System.out.println(e);
        }finally{
            System.out.println("异常走");
        }
        System.out.println("123");
    }

    @Test
    public void test03(){
        throw new UserFindException("自定义异常：用户没有找到");
    }

    @Test
    public void test02(){
        UserService[] userServices = new UserService[20];
        for (int i = 20; i >=1 ; i--) {
            userServices[20-i] = new UserService(i);
        }
        System.out.println(Arrays.toString(userServices));
        Arrays.sort(userServices);
        System.out.println(Arrays.toString(userServices));
    }

    @Test
    public void test01(){
        UserService userService = new UserService();
        userService.start("zhangsan","123456",(username,password)->{
            System.out.println(username);
            System.out.println(password);
            return true;
        });
    }
}
