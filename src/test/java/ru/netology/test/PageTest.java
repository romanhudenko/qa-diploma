package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.pages.CardInputPage;

import static com.codeborne.selenide.Selenide.*;

public class PageTest {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    public void openBrowser() {
        open("http://localhost:8080/");
    }

    @Test
    public void criticalPath() {
        DataHelper.BankCard card = DataHelper.getValidCard();
        CardInputPage page = new CardInputPage();
        page.selectBuy();
        page.inputCardNumber(card.getNumber());
        page.inputMonth(card.getExpirationMonth());
        page.inputYear(card.getExpirationYear());
        page.inputOwner(card.getCardHolder());
        page.inputCVV(card.getCVV());
        page.clickNext();
        page.checkForSuccess();
        String paymentId = DataHelper.getLastOrderPaymentId();
        String status = DataHelper.getPaymentStatus(paymentId);
        Assertions.assertEquals("APPROVED", status);
    }

    @Test
    public void criticalCreditPath() {
        DataHelper.BankCard card = DataHelper.getValidCard();
        CardInputPage page = new CardInputPage();
        page.selectCredit();
        page.inputCardNumber(card.getNumber());
        page.inputMonth(card.getExpirationMonth());
        page.inputYear(card.getExpirationYear());
        page.inputOwner(card.getCardHolder());
        page.inputCVV(card.getCVV());
        page.clickNext();
        page.checkForSuccess();
        String paymentId = DataHelper.getLastOrderPaymentId();
        String status = DataHelper.getCreditRequestStatus(paymentId);
        Assertions.assertEquals("APPROVED", status);
    }

    @Test
    public void paymentDeclined() {
        DataHelper.BankCard card = DataHelper.getDeclinedCard();
        CardInputPage page = new CardInputPage();
        page.selectBuy();
        page.inputCardNumber(card.getNumber());
        page.inputMonth(card.getExpirationMonth());
        page.inputYear(card.getExpirationYear());
        page.inputOwner(card.getCardHolder());
        page.inputCVV(card.getCVV());
        page.clickNext();
        page.checkForError();
        String paymentId = DataHelper.getLastOrderPaymentId();
        String status = DataHelper.getPaymentStatus(paymentId);
        Assertions.assertEquals("DECLINED", status);
    }

    @Test
    public void creditDeclined() {
        DataHelper.BankCard card = DataHelper.getDeclinedCard();
        CardInputPage page = new CardInputPage();
        page.selectCredit();
        page.inputCardNumber(card.getNumber());
        page.inputMonth(card.getExpirationMonth());
        page.inputYear(card.getExpirationYear());
        page.inputOwner(card.getCardHolder());
        page.inputCVV(card.getCVV());
        page.clickNext();
        page.checkForError();
        String paymentId = DataHelper.getLastOrderPaymentId();
        String status = DataHelper.getCreditRequestStatus(paymentId);
        Assertions.assertEquals("DECLINED", status);
    }

    @Test
    public void invalidCard() {
        DataHelper.BankCard card = DataHelper.getInvalidCard();
        CardInputPage page = new CardInputPage();
        page.selectCredit();
        page.inputCardNumber(card.getNumber());
        page.inputMonth(card.getExpirationMonth());
        page.inputYear(card.getExpirationYear());
        page.inputOwner(card.getCardHolder());
        page.inputCVV(card.getCVV());
        page.clickNext();
        page.checkForError();
    }

    @Test
    public void emptyForm() {
        CardInputPage page = new CardInputPage();
        page.selectBuy();
        page.clickNext();
        page.checkForCardNumberError();
        page.checkForMonthError();
        page.checkForYearError();
        page.checkForOwnerError();
        page.checkForCVVError();
    }

    @Test
    public void invalidCardNumber() {
        CardInputPage page = new CardInputPage();
        page.selectBuy();
        page.inputCardNumber(DataHelper.getInvalidCardNumber());
        page.clickNext();
        page.checkForCardNumberError();
    }

    @Test
    public void invalidMonth() {
        CardInputPage page = new CardInputPage();
        page.selectBuy();
        page.inputMonth("3");
        page.clickNext();
        page.checkForMonthError();
    }

    @Test
    public void invalidYear() {
        CardInputPage page = new CardInputPage();
        page.selectBuy();
        page.inputYear("0");
        page.clickNext();
        page.checkForYearError();
    }

    @Test
    public void expiredCard() {
        CardInputPage page = new CardInputPage();
        page.selectBuy();
        page.inputMonth(DataHelper.getPreviousMonth());
        page.inputYear(DataHelper.getCurrentYear());
        page.clickNext();
        page.checkForMonthError();
        page.checkForYearError();
    }

    @Test
    public void invalidOwner() {
        CardInputPage page = new CardInputPage();
        page.selectBuy();
        page.inputOwner(DataHelper.getInvalidOwner());
        page.clickNext();
        page.checkForOwnerError();
    }

    @Test
    public void invalidCVV() {
        CardInputPage page = new CardInputPage();
        page.selectBuy();
        page.inputCVV(DataHelper.getInvalidCVV());
        page.clickNext();
        page.checkForCVVError();
    }
}
