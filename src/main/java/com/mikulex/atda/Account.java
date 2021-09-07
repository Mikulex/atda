package com.mikulex.atda;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
public class Account {

    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    private @Id @GeneratedValue Long id;
    
    private String name;
    private @JsonIgnore String password;
    @OneToMany
    private List<Task> taskList;

    public Account(String name, String password){
        this.name = name;
        this.setPassword(password);
        this.taskList = new ArrayList<Task>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setPassword(String password){
        this.password = PASSWORD_ENCODER.encode(password);
    }
    
    public List<Task> getCheckList(){
        return this.taskList;
    }

    public void setCheckList(List<Task> checkList){
        this.taskList = checkList;
    }

    @Override
    public boolean equals(Object obj){
        if(Objects.isNull(obj)) return false;
        if(!(obj instanceof Account)) return false;
        if(this == obj) return true;

        Account account = (Account) obj;
        return (Objects.equals(this.name, account.name)
        && Objects.equals(this.password, account.password)
        && this.id == account.id
        && Objects.equals(this.taskList, account.taskList));
    }

    @Override
    public int hashCode(){
        return Objects.hash(name, password, taskList, id);
    }
}
