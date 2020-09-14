package com.bridgelabz.censusanalyser.service;

import com.bridgelabz.censusanalyser.exception.CensusAnalyserException;
import com.bridgelabz.censusanalyser.model.CSVStateCensusDao;
import com.bridgelabz.censusanalyser.utility.EnumSorting;
import com.google.gson.Gson;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StateCensusAnalyser {

    public enum Country {
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
        censusMap = new IndianCensusAdaptor().loadCsvData(country, csvFilePath);
        return censusMap.size();
    }

    /**
     *  sorting census data
     *
     * @param
     * @return
     * @throws CensusAnalyserException
     */

    public String getStateSortedCensusData(EnumSorting enumSorting) throws CensusAnalyserException {
        if (censusMap == null || censusMap.size() == 0) {
            throw new CensusAnalyserException("NO Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        ComparatorSort comparatorSort = new ComparatorSort();
        Comparator<CSVStateCensusDao> censusComparator = comparatorSort.sortData(enumSorting);
        List sortedResult = censusMap.values().stream().sorted(censusComparator).collect(Collectors.toList());
        return new Gson().toJson(sortedResult);
    }

}



