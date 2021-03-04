
import org.junit.Test;
import top.xsword.annotation.DBSelect;
import top.xsword.annotation.NotNull;
import top.xsword.bean.Student;
import top.xsword.exception.UserFindException;
import top.xsword.service.UserService;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Test01 {

    @Test
    public void Test11(){
        Map<String,String> testDaoSql = getTestDaoSql();
        String s = testDaoSql.get("TestDao.insert");
        System.out.println(s);
    }


    public Map getTestDaoSql(){
        Map<String,String> sqlMap = new HashMap<>();
        Class<TestDao> testDaoClass = TestDao.class;
        Method[] methods = testDaoClass.getMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(DBSelect.class)) {
                DBSelect annotation = method.getAnnotation(DBSelect.class);
                String value = annotation.value();
                StringBuilder stringBuilder = new StringBuilder(value);
                String[] params = annotation.params();
                for (int i = 0; i < charCount(value); i++) {
                    int i1 = stringBuilder.indexOf("?");
                    stringBuilder.deleteCharAt(i1);
                    stringBuilder.insert(i1,params[i]);
                }

                String packageName = testDaoClass.getPackageName();

                String key;
                if(packageName == null || packageName == ""){
                    key = testDaoClass.getName() + '.' + method.getName();
                }else{
                    key = packageName + '.' + testDaoClass.getName() + '.' + method.getName();
                }
                sqlMap.put(key,stringBuilder.toString());
            }
        }
        return sqlMap;
    }

    public void showMap(Map map){
        Set<Map.Entry<String, String>> entries = map.entrySet();
        Iterator<Map.Entry<String, String>> iterator = entries.iterator();
        while(iterator.hasNext()){
            String key = iterator.next().getKey();
            System.out.println(key);
            System.out.println(map.get(key));
        }
    }

    public int charCount(String str){
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if(str.charAt(i) == '?'){
                count++;
            }
        }
        return count;
    }

    @Test
    public void test10() throws NoSuchFieldException, IllegalAccessException {
        TestDao testDao = new TestDao();
        testDao.setName("ywx");
        Class<TestDao> testDaoClass = TestDao.class;
        Field name = testDaoClass.getDeclaredField("name");
        boolean annotationPresent = name.isAnnotationPresent(NotNull.class);
        if(annotationPresent){
            Object o = name.get(testDao);
            if(o == null){
                throw new NullPointerException("\n"+testDaoClass.toString()+"\n"+name+"为空");
            }else{
                System.out.println(o);
            }
        }
    }

    @Test
    public void test09() throws NoSuchMethodException {
        Class<TestDao> testDaoClass = TestDao.class;
        Method DBSelect = testDaoClass.getMethod("select");
        DBSelect annotation = DBSelect.getAnnotation(DBSelect.class);
        String value = annotation.value();
        String[] params = annotation.params();
        System.out.printf("value = {%s}\n",value);
        System.out.printf("params = %s\n",Arrays.toString(params));
        String SQL = value.replace("?", params[0]);
        System.out.printf("sql = {%s}\n",SQL);
    }

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
