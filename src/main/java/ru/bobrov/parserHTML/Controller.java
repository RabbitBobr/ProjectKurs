package ru.bobrov.parserHTML;

import ru.bobrov.parserHTML.model.Model;

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

    public void onCitySelect(String cityName){
        model.selectCity(cityName);
    }
}
