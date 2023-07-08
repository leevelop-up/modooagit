package com.example.modooagit.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)


    private Long id;
    //@Column(name = "username")
    private String name;

    private String pw;

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
