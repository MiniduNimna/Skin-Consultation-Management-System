/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

/**
 *
 * @author Nimna Narangoda
 */
import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;


public class ConsultationWindow extends JFrame implements ActionListener, Serializable {
    JPanel panelEast = new JPanel();
    JPanel panelWest = new JPanel();
    JPanel panelCenter = new JPanel();
    JPanel panelSouth = new JPanel();
    JPanel panelNorth = new JPanel();
    JSpinner spinner;
    JTextField TFMedicalNO;
    JTextField TFName;
    JTextField TFSurname;
    JFormattedTextField TFDoB;
    JFormattedTextField TFMobileNo;
    JTextField TFId;
    JTextArea textAreaNotes;
    JFileChooser fileChooser;
    JComboBox cmbCost;
    JScrollPane scrollPane;

    JLabel labelGreetingConsult;
    JLabel labelDateTime;
    JLabel labelDoctor;
    JLabel labelName;
    JLabel labelSurname;
    JLabel labelDoB;
    JLabel labelMobileNo;
    JLabel labelId;
    JLabel labelNotes;
    JLabel labelCost;
    JLabel labelCostShow;

    JButton buttonCheck;
    JButton buttonConfirmConsult;
    JButton buttonCancelConsult;
    JButton uploadButton;

    Consultation consultation = new Consultation();
    Doctor doctor = new Doctor();
    Patient patient = new Patient();
    String[] Hour;
    String dateString;
    int cost;


    ConsultationWindow() throws ParseException {
        // Setting frame specifications
        setTitle("Add a Consultation");
        setSize(750, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);   // To make the window Disappear with close button without making the program end
        setLocationRelativeTo(null);
        setResizable(false);

        //Making Panels
        Color customColor1 = new Color(134, 229, 255);
        Color customColor2 = new Color(0, 129, 201);
        Color customColor3 = new Color(91, 192, 248);
        Color customColor4 = new Color(255, 201, 60);
        panelNorth.setBackground(customColor1);
        panelEast.setBackground(customColor4);
        panelCenter.setBackground(customColor3);
        panelSouth.setBackground(customColor2);

        panelEast.setLayout(null);
        panelSouth.setLayout(null);

        panelEast.setPreferredSize(new Dimension(150, 100));
        panelWest.setPreferredSize(new Dimension(300, 100));
        panelCenter.setPreferredSize(new Dimension(50, 100));
        panelSouth.setPreferredSize(new Dimension(50, 100));
        panelNorth.setPreferredSize(new Dimension(50, 50));

        labelGreetingConsult = new JLabel("Fill in the Details Below to Add a Consultation");
        labelGreetingConsult.setFont(new Font("Abadi", Font.BOLD, 15));

        labelDateTime = new JLabel("Date and Time of Consultation");
        labelDateTime.setPreferredSize(new Dimension(240,30));

        labelDoctor = new JLabel("Select a Doctor(Enter MedicalLicenseNo)");
        labelDoctor.setPreferredSize(new Dimension(240,30));

        labelName = new JLabel("Patient Name");
        labelName.setPreferredSize(new Dimension(240,30));

        labelSurname = new JLabel("Patient Surname");
        labelSurname.setPreferredSize(new Dimension(240,30));

        labelDoB = new JLabel("Patient Date Of Birth (DD/MM/YYYY)");
        labelDoB.setPreferredSize(new Dimension(240,30));

        labelMobileNo = new JLabel("Patient Mobile Number");
        labelMobileNo.setPreferredSize(new Dimension(240,30));

        labelId = new JLabel("Patient NIC Number");
        labelId.setPreferredSize(new Dimension(240,30));

        labelCost = new JLabel("Cost (Select Duration of the Consultation)");
        labelCost.setPreferredSize(new Dimension(260,30));

        labelCost.setPreferredSize(new Dimension(240, 30));

        labelCostShow = new JLabel();
        labelCostShow.setPreferredSize(new Dimension(240,30));
        labelCostShow.setForeground(Color.RED);

        labelNotes = new JLabel("Add Notes (Visual or Textual)");
        labelNotes.setPreferredSize(new Dimension(240,30));



        // Get date and time.But time only in hours
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, Calendar.HOUR);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        SpinnerDateModel model = new SpinnerDateModel(calendar.getTime(), null, null, Calendar.HOUR);

