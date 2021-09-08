package com.mikulex.atda;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class Account implements UserDetails {

    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    private @Id @GeneratedValue Long id;
    private String name;
    private @JsonIgnore String password;
    private Set<GrantedAuthority> authorities = new HashSet<>();

    @OneToMany
    private List<Task> taskList;

    public Account(String name, String password){
        this.name = name;
        this.setPassword(password);
        this.taskList = new ArrayList<Task>();
        authorities.add(new SimpleGrantedAuthority("USER"));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getUsername(){
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
    
    public List<Task> getTaskList(){
        return this.taskList;
    }

    public void setTaskList(List<Task> taskList){
        this.taskList = taskList;
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

    // UserDetails interface implementations
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
}
