package com.standard.sparta.domain;
//Model Class를 만들 때 필요한 3가지 1,2,3
//Model이자, Entity이다. (domain 이하)
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

//3. Getter, Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    //1.필드명 (DB 값 들어가거나 , 모델관리 용)
    private Long id;
    private String email;
    private String name;
    private boolean isDeleted;
    //2.생성자
    public Member(String email, String name) {
        this.email = email;
        this.name = name;
    }
    public Member(Long id, boolean isDeleted, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.isDeleted = isDeleted;
    }

}
