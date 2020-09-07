package com.bridgelabz.censusanalyser.service;

import com.bridgelabz.censusanalyser.utility.OpenCsvBuilder;

public class CsvBuilderFactory {

    public static ICsvBuilder createCsvBuilder() {
        return new OpenCsvBuilder();
    }
}
