package ru.bobrov.parserHTML;

import ru.bobrov.parserHTML.model.HHStrategy;
import ru.bobrov.parserHTML.model.Model;
import ru.bobrov.parserHTML.model.Provider;
import ru.bobrov.parserHTML.view.HtmlView;

/**
 * Created by user on 24.05.17.
 */
public class Aggregator {
    public static void main(String[] args) {
        Provider provider = new Provider(new HHStrategy());
        HtmlView view = new HtmlView();
        Model model = new Model(view, provider);
        Controller controller = new Controller(model);

        view.setController(controller);

        view.userCitySelectEmulationMethod();
    }
}
