package com.bridgelabz.censusanalyser.model;

import com.opencsv.bean.CsvBindByName;

public class CsvUSData {
    @CsvBindByName(column = "USState", required = true)
    public String USState;

    @CsvBindByName(column = "USPopulation", required = true)
    public int USPopulation;

    @CsvBindByName(column = "USAreaInSqKm", required = true)
    public int USAreaInSqKm;


    @Override
    public String toString() {
        return "CsvUSData{" +
                "USState='" + USState + '\'' +
                ", USPopulation=" + USPopulation +
                ", USAreaInSqKm=" + USAreaInSqKm +
                '}';
    }
}
