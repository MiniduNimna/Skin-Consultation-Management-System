/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

/**
 *
 * @author Nimna Narangoda
 */
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

interface skinConsultationManager{
    // Interface Methods
    void addDoctor(Doctor doctor);
    void deleteDoctor(ArrayList<Doctor> doctorList, String medicalLicenseNo);
    ArrayList<Doctor> printDoctor(ArrayList<Doctor> doctorList,Doctor doctor);
    void saveReport();
    void readData();
}

public class WestminsterSkinConsultationManager implements skinConsultationManager {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner input = new Scanner(System.in);
        WestminsterSkinConsultationManager manager = new WestminsterSkinConsultationManager();
        Doctor doc = new Doctor();
        Patient pat = new Patient();
        Consultation con = new Consultation();
        String selection;
        manager.readData();// read data of arrayLists in the start

        while (true){

            System.out.println();
            System.out.println();
            // Menu Console
            System.out.println("--------------Welcome to Westminster Skin Consultation Manager Menu----------------");
            System.out.println();
            System.out.println("1 : Add a Doctor\n2 : Delete a Doctor\n3 : Print the List of Doctors\n4 : Save Data into a File\n5 : Open the Graphical User Interface\n6 : Display Consultation List\n7 : End");
            System.out.println();
            System.out.println("Enter the number of the procedure you want to perform");
            selection = input.next();

            // Adding a Doctor
            if (selection.equals("1")){
                if (Doctor.getDoctorList().size() <= 9) { // Checking the number of doctors already allocated for the clinic
                    System.out.println("Enter Doctor name : ");
                    String menuName = input.next();
                    System.out.println("Enter Doctor Surname : ");
                    String menuSurname = input.next();
                    System.out.println("Enter Doctor Date of Birth : (DD/MM/YYYY)");
                    String  menuDateOfBirth = input.next();
                    System.out.println("Enter Doctor Mobile Number : ");
                    String  menuMobileNumber = input.next();
                    System.out.println("Enter Doctor Medical License Number : ");
                    String menuMedicalLicenseNumber = input.next();
                    System.out.println("Enter Doctor Specialisation : ");
                    String menuSpecialisation = input.next();
                    doc = new Doctor(menuName, menuSurname, menuDateOfBirth, menuMobileNumber, menuMedicalLicenseNumber, menuSpecialisation);
                    manager.addDoctor(doc);
                    System.out.println("-----Doctor Added to the System Successfully-----");
                    System.out.println();
                    System.out.println(Doctor.getDoctorList());
                }
                else{
                    System.out.println("-----Can Not Add More Doctors. Already there are 10 doctors allocated for the clinic.-----");
                }
            }

            // Deleting a doctor
            else if (selection.equals("2")){
                if (Doctor.getDoctorList().isEmpty()){        // Checking whether the doctorList is empty
                    System.out.println("-----No Doctors allocated for the Clinic to Delete-----");
                }
                else{
                    System.out.println("Enter the Medical License Number of the Doctor You Want to Delete : ");
                    String menuMedicalLicenseNumber = input.next();
                    manager.deleteDoctor(Doctor.getDoctorList(),menuMedicalLicenseNumber);
                }
            }

            // Print the list of doctors
            else if (selection.equals("3")){
                if (Doctor.getDoctorList().isEmpty()){        // Checking whether the doctorList is empty
                    System.out.println("-----No Doctors allocated for the Clinic to Display-----");
                }
                else{
                    manager.printDoctor(Doctor.getDoctorList(),doc);
                }
            }

            //Save information into a file
            else if (selection.equals("4")){
                manager.saveReport();

            }

            //Open GUI
            else if(selection.equals("5")){
                GUI.main(new String[] {});
            }

            // Display Consultation List to the manager
            else if(selection.equals("6")){
                if (Consultation.getConsultationList().isEmpty()){        // Checking whether the ConsultationList is empty
                    System.out.println("-----No Consultations allocated for the Clinic to Display-----");
                }
                else{
                    manager.displayConsultation(con);
                }
            }

            // Ending the menu
            else if(selection.equals("7")){
                break;
            }

            // Handling invalid inputs
            else{
                System.out.println("-----Invalid Input-----");
            }
        }
    }


    // Implemented interface methods
    public void addDoctor(Doctor doctor){
        Doctor.addDoctor(doctor);
    }

    public void deleteDoctor(ArrayList<Doctor> doctorList, String medicalLicenseNo){
        int counter = 0;
        for (int i = 0; i < doctorList.size(); i++) {         // iterating through doctor objects in the ArrayList
            Doctor doctor = doctorList.get(i);
            if (medicalLicenseNo.equals(doctor.getMedicalLicenseNumber())) {       // checking the user input license number
                displayDoctorDetails(doctor);
                doctorList.remove(doctor);
                System.out.println();
                System.out.println("-----Doctor Has Been Deleted Successfully-----");
                System.out.println("-----There are " + Doctor.getDoctorList().size() + " Doctors Remaining in the Clinic-----");
                counter ++;
            }
        }
        if (counter ==  0){
            System.out.println("-----There is No Doctor in the Clinic with that Medical License Number-----");
        }
    }

    public ArrayList<Doctor> printDoctor(ArrayList<Doctor> doctorList, Doctor doctor){
        ArrayList<Doctor> sortedDoctorList = doctorList;
        sortedDoctorList.sort(new Comparator<Doctor>() {           // Sorting new array
            @Override
            public int compare(Doctor doc1, Doctor doc2) {          // Compare surname attribute of objects in the array
                return doc1.getSurname().compareTo(doc2.getSurname());
            }
        });
        for (int i = 0; i < sortedDoctorList.size(); i++) {         // iterating through doctor objects in the ArrayList
            displayDoctorDetails(sortedDoctorList.get(i));
        }
        return sortedDoctorList;
    }

    public void saveReport(){
        try {
            FileOutputStream fos = new FileOutputStream("Consultation Center Details.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(Doctor.getDoctorList());
            oos.writeObject(Patient.getPatientList());
            oos.writeObject(Consultation.getConsultationList());
            oos.close();
            fos.close();
        }
        catch (IOException e){
            System.out.println("An Error Occurred");
            e.printStackTrace();
        }
        System.out.println("-----Information Successfully Saved in a File-----");
    }

    public void readData() {
        Path path = Paths.get("Consultation Center Details.dat");
        if (Files.exists(path)) {
            try {
                FileInputStream fis = new FileInputStream("Consultation Center Details.dat");
                ObjectInputStream ois = new ObjectInputStream(fis);
                ArrayList<Doctor> savedDoctorList = (ArrayList<Doctor>) ois.readObject();
                ArrayList<Patient> savedPatientList = (ArrayList<Patient>) ois.readObject();
                ArrayList<Consultation> savedConsultationList = (ArrayList<Consultation>) ois.readObject();
                ois.close();
                fis.close();
                Doctor.setDoctorList(savedDoctorList);
                Patient.setPatientList(savedPatientList);
                Consultation.setConsultationList(savedConsultationList);
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("An Error Occurred");
                e.printStackTrace();
            }
        }

    }

    public void displayConsultation(Consultation con){
        for (int i = 0; i < Consultation.getConsultationList().size(); i++) {         // iterating through doctor objects in the ArrayList
            con = Consultation.getConsultationList().get(i);
            System.out.println("Patient ID    : " + con.getPatient());
            System.out.println("Doctor ID     : " + con.getDoctor());
            System.out.println("Date and Time : " + con.getDateAndTime());
            System.out.println("Cost          : " + con.getCost());
            System.out.println("_________________________________________________________________");
        }
    }

    public void displayDoctorDetails(Doctor doctor){
        System.out.println("Name                   = " + doctor.getName());
        System.out.println("Surname                = " + doctor.getSurname());
        System.out.println("Date Of Birth          = " + doctor.getDateOfBirth());
        System.out.println("Mobile Number          = " + doctor.getMobileNumber());
        System.out.println("Medical License Number = " + doctor.getMedicalLicenseNumber());
        System.out.println("Specialisation         = " + doctor.getSpecialisation());
        System.out.println("_________________________________________________________________");
    }
}

