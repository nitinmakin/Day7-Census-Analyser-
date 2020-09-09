package com.bridgelabz.censusanalyser.service;

import com.bridgelabz.censusanalyser.exception.CensusAnalyserException;
import com.bridgelabz.censusanalyser.model.CSVStateCensus;
import com.bridgelabz.censusanalyser.model.CSVStateCensusDao;
import com.bridgelabz.censusanalyser.model.CSVStatesCode;
import com.bridgelabz.csvbuilder.exception.CsvBuilderException;
import com.bridgelabz.csvbuilder.service.CsvBuilderFactory;
import com.bridgelabz.csvbuilder.service.ICsvBuilder;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class StateCensusAnalyser {

    List<CSVStateCensusDao> censusList;
    List<CSVStateCensusDao> stateCodeList;
    Map<String, CSVStateCensusDao> stateCensusMap;
    Map<String, CSVStateCensusDao> stateCodeMap;


    public StateCensusAnalyser() {
        this.censusList = new ArrayList();
        this.stateCodeList = new ArrayList<>();
        this.stateCensusMap = new HashMap<>();
        this.stateCodeMap = new HashMap<>();
    }

    public int loadCsvData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICsvBuilder csvBuilder = CsvBuilderFactory.createCsvBuilder();
            Iterator<CSVStateCensus> csvFileIterator = csvBuilder.getCSVFileIterator(reader, CSVStateCensus.class);
            Iterable<CSVStateCensus> indianCensusCsv = () -> csvFileIterator;
            StreamSupport.stream(indianCensusCsv.spliterator(), false)
                    .forEach(csvCensus -> this.stateCensusMap.put(csvCensus.state, new CSVStateCensusDao(csvCensus)));

            return stateCensusMap.size();
        } catch (Exception e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
    }

    public int loadIndiaStateCodeCsv(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICsvBuilder csvBuilder = CsvBuilderFactory.createCsvBuilder();
            Iterator<CSVStatesCode> censusCSVIterator = csvBuilder.getCSVFileIterator(reader, CSVStatesCode.class);
            Iterable<CSVStatesCode> IndianStatesCodesCsv = () -> censusCSVIterator;
            StreamSupport.stream(IndianStatesCodesCsv.spliterator(), false)
                    .forEach(csvCensus -> this.stateCodeMap.put(csvCensus.StateName, new CSVStateCensusDao(csvCensus)));
            return stateCodeMap.size();
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (CsvBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(), e.type.name());
        }
    }

    public String getStateSortedCensusData() throws CensusAnalyserException {
        if (stateCensusMap == null || stateCensusMap.size() == 0) {
            throw new CensusAnalyserException("NO Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CSVStateCensusDao> censusComparator = Comparator.comparing(census -> census.state);
        List sortedResult = this.sort(censusComparator);
        return new Gson().toJson(sortedResult);

    }

    public String getStateCodeSortedCensusData() throws CensusAnalyserException {
        if (stateCodeMap == null || stateCodeMap.size() == 0) {
            throw new CensusAnalyserException("NO Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CSVStateCensusDao> censusComparator = Comparator.comparing(census -> census.StateCode);
        List sortedResult = this.sort(censusComparator);
        return new Gson().toJson(sortedResult);
    }

    public String getStatePopulationSortedCensusData() throws CensusAnalyserException {
        if (stateCensusMap == null || stateCensusMap.size() == 0) {
            throw new CensusAnalyserException("NO Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CSVStateCensusDao> censusComparator = Comparator.comparing(census -> census.population);
        List sortedResult = this.sort(censusComparator.reversed());
        return new Gson().toJson(sortedResult);
    }

    public String getStatePopulationDensitySortedCensusData() throws CensusAnalyserException {
        if (stateCensusMap == null || stateCensusMap.size() == 0) {
            throw new CensusAnalyserException("NO Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CSVStateCensusDao> censusComparator = Comparator.comparing(census -> census.densityPerSqKm);
        List sortedResult = this.sort(censusComparator.reversed());
        return new Gson().toJson(sortedResult);
    }

    public String getStateAreaSortedCensusData() throws CensusAnalyserException {
        if (stateCensusMap == null || stateCensusMap.size() == 0) {
            throw new CensusAnalyserException("NO Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CSVStateCensusDao> censusComparator = Comparator.comparing(census -> census.areaInSqKm);
        List sortedResult = this.sort(censusComparator.reversed());
        return new Gson().toJson(sortedResult);
    }


    private List sort(Comparator<CSVStateCensusDao> censusComparator) {
        List sortedResult = stateCensusMap.values().stream().sorted(censusComparator).collect(Collectors.toList());
        return sortedResult;
    }
}



