package com.fpoly.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Table(name = "`users`")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message="Field username isn't empty!")
    @Column(name = "userName")
    @Length(min = 3,message = "Field userName length must >= 3")
    private String userName;

    @NotBlank(message="Field passWord isn't empty!")
    @Column(name = "passWord")
    private String passWord;

    @NotBlank(message="Field isn't empty!")
    @Length(min = 3,message = "Field fullName length must >= 3")
    @Column(name = "fullName")
    private String fullName;

    public User() {
    }

    public User(Long id, String userName, String passWord, String fullName) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.fullName = fullName;
    }

    public User(String userName, String passWord, String fullName) {
        this.userName = userName;
        this.passWord = passWord;
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
