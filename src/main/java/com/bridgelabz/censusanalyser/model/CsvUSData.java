package com.bridgelabz.censusanalyser.model;

import com.opencsv.bean.CsvBindByName;

public class CsvUSData {
    @CsvBindByName(column = "State", required = true)
    public String State;

    @CsvBindByName(column = "Population", required = true)
    public long Population;


    @CsvBindByName(column = "Land area", required = true)
    public double area;

    @CsvBindByName(column = "Population Density", required = true)
    public double Population_Density;

    @Override
    public String toString() {
        return "CsvUSData{" +
                "State='" + State + '\'' +
                ", Population='" + Population + '\'' +
                ", area='" + area + '\'' +
                ", Population_Density='" + Population_Density + '\'' +
                '}';
    }
}


