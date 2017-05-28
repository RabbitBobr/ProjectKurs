package ru.bobrov.parserHTML;

import ru.bobrov.parserHTML.model.Model;
import ru.bobrov.parserHTML.vo.Vacancy;

import java.util.List;

/**
 * Created by user on 24.05.17.
 */
public class Controller {
    private Model model;

    public Controller(Model model) {
        if (model == null)
            throw new IllegalArgumentException();

        this.model = model;
    }

    public List<Vacancy> onCitySelect(String cityName){
        return model.selectCity(cityName);
    }
}
