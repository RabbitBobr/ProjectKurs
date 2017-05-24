package ru.bobrov.parserHTML.model;

import ru.bobrov.parserHTML.view.View;
import ru.bobrov.parserHTML.vo.Vacancy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 24.05.17.
 */
public class Model {
    private View view;
    private Provider[] providers;

    public Model(View view, Provider... providers) {
        if (view == null || providers == null || providers.length == 0)
            throw new IllegalArgumentException();

        this.view = view;
        this.providers = providers;
    }

    public void selectCity(String city){
        List<Vacancy> list = new ArrayList<>();

        for (Provider provider : providers){
            list.addAll(provider.getJavaVacancies(city));
        }

        view.update(list);
    }
}
