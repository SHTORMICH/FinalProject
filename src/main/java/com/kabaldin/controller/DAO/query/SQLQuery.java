package com.kabaldin.controller.DAO.query;

public abstract class SQLQuery {

    public static class UserQuery {
        public static final String INSERT_USER = "INSERT INTO user(login, email, password, first_name, last_name, phone_number, account, role_id) VALUES (?, ?, ?, ?, ?, ?, 0, 3);";
        public static final String SELECT_USER_BY_LOGIN = "SELECT * FROM user WHERE login=?;";
        public static final String SELECT_USER_BY_LOGIN_AND_PASSWORD = "SELECT * FROM user WHERE login=?, password=?;";
        public static final String SELECT_ONE_USER_BY_LOGIN_AND_PASSWORD = "SELECT COUNT(1) FROM user WHERE login=?, password=?;";

        public static final String SELECT_USER_BY_EMAIL = "SELECT * FROM user WHERE email=?;";
        public static final String SELECT_USER_BY_ROLE = "SELECT * FROM user WHERE role=?;";
        public static final String SELECT_ALL_USERS = "SELECT * FROM user;";
        public static final String UPDATE_USER_BY_LOGIN = "UPDATE user SET email=?, password=?, first_name=?, last_name=?, phone_number=? WHERE login=?;";
        public static final String UPDATE_INCREMENT_ACCOUNT = "UPDATE user SET account = account + ? WHERE login=?;";
        public static final String UPDATE_DECREMENT_ACCOUNT = "UPDATE user SET account = account - ? WHERE login=?;";
        public static final String DELETE_USER = "DELETE FROM user WHERE login=?;";
    }

    public static class RequestQuery {
        public static final String ADD_REQUEST = "INSERT INTO request(description, date, master, total_cost, user_login, compilation_status_id, payment_status_id) \n" +
                "VALUES (?, NOW(), 'no_master', ?, ?, 1, 1);";
        public static final String SELECT_ALL_REQUESTS = "SELECT * FROM request;";
        public static final String SELECT_ALL_REQUESTS_BY_USER_LOGIN = "SELECT * FROM request WHERE user_login=?;";
        public static final String UPDATE_COMPILATION_STATUS = "UPDATE request SET compilation_status_id = 2;";
        public static final String UPDATE_PAYMENT_STATUS_TO_PAID = "UPDATE request SET payment_status_id = 2;";
        public static final String UPDATE_PAYMENT_STATUS_TO_CANCELED = "UPDATE request SET payment_status_id = 3;";
        public static final String UPDATE_DESCRIPTION = "UPDATE request SET description=?;";
        public static final String UPDATE_MASTER = "UPDATE request SET master=? WHERE id=?;";
        public static final String UPDATE_TOTAL_COST = "UPDATE request SET total_cost=? WHERE id=?;";
        public static final String DELETE_REQUEST = "DELETE FROM request WHERE id=?";
    }

    static class FeedbackQuery {
        public static final String SELECT_ALL_FEEDBACKS = "SELECT * FROM feedback;";
        public static final String INSERT_FEEDBACK_TO_ORDER = "INSERT INTO feedback(master_name, feedback, rating, request_id) \n" +
                "VALUES (?, NOW(), ?, ?, ?);";
    }

    static class CompilationStatusQuery {
        public final String SELECT_ALL_COMPILATION_STATUS = "SELECT * FROM compilation_status;";
        //private final String INSERT_COMPILATION_STATUS = "INSERT INTO compilation_status(id, compilation_status) VALUES (default, ?);";
    }

    static class PaymentStatusQuery {
        public final String SELECT_ALL_PAYMENT_STATUS = "SELECT * FROM payment_status;";
        //private final String INSERT_PAYMENT_STATUS = "INSERT INTO payment_status(id, payment_status) VALUES (default, ?);";
    }

    static class RoleQuery {
        public final String SELECT_ALL_ROLE = "SELECT * FROM role";
        //private final String INSERT_ROLE = "INSERT INTO role (id, role) VALUES (default, ?);";
    }

}

/*INSERT INTO user(login, email, password, first_name, last_name, phone_number, account, role_id)
    VALUES ('sashka', 'shaska@gmail.com', 12345, 'Sasha', 'Kabaldin', 380930319078, 0, 1);
    INSERT INTO user(login, email, password, first_name, last_name, phone_number, account, role_id)
    VALUES ('dima', 'dima@gmail.com', 12345, 'Dima', 'Kabaldin', 3809999999, 0, 1);
    INSERT INTO user(login, email, password, first_name, last_name, phone_number, account, role_id)
    VALUES ('masha', 'masha@gmail.com', 12345, 'Masha', 'Kabaldin', 380988888888, 0, 1);
    DELETE FROM user WHERE login='dima';
    UPDATE user SET account = account + '100' WHERE login = 'sashka';
    SELECT * FROM user;*/
