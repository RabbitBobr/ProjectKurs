package ru.bobrov.parserHTML.gui;

import ru.bobrov.parserHTML.Controller;
import ru.bobrov.parserHTML.model.HHStrategy;
import ru.bobrov.parserHTML.model.Model;
import ru.bobrov.parserHTML.model.Provider;
import ru.bobrov.parserHTML.view.HtmlView;
import ru.bobrov.parserHTML.vo.Vacancy;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


/**
 * Created by user on 24.05.17.
 */
public class MainForm extends JFrame {

    private java.util.List<Vacancy> vacancies = new ArrayList<>();

    public void go() {
        JFrame frame = new JFrame("Поиск работы");
        frame.setSize(new Dimension(600, 400));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        //Северная панель
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        JLabel nameLabel = new JLabel("Параметры поиска на сайте hh.ru");

        JLabel protLabel = new JLabel("Параметры поиска ");
        JLabel fatLabel = new JLabel("Город ");


        final JTextArea protTextArea = new JTextArea();
        final JTextArea fatTextArea = new JTextArea();


        JButton addIngredientButton = new JButton("Начать поиск");

        //Описание
        panel.add(nameLabel, new GridBagConstraints(0,1,2,1,1,1,
                GridBagConstraints.NORTH,GridBagConstraints.HORIZONTAL,
                new Insets(1,1,1,1), 0, 0));


//Поиск
        panel.add(protLabel, new GridBagConstraints(0,2,2,1,1,1,
                GridBagConstraints.NORTH,GridBagConstraints.HORIZONTAL,
                new Insets(1,1,1,1), 0, 0));
        panel.add(protTextArea, new GridBagConstraints(0,3,2,1,1,1,
                GridBagConstraints.NORTH,GridBagConstraints.HORIZONTAL,
                new Insets(1,1,1,1), 0, 0));
//Город
        panel.add(fatLabel, new GridBagConstraints(0,4,1,1,1,1,
                GridBagConstraints.NORTH,GridBagConstraints.HORIZONTAL,
                new Insets(1,1,1,1), 0, 0));
        panel.add(fatTextArea, new GridBagConstraints(1,4,1,1,1,1,
                GridBagConstraints.NORTH,GridBagConstraints.HORIZONTAL,
                new Insets(1,1,1,1), 0, 0));
//Кнопки
        panel.add(addIngredientButton, new GridBagConstraints(0,6,1,1,1,1,
                GridBagConstraints.NORTH,GridBagConstraints.HORIZONTAL,
                new Insets(1,1,1,1), 0, 0));


        frame.setVisible(true);
        frame.pack();

    }

    private void setVacancies (String seachString, String city) {
        Provider provider = new Provider(new HHStrategy());
        HtmlView view = new HtmlView();
        Model model = new Model(view, provider);
        Controller controller = new Controller(model);

        view.setController(controller);

        view.userCitySelectEmulationMethod(seachString, city);

    }


}
