package com.inditex.hiring.infrastructure.service;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;
import java.util.stream.Collectors;

public class OffsetDateTimeHandler {

    private static java.time.format.DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

    public OffsetDateTimeHandler() {
        //
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    public OffsetDateTime now() {
        return OffsetDateTime.now().truncatedTo(ChronoUnit.SECONDS);
    }

    public OffsetDateTime toOffsetDateTime(String sdate) {
        return OffsetDateTime.parse(sdate).truncatedTo(ChronoUnit.SECONDS);
    }

    public OffsetDateTime toOffsetDateTimeWithoutFormat(String sdate) {
        String formatedDate = Arrays.stream(sdate.split(" ")).collect(Collectors.joining("T"));
        dtf.withZone(ZoneId.of("UTC"));
        return OffsetDateTime.parse(formatedDate).truncatedTo(ChronoUnit.SECONDS);
    }

    public OffsetDateTime from(Date date) {
        return date.toInstant().atOffset(ZoneOffset.UTC);
    }

    public String toStringUTC(OffsetDateTime anyDate) {
        return dtf.format(anyDate.truncatedTo(ChronoUnit.SECONDS));
    }
}
