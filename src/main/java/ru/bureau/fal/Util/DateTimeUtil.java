package ru.bureau.fal.Util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateTimeUtil {
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm";
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);

//    @Converter(autoApply = false)
//    public class LocalDateAttributeConverter implements AttributeConverter<LocalDate, Date> {
//
//        @Override
//        public Date convertToDatabaseColumn(LocalDate locDate) {
//            return (locDate == null ? null : Date.from(locDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
//        }
//
//        @Override
//        public LocalDate convertToEntityAttribute(Date sqlDate) {
//            return (sqlDate == null ? null : sqlDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
//        }
//    }
}
