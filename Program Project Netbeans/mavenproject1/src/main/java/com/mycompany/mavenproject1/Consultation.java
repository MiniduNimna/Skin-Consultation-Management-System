/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

/**
 *
 * @author Nimna Narangoda
 */
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;

public class Consultation implements Serializable {

    private static ArrayList<Consultation> consultationList = new ArrayList<>();
    private String dateAndTime;
    private int cost;
    ArrayList<String> details;
    ArrayList<BufferedImage> imageList;
    private String  doctorId;
    private String patientId;

    //Constructor
    public Consultation(String patient,String doctor,String dateAndTime,int cost,ArrayList<String> details){
        this.patientId = patient;
        this.doctorId = doctor;
        this.dateAndTime = dateAndTime;
        this.cost = cost;
        this.details = details;
        //this.imageList = imageList;

    }
    public Consultation(String doctor,String  dateAndTime) {
        this.doctorId = doctor;
        this.dateAndTime = dateAndTime;
    }

    public Consultation(){  // Default constructor
    }

    public static void addConsultation(Consultation consultation) {
        consultationList.add(consultation);
    }

    // Setters and Getters

    public String  getDateAndTime() {
        return dateAndTime;
    }

    public int getCost(){
        return cost;
    }


    public String getDoctor(){
        return doctorId;
    }

    public String  getPatient(){
        return patientId;
    }

    public static ArrayList<Consultation> getConsultationList() {
        return consultationList;
    }
    public ArrayList<String> getDetails() {
        return details;
    }

    public ArrayList<BufferedImage> getImageList() {
        return imageList;
    }

    public void setDateAndTime(String  dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public void setCost(int cost){
        this.cost = cost;
    }

    public void setDoctor(String doctor){
        this.doctorId = doctor;
    }

    public void setPatient(String  patient){
        this.patientId = patient;
    }

    public void setDetails(ArrayList<String> details) {
        this.details = details;
    }

    public void setImageList(ArrayList<BufferedImage> imageList) {
        this.imageList = imageList;
    }

    public static void setConsultationList(ArrayList<Consultation> consultationList) {
        Consultation.consultationList = consultationList;
    }
}

