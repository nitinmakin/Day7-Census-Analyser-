package com.bridgelabz.censusanalyser.model;

import com.opencsv.bean.CsvBindByName;

public class CSVStateCensus {
    @CsvBindByName(column = "State", required = true)
    public String State;

    @CsvBindByName(column = "Population", required = true)
    public long population;

    @CsvBindByName(column = "AreaInSqKm", required = true)
    public long areaInSqKm;

    @CsvBindByName(column = "DensityPerSqKm", required = true)
    public long densityPerSqKm;

    @Override
    public String toString() {
        return "IndiaCensusCSV{" +
                "State='" + State + '\'' +
                ", Population='" + population + '\'' +
                ", AreaInSqKm='" + areaInSqKm + '\'' +
                ", DensityPerSqKm='" + densityPerSqKm + '\'' +
                '}';
    }
}

