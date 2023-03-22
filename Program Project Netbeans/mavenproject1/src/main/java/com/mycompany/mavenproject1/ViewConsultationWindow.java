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
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewConsultationWindow extends JFrame implements ActionListener {
    JLabel labelValidate;
    JTextField TFValidate;

    Patient patient;
    Consultation consultation;

    JLabel labelHead;
    JLabel labelDateTime;
    JLabel labelDoctor;
    JLabel labelName;
    JLabel labelSurname;
    JLabel labelDoB;
    JLabel labelMobileNo;
    JLabel labelId;
    JLabel labelCost;
    JLabel labelNote;
    JTextArea textAreaNotes;
    JScrollPane scrollPane;

    JButton buttonVerify;
    JButton buttonOk;
    JPanel panelCenter = new JPanel();

    ViewConsultationWindow() {
        setTitle("View Consultation");
        setSize(750, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);   // To make the window Disappear with close button without making the program end
        setLocationRelativeTo(null);
        setResizable(false);
        JPanel panelNorth = new JPanel();

        JPanel panelSouth = new JPanel();

        Color customColor1 = new Color(134, 229, 255);
        Color customColor2 = new Color(0, 129, 201);
        Color customColor3 = new Color(91, 192, 248);
        Color customColor4 = new Color(255, 201, 60);
        panelNorth.setBackground(customColor1);
        panelCenter.setBackground(customColor4);
        panelSouth.setBackground(customColor2);

        panelNorth.setLayout(null);
        panelCenter.setLayout(null);
        panelSouth.setLayout(null);

        panelNorth.setPreferredSize(new Dimension(100, 100));
        panelCenter.setPreferredSize(new Dimension(500, 100));
        panelCenter.setVisible(false);
        panelSouth.setPreferredSize(new Dimension(100, 70));

        labelValidate = new JLabel("Enter Patient NIC Number :");
        labelValidate.setBounds(100, 40, 200, 20);

        TFValidate = new JTextField();
        TFValidate.setBounds(280, 40, 150, 20);

        buttonVerify = new JButton("Verify ID");
        buttonVerify.addActionListener(this);
        buttonVerify.setBounds(500, 38, 100, 25);
        buttonVerify.setFocusable(false);

        buttonOk = new JButton("OK");
        buttonOk.addActionListener(this);
        buttonOk.setBounds(320, 20, 100, 25);
        buttonOk.setFocusable(false);

        labelHead = new JLabel("Consultation Details");
        labelHead.setBounds(280, 10, 200, 25);
        labelHead.setFont(new Font("Abadi", Font.BOLD, 20));

        labelId = new JLabel();
        labelId.setBounds(90, 40, 500, 20);
        labelName = new JLabel();
        labelName.setBounds(90, 70, 500, 20);
        labelSurname = new JLabel();
        labelSurname.setBounds(90, 100, 500, 20);
        labelDoB = new JLabel();
        labelDoB.setBounds(90, 130, 500, 20);
        labelMobileNo = new JLabel();
        labelMobileNo.setBounds(90, 160, 500, 20);
        labelDoctor = new JLabel();
        labelDoctor.setBounds(90, 190, 500, 20);
        labelDateTime = new JLabel();
        labelDateTime.setBounds(90, 220, 500, 20);
        labelCost = new JLabel();
        labelCost.setBounds(90, 250, 500, 20);
        labelNote = new JLabel("Patient Notes : ");
        labelNote.setBounds(90, 280, 100, 20);
        textAreaNotes = new JTextArea(5,40);
        textAreaNotes.setLineWrap(true);
        textAreaNotes.setWrapStyleWord(true);
        scrollPane = new JScrollPane(textAreaNotes);
        scrollPane.setPreferredSize(new Dimension(250,80));
        scrollPane.setBounds(200, 280, 250, 80);

        add(panelCenter, BorderLayout.CENTER);
        add(panelSouth, BorderLayout.SOUTH);
        add(panelNorth, BorderLayout.NORTH);

        panelNorth.add(labelValidate);
        panelNorth.add(TFValidate);
        panelNorth.add(buttonVerify);
        panelSouth.add(buttonOk);
        panelCenter.add(labelHead);
        panelCenter.add(labelNote);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == buttonOk){
            dispose();
        }

        if (e.getSource() == buttonVerify) {
            int count = 0;
            for (int i = 0; i < Patient.getPatientList().size(); i++) { // iterating through patient objects in the ArrayList to get Relevant patient object
                patient = Patient.getPatientList().get(i);
                if (TFValidate.getText().equals(patient.getPatientId())) { //Validating the patient ID
                    count ++;
                    labelId.setText("Patient NIC Number : " + patient.getPatientId() );
                    labelName.setText("Patient Name : " + patient.getName() );
                    labelSurname.setText("Patient Surname : " + patient.getSurname() );
                    labelDoB.setText("Patient Date of Birth : " + patient.getDateOfBirth() );
                    labelMobileNo.setText("Patient Mobile Number : " + patient.getMobileNumber() );
                    // Iterating through Consultation objects array list to get Relevant consultation details
                    for (int j = 0; j < Consultation.getConsultationList().size(); j++){
                        consultation = Consultation.getConsultationList().get(j);
                        if(TFValidate.getText().equals(consultation.getPatient())){
                            labelDoctor.setText("Booked Doctor's Medical License Number : " + consultation.getDoctor() );
                            labelDateTime.setText("Date and Time of Consultation : " + consultation.getDateAndTime() );
                            labelCost.setText("Patient Full Cost : " + consultation.getCost() + " Pounds" );
                            // Iterate through the details arraylist and append each element to the text area to get notes
                            for (String detail : consultation.getDetails()) {
                                textAreaNotes.append(detail + "\n");
                            }
                        }

                    }
                    panelCenter.add(labelId);
                    panelCenter.add(labelName);
                    panelCenter.add(labelSurname);
                    panelCenter.add(labelDoB);
                    panelCenter.add(labelMobileNo);
                    panelCenter.add(labelDoctor);
                    panelCenter.add(labelDateTime);
                    panelCenter.add(labelCost);
                    panelCenter.add(scrollPane);
                    panelCenter.setVisible(true);
                }
            }
            if(count == 0){  // display warning for invalid ID
                JOptionPane.showMessageDialog(null,"Invalid patient NIC Number","Patient ID",JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

