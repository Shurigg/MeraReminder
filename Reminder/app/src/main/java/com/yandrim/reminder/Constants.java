package com.yandrim.reminder;

public class Constants {
    public static final int TAB_ONE = 0;
    public static final int TAB_TWO = 1;
    public static final int TAB_THREE = 2;
    public static final int TAB_FOUR = 3;

    public static class URL{
        private static final String HOST = "http://192.168.0.60:8080/";
        public static final String GET_BIRTHDAYS = HOST + "birthdays";
        public  static final String GET_REMINDERS = HOST + "reminders";
        public static final String GET_MEETINGS = HOST + "meetings";
    }
}
