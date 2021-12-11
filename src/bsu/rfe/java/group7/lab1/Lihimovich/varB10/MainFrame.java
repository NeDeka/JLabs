package bsu.rfe.java.group7.lab1.Lihimovich.varB10;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

@SuppressWarnings("serial")
// Главный класс приложения, он же класс фрейма

public class MainFrame extends JFrame {
    // Размеры окна приложения в виде констант
    private static final int WIDTH = 400;
    private static final int HEIGHT = 320;

    // Текстовые поля для считывания значений переменных,
// как компоненты, совместно используемые в различных методах
    private JTextField textFieldX;
    private JTextField textFieldY;
    private JTextField textFieldZ;
    // Текстовое поле для отображения результата,
// как компонент, совместно используемый в различных методах
    private JTextField textFieldResult;
    private JTextField textFieldMem;

    // Группа радио-кнопок для обеспечения уникальности выделения в группе
    private ButtonGroup radioButtons = new ButtonGroup();
    private ButtonGroup memradioButtons = new ButtonGroup();

    // Контейнер для отображения радио-кнопок
    private Box hboxFormulaType = Box.createVerticalBox();
    private int formulaId = 1;

    private Box hboxMemType = Box.createVerticalBox();
    private int memId = 1;

    // Формула №1 для рассчѐта
    public Double calculate1(Double x, Double y,Double z) {
        return (Math.sin(3.14*y*y)+Math.log(y*y))/(Math.sin(3.14*z*z)+Math.sin(x)+Math.log(z*z)+x*x+Math.exp(Math.cos(z*x)));
    }
    // Формула №2 для рассчѐта
    public Double calculate2(Double x, Double y,Double z) {
        return (Math.pow(Math.sin(Math.pow(z,y)),2))/(Math.sqrt(1+x*x*x));
    }

