package com.mockService;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class App {
    private JPanel Container;
    private JPanel TitleLayout;
    private JPanel DataLayout;
    private JPanel ResultLayout;
    private JComboBox comboBoxRequestType;
    private JComboBox comboBoxRequestCode;
    private JButton launchButton;
    private JTextField textFieldResponseCodeLabel;
    private JTextField textFieldResponseTextLabel;
    private JLabel title;
    private JLabel requestTypeLabel;
    private JLabel requestCodeLabel;
    private JLabel ResponseCodeLabel;
    private JLabel ResponseTextLabel;

    public static void main(String[] args){
        JFrame frame = new JFrame("App");       // defined the panel
        frame.setContentPane(new App().Container);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(500,500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }




    public App() {

        System.out.println("c'tor?");
        comboBoxRequestType.addItem(new APIMethod(0,"GET"));
        comboBoxRequestType.addItem(new APIMethod(1,"POST"));
        comboBoxRequestType.addItem(new APIMethod(2,"PUT"));
        comboBoxRequestType.addItem(new APIMethod(3,"PATCH"));
        comboBoxRequestType.addItem(new APIMethod(4,"DELETE"));

        comboBoxRequestCode.addItem(new APICode(0,200));

        launchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("launch clicked");
                JOptionPane.showMessageDialog(null,"launch clicked");
                APIMethod selectedItem = (APIMethod)comboBoxRequestType.getSelectedItem();
                System.out.println(selectedItem.getId());
                System.out.println(selectedItem.getType());




            }
        });


    }



}
