import java.util.*;
import java.io.*;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.TextField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Picker extends JFrame implements ActionListener{
    private static final int WIDTH = 700;
    private static final int HEIGHT = 300;

    private JTextArea text;
    
    private JButton period5;
    private JButton period6;
    
    public static String pickStudent(String periodnumber) {
        Random r = new Random(System.nanoTime());
        String[] students = getFileAsArray(periodnumber+".txt");
        return(students[r.nextInt(students.length)]);
    }
    
    public static String[] getFileAsArray(String filename) {
        List<String> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String input;
            while ((input = br.readLine()) != null) {
                list.add(input);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return list.toArray(new String[list.size()]);
    }
    
    public Picker ()  {
        super ("Classroom Picker");
        
        setSize(WIDTH,HEIGHT);
        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        JPanel top = new JPanel();
        JPanel bot = new JPanel();
        top.setLayout(new BoxLayout(top, BoxLayout.X_AXIS));
        text = new JTextArea();
        Font font = new Font("Helvetica", Font.BOLD, 50);
        text.setFont(font);
        text.setSelectedTextColor(Color.GREEN);
        text.setBackground(Color.BLACK);
        text.setForeground(Color.YELLOW);
        text.setText("Student Picker\n\n");
        period5 = new JButton("Pick Period 5");
        period5.addActionListener(this);
        period5.setActionCommand("5");
        period6 = new JButton("Pick Period 6");
        period6.addActionListener(this);
        period6.setActionCommand("6");
        bot.add(period5);
        bot.add(period6);
        top.add(text);
        main.add(top);
        main.add(bot);
        getContentPane().add(main);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void actionPerformed(ActionEvent e){
        text.setText(pickStudent(e.getActionCommand()));
    }
    
    public static void main( String args[] )
    {
        Picker run = new Picker();
    }
}