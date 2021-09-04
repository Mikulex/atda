package com.mikulex.atda;

@Entity
public class Account {

    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    private @Id @GeneratedValue Long id;
    
    private String name;
    private @JsonIgnore String password;
    private List<Task> taskList;

    public Account(String name, String password){
        this.name = name;
        this.password = this.setPassword(password);
        this.taskList = new ArrayList<Task>();
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getPassword(){
        return password;
    }

    public String setPassword(password){
        this.password = PASSWORD_ENCODER.encode(password);
    }
    
    public List<Task> getCheckList(){
        return this.checklList;
    }

    public void setCheckList(List<Task> checkList){
        this.checklList = checkList;
    }

    @Override
    public boolean equals(Object obj){
        if(Objects.isNull(obj)) return false;
        if(!(obj instanceof Account)) return false;
        if(this == obj) return true;

        Account account = (Account) obj;
        return (Objects.equals(this.name, account.name)
        && Objects.equals(this.password, account.password)
        && Objects.equals(this.checkList, account.checkList))
    }
}
