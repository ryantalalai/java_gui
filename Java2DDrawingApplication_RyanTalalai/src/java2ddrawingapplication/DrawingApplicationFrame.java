/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java2ddrawingapplication;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;


/**
 *
 * @author acv
 */
public class DrawingApplicationFrame extends JFrame
{


    // Create the panels for the top of the application. One panel for each
    // line and one to contain both of those panels.
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel panel3 = new JPanel();

    // create the widgets for the firstLine Panel.
    JLabel line1 = new JLabel("Shape:");
    String[] string = { "Line", "Oval", "Rectangle" };
    JComboBox shape_list = new JComboBox(string);
    JButton color1 = new JButton("1st Color");
    JButton color2 = new JButton("2nd Color...");
    JButton undo = new JButton("Undo");
    JButton clear = new JButton("Clear");
    //create the widgets for the secondLine Panel.
    JLabel line2 = new JLabel("Options:");
    JCheckBox filled = new JCheckBox("Filled");
    JCheckBox gradient = new JCheckBox("Use Gradient");
    JCheckBox dashed = new JCheckBox("Dashed");
    JLabel line_width = new JLabel("Line Width:");
    JLabel dash_length = new JLabel("Dash Length:");
    SpinnerModel spin1 = new SpinnerNumberModel(1, 1, 200, 1);
    JSpinner spinner1 = new JSpinner(spin1);
    SpinnerModel spin2 = new SpinnerNumberModel(1, 1, 200, 1);
    JSpinner spinner2 = new JSpinner(spin2);
    
    Color color_one = Color.BLACK;
    Color color_two = Color.RED;
    ArrayList<MyShapes> shapes = new ArrayList();
    DrawPanel draw_panel = new DrawPanel();
    MyShapes shape;
    Paint paint;
    Stroke stroke;
    Point start;
    Point end;
    
    
    JLabel status_label = new JLabel("");
    
  
    // Constructor for DrawingApplicationFrame
    public DrawingApplicationFrame()
    {
       panel1.setBackground(Color.CYAN);
       panel1.add(line1);
       panel1.add(shape_list);
       panel1.add(color1);
       panel1.add(color2);
       panel1.add(undo);
       panel1.add(clear);
       
       
       panel2.setBackground(Color.CYAN);
       panel2.add(line2);
       panel2.add(filled);
       panel2.add(gradient);
       panel2.add(dashed);
       panel2.add(line_width);
       panel2.add(spinner1);
       panel2.add(dash_length);
       panel2.add(spinner2);
       
       panel3.setLayout(new GridLayout(2,1));
       panel3.add(panel1);
       panel3.add(panel2);
       setLayout(new BorderLayout());
       
       draw_panel.setBackground(Color.WHITE);
       
       add(panel3, BorderLayout.NORTH);
       add(draw_panel, BorderLayout.CENTER);
       add(status_label, BorderLayout.SOUTH);
       
       
       undo.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent event) {
               if(shapes.size() > 0) {
                   shapes.remove(shapes.size() - 1);
                   repaint();
               }
           }
       });
       
       clear.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent event) {
               if(shapes.size() > 0) {
                   shapes.clear();
                   repaint();
               }
           }
       });
       

       
       color1.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent event) {
               Color color = JColorChooser.showDialog(color1, "Choose a Color", Color.BLACK);
               color_one = color;
           }
       });
       
       color2.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent event) {
               Color color = JColorChooser.showDialog(color2, "Choose a Color", Color.RED);
               color_two = color;
           }
       });
         
       
    }


    // Create a private inner class for the DrawPanel.
    private class DrawPanel extends JPanel
    {

        public DrawPanel()
        {
            MouseHandler mouse_handler = new MouseHandler();
            addMouseListener(mouse_handler);
            addMouseMotionListener(mouse_handler);
            
                    }

        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            //loop through and draw each shape in the shapes arraylist
            
            for(MyShapes shape: shapes){
                shape.draw(g2d);
            }
        }


        private class MouseHandler extends MouseAdapter implements MouseMotionListener
        {

            public void mousePressed(MouseEvent event)
            {
                int line_width = (int)spinner1.getValue();
                int dash_length = (int)spinner2.getValue();
                start = new Point(event.getX(), event.getY());
                end = new Point(event.getX(), event.getY());
                
                if (dashed.isSelected())
                 {
                    stroke = new BasicStroke((float) line_width, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 10, new float[]{(float) dash_length}, 0);
                  } 
                
                else
                {
                stroke = new BasicStroke(line_width, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
                }
                if (gradient.isSelected()) {
                    paint = new GradientPaint(0, 0, color_one, 50, 50, color_two, true);
                }
                else {
                    paint = new GradientPaint(0, 0, color_one, 50, 50, color_one, true);
                }
                
                if (shape_list.getSelectedIndex() == 0) {
                    shapes.add(new MyLine(start, end, paint, stroke));
                }
                else if (shape_list.getSelectedIndex() == 1) {
                    shapes.add(new MyOval(start, end, paint, stroke, filled.isSelected()));
                }
                else if (shape_list.getSelectedIndex() == 2) {
                    shapes.add(new MyRectangle(start, end, paint, stroke, filled.isSelected()));
                }
                
                }
            

            public void mouseReleased(MouseEvent event)
            {
                end = new Point(event.getX(), event.getY());
                
                shapes.get(shapes.size() - 1).setPaint(paint);
                shapes.get(shapes.size() - 1).setEndPoint(end);
                repaint();
                }
            

            
            public void mouseDragged(MouseEvent event)
            {
                end = new Point(event.getX(), event.getY());
                shapes.get(shapes.size() - 1).setEndPoint(end);
                shapes.get(shapes.size() - 1).setPaint(paint);
                repaint();
                
                status_label.setText("(" + event.getX() + ", " + event.getY() + ")");
               
            }

            public void mouseMoved(MouseEvent event) {
               status_label.setText("(" + event.getX() + ", " + event.getY() + ")");
           }
           
        }

    }
}