    /*public Double calculate1(Double x, Double y,Double z){
        return x+y+z;
    }
    public Double calculate2(Double x, Double y,Double z){
        return x*y*z;
    }*/
    // Вспомогательный метод для добавления кнопок на панель
    private void addRadioButton(String buttonName, final int formulaId) {
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                MainFrame.this.formulaId = formulaId;
            }
        });
        radioButtons.add(button);
        hboxFormulaType.add(button);
    }
    private void addMemRadioButton(String buttonName, final int memId) {
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                MainFrame.this.memId = memId;
                if(memId==1){
                    textFieldMem.setText(mem1.toString());
                }if(memId==2){
                    textFieldMem.setText(mem2.toString());
                }if(memId==3){
                    textFieldMem.setText(mem3.toString());
                }
            }
        });
        memradioButtons.add(button);
        hboxMemType.add(button);
    }

    private Double mem1=0.0;
    private Double mem2=0.0;
    private Double mem3=0.0;

    // Конструктор класса
    public MainFrame() {
        super("Вычисление формулы");
        setSize(WIDTH, HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();
    // Отцентрировать окно приложения на экране
        setLocation((kit.getScreenSize().width - WIDTH)/2, (kit.getScreenSize().height - HEIGHT)/2);


// Формулы / mem
        hboxFormulaType.add(Box.createVerticalGlue());
        hboxFormulaType.add(Box.createHorizontalGlue());
        addRadioButton("Формула 1", 1);
        addRadioButton("Формула 2", 2);
        radioButtons.setSelected(radioButtons.getElements().nextElement().getModel(), true);
        hboxFormulaType.add(Box.createHorizontalGlue());
        hboxFormulaType.add(Box.createVerticalGlue());
        //hboxFormulaType.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        hboxMemType.add(Box.createVerticalGlue());
        hboxMemType.add(Box.createHorizontalGlue());
        addMemRadioButton("Переменная 1", 1);
        addMemRadioButton("Переменная 2", 2);
        addMemRadioButton("Переменная 3", 3);
        memradioButtons.setSelected(memradioButtons.getElements().nextElement().getModel(), true);
        hboxMemType.add(Box.createVerticalGlue());
        hboxMemType.add(Box.createHorizontalGlue());
        //hboxMemType.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        Box hboxMemFormula = Box.createHorizontalBox();
        hboxMemFormula.add(Box.createHorizontalGlue());
        hboxMemFormula.add(hboxFormulaType);
        hboxMemFormula.add(hboxMemType);
        hboxMemFormula.add(Box.createHorizontalGlue());

// Создать область с полями ввода для X/Y/Z
        JLabel labelForX = new JLabel("X:");
        textFieldX = new JTextField("0", 10);
        textFieldX.setMaximumSize(textFieldX.getPreferredSize());
        JLabel labelForY = new JLabel("Y:");
        textFieldY = new JTextField("0", 10);
        textFieldY.setMaximumSize(textFieldY.getPreferredSize());
        JLabel labelForZ = new JLabel("Z:");
        textFieldZ = new JTextField("0", 10);
        textFieldZ.setMaximumSize(textFieldZ.getPreferredSize());

        Box hboxVariables = Box.createVerticalBox();
        //hboxVariables.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        Box hboxX = Box.createHorizontalBox();
        hboxVariables.add(Box.createVerticalStrut(10));
        hboxVariables.add(Box.createHorizontalGlue());

        hboxX.add(labelForX);
        hboxX.add(textFieldX);
        Box hboxY = Box.createHorizontalBox();
        hboxY.add(labelForY);
        hboxY.add(textFieldY);
        Box hboxZ = Box.createHorizontalBox();
        hboxZ.add(labelForZ);
        hboxZ.add(textFieldZ);

        hboxVariables.add(hboxX);
        hboxVariables.add(hboxY);
        hboxVariables.add(hboxZ);
        hboxVariables.add(Box.createHorizontalGlue());
        hboxVariables.add(Box.createVerticalStrut(10));

// Создать область для вывода результата
        JLabel labelForResult = new JLabel("Результат:");
        textFieldResult = new JTextField("0", 15);
        textFieldResult.setMaximumSize(textFieldResult.getPreferredSize());
        Box hboxResultat = Box.createHorizontalBox();
        hboxResultat.add(Box.createHorizontalGlue());
        hboxResultat.add(labelForResult);
        hboxResultat.add(textFieldResult);
        hboxResultat.add(Box.createHorizontalGlue());

        JLabel labelForMem = new JLabel("mem:");
        textFieldMem = new JTextField("0", 15);
        textFieldMem.setMaximumSize(textFieldMem.getPreferredSize());
        Box hboxResMem = Box.createHorizontalBox();
        hboxResMem.add(Box.createHorizontalGlue());
        hboxResMem.add(Box.createHorizontalStrut(27));
        hboxResMem.add(labelForMem);
        hboxResMem.add(textFieldMem);
        hboxResMem.add(Box.createHorizontalGlue());

        Box hboxResult = Box.createVerticalBox();
        hboxResult.add(Box.createVerticalGlue());
        hboxResult.add(hboxResultat);
        hboxResult.add(hboxResMem);
        hboxResult.add(Box.createVerticalGlue());
        //hboxResult.setBorder(BorderFactory.createLineBorder(Color.BLACK));


        Box hboxRes = Box.createHorizontalBox();
        hboxRes.add(Box.createHorizontalGlue());
        hboxRes.add(hboxVariables);
        hboxRes.add(Box.createHorizontalStrut(20));
        hboxRes.add(hboxResult);
        hboxRes.add(Box.createHorizontalGlue());

// Создать область для кнопок
        JButton buttonCalc = new JButton("Вычислить");
        buttonCalc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    Double x = Double.parseDouble(textFieldX.getText());
                    Double y = Double.parseDouble(textFieldY.getText());
                    Double z = Double.parseDouble(textFieldY.getText());
                    Double result;
                    if (formulaId==1)
                        result = calculate1(x, y, z);
                    else
                        result = calculate2(x, y, z);
                    textFieldResult.setText(result.toString());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(MainFrame.this,"Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        JButton buttonReset = new JButton("Очистить поля");
        buttonReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                textFieldX.setText("0");
                textFieldY.setText("0");
                textFieldZ.setText("0");
                textFieldResult.setText("0");
            }
        });

        JButton buttonM = new JButton("M+");
        buttonM.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(memId==1){
                    //textFieldMem.setText(mem1.toString());
                    mem1+=Double.valueOf((textFieldResult).getText());
                    //textFieldResult.setText(mem1.toString());
                    textFieldMem.setText(mem1.toString());
                }
                if(memId==2){
                    //textFieldMem.setText(mem2.toString());
                    mem2+=Double.valueOf((textFieldResult).getText());
                    //textFieldResult.setText(mem2.toString());
                    textFieldMem.setText(mem2.toString());
                }
                if(memId==3){
                    //textFieldMem.setText(mem3.toString());
                    mem3+=Double.valueOf((textFieldResult).getText());
                    //textFieldResult.setText(mem3.toString());
                    textFieldMem.setText(mem3.toString());
                }
            }
        });

        JButton buttonMC = new JButton("MC");
        buttonMC.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(memId==1){
                    mem1 = 0.0;
                    textFieldMem.setText("0");
                }
                if(memId==2){
                    mem2 = 0.0;
                    textFieldMem.setText("0");
                }
                if(memId==3){
                    mem3 = 0.0;
                    textFieldMem.setText("0");
                }
            }
        });

// кнопки M+ / MC
        Box hboxMButtons = Box.createHorizontalBox();
        hboxMButtons.add(Box.createHorizontalGlue());
        hboxMButtons.add(buttonM);
        hboxMButtons.add(Box.createHorizontalStrut(30));
        hboxMButtons.add(buttonMC);
        hboxMButtons.add(Box.createHorizontalGlue());
        //hboxMButtons.setBorder(BorderFactory.createLineBorder(Color.BLACK));

// кнопки Вычислить и очистить поля
        Box hboxButtons = Box.createHorizontalBox();
        hboxButtons.add(buttonCalc);
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.add(buttonReset);
        //hboxButtons.setBorder(BorderFactory.createLineBorder(Color.BLACK));


// Связать области воедино в компоновке BoxLayout
        Box contentBox = Box.createVerticalBox();
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(hboxMemFormula);
        contentBox.add(hboxRes);
        contentBox.add(hboxMButtons);
        contentBox.add(hboxButtons);
        contentBox.add(Box.createVerticalGlue());
        getContentPane().add(contentBox, BorderLayout.CENTER);
    }
    // Главный метод класса
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}