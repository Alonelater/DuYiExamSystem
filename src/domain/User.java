package domain;



//domain 实体对象、
public class User {
    private String account;//属性用来存储账号
    private  String password;//属性用来存储密码

    public User() {
    }
    public User(String account, String password) {
        this.account = account;
        this.password = password;
    }
    public String getAccount() {
        return account;
    }
    public void setAccount(String account) {
        this.account = account;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
