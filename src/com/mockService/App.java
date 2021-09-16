package com.mockService;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


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
        comboBoxRequestCode.addItem(new APICode(1,201));

        launchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id;
                String type;
                int code;

                APIMethod selectedItem = (APIMethod)comboBoxRequestType.getSelectedItem();
                APICode selectedCode = (APICode) comboBoxRequestCode.getSelectedItem();
                id = selectedItem.getId();
                type = selectedItem.getType();
                code = selectedCode.getCode();

                sendRequestToMockService(id,type,code);



            }
        });


    }


    //todo good examples https://stackoverflow.com/questions/309424/how-do-i-read-convert-an-inputstream-into-a-string-in-java
    public void sendRequestToMockService(int id,String type,int code){
        try {
            int responseCode;
            String responseMessage;
            String endPoint = type+code;
            System.out.println("type " + type + " code " + code);

            URL url = new URL("http://localhost:8081/" + endPoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            if(type == "PATCH"){            // seems to be some problem with patch request.
                connection.setRequestProperty("X-HTTP-Method-Override", "PATCH");
                connection.setRequestMethod("POST");
            }else{
                connection.setRequestMethod(type);
            }
            connection.setConnectTimeout(10000);
            responseCode = connection.getResponseCode();
            responseMessage = connection.getResponseMessage();
            System.out.println(responseCode);
            System.out.println(responseMessage);

            if(responseCode < 400){             // if response code less than 400, read from inputStream
                String line;
                StringBuilder stringBuilder = new StringBuilder();
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                while((line = bufferedReader.readLine()) != null){
                    System.out.println(line);
                    stringBuilder.append(line);
                }
                System.out.println(stringBuilder);
                // todo convert xml to params -> code, type, body and return to gui to display


            }else{                              // else, we read from errorStream


            }


        }catch (Exception e){
            System.out.println("Exception catch -> " + e.getMessage());
        }



    }


}
