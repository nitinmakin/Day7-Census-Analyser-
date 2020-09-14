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

public class IndianCensusAdaptor extends CsvLoader {
    @Override
    public Map<String, CSVStateCensusDao> loadCsvData(String csvFilePath) throws CensusAnalyserException {
        return null;
    }
    public Map<String, CSVStateCensusDao> loadCsvData(StateCensusAnalyser.Country country, String csvFilePath) throws CensusAnalyserException {
        if (country.equals(StateCensusAnalyser.Country.INDIA))
            return this.loadCsvData(CSVStateCensus.class, csvFilePath);
        else if (country.equals(StateCensusAnalyser.Country.US))
            return this.loadCsvData(CsvUSData.class, csvFilePath);
        else if (country.equals(StateCensusAnalyser.Country.INDIAN_STATE_CODE))
            return this.loadCsvData(CSVStatesCode.class, csvFilePath);
        else
            throw new CensusAnalyserException("incorrect country", CensusAnalyserException.ExceptionType.INVALID_COUNTRY);

    }

    private <E> Map loadCsvData(Class<E> censusCsvData, String... csvFilePath) throws CensusAnalyserException {
        Map<String, CSVStateCensusDao> censusMap = new HashMap<>();

        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath[0]))) {
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
