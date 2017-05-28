package ru.bobrov.parserHTML.model;

import ru.bobrov.parserHTML.vo.Vacancy;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rabbik on 25.05.2017.
 */
public class ListVacanciesTableModel extends AbstractTableModel {
    private List<Vacancy> vacancies = new ArrayList<>();
    private List<Vacancy> saveList = new ArrayList<>();

    public ListVacanciesTableModel(List<Vacancy> oldVacancy) {
        vacancies = oldVacancy;
    }

    public void setVacancies(List<Vacancy> vacancies) {
        this.vacancies = vacancies;
    }

    @Override
    public String getColumnName(int columnIndex) {
        String result = "";
        switch (columnIndex) {
            case 0: result = "Вакансия"; break;
            case 1: result = "Город"; break;
            case 2: result = "Компания"; break;
            case 3: result = "Зарплата"; break;
            case 4: result = "Ссылка"; break;
        }
        return result;
    }

    public int getRowCount() {
        return vacancies.size();
    }

    public int getColumnCount() {
        return 5;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {

        Vacancy vacancy = vacancies.get(rowIndex);
        String result = "";
        switch (columnIndex) {
            case 0: result = vacancy.getTitle(); break;
            case 1: result = vacancy.getCity(); break;
            case 2: result = vacancy.getCompanyName(); break;
            case 3: result = vacancy.getSalary(); break;
            case 4: result = vacancy.getSiteName(); break;
        }
        return result;

    }

    public void filterSalary() {
        List<Vacancy> result = new ArrayList<>(vacancies);
        for (Vacancy v : vacancies)
            if (v.getSalary().equals(""))
                result.remove(v);
        saveList = vacancies;
        vacancies = result;
    }

    public void falseFilter() {
        vacancies = saveList;
    }

    public List<Vacancy> getVacancies() {
        return vacancies;
    }
}
