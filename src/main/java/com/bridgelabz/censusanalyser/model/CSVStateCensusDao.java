package com.bridgelabz.censusanalyser.model;

public class CSVStateCensusDao {
    public String state;
    public int areaInSqKm;
    public int densityPerSqKm;
    public int population;
    public String StateName;
    public String StateCode;
    public String SrNo;
    public String TIN;
    public String USState;
    public int USAreaInSqKm;
    public int USPopulation;


    public CSVStateCensusDao(CSVStateCensus csvStateCensus) {
        state = csvStateCensus.state;
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
      USState = csvUSData.USState;
      USAreaInSqKm = csvUSData.USAreaInSqKm;
      USPopulation = csvUSData.USPopulation;

    }

}
