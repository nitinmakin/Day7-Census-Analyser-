package com.bridgelabz.censusanalyser.service;

import com.bridgelabz.censusanalyser.exception.CensusAnalyserException;
import com.bridgelabz.censusanalyser.model.CSVStateCensus;
import com.bridgelabz.censusanalyser.model.CSVStateCensusDao;
import com.bridgelabz.censusanalyser.model.CSVStatesCode;
import com.bridgelabz.censusanalyser.model.CsvUSData;
import com.bridgelabz.csvbuilder.service.CsvBuilderFactory;
import com.bridgelabz.csvbuilder.service.ICsvBuilder;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public class CensusLoader {

    public <E> Map loadCsvData(String csvFilePath, Class<E> censusCsvData) throws CensusAnalyserException {
        Map<String, CSVStateCensusDao>  censusMap = new HashMap<>();

        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICsvBuilder csvBuilder = CsvBuilderFactory.createCsvBuilder();
            Iterator<E> csvFileIterator = csvBuilder.getCSVFileIterator(reader, censusCsvData);
            Iterable<E> CensusCsv = () -> csvFileIterator;
            String className = censusCsvData.getSimpleName();
            if (className.equalsIgnoreCase("CSVStateCensus")) {
                StreamSupport.stream(CensusCsv.spliterator(), false)
                        .map(CSVStateCensus.class::cast)
                        .forEach(csvCensus -> censusMap.put(csvCensus.State, new CSVStateCensusDao(csvCensus)));
                return censusMap;
            } else if (className.equalsIgnoreCase("CsvUSData")) {
                StreamSupport.stream(CensusCsv.spliterator(), false)
                        .map(CsvUSData.class::cast)
                        .forEach(csvCensus1 -> censusMap.put(csvCensus1.State, new CSVStateCensusDao(csvCensus1)));
                return censusMap;
            } else if (className.equalsIgnoreCase("CSVStatesCode")) {
                StreamSupport.stream(CensusCsv.spliterator(), false)
                        .map(CSVStatesCode.class::cast)
                        .forEach(csvCensus2 -> censusMap.put(csvCensus2.StateName, new CSVStateCensusDao(csvCensus2)));
                return censusMap;
            }

        } catch (Exception e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
        return null;
    }
}
