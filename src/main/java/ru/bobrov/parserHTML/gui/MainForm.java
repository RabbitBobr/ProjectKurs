package ru.bobrov.parserHTML.gui;

import ru.bobrov.parserHTML.Controller;
import ru.bobrov.parserHTML.model.HHStrategy;
import ru.bobrov.parserHTML.model.ListVacanciesTableModel;
import ru.bobrov.parserHTML.model.Model;
import ru.bobrov.parserHTML.model.Provider;
import ru.bobrov.parserHTML.view.HtmlView;
import ru.bobrov.parserHTML.vo.Vacancy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.List;


/**
 * Created by user on 24.05.17.
 */
public class MainForm extends JFrame {

    private final String PATH_TO_RESOURCES = "classes/";
    private File fileData = new File(PATH_TO_RESOURCES + "SaveListTable.slt");

    private ListVacanciesTableModel listTableModel;
    private JTable table;

    public void go() {



        JFrame frame = new JFrame("Поиск работы");
        frame.setSize(new Dimension(600, 400));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        //Северная панель
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        JLabel nameLabel = new JLabel("Параметры поиска на сайте hh.ru (строго латинскими буквами)");

        JLabel protLabel = new JLabel("Параметры поиска ");
        JLabel fatLabel = new JLabel("Город ");


        final JTextArea protTextArea = new JTextArea();
        final JTextArea fatTextArea = new JTextArea();


        JButton addIngredientButton = new JButton("Начать поиск");
        addIngredientButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                listTableModel.setVacancies(setVacancies(protTextArea.getText(), fatTextArea.getText()));
                table.updateUI();
            }
        });

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
        panel.add(addIngredientButton, new GridBagConstraints(0,6,2,1,1,1,
                GridBagConstraints.NORTH,GridBagConstraints.HORIZONTAL,
                new Insets(1,1,1,1), 0, 0));

//Центральная панель
        listTableModel = new ListVacanciesTableModel(readData());
        table = new JTable(listTableModel);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    Desktop desktop = Desktop.getDesktop();
                    try {

                        URI url = new URI(listTableModel.getValueAt(table.getSelectedRow(), 4).toString());
                        desktop.browse(url);
                    } catch (URISyntaxException | IOException ex) {
                        ex.getMessage();
                    }
                }
            }
        });
        JScrollPane scrollPaneTable = new JScrollPane(table);

//Южная панель
        JPanel panelS = new JPanel();
        panelS.setLayout(new BoxLayout(panelS, BoxLayout.X_AXIS));
        final JCheckBox slaryTrue = new JCheckBox("Только с зарплатой");
        JButton searchButtonTable = new JButton("Применить фильтр");
        searchButtonTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (slaryTrue.isSelected())
                    listTableModel.filterSalary();
                else
                    listTableModel.falseFilter();
                table.updateUI();
            }
        });
        JButton saveButton = new JButton("Сохранить");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveData(listTableModel.getVacancies());
            }
        });
        JButton loadButton = new JButton("Считать данные с диска");
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listTableModel.setVacancies(readData());
                table.updateUI();
            }
        });
        panelS.add(slaryTrue);
        panelS.add(searchButtonTable);
        panelS.add(saveButton);
        panelS.add(loadButton);

        frame.add(panel, BorderLayout.NORTH);
        frame.add(scrollPaneTable, BorderLayout.CENTER);
        frame.add(panelS, BorderLayout.SOUTH);

        frame.setVisible(true);
        frame.pack();

    }

    private java.util.List<Vacancy> setVacancies (String seachString, String city) {
        java.util.List<Vacancy> result;
        Provider provider = new Provider(new HHStrategy());
        HtmlView view = new HtmlView();
        Model model = new Model(view, provider);
        Controller controller = new Controller(model);

        view.setController(controller);

        result = view.userCitySelectEmulationMethod(seachString, city);
return result;
    }

    private void saveData(java.util.List<Vacancy> saveList) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileData))) {
            for (Vacancy v : saveList) {
                out.writeObject(v);
            }
        } catch (IOException ex) {
            ex.getMessage();
        }
    }

    private List<Vacancy> readData () {
        List<Vacancy> result = new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileData))) {
            while (true) {
                Vacancy vacancy = (Vacancy) in.readObject();
                result.add(vacancy);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.getMessage();
        }

        return result;
    }


}
