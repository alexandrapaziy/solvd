package com.solvd.bank.adapter;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateAdapter extends XmlAdapter<String, LocalDateTime> {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    @Override
    public LocalDateTime unmarshal(String s) throws Exception {
        if (s == null || s.isEmpty()) {
            return null;
        }
        return LocalDateTime.parse(s, formatter);
    }

    @Override
    public String marshal(LocalDateTime localDateTime) throws Exception {
        if (localDateTime == null) {
            return null;
        }
        return localDateTime.format(formatter);
    }
}