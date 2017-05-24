package ru.bobrov.parserHTML.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Created by Rabbik on 24.05.2017.
 */
public class SeachForm {
    public void go(int close) {
        JFrame frame = new JFrame("Параметры поиска");
        frame.setSize(new Dimension(300, 150));
        frame.setDefaultCloseOperation(close);
        if (close != 3)
            frame.addWindowListener(new WindowListener() {
                @Override
                public void windowOpened(WindowEvent e) {

                }

                @Override
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }

                @Override
                public void windowClosed(WindowEvent e) {

                }

                @Override
                public void windowIconified(WindowEvent e) {

                }

                @Override
                public void windowDeiconified(WindowEvent e) {

                }

                @Override
                public void windowActivated(WindowEvent e) {

                }

                @Override
                public void windowDeactivated(WindowEvent e) {

                }
            });
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridBagLayout());

        JLabel nameLabel = new JLabel("Параметры поиска на сайте hh.ru");

        JLabel protLabel = new JLabel("Параметры поиска ");
        JLabel fatLabel = new JLabel("Город ");


        final JTextArea protTextArea = new JTextArea();
        final JTextArea fatTextArea = new JTextArea();


        JButton addIngredientButton = new JButton("Начать поиск");
        JButton delButton = new JButton("Очистить поля");
        delButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                protTextArea.setText("");
                fatTextArea.setText("");


            }
        });

        addIngredientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {



            }
        });
//Описание
        frame.add(nameLabel, new GridBagConstraints(0,1,2,1,1,1,
                GridBagConstraints.NORTH,GridBagConstraints.HORIZONTAL,
                new Insets(1,1,1,1), 0, 0));


//Поиск
        frame.add(protLabel, new GridBagConstraints(0,2,2,1,1,1,
                GridBagConstraints.NORTH,GridBagConstraints.HORIZONTAL,
                new Insets(1,1,1,1), 0, 0));
        frame.add(protTextArea, new GridBagConstraints(0,3,2,1,1,1,
                GridBagConstraints.NORTH,GridBagConstraints.HORIZONTAL,
                new Insets(1,1,1,1), 0, 0));
//Город
        frame.add(fatLabel, new GridBagConstraints(0,4,1,1,1,1,
                GridBagConstraints.NORTH,GridBagConstraints.HORIZONTAL,
                new Insets(1,1,1,1), 0, 0));
        frame.add(fatTextArea, new GridBagConstraints(1,4,1,1,1,1,
                GridBagConstraints.NORTH,GridBagConstraints.HORIZONTAL,
                new Insets(1,1,1,1), 0, 0));
//Кнопки
        frame.add(addIngredientButton, new GridBagConstraints(0,6,1,1,1,1,
                GridBagConstraints.NORTH,GridBagConstraints.HORIZONTAL,
                new Insets(1,1,1,1), 0, 0));
        frame.add(delButton, new GridBagConstraints(1,6,1,1,1,1,
                GridBagConstraints.NORTH,GridBagConstraints.HORIZONTAL,
                new Insets(1,1,1,1), 0, 0));

        frame.setVisible(true);

    }
   }

