package ru.bobrov.parserHTML.model;

import ru.bobrov.parserHTML.vo.Vacancy;

import java.util.List;

/**
 * Created by user on 24.05.17.
 */
public class Provider {
    private Strategy strategy;

    public Provider(Strategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public List<Vacancy> getJavaVacancies(String searchString){
        return strategy.getVacancies(searchString);
    }
}
