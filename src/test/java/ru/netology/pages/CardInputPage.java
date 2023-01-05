package ru.netology.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class CardInputPage {

    private final SelenideElement buyButton = $(By.xpath("//*[text() = 'Купить']/../.."));
    private final SelenideElement creditButton = $(By.xpath("//*[text() = 'Купить в кредит']/../.."));
    private final SelenideElement cardNumberInput = $("input[placeholder='0000 0000 0000 0000']");
    private final SelenideElement monthInput = $("input[placeholder='08']");
    private final SelenideElement yearInput = $("input[placeholder='22']");
    private final SelenideElement ownerInput = $(By.xpath("//*[text() = 'Владелец']/..//*[@class='input__control']"));
    private final SelenideElement CVVInput = $("input[placeholder='999']");
    private final SelenideElement nextButton = $(By.xpath("//*[text() = 'Продолжить']/../.."));
    private final SelenideElement notificationError = $("div.notification_status_error div.notification__content");
    private final SelenideElement notificationSuccess = $("div.notification_status_ok div.notification__content");
    private final SelenideElement cardNumberErrorText = $(By.xpath("//*[text() = 'Номер карты']/..//span[text()='Неверный формат']"));
    private final SelenideElement monthErrorText = $(By.xpath("//*[text() = 'Месяц']/..//span[text()='Неверный формат']"));
    private final SelenideElement yearErrorText = $(By.xpath("//*[text() = 'Год']/..//span[text()='Неверный формат']"));
    private final SelenideElement ownerErrorText = $(By.xpath("//*[text() = 'Владелец']/..//span[text()='Поле обязательно для заполнения']"));
    private final SelenideElement cvvErrorText = $(By.xpath("//*[text() = 'CVC/CVV']/..//span[text()='Неверный формат']"));

    public void selectBuy() {
        buyButton.click();
    }

    public void selectCredit() {
        creditButton.click();
    }

    public void inputCardNumber(String number) {
        cardNumberInput.sendKeys(number);
    }

    public void inputMonth(String monthNumber) {
        monthInput.sendKeys(monthNumber);
    }

    public void inputYear(String yearNumber) {
        yearInput.sendKeys(yearNumber);
    }

    public void inputOwner(String owner) {
        ownerInput.sendKeys(owner);
    }

    public void inputCVV(String code) {
        CVVInput.sendKeys(code);
    }

    public void clickNext() {
        nextButton.click();
    }

    public void checkForError() {
        notificationError.shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

    public void checkForSuccess() {
        notificationSuccess.shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

    public void checkForCardNumberError() {
        cardNumberErrorText.shouldBe(Condition.visible);
    }

    public void checkForMonthError() {
        monthErrorText.shouldBe(Condition.visible);
    }

    public void checkForYearError() {
        yearErrorText.shouldBe(Condition.visible);
    }

    public void checkForOwnerError() {
        ownerErrorText.shouldBe(Condition.visible);
    }

    public void checkForCVVError() {
        cvvErrorText.shouldBe(Condition.visible);
    }

    public void checkForNoErrors() {
        cardNumberErrorText.shouldBe(Condition.hidden);
        monthErrorText.shouldBe(Condition.hidden);
        yearErrorText.shouldBe(Condition.hidden);
        ownerErrorText.shouldBe(Condition.hidden);
        cvvErrorText.shouldBe(Condition.hidden);
    }
}