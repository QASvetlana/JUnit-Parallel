package ru.sandreeva.page;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class YandexManePage {

    private SelenideElement searchInput = $(".input__control");
    private SelenideElement searchBtn = $("button[type='submit']");

    public YandexResultsPage doSearch(String searchQuery) {
        searchInput.setValue(searchQuery);
        searchBtn.click();
        return new YandexResultsPage();
    }
}

