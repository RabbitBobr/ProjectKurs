package ru.bobrov.parserHTML.view;

import ru.bobrov.parserHTML.Controller;
import ru.bobrov.parserHTML.vo.Vacancy;

import java.util.List;

/**
 * Created by user on 24.05.17.
 */
public interface View {
    void update(List<Vacancy> vacancies);
    void setController(Controller controller);
}
