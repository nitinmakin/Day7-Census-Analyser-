package com.bridgelabz.censusanalyser.model;

public class CSVStateCensusDao {
    public String State;
    public long areaInSqKm;
    public long densityPerSqKm;
    public long population;
    public String StateName;
    public String StateCode;
    public long SrNo;
    public long TIN;
    public double area;
    public double populationDensity;
    public long Population;

    public CSVStateCensusDao(CSVStateCensus csvStateCensus) {
        State = csvStateCensus.State;
        areaInSqKm = csvStateCensus.areaInSqKm;
        densityPerSqKm = csvStateCensus.densityPerSqKm;
        population = csvStateCensus.population;

    }

    public CSVStateCensusDao(CSVStatesCode csvStatesCode) {
        StateName = csvStatesCode.StateName;
        StateCode = csvStatesCode.StateCode;
        SrNo = csvStatesCode.SrNo;
        TIN = csvStatesCode.TIN;
    }
    public CSVStateCensusDao(CsvUSData csvUSData) {
      State = csvUSData.State;
      Population = csvUSData.Population;
      area = csvUSData.area;
      populationDensity = csvUSData.Population_Density;

    }

}
