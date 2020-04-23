///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package javafx.pkg4labs.controller.siswa;
//
///**
// *
// * @author Diazs
// */
//
//import java.awt.BorderLayout;
//import java.awt.Color;
//import static java.awt.Component.LEFT_ALIGNMENT;
//import java.awt.Dimension;
//import java.awt.Font;
//import java.awt.font.FontRenderContext;
//import java.awt.geom.AffineTransform;
//import java.util.ArrayList;
//import javafx.pkg4labs.model.GuruBK;
//import javax.swing.BorderFactory;
//import javax.swing.Box;
//import javax.swing.BoxLayout;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.JTextArea;
//import javax.swing.JTextField;
//
//public class Curhat extends javax.swing.JFrame {
//    
//    //Deklarasi variable
//    private static JTextField input;
//    private static JButton btn;
//    private static JPanel panel1,panel2;
//    private static JScrollPane listScroller;
//    private static JPanel listPane;
//    private static JLabel label;
//    private static JPanel buttonPane;
//    private static JFrame frame;
//    
//    
//    Curhat(){
//        initialize();
//    }
//    
//    private void initialize(){
//                frame = new JFrame();
//                
//                input = new JTextField();
//                input.setPreferredSize(new Dimension(150,25));
//                input.setText("Ketik pesan");
//                
//                btn = new JButton("Send");
//                btn.setBackground(new java.awt.Color(0,153,102));
//                JLabel placeHolder = new JLabel("Mulai Percakapan Anda Dengan "+(GuruBK.getJenisKelamin().equalsIgnoreCase("Perempuan")?"Bu ":"Pak ")+GuruBK.getNama());
//                panel1 = new JPanel();
//                panel1.setLayout(new BoxLayout(panel1, BoxLayout.PAGE_AXIS));
//                Color me = new Color(0,204,0);
//                Color colOposite = new Color(255,255,204);
//                
//                for (int i = 0; i < 2; i++) {
//                    String text = "Assalamualaikum bu saya mau tanya boleh ga jancuk koe gimana sih kamu teh gak bener bat dah ajik kesel ih nahanya kunaon ai maneh kenapa ai kamu teh gamau diem sih kalo diomongin teh bla bla bla harus mandiri kamu jadi orang jangan tergantung sama orang lain agar kamu selamat dunia akhirat oke";
//                    JTextArea textArea = new JTextArea();
//                    textArea.setEditable(false);
//                    textArea.setPreferredSize(new Dimension(350, 35));
//                    textArea.setMinimumSize(new Dimension(100,35));
//                    textArea.setMaximumSize(new Dimension(500, textArea.getMaximumSize().width));
//                    
//                    textArea.setBackground(i%2==0?colOposite:me);
//                    textArea.setFont(new Font("Tahoma", Font.PLAIN, 16));
//                    textArea.setWrapStyleWord(true);
//                    textArea.setText(limit_word(text));
//                    panel1.add(Box.createHorizontalGlue());
//                    panel1.add(textArea,LEFT_ALIGNMENT);
//                    panel1.add(Box.createRigidArea(new Dimension(0,5)));
//                }
//                
//                Color colListPane = new Color(102,0,153);
//                Color colLabel = new Color(255,255,0);
//                Color colButPane = new Color(102,102,102);
//                
//                panel1.setBackground(Color.white);
//      
//                listScroller = new JScrollPane(panel1);
//                listScroller.setPreferredSize(new Dimension(250, 80));
//                listScroller.setAlignmentX(LEFT_ALIGNMENT);
//                
//                //Lay out the label and scroll pane from top to bottom.
//                listPane = new JPanel();
//                listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));
//                label = new JLabel("Bu Hani");
//                label.setForeground(colLabel);
//                label.setFont(new Font("Tahoma", Font.BOLD, 25));
//                
//                listPane.setBackground(new java.awt.Color(153,0,255));
//                listPane.add(label);
//                listPane.add(Box.createRigidArea(new Dimension(0,10)));
//                listPane.add(listScroller);
//                listPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
//
//                //Lay out the buttons from left to right.
//                buttonPane = new JPanel();
//                buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
//                buttonPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//                buttonPane.setBackground(colButPane);
//                buttonPane.add(Box.createRigidArea(new Dimension(0,30)));
//                buttonPane.add(Box.createHorizontalGlue());
//                buttonPane.add(input);
//                buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
//                buttonPane.add(btn);
//                
//                frame.add(listPane, BorderLayout.CENTER);
//                frame.add(buttonPane, BorderLayout.PAGE_END);
//                frame.pack();
//                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//                frame.setLocationByPlatform(true);
//                frame.setSize(500, 500);
//                frame.setResizable(false);
//                frame.setVisible(true);
//                
//                pack();
//    }
//    
//    private String limit_word(String text){
//        String result = "";
//        ArrayList<String> divWord = new ArrayList<>();
//        int start = 0;
//        int end = 1;
//        
//        AffineTransform affinetransform = new AffineTransform();     
//        FontRenderContext frc = new FontRenderContext(affinetransform,true,true);     
//        Font font = new Font("Tahoma", Font.PLAIN, 16);
//        
//        while(text.length()>=end){
//            
//            String temp = text.substring(start,end);
//            
//            if((int)(font.getStringBounds(temp, frc).getWidth())>427){
//                if(divWord.isEmpty()){
//                    divWord.add(temp);
//                }else{
//                    divWord.add("\n"+temp);
//                }
//                start = end;
//            }
//            if(end==text.length()){
//                if (temp.length()!=0) {
//                    if (divWord.isEmpty()) {
//                        temp = temp.substring(0, 1).equals(" ")?temp.substring(1):temp;
//                        divWord.add(temp);
//                    }else{
//                        temp = temp.substring(0, 1).equals(" ")?temp.substring(1):temp;
//                        System.out.println("end = "+end+" length = "+text.length());
//                        divWord.add("\n\r"+temp);
//                    }
//                }
//            }
//            end++;
//        }
//        
//        for(String kata : divWord){
//            result+=kata;
//            System.out.print(kata);
//        }
//        
//        return result;
//    }
//    
//}
