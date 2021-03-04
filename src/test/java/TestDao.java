import top.xsword.annotation.DBSelect;
import top.xsword.annotation.NotNull;
import top.xsword.annotation.NotRun;

public class TestDao {

    @NotNull
    String name;

    public void setName(String name){
        this.name = name;
    }

    @NotRun(require=false)
    public void run(){

    }

    @DBSelect(value = "select * from user where id = ?",params = "1")
    public void select(){}

    @DBSelect(value = "delete from user where id = ?",params = "5")
    public void delete(){}

    @DBSelect(value = "update user set name = ? where id = ?",params = {"ywx","1"})
    public void update(){}

    @DBSelect(value = "insert into user values(?,?,?)",params = {"2","ywx","123456"})
    public void insert(){}
}
