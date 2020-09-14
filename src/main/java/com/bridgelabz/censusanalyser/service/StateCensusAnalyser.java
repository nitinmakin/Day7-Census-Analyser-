package com.bridgelabz.censusanalyser.service;

import com.bridgelabz.censusanalyser.exception.CensusAnalyserException;
import com.bridgelabz.censusanalyser.model.CSVStateCensus;
import com.bridgelabz.censusanalyser.model.CSVStateCensusDao;
import com.bridgelabz.censusanalyser.model.CSVStatesCode;
import com.bridgelabz.censusanalyser.model.CsvUSData;
import com.bridgelabz.censusanalyser.utility.EnumSorting;
import com.google.gson.Gson;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StateCensusAnalyser {

    private Object Country;

    public enum Country{
        INDIA, US, INDIAN_STATE_CODE
    }

    Map<String, CSVStateCensusDao> censusMap;

    public StateCensusAnalyser() {
        this.censusMap = new HashMap<>();
    }

    /**
     * to load all state census data
     *
     * @param csvFilePath
     * @return
     * @throws CensusAnalyserException
     */
    public int loadStateCsvData(Country country, String csvFilePath) throws CensusAnalyserException {
        censusMap = new CensusLoader().loadCsvData(country, csvFilePath);
        return censusMap.size();
    }


    /**
     * for sorting census data in ascending order
     *
     * @param sortVariable
     * @return
     * @throws CensusAnalyserException
     */

    public String getStateSortedCensusData(EnumSorting sortVariable) throws CensusAnalyserException {
        if (censusMap == null || censusMap.size() == 0) {
            throw new CensusAnalyserException("NO Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CSVStateCensusDao> censusComparator = sortVariable.sortData();
        List sortedResult = this.sort(censusComparator);
        return new Gson().toJson(sortedResult);
    }

    /**
     * for sorting census data in descending order
     *
     * @param sortVariable
     * @return
     * @throws CensusAnalyserException
     */

    public String getStateSortedCensusDataInDescending(EnumSorting sortVariable) throws CensusAnalyserException {
        if (censusMap == null || censusMap.size() == 0) {
            throw new CensusAnalyserException("NO Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CSVStateCensusDao> censusComparator = sortVariable.sortData();
        List sortedResult = this.sort(censusComparator.reversed());
        return new Gson().toJson(sortedResult);
    }

    /**
     * sorting method
     *
     * @param censusComparator
     * @return
     */
    private List sort(Comparator<CSVStateCensusDao> censusComparator) {
        List sortedResult = censusMap.values().stream().sorted(censusComparator).collect(Collectors.toList());
        return sortedResult;
    }
}



