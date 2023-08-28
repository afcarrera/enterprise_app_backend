package com.sicpa.enterprise_control.util;

public class Messages {
    public enum Errors {
        INVALID_EMAIL("Invalid email."),
        INVALID_NAME("Invalid name."),
        INVALID_SURNAME("Invalid surname."),
        INVALID_PHONE("Invalid phone."),
        INVALID_POSITION("Invalid position."),
        INVALID_ENTERPRISE("Invalid enterprise."),
        INVALID_AGE("Invalid age."),
        OBJECT_IS_UPDATED("There is another update process on the object, please try again later.")
        ;

        private final String text;

        /**
         * @param text name of error
         */
        Errors(final String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }
    }

    public enum NotFound {
        NOT_FOUND_EMPLOYEE("Employee not found."),
        NOT_FOUND_DEPARTMENT("Department not found."),
        NOT_FOUND_ENTERPRISE("Enterprise not found.")
        ;

        private final String text;

        /**
         * @param text name of not found message
         */
        NotFound(final String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }
    }
    public enum Required {
        EMPLOYEE_NAME("Employee name is required."),
        EMPLOYEE_SURNAME("Employee surname is required."),
        EMPLOYEE_AGE("Employee age is required."),
        EMPLOYEE_EMAIL("Employee email is required."),
        EMPLOYEE_POSITION("Employee position is required."),
        ENTERPRISE_NAME("Enterprise name is required."),
        ENTERPRISE_PHONE("Enterprise phone is required."),
        ENTERPRISE_ADDRESS("Enterprise address is required."),
        DEPARTMENT_NAME("Department name is required."),
        DEPARTMENT_DESCRIPTION("Department description is required."),
        DEPARTMENT_PHONE("Department phone is required."),
        DEPARTMENT_ENTERPRISE("Department idEnterprise is required."),
        ;

        private final String text;

        /**
         * @param text name of required message
         */
        Required(final String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }
    }
}
