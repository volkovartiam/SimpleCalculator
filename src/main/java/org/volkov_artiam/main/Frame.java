package org.volkov_artiam.main;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Frame extends JFrame {

    JTextArea textAreaExp = new JTextArea();
    JTextArea textAreaResult = new JTextArea();
    JRadioButton btnShowSteps = new JRadioButton("Show steps");
    JButton btnCalculate = new JButton("Calculate");

    boolean showSteps = false;

    Calculator calculator;

    public Frame() {

        this.setSize(440, 250);
        this.setResizable(false);
        setLocationRelativeTo( null ) ;
        setDefaultCloseOperation( EXIT_ON_CLOSE ) ;
        setLocationRelativeTo(null);
        setDefaultLookAndFeelDecorated(true);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        btnCalculate.setBounds(300, 30, 90, 25);
        btnCalculate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textAreaResult.setText("");
                String exp = getExpTextArea();
                try {
                    calculator = new Calculator();
                    calculator.calculate(exp);
                    if(showSteps) {
                        setSteps(calculator.getSteps() );
                    }
                    setAnswer(calculator.getAnswer() );
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, exception.getMessage());
                }
            }
        });
        panel.add(btnCalculate);

        JScrollPane scrollExp = new JScrollPane();
        scrollExp.setBounds(10, 30, 280, 70);
        panel.add(scrollExp);
        scrollExp.setViewportView(textAreaExp);
        textAreaExp.setText("1+1");

        JScrollPane scrollResult = new JScrollPane();
        scrollResult.setBounds(10, 130, 280, 70);
        panel.add(scrollResult);
        scrollResult.setViewportView(textAreaResult);

        btnShowSteps.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(btnShowSteps.isSelected()) {
                    showSteps = true;
                } else {
                    showSteps = false;
                }
            }
        });

        btnShowSteps.setBounds(300, 130, 93, 25);
        panel.add(btnShowSteps);

        JLabel lblAnswer = new JLabel("Answer");
        lblAnswer.setBounds(10, 111, 94, 14);
        panel.add(lblAnswer);

        JLabel lblExp = new JLabel("Expression");
        lblExp.setBounds(10, 11, 114, 14);
        panel.add(lblExp);

        this.add(panel);
    }


    public String getExpTextArea() {
        return textAreaExp.getText();
    }

    public void setAnswer(String answer) {
        textAreaResult.append("Answer = " + answer);
    }

    public void setSteps(ArrayList<String> calculatorSteps) {
        for(String step : calculatorSteps) {
            textAreaResult.append(step + "\n");
        }
    }
}
