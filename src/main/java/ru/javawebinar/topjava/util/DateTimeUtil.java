package ru.javawebinar.topjava.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Util to filter By Time
     *
     * @param lt
     * @param startTime
     * @param endTime
     * @return
     */
    public static boolean isBetweenHalfOpen(LocalTime lt, LocalTime startTime, LocalTime endTime) {
        return lt.compareTo(startTime) >= 0 && lt.compareTo(endTime) < 0;
    }

    /**
     * Util to filter By Date
     *
     * @param lt
     * @param startDate
     * @param endDate
     * @return
     */
    public static boolean isBetweenHalfOpen(LocalDate lt, LocalDate startDate, LocalDate endDate) {
        return lt.compareTo(startDate) >= 0 && lt.compareTo(endDate) <= 0;
    }

//
//    // TODO
//    public static <T extends Comparable> boolean isBetweenHalfOpen(T lt, T startDate, T endDate) {
//        return lt.compareTo(startDate) >= 0 && lt.compareTo(endDate) <= 0;
//    }






    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }

}

