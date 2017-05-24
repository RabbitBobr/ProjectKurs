package ru.bobrov.parserHTML.view;

import ru.bobrov.parserHTML.Controller;
import ru.bobrov.parserHTML.vo.Vacancy;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by user on 24.05.17.
 */
public class HtmlView implements View {
    private Controller controller;

    private final String filePath = "vacancies.html";

    @Override
    public void update(List<Vacancy> vacancies) {
        try {
            updateFile(getUpdatedFileContent(vacancies));
            java.net.URI uri;
            try {
                uri = new URI(filePath);
                java.awt.Desktop.getDesktop().browse(uri);

            } catch (IOException ex) {
                ex.printStackTrace();
            }  catch (URISyntaxException ex) {
                ex.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void userCitySelectEmulationMethod(String seachString, String city) {
        controller.onCitySelect(seachString + "+" + city);
    }

    private String getUpdatedFileContent(List<Vacancy> vacancies) {
        StringBuilder htmlContent = new StringBuilder();

        htmlContent.append("<!DOCTYPE html>");
        htmlContent.append("<html lang=\"ru\">");
        htmlContent.append("<head>");
        htmlContent.append("    <meta charset=\"utf-8\">");
        htmlContent.append("    <title>Вакансии</title>");
        htmlContent.append("</head>");
        htmlContent.append("<body>");
        htmlContent.append("<table>");
        htmlContent.append("    <tr>");
        htmlContent.append("        <th>Title</th>");
        htmlContent.append("        <th>City</th>");
        htmlContent.append("        <th>Company Name</th>");
        htmlContent.append("        <th>Salary</th>");
        htmlContent.append("    </tr>");

        for (Vacancy vacancy : vacancies) {
            htmlContent.append(String.format("    <tr class=\"vacancy\">"));
            htmlContent.append(String.format("        <td class=\"title\"><a href=\"%s\">%s</a></td>", vacancy.getUrl(), vacancy.getTitle()));
            htmlContent.append(String.format("        <td class=\"city\">%s</td>", vacancy.getCity()));
            htmlContent.append(String.format("        <td class=\"companyName\">%s</td>", vacancy.getCompanyName()));
            htmlContent.append(String.format("        <td class=\"salary\">%s</td>", vacancy.getSalary()));
            htmlContent.append(String.format("    </tr>"));
        }

        htmlContent.append("    </tr>");
        htmlContent.append("</table>");
        htmlContent.append("</body>");
        htmlContent.append("</html>");

        return htmlContent.toString();
    }

    private void updateFile(String htmlContent) throws IOException{

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))){
            writer.write(htmlContent);
        }
    }
}
