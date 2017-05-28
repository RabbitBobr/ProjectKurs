package ru.bobrov.parserHTML.model;

import ru.bobrov.parserHTML.vo.Vacancy;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 24.05.17.
 */
public class HHStrategy implements Strategy {

    private static final String URL_FORMAT = "http://hh.ru/search/vacancy?text=%s&page=%d";



    public List<Vacancy> getVacancies(String searchString) {
        List<Vacancy> vacancies = new ArrayList<Vacancy>();
        int page = 0;
        try {

            Document document = getDocument(searchString, page);

            while (true){
                Elements elements = document.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy");
                if (elements.isEmpty())
                    break;
                for (Element element : elements) {
                    String title = element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-title").text();
                    String city = element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-address").text();
                    String companyName = element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-employer").text();
                    String siteName = element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-title").attr("href");
                    String url = element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-title").attr("href");
                    String salary = element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-compensation").text();

                    Vacancy vacancy = new Vacancy();
                    vacancy.setTitle(title);
                    vacancy.setCity(city);
                    vacancy.setCompanyName(companyName);
                    vacancy.setSiteName(siteName);
                    vacancy.setUrl(url);

                    if (salary != null && salary.length() > 0)
                        vacancy.setSalary(salary);
                    else
                        vacancy.setSalary("");

                    vacancies.add(vacancy);


                }

                document = getDocument(searchString, ++page);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return vacancies;
    }

    protected Document getDocument(String searchString, int page) throws Exception{
        Document document = null;
        try {
            String url = String.format(URL_FORMAT, searchString, page);
            document = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                    .referrer("none")
                    .get();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return document;
    }
}
