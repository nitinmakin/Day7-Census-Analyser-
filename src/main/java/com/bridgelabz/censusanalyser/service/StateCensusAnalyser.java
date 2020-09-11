package com.bridgelabz.censusanalyser.service;

import com.bridgelabz.censusanalyser.exception.CensusAnalyserException;
import com.bridgelabz.censusanalyser.model.CSVStateCensus;
import com.bridgelabz.censusanalyser.model.CSVStateCensusDao;
import com.bridgelabz.censusanalyser.model.CSVStatesCode;
import com.bridgelabz.censusanalyser.model.CsvUSData;
import com.bridgelabz.csvbuilder.service.CsvBuilderFactory;
import com.bridgelabz.csvbuilder.service.ICsvBuilder;
import com.google.gson.Gson;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class StateCensusAnalyser {

    Map<String, CSVStateCensusDao> censusMap;

    public StateCensusAnalyser() {
        this.censusMap = new HashMap<>();
    }


    public int loadIndianStateCsvData(String csvFilePath) throws CensusAnalyserException {
        censusMap =  new CensusLoader().loadCsvData(csvFilePath, CSVStateCensus.class);
        return censusMap.size();
    }

    public int loadUSCensusData(String csvFilePath) throws CensusAnalyserException {
        censusMap =  new CensusLoader().loadCsvData(csvFilePath, CSVStateCensus.class);
        return censusMap.size();
    }
    public int loadIndiaStateCodeCsv(String csvFilePath) throws CensusAnalyserException {
        censusMap =  new CensusLoader().loadCsvData(csvFilePath, CSVStateCensus.class);
        return censusMap.size();
    }


    public String getStateSortedCensusData() throws CensusAnalyserException {
        if (censusMap == null || censusMap.size() == 0) {
            throw new CensusAnalyserException("NO Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CSVStateCensusDao> censusComparator = Comparator.comparing(census -> census.State);
        List sortedResult = this.sort(censusComparator);
        return new Gson().toJson(sortedResult);
    }

    public String getStateCodeSortedCensusData() throws CensusAnalyserException {
        if (censusMap == null || censusMap.size() == 0) {
            throw new CensusAnalyserException("NO Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CSVStateCensusDao> censusComparator = Comparator.comparing(census -> census.StateCode);
        List sortedResult = this.sort(censusComparator);
        return new Gson().toJson(sortedResult);
    }

    public String getStatePopulationSortedCensusData() throws CensusAnalyserException {
        if (censusMap == null || censusMap.size() == 0) {
            throw new CensusAnalyserException("NO Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CSVStateCensusDao> censusComparator = Comparator.comparing(census -> census.population);
        List sortedResult = this.sort(censusComparator.reversed());
        return new Gson().toJson(sortedResult);
    }

    public String getStatePopulationDensitySortedCensusData() throws CensusAnalyserException {
        if (censusMap == null || censusMap.size() == 0) {
            throw new CensusAnalyserException("NO Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CSVStateCensusDao> censusComparator = Comparator.comparing(census -> census.densityPerSqKm);
        List sortedResult = this.sort(censusComparator.reversed());
        return new Gson().toJson(sortedResult);
    }

    public String getStateAreaSortedCensusData() throws CensusAnalyserException {
        if (censusMap == null || censusMap.size() == 0) {
            throw new CensusAnalyserException("NO Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CSVStateCensusDao> censusComparator = Comparator.comparing(census -> census.areaInSqKm);
        List sortedResult = this.sort(censusComparator.reversed());
        return new Gson().toJson(sortedResult);
    }

    public String getUSStateSortedCensusData() throws CensusAnalyserException {
        if (censusMap == null || censusMap.size() == 0) {
            throw new CensusAnalyserException("NO Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CSVStateCensusDao> censusComparator = Comparator.comparing(census -> census.State);
        List sortedResult = this.sort(censusComparator);
        return new Gson().toJson(sortedResult);
    }

    public String getUSPopulationSortedCensusDataInDescending() throws CensusAnalyserException {
        if (censusMap == null || censusMap.size() == 0) {
            throw new CensusAnalyserException("NO Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CSVStateCensusDao> censusComparator = Comparator.comparing(census -> census.Population);
        List sortedResult = this.sort(censusComparator.reversed());
        return new Gson().toJson(sortedResult);
    }

    public String getUSPopulationDensitySortedCensusDataInDescending() throws CensusAnalyserException {
        if (censusMap == null || censusMap.size() == 0) {
            throw new CensusAnalyserException("NO Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CSVStateCensusDao> censusComparator = Comparator.comparing(census -> census.Population_Density);
        List sortedResult = this.sort(censusComparator.reversed());
        return new Gson().toJson(sortedResult);
    }

    public String getUSAreaSortedCensusDataInDescending() throws CensusAnalyserException {
        if (censusMap == null || censusMap.size() == 0) {
            throw new CensusAnalyserException("NO Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CSVStateCensusDao> censusComparator = Comparator.comparing(census -> census.area);
        List sortedResult = this.sort(censusComparator.reversed());
        return new Gson().toJson(sortedResult);
    }

    private List sort(Comparator<CSVStateCensusDao> censusComparator) {
        List sortedResult = censusMap.values().stream().sorted(censusComparator).collect(Collectors.toList());
        return sortedResult;
    }
}



