package ru.bobrov.parserHTML.model;

import ru.bobrov.parserHTML.vo.Vacancy;

import java.util.List;

/**
 * Created by user on 24.05.17.
 */
public interface Strategy {
    public List<Vacancy> getVacancies(String searchString);
}
