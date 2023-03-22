/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

/**
 *
 * @author Nimna Narangoda
 */
import java.io.Serializable;

public class Person implements Serializable {
    private String name;
    private String surname;
    private String  dateOfBirth;
    private String mobileNumber;

    // Constructor
    public Person(String name,String surname,String  dateOfBirth,String  mobileNumber){
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.mobileNumber = mobileNumber;
    }

    public Person() {

    }

    // Setters and Getters
    public String getName(){
        return name;
    }

    public String getSurname(){
        return surname;
    }

    public String  getDateOfBirth(){
        return dateOfBirth;
    }

    public String  getMobileNumber(){
        return mobileNumber;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setSurname(String surname){
        this.surname = surname;
    }

    public void setDateOfBirth(String  dateOfBirth){
        this.dateOfBirth = dateOfBirth;
    }

    public void setMobileNumber(String  mobileNumber){
        this.mobileNumber = mobileNumber;
    }
}

