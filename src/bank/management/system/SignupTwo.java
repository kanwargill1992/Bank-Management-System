package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import com.toedter.calendar.JDateChooser;

public class SignupTwo extends JFrame implements ActionListener {

    JTextField aadharTextField, panTextField;
    JRadioButton seniorYes, seniorNo, existingYes, existingNo;
    JButton next;
    JComboBox income, religion, category, qualification, occupation;
    String formno;

    SignupTwo(String formno) {
        this.formno = formno;
        setLayout(null);

        setTitle("NEW ACCOUNT APPLICATION FORM - PAGE 2");

        JLabel additionalDetails = new JLabel("Page 2: Additional Details");
        additionalDetails.setFont(new Font("Raleway", Font.BOLD, 22));
        additionalDetails.setBounds(270, 80, 400, 30);
        add(additionalDetails);

        JLabel rel = new JLabel("Religion:");
        rel.setFont(new Font("Raleway", Font.BOLD, 20));
        rel.setBounds(100, 140, 100, 30);
        add(rel);

        String[] valReligion = {"Sikh", "Christain", "Muslim", "Hindu", "Other"};
        religion = new JComboBox(valReligion);
        religion.setBounds(300, 140, 400, 30);
        add(religion);

        JLabel cat = new JLabel("Category:");
        cat.setFont(new Font("Raleway", Font.BOLD, 20));
        cat.setBounds(100, 190, 200, 30);
        add(cat);

        String[] valCategory = {"General", "OBC", "SC", "ST", "Other"};
        category = new JComboBox(valCategory);
        category.setBounds(300, 190, 400, 30);
        add(category);

        JLabel inc = new JLabel("Income:");
        inc.setFont(new Font("Raleway", Font.BOLD, 20));
        inc.setBounds(100, 240, 200, 30);
        add(inc);

        String[] valIncome = {"Null", "< 40,000", "< 66,000", "< 100,000", "< 125,000", "<200,000", "Other"};
        income = new JComboBox(valIncome);
        income.setBounds(300, 240, 400, 30);
        add(income);

        JLabel edu = new JLabel("Educational");
        edu.setFont(new Font("Raleway", Font.BOLD, 20));
        edu.setBounds(100, 290, 200, 30);
        add(edu);

        JLabel qual = new JLabel("Qualification:");
        qual.setFont(new Font("Raleway", Font.BOLD, 20));
        qual.setBounds(100, 315, 200, 30);
        add(qual);

        String[] valQualification = {"Non-Graduate", "Graduate", "Post-graduation", "Doctorate", "Other"};
        qualification = new JComboBox(valQualification);
        qualification.setBounds(300, 315, 400, 30);
        add(qualification);

        JLabel occu = new JLabel("Occupation:");
        occu.setFont(new Font("Raleway", Font.BOLD, 20));
        occu.setBounds(100, 390, 200, 30);
        add(occu);

        String[] valOccupation = {"Employed", "Un-employed", "Self-employed", "Student", "Retired", "Other"};
        occupation = new JComboBox(valOccupation);
        occupation.setBounds(300, 390, 400, 30);
        add(occupation);

        JLabel pan = new JLabel("PAN Number:");
        pan.setFont(new Font("Raleway", Font.BOLD, 20));
        pan.setBounds(100, 440, 200, 30);
        add(pan);

        panTextField = new JTextField();
        panTextField.setFont(new Font("Raleway", Font.BOLD, 14));
        panTextField.setBounds(300, 440, 400, 30);
        add(panTextField);

        JLabel aadhar = new JLabel("Aadhar Number:");
        aadhar.setFont(new Font("Raleway", Font.BOLD, 20));
        aadhar.setBounds(100, 490, 200, 30);
        add(aadhar);

        aadharTextField = new JTextField();
        aadharTextField.setFont(new Font("Raleway", Font.BOLD, 14));
        aadharTextField.setBounds(300, 490, 400, 30);
        add(aadharTextField);

        JLabel senior = new JLabel("Senior Citizen:");
        senior.setFont(new Font("Raleway", Font.BOLD, 20));
        senior.setBounds(100, 540, 200, 30);
        add(senior);

        seniorYes = new JRadioButton("Yes");
        seniorYes.setBounds(300, 540, 200, 30);
        add(seniorYes);

        seniorNo = new JRadioButton("No");
        seniorNo.setBounds(500, 540, 200, 30);
        add(seniorNo);

        ButtonGroup seniorGroup = new ButtonGroup();
        seniorGroup.add(seniorYes);
        seniorGroup.add(seniorNo);

        JLabel exist = new JLabel("Existing Account:");
        exist.setFont(new Font("Raleway", Font.BOLD, 20));
        exist.setBounds(100, 590, 200, 30);
        add(exist);

        existingYes = new JRadioButton("Yes");
        existingYes.setBounds(300, 590, 200, 30);
        add(existingYes);

        existingNo = new JRadioButton("No");
        existingNo.setBounds(500, 590, 200, 30);
        add(existingNo);

        ButtonGroup existingGroup = new ButtonGroup();
        existingGroup.add(existingYes);
        existingGroup.add(existingNo);

        next = new JButton("Next");
        next.setFont(new Font("Raleway", Font.BOLD, 14));
        next.setBounds(620, 660, 80, 30);
        next.addActionListener(this);
        add(next);

        getContentPane().setBackground(Color.WHITE);

        setSize(800, 800);
        setVisible(true);
        setLocation(350, 10);
    }

    public void actionPerformed(ActionEvent ae) {
        String sreligion = (String) religion.getSelectedItem();
        String scategory = (String) category.getSelectedItem();
        String sincome = (String) income.getSelectedItem();
        String seducation = (String) qualification.getSelectedItem();
        String soccupation = (String) occupation.getSelectedItem();
        String span = panTextField.getText();
        String saadhar = aadharTextField.getText();
        String senior = null;
        if (seniorYes.isSelected()) {
            senior = "Yes";
        } else if (seniorNo.isSelected()) {
            senior = "No";
        }
        String exisitng = null;
        if (existingYes.isSelected()) {
            exisitng = "Yes";
        } else if (existingNo.isSelected()) {
            exisitng = "No";
        }

        try {
            if (sreligion.equals("")) {
                JOptionPane.showMessageDialog(null, "Religion is required");
            } else {
                Conn c = new Conn();
                String query = "insert into signuptwo values('" + formno +"' ,'" + sreligion + "', '" + scategory + "', '" + sincome + "', '" + seducation + "', '" + soccupation + "', '" + span + "', '" + saadhar + "', '" + senior + "', '" + exisitng + "')";
                c.s.executeUpdate(query);

                setVisible(false);
                new SignupThree(formno).setVisible(true);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        new SignupTwo("");
    }
}