        // Create a JSpinner object using the spinner model
        spinner = new JSpinner(model);
        spinner.setPreferredSize(new Dimension(250,30));
        // Let user input date and time in hours
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "dd/MM/yyyy , HH" + ":00");
        spinner.setEditor(editor);

        TFMedicalNO = new JTextField();
        TFMedicalNO.setPreferredSize(new Dimension(250,30));

        TFName = new JTextField();
        TFName.setPreferredSize(new Dimension(250,30));

        TFSurname = new JTextField();
        TFSurname.setPreferredSize(new Dimension(250,30));

        MaskFormatter formatterDoB = new MaskFormatter("##/##/####");
        formatterDoB.setValidCharacters("0123456789");

        TFDoB = new JFormattedTextField(formatterDoB);
        TFDoB.setPreferredSize(new Dimension(250,30));

        MaskFormatter formatterMobileNo = new MaskFormatter("###-#######");
        formatterMobileNo.setValidCharacters("0123456789");

        TFMobileNo = new JFormattedTextField(formatterMobileNo);
        TFMobileNo.setPreferredSize(new Dimension(250,30));

        TFId = new JTextField();
        TFId.setPreferredSize(new Dimension(250,30));

        Hour = new String[]{"1 Hour", "2 Hours", "3 Hours", "5 Hours", "8 Hours"};  // String array hold the selection of combo box
        cmbCost = new JComboBox(Hour);
        cmbCost.addActionListener(this);
        cmbCost.setSelectedIndex(-1);  //Setting default value to null
        cmbCost.setPreferredSize(new Dimension(250,30));

        textAreaNotes = new JTextArea(10,20);
        textAreaNotes.setLineWrap(true);
        textAreaNotes.setWrapStyleWord(true);
        scrollPane = new JScrollPane(textAreaNotes);
        scrollPane.setPreferredSize(new Dimension(250,100));

        fileChooser = new JFileChooser();
        uploadButton = new JButton("Upload Image");
        uploadButton.addActionListener(this);
        uploadButton.setFocusable(false);
        uploadButton.setPreferredSize(new Dimension(250,30));

        buttonConfirmConsult = new JButton("Confirm");
        buttonConfirmConsult.addActionListener(this);
        buttonConfirmConsult.setBounds(150,30,100,30);
        buttonConfirmConsult.setFocusable(false);
        buttonConfirmConsult.setEnabled(false);
        buttonCancelConsult = new JButton("Cancel");
        buttonCancelConsult.setBounds(500,30,100,30);
        buttonCancelConsult.setFocusable(false);
        buttonCancelConsult.addActionListener(this);
        buttonCheck = new JButton("Check Availability");
        buttonCheck.setBounds(5,45,140,20);
        buttonCheck.setFocusable(false);
        buttonCheck.addActionListener(this);

        //Adding all the components to the frame
        add(panelEast,BorderLayout.EAST);
        add(panelWest,BorderLayout.WEST);
        add(panelCenter,BorderLayout.CENTER);
        add(panelSouth,BorderLayout.SOUTH);
        add(panelNorth,BorderLayout.NORTH);

        panelNorth.add(labelGreetingConsult);
        panelWest.add(labelDateTime);
        panelWest.add(labelDoctor);
        panelWest.add(labelName);
        panelWest.add(labelSurname);
        panelWest.add(labelDoB);
        panelWest.add(labelMobileNo);
        panelWest.add(labelId);
        panelWest.add(labelCost);
        panelWest.add(labelNotes);

        panelCenter.add(spinner);
        panelCenter.add(TFMedicalNO);
        panelCenter.add(TFName);
        panelCenter.add(TFSurname);
        panelCenter.add(TFDoB);
        panelCenter.add(TFMobileNo);
        panelCenter.add(TFId);
        panelCenter.add(cmbCost);
        panelCenter.add(labelCostShow);
        panelCenter.add(scrollPane);
        panelCenter.add(uploadButton);

        panelEast.add(buttonCheck);
        panelSouth.add(buttonConfirmConsult);
        panelSouth.add(buttonCancelConsult);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonCancelConsult) {

            dispose();
        }
        //Check availability of the doctor
        if (e.getSource() == buttonCheck){
            buttonConfirmConsult.setEnabled(true);
            int count1 = 0;
            int count2 = 0;
            int count3 = 0;
            Object value = spinner.getValue();
            Date date = (Date) value;
            dateString = date.toString();
            System.out.println(dateString);
            for (int i = 0; i < Doctor.getDoctorList().size(); i++) {// iterating through doctor objects in the ArrayList
                doctor = Doctor.getDoctorList().get(i);
                if (TFMedicalNO.getText().equals(doctor.getMedicalLicenseNumber())){    // Validating the entered Medical License Number
                    count1++;
                    if(Consultation.getConsultationList().isEmpty()){  // Validate the very first consultation
                        JOptionPane.showMessageDialog(null, "Doctor is available.", "Doctor Availability", JOptionPane.INFORMATION_MESSAGE);
                    }
                    for (int j = 0; j < Consultation.getConsultationList().size(); j++){ // iterating through Consultation objects in the ArrayList
                        consultation = Consultation.getConsultationList().get(j);
                        if(TFMedicalNO.getText().equals(consultation.getDoctor()) && dateString.equals(consultation.getDateAndTime())) {
                            count3 ++;
                            JOptionPane.showMessageDialog(null, "Doctor is Unavailable on that date and time.\n A new Doctor will be allocated.", "Doctor Availability", JOptionPane.WARNING_MESSAGE);
                            int k = 0;
                            while(k < Doctor.getDoctorList().size() ){
                                // Adding a random doctor
                                Random random = new Random();
                                int randomInt = random.nextInt(Doctor.getDoctorList().size()); //creating a random number from 0 to 9
                                doctor = Doctor.getDoctorList().get(randomInt);
                                TFMedicalNO.setText(doctor.getMedicalLicenseNumber());    //Store Medical License number of the random doctor
                                // Checking whether the random doctor is available in given specific date and time
                                if( !(TFMedicalNO.getText().equals(consultation.getDoctor()) && dateString.equals(consultation.getDateAndTime()))){
                                    count2 ++;
                                    break;
                                }
                            }
                            if(count2 == 0){
                                JOptionPane.showMessageDialog(null, "All the Doctors are not available on that date and time.", "Doctor Availability", JOptionPane.WARNING_MESSAGE);
                            }
                        }
                        if(count3 == 0) {
                            JOptionPane.showMessageDialog(null, "Doctor is available.", "Doctor Availability", JOptionPane.INFORMATION_MESSAGE);
                            count3 ++;
                        }
                    }

                }

            }
            if (count1 == 0){
                JOptionPane.showMessageDialog(null,"Invalid Medical License Number.","Doctor Availability",JOptionPane.WARNING_MESSAGE);
            }
        }

        // Adding all the information of consultation to relevant Lists
        if (e.getSource() == buttonConfirmConsult) {
            // Validating whether user have entered necessary information
            if (TFMedicalNO.getText().isEmpty() || TFId.getText().isEmpty() || TFName.getText().isEmpty() || TFMobileNo.getText().isEmpty() || -1 == cmbCost.getSelectedIndex()){
                JOptionPane.showMessageDialog(null,"Fill all the details to book a consultation.\nOnly DoB,notes and images are optional.","Consultation",JOptionPane.ERROR_MESSAGE);
            }
            else{
                // Store data in JTextArea to an array List by splitting
                ArrayList<String> detailsList = new ArrayList<>();
                String textFromJTextArea = textAreaNotes.getText();
                String[] detailsArray = textFromJTextArea.split("\n");
                for (String detail : detailsArray) {
                    detailsList.add(detail);
                }
                // Adding data to the consultation list
                consultation = new Consultation(TFId.getText(),TFMedicalNO.getText(),dateString,cost,detailsList);
                Consultation.addConsultation(consultation);

                // Adding data to the patient list
                Patient patient = new Patient(TFName.getText(),TFSurname.getText(),(String) TFDoB.getValue(),(String) TFMobileNo.getValue(),TFId.getText());
                Patient.addPatient(patient);
                System.out.println("poo");
                System.out.println(Patient.getPatientList());
                dispose();
            }

        }

        //Calculating the cost
        if(e.getSource() == cmbCost) {
            int selection = cmbCost.getSelectedIndex();
            if(selection != -1) {
                if (TFId.getText().isEmpty()) { // Check whether user have entered an ID number
                    JOptionPane.showMessageDialog(null,"First enter the Patient NIC Number","Cost",JOptionPane.ERROR_MESSAGE);
                }
                else{
                    if(Patient.getPatientList().isEmpty()){
                        cost = 15;
                        if (selection == 0) {
                        } else if (selection == 1) {
                            cost = cost * 2;
                        } else if (selection == 2) {
                            cost = cost * 3;
                        } else if (selection == 3) {
                            cost = cost * 5;
                        } else if (selection == 4) {
                            cost = cost * 8;
                        } else {
                            labelCostShow = new JLabel("Error");
                        }
                    }
                    else{
                        for (int i = 0; i < Patient.getPatientList().size(); i++) {// iterating through doctor objects in the ArrayList
                            patient = Patient.getPatientList().get(i);
                            if (TFId.getText().equals(patient.getPatientId())) {   //Checking whether user have booked a consultation before
                                cost = 25;
                            } else {
                                cost = 15;
                            }
                            if (selection == 0) {
                            } else if (selection == 1) {
                                cost = cost * 2;
                            } else if (selection == 2) {
                                cost = cost * 3;
                            } else if (selection == 3) {
                                cost = cost * 5;
                            } else if (selection == 4) {
                                cost = cost * 8;
                            } else {
                                labelCostShow = new JLabel("Error");
                            }
                        }
                    }
                    labelCostShow.setText("Full Cost is : " + cost + " Pounds");
                    labelCostShow.setPreferredSize(new Dimension(250,15));
                    revalidate();  // Refresh the layout structure to add label showing the cost in real time
                }
            }
        }

        if(e.getSource() == uploadButton){
            //getting the selected file from computer
            int returnVal = fileChooser.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                }

            }
        }

}



