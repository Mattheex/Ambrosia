package com.example.ambrosia.Users;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

public class User {

    private Date born;
    private String first;
    private String last;
    private String password;
    private String mail;
    private String sexe;
        private Integer age;

    public User(){}

    public User(Date born, String firstname, String lastname, String password, String sexe, Integer age ) {
        this.born = born;
        this.first = firstname;
        this.last = lastname;
        this.password = password;
        this.sexe = sexe;
        this.age = age;
    }

    public String getFirst() { return first; }

    public void setFirst(String first) { this.first = first; }

    public String getLast() { return last; }

    public void setLast(String last) { this.last = last; }

    public Date getBorn() { return born; }

    public void setBorn(Date born) {
        this.born = born;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() { return mail; }

    public void setMail(String mail) { this.mail = mail; }

    public String getSexe() { return sexe; }

    public void setSexe(String sexe) { this.sexe = sexe; }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

}
