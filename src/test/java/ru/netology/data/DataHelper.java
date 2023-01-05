package ru.netology.data;

import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataHelper {

    @Value
    public static class BankCard {
        String number;
        String cardHolder;
        String expirationMonth;
        String expirationYear;
        String CVV;
    }

    public static String generateDate(int years) {
        return LocalDate.now().plusYears(years).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static BankCard getValidCard() {
        return new BankCard("4444444444444441", "Petr Ivanov", "01", generateDate(1), "111");
    }

    public static BankCard getDeclinedCard() {
        return new BankCard("4444444444444442", "Petr Ivanov", "01", generateDate(1), "111");
    }

    public static BankCard getInvalidCard() {
        return new BankCard("4444444444444444", "Petr Ivanov", "01", generateDate(1), "111");
    }

    public static String getInvalidCardNumber() {
        return "444444444444";
    }

    public static String getPreviousMonth() {
        return LocalDate.now().minusMonths(1).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getCurrentYear() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getInvalidOwner() {
        return "Petr Ivanov 22";
    }

    public static String getCyrillicOwner() {
        return "Пётр Иванов";
    }

    public static String getInvalidCVV() {
        return "1";
    }
}
