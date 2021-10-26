package ru.sandreeva.page;

import com.codeborne.selenide.CollectionCondition;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    public static void checkListTab(String name) {
        $$("li[role='listitem']").shouldHave(CollectionCondition.itemWithText("Картинки"));
    }

}
