/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pizza_servings_calculator;

/**
 *
 * @author ryan
 */

import java.awt.Frame;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.lang.Math;


public class Pizza_Servings_Calculator extends JFrame{

    private final Frame frame;
    private final JPanel line2 = new JPanel();
    String size;
    double servings;
    
    Pizza_Servings_Calculator(){
        frame = new JFrame();
        JLabel line1 = new JLabel("Pizza Servings Calculator", SwingConstants.CENTER);
        line1.setFont(new Font("Serif", Font.BOLD, 24));
        line1.setForeground(Color.red);
        JLabel line2_text = new JLabel("Enter the size of the pizza in inches: ");
        line2.add(line2_text);
        JTextField text_field = new JTextField(4);
        line2.add(text_field);
        JButton calc_button = new JButton("Calculate Servings");
        JLabel return_line = new JLabel("",SwingConstants.CENTER);
        calc_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                size = text_field.getText();
                servings = Math.pow(Double.parseDouble(size)/8,2);
                return_line.setText("A " + size + " inch pizza will serve " + servings + " people.");    
            }        
        });
    
    frame.add(line1);
    frame.add(line2);
    frame.add(calc_button);
    frame.add(return_line);
    frame.setLayout(new GridLayout(4,1));
    frame.setTitle("Pizza Servings Calculator");
    frame.setSize(350,300);
    frame.setVisible(true);
            
    }
    public static void main(String[] args) {
        new Pizza_Servings_Calculator();
    }
    
}
