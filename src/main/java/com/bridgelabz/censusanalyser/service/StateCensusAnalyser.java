package com.bridgelabz.censusanalyser.service;

import com.bridgelabz.censusanalyser.exception.CensusAnalyserException;
import com.bridgelabz.censusanalyser.model.CSVStateCensus;
import com.bridgelabz.censusanalyser.model.CSVStateCensusDao;
import com.google.gson.Gson;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StateCensusAnalyser {

    Map<String, CSVStateCensusDao> censusMap;

    public StateCensusAnalyser() {
        this.censusMap = new HashMap<>();
    }


    public int loadIndianStateCsvData(String csvFilePath) throws CensusAnalyserException {
        censusMap = new CensusLoader().loadCsvData(csvFilePath, CSVStateCensus.class);
        return censusMap.size();
    }

    public int loadUSCensusData(String csvFilePath) throws CensusAnalyserException {
        censusMap = new CensusLoader().loadCsvData(csvFilePath, CSVStateCensus.class);
        return censusMap.size();
    }

    public int loadIndiaStateCodeCsv(String csvFilePath) throws CensusAnalyserException {
        censusMap = new CensusLoader().loadCsvData(csvFilePath, CSVStateCensus.class);
        return censusMap.size();
    }


    public void checkNull() throws CensusAnalyserException {
        if (censusMap == null || censusMap.size() == 0) {
            throw new CensusAnalyserException("NO Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
    }

    public String getStateSortedCensusData() throws CensusAnalyserException {
        this.checkNull();
        Comparator<CSVStateCensusDao> censusComparator = Comparator.comparing(census -> census.State);
        List sortedResult = this.sort(censusComparator);
        return new Gson().toJson(sortedResult);
    }

    public String getStateCodeSortedCensusData() throws CensusAnalyserException {
        this.checkNull();
        Comparator<CSVStateCensusDao> censusComparator = Comparator.comparing(census -> census.StateCode);
        List sortedResult = this.sort(censusComparator);
        return new Gson().toJson(sortedResult);
    }

    public String getStatePopulationSortedCensusData() throws CensusAnalyserException {
        this.checkNull();
        Comparator<CSVStateCensusDao> censusComparator = Comparator.comparing(census -> census.population);
        List sortedResult = this.sort(censusComparator.reversed());
        return new Gson().toJson(sortedResult);
    }

    public String getStatePopulationDensitySortedCensusData() throws CensusAnalyserException {
        this.checkNull();
        Comparator<CSVStateCensusDao> censusComparator = Comparator.comparing(census -> census.densityPerSqKm);
        List sortedResult = this.sort(censusComparator.reversed());
        return new Gson().toJson(sortedResult);
    }

    public String getStateAreaSortedCensusData() throws CensusAnalyserException {
        this.checkNull();
        Comparator<CSVStateCensusDao> censusComparator = Comparator.comparing(census -> census.areaInSqKm);
        List sortedResult = this.sort(censusComparator.reversed());
        return new Gson().toJson(sortedResult);
    }

    public String getUSStateSortedCensusData() throws CensusAnalyserException {
        this.checkNull();
        Comparator<CSVStateCensusDao> censusComparator = Comparator.comparing(census -> census.State);
        List sortedResult = this.sort(censusComparator);
        return new Gson().toJson(sortedResult);
    }

    public String getUSPopulationSortedCensusDataInDescending() throws CensusAnalyserException {
        this.checkNull();
        Comparator<CSVStateCensusDao> censusComparator = Comparator.comparing(census -> census.Population);
        List sortedResult = this.sort(censusComparator.reversed());
        return new Gson().toJson(sortedResult);
    }

    public String getUSPopulationDensitySortedCensusDataInDescending() throws CensusAnalyserException {
        this.checkNull();
        Comparator<CSVStateCensusDao> censusComparator = Comparator.comparing(census -> census.Population_Density);
        List sortedResult = this.sort(censusComparator.reversed());
        return new Gson().toJson(sortedResult);
    }

    public String getUSAreaSortedCensusDataInDescending() throws CensusAnalyserException {
        this.checkNull();
        Comparator<CSVStateCensusDao> censusComparator = Comparator.comparing(census -> census.area);
        List sortedResult = this.sort(censusComparator.reversed());
        return new Gson().toJson(sortedResult);
    }

    private List sort(Comparator<CSVStateCensusDao> censusComparator) {
        List sortedResult = censusMap.values().stream().sorted(censusComparator).collect(Collectors.toList());
        return sortedResult;
    }
}



