package ru.sandreeva;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.ResourceLock;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.sandreeva.page.YandexManePage;

@ExtendWith(SimpleCallback.class)
public class ParallelTest {

    @ValueSource(strings = {
            "qa.guru",
            "selenide",
            "qameta",
            "allure"
    })

    @ParameterizedTest(name = "{0} test")
    void yandexSearchTest(String searchQuery, TestInfo testInfo) {
        Configuration.startMaximized = true;
        Selenide.open("http://ya.ru");
        new YandexManePage().doSearch(searchQuery)
                .checkResults(searchQuery);
        System.out.println("Config for test: "
                + testInfo.getDisplayName()
                + " "
                + Configuration.startMaximized
        );
    }

    @DisplayName("JDI test")
    @Test
    void minimizedWindowTest(TestInfo testInfo) {
        Configuration.startMaximized = false;
        Selenide.open("http://ya.ru");
        new YandexManePage().doSearch("JDI")
                .checkResults("JDI");
        System.out.println("Config for test: "
                + testInfo.getDisplayName()
                + " "
                + Configuration.startMaximized
        );
    }


}
