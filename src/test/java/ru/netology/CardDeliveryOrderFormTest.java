package ru.netology;


import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryOrderFormTest {

    private String generateData(int addDays, String pattern){

        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }


    @Test
    public void shouldBeSuccessCompleted() {
        open("http://localhost:9999");
        $x("//span[@data-test-id= 'city']//input").setValue("Краснодар");
        String currentDate = generateData(3, "dd.MM.yyyy");
        $x("//span[@data-test-id= 'date']//input").sendKeys(Keys.chord(Keys.SHIFT,Keys.HOME),Keys.DELETE);
        $x("//span[@data-test-id= 'date']//input").sendKeys(currentDate);
        $x("//span[@data-test-id= 'name']//input").setValue("Сергеев Сергей");
        $x("//span[@data-test-id= 'phone']//input").setValue("+79998887723");
        $x("//label[@data-test-id= 'agreement']").click();
        $x("//span[contains(text(),  'Забронировать')]").click();
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " + currentDate));

    }
}
