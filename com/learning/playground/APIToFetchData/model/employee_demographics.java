package com.learning.playground.apiToFetchData.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class employee_demographics {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long employee_id;
    private String first_name;
    private String last_name;
    private int age;
    private String gender;
    private String birth_date;

    protected employee_demographics() {}
    public employee_demographics(String firstName, String lastname, int age, String gender, String dob) {
        this.first_name = firstName;
        this.last_name = lastname;
        this.age = age;
        this.gender = gender;
        this.birth_date = dob;
    }

    public Long getId() {
        return employee_id;
    }
    @Override
    public String toString() {
        return String.format(
                "employee_demographics[employee_id=%d, first_name='%s', last_name='%s', age='%d', gender='%s', dob='%s']",
                employee_id, first_name, last_name,age ,gender, birth_date );
    }

    public String getFirstName() {
        return first_name;
    }
    public String getLastName() {
        return last_name;

    }
    public String getGender() {
        return gender;

    }
    public String getDOB() {
        return birth_date;

    }
    public int getAge() {
        return age;

    }

    public void setFirstName(String name) {
        this.first_name = name;
    }
    public void setLastName(String name) {
        this.last_name = name;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public void setDOB(String dob) {
        this.birth_date = dob;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setId(Long id) {
        this.employee_id = id;
    }

}
