package com.eazybytes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
public class Customer {


    /**
     *  @GeneratedValue(strategy = GenerationType.AUTO)
     *  id를 Spring-data-jpa가 자동적으로 입력되고 생성됨을 의미하는데
     *  generator = "native" 는  생성을 native에게 맡긴다는 의미이고,
     *  GenericGenerator를 generator native를 설정해주기 위함이다.
     *
     *  여기서 말하는 native는 DB서버를 말한다.
     * */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name="native")
    private int id;
    private String email;
    private String pwd;
    private String role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
