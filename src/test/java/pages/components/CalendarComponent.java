package pages.components;

import static com.codeborne.selenide.Selenide.$;

public class CalendarComponent {

    public void setDate(String day, String month, String year) {
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(month);
        $(".react-datepicker__year-select").selectOption(year);
        String selectorDay = ".react-datepicker__day--0%s";
        String formatSelectorDay = String.format(selectorDay, day);
        $(formatSelectorDay).click();
    }
}
