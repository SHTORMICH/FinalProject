package com.kabaldin.controller.DAO.query;

public abstract class SQLQuery {

    public static class UserQuery {
        public static final String INSERT_USER = "INSERT INTO user(login, email, password, first_name, last_name, phone_number, account, role_id) VALUES (?, ?, ?, ?, ?, ?, 0, 3);";
        public static final String SELECT_USER_BY_LOGIN = "SELECT * FROM user WHERE login=?;";
        public static final String SELECT_ALL_MASTERS = "SELECT first_name, last_name, login FROM user WHERE role_id=2 ORDER BY password;";
        public static final String SELECT_MASTERS_BY_LOGIN = "SELECT first_name, last_name FROM user WHERE role_id=2 AND login=?;";

        public static final String SELECT_ALL_USERS = "SELECT * FROM user WHERE role_id = 2 OR role_id = 3;";
        public static final String UPDATE_USER_BY_LOGIN = "UPDATE user SET email=?, password=?, first_name=?, last_name=?, phone_number=? WHERE login=?;";
        public static final String UPDATE_INCREMENT_ACCOUNT = "UPDATE user SET account = account + ? WHERE login=?;";
        public static final String UPDATE_DECREMENT_ACCOUNT = "UPDATE user SET account = account - ? WHERE login=?;";
        public static final String DELETE_USER = "DELETE FROM user WHERE login=?;";
    }

    public static class RequestQuery {
        public static final String ADD_REQUEST = "INSERT INTO request(description, date, master, total_cost, user_login, compilation_status_id, payment_status_id) \n" +
                "VALUES (?, NOW(), 'Not assigned', ?, ?, 1, 1);";
        public static final String SELECT_REQUEST_BY_FILTER = "SELECT * FROM request";
        public static final String SELECT_ALL_REQUESTS = "SELECT * FROM request;";
        public static final String SELECT_ALL_REQUESTS_BY_USER_LOGIN = "SELECT * FROM request WHERE user_login=?;";
        public static final String SELECT_ALL_REQUESTS_FOR_MASTER = "SELECT * FROM request WHERE master=? OR master='Not assigned';";
        public static final String SELECT_ALL_REQUESTS_BY_COLUMN_CLAUSE = "SELECT * FROM request ORDER BY ";
        public static final String COUNT_ALL_REQUESTS = "SELECT COUNT(id) FROM request;";
        public static final String COUNT_ALL_REQUESTS_BY_LOGIN = "SELECT COUNT(id) FROM request ";
        public static final String UPDATE_COMPILATION_STATUS = "UPDATE request SET compilation_status_id=? WHERE id=?;";
        public static final String UPDATE_PAYMENT_STATUS = "UPDATE request SET payment_status_id=? WHERE id=?;";
        public static final String UPDATE_PAYMENT_STATUS_TO_PAID = "UPDATE request SET payment_status_id=2 WHERE id=?;";
        public static final String UPDATE_PAYMENT_STATUS_TO_CANCELED = "UPDATE request SET payment_status_id=3 WHERE id=?;";
        public static final String UPDATE_DESCRIPTION = "UPDATE request SET description=? WHERE id=?;";
        public static final String UPDATE_MASTER = "UPDATE request SET master=? WHERE id=?;";
        public static final String UPDATE_TOTAL_COST = "UPDATE request SET total_cost=? WHERE id=?;";
    }

    public static class FeedbackQuery {
        public static final String SELECT_ALL_FEEDBACKS = "SELECT * FROM feedback;";
        public static final String INSERT_FEEDBACK_TO_ORDER = "INSERT INTO feedback(master_name, datatime, feedback, rating, request_id) \n" +
                "VALUES (?, NOW(), ?, 5, ?);";
    }

    public static class CompilationStatusQuery {
        public static final String SELECT_ALL_COMPILATION_STATUS = "SELECT id, compilation_status FROM compilation_status;";
    }

    public static class PaymentStatusQuery {
        public static final String SELECT_ALL_PAYMENT_STATUS = "SELECT * FROM payment_status;";
    }

    public static class RoleQuery {
        public static final String SELECT_ALL_ROLE = "SELECT id, role FROM role";
    }

}
