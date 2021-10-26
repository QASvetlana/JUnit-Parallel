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
import org.junit.jupiter.params.provider.*;
import ru.sandreeva.domain.MenuItem;
import ru.sandreeva.page.MainPage;
import ru.sandreeva.page.YandexManePage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@ExtendWith(SimpleCallback.class)
class ParallelTest {

    private YandexManePage ymp = new YandexManePage();


    static Stream<Arguments> testWithMethodSource() {
        return Stream.of(
                Arguments.of("Поиск"));
    }

    @MethodSource("testWithMethodSource")
    @ParameterizedTest()
    @DisplayName("Test with MethodSource")
    void openTabsUsingMethodSource(String name){
        Configuration.startMaximized = true;
        Selenide.open("https://yandex.ru/search/?lr=2&clid=2257349&win=510&text=hgh&src=suggest_Pers");
        MainPage.checkListTab(name);
        System.out.println(name);
    }



    @EnumSource(MenuItem.class)
    @ParameterizedTest(name = "{1}")
    void checkSearchResultForSeveralMenuItems(MenuItem menuItem) {
        Configuration.startMaximized = true;
        Selenide.open("http://ya.ru");
        ymp.doSearch("погода")
        .switchToMenuItem(menuItem);
        System.out.println();
    }




    @CsvSource({
            "погода, So complex displayed name",
            "новости, So complex displayed name",
    })
    @ParameterizedTest(name = "{1}")
    void testWithComplexName(String searchQuery, String testName) {
        Configuration.startMaximized = true;
        Selenide.open("http://ya.ru");
        ymp.doSearch(searchQuery)
                .checkResults(searchQuery);
    }

    @ValueSource(strings = {
            "погода",
            "новости",
            "музыка",
            "фильмы"
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

    @DisplayName("JDI should be present in search results")
    @Test
    void minimizedWindowTest(TestInfo testInfo) {
        Configuration.startMaximized = true;
        Selenide.open("http://ya.ru");
        new YandexManePage().doSearch("JDI")
                .checkResults("JDI");


    }


}
