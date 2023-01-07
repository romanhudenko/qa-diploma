package ru.netology.data;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {

    private static final String db_link = System.getProperty("db_link");
    private static final String db_username = System.getProperty("db_username");
    private static final String db_password = System.getProperty("db_password");

    private static final String orderSQL = "SELECT payment_id FROM order_entity ORDER BY created DESC LIMIT 1";
    private static final String paymentStatusSQL = "SELECT status FROM payment_entity WHERE transaction_id = ? LIMIT 1";
    private static final String creditRequestStatusSQL = "SELECT status FROM credit_request_entity WHERE bank_id = ? LIMIT 1";

    public static String getLastOrderPaymentId() {
        String output = null;
        try (
                var conn = DriverManager.getConnection(
                        db_link, db_username, db_password
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
                        db_link, db_username, db_password
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
                        db_link, db_username, db_password
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
}
