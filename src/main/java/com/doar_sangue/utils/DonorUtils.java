package com.doar_sangue.utils;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class DonorUtils {

    public static LocalDate convertStringToDate(String dataString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            return LocalDate.parse(dataString, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Data inválida: " + dataString);
        }
    }

    public static String convertDateToString(LocalDate data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return data.format(formatter);
    }

    public static int calcAge(LocalDate dataNasc) {
        return Period.between(dataNasc, LocalDate.now()).getYears();
    }

    public static final Map<String, List<String>> COMPATIBILIDADE = new HashMap<>();

    static {
        COMPATIBILIDADE.put("A+", Arrays.asList("A+", "A-", "O+", "O-"));
        COMPATIBILIDADE.put("A-", Arrays.asList("A-", "O-"));
        COMPATIBILIDADE.put("B+", Arrays.asList("B+", "B-", "O+", "O-"));
        COMPATIBILIDADE.put("B-", Arrays.asList("B-", "O-"));
        COMPATIBILIDADE.put("AB+", Arrays.asList("A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-"));
        COMPATIBILIDADE.put("AB-", Arrays.asList("A-", "B-", "O-", "AB-"));
        COMPATIBILIDADE.put("O+", Arrays.asList("O+", "O-"));
        COMPATIBILIDADE.put("O-", Arrays.asList("O-"));
    }
}
