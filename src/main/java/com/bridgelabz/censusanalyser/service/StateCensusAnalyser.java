package com.bridgelabz.censusanalyser.service;

import com.bridgelabz.censusanalyser.exception.CensusAnalyserException;
import com.bridgelabz.censusanalyser.model.CSVStateCensus;
import com.bridgelabz.censusanalyser.model.CSVStateCensusDao;
import com.bridgelabz.censusanalyser.model.CSVStatesCode;
import com.bridgelabz.censusanalyser.model.CsvUSData;
import com.bridgelabz.csvbuilder.exception.CsvBuilderException;
import com.bridgelabz.csvbuilder.service.CsvBuilderFactory;
import com.bridgelabz.csvbuilder.service.ICsvBuilder;
import com.google.gson.Gson;

import java.io.IOException;
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

    public int loadCsvData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICsvBuilder csvBuilder = CsvBuilderFactory.createCsvBuilder();
            Iterator<CSVStateCensus> csvFileIterator = csvBuilder.getCSVFileIterator(reader, CSVStateCensus.class);
            Iterable<CSVStateCensus> indianCensusCsv = () -> csvFileIterator;
            StreamSupport.stream(indianCensusCsv.spliterator(), false)
                    .forEach(csvCensus -> this.censusMap.put(csvCensus.State, new CSVStateCensusDao(csvCensus)));

            return censusMap.size();
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
                    .forEach(csvCensus -> this.censusMap.put(csvCensus.StateName, new CSVStateCensusDao(csvCensus)));
            return censusMap.size();
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (CsvBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(), e.type.name());
        }
    }

    public int loadUSCensusData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICsvBuilder csvBuilder = CsvBuilderFactory.createCsvBuilder();
            Iterator<CsvUSData> censusUsCSVIterator = csvBuilder.getCSVFileIterator(reader, CsvUSData.class);
            Iterable<CsvUSData> USCensusData = () -> censusUsCSVIterator;
            StreamSupport.stream(USCensusData.spliterator(), false)
                    .forEach(USCensus -> this.censusMap.put(USCensus.State, new CSVStateCensusDao(USCensus)));
            return censusMap.size();
        } catch (Exception e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
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



