package com.example.demo.user;

import javax.persistence.*;

@Entity
//@Table
@Table(name="users", schema = "public")
public class User {
    @Id
    @SequenceGenerator(
            name="user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long id;
    @Column(name = "name", nullable = false, length = 20)
    private String name;
    @Column(nullable = false, unique = true, length = 45)
    private String email;
    @Column(nullable = false, length = 64)
    private String password;
    private String userType;

    public User(){

    }
    public User(Long id ,String name, String email, String password, String userType){
        this.id=id;

        this.name=name;
        this.email=email;
        this.password=password;
        this.userType=userType;
    }
    public User(String name, String email, String password, String userType){

        this.name=name;
        this.email=email;
        this.password=password;
        this.userType=userType;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }




    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUserType() {
        return userType;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }



    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", userType='" + userType + '\'' +
                '}';
    }
}
