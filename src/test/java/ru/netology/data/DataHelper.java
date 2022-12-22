package ru.netology.data;

import lombok.Value;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataHelper {

    private static final String orderSQL = "SELECT payment_id FROM order_entity ORDER BY created DESC LIMIT 1";
    private static final String paymentStatusSQL = "SELECT status FROM payment_entity WHERE transaction_id = ? LIMIT 1";
    private static final String creditRequestStatusSQL = "SELECT status FROM credit_request_entity WHERE bank_id = ? LIMIT 1";

    @Value
    public static class BankCard {
        String number;
        String cardHolder;
        String expirationMonth;
        String expirationYear;
        String CVV;
    }

    public static String getLastOrderPaymentId() {
        String output = null;
        try (
                var conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "petya", "qwerty123"
                );
                var statement = conn.prepareStatement(orderSQL)
        ) {
            try (var rs = statement.executeQuery()) {
                if (rs.next()) {
                    output = rs.getString(1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return output;
    }

    public static String getPaymentStatus(String paymentId) {
        var status = "";
        try (
                var conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "petya", "qwerty123"
                );
                var statement = conn.prepareStatement(paymentStatusSQL)
        ) {
            statement.setString(1, paymentId);
            try (var rs = statement.executeQuery()) {
                if (rs.next()) {
                    status = rs.getString(1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return status;
    }

    public static String getCreditRequestStatus(String paymentId) {
        var status = "";
        try (
                var conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "petya", "qwerty123"
                );
                var statement = conn.prepareStatement(creditRequestStatusSQL)
        ) {
            statement.setString(1, paymentId);
            try (var rs = statement.executeQuery()) {
                if (rs.next()) {
                    status = rs.getString(1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return status;
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
        return "Петя Иванов22";
    }

    public static String getInvalidCVV() {
        return "1";
    }
}
