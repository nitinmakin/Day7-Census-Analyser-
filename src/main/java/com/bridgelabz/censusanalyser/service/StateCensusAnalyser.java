package com.bridgelabz.censusanalyser.service;

import com.bridgelabz.censusanalyser.exception.CensusAnalyserException;
import com.bridgelabz.censusanalyser.model.CSVStateCensus;
import com.bridgelabz.censusanalyser.model.CSVStatesCode;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

public class StateCensusAnalyser {
    public int loadCsvData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            CsvToBeanBuilder<CSVStateCensus> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(CSVStateCensus.class);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<CSVStateCensus> csvToBean = csvToBeanBuilder.build();
            Iterator<CSVStateCensus> censusCSVIterator = csvToBean.iterator();

            int numberOfEntries = 0;
            while (censusCSVIterator.hasNext()) {
                numberOfEntries++;
                censusCSVIterator.next();
            }
            return numberOfEntries;
        } catch (Exception e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
    }

<<<<<<< HEAD
    public int loadIndiaStateCode(String csvFilePath) throws CensusAnalyserException {
=======
    public int loadIndiaStateCodeCsv(String csvFilePath) throws CensusAnalyserException {
>>>>>>> UC2_Load_indian_states_code_info
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            CsvToBeanBuilder<CSVStatesCode> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(CSVStatesCode.class);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<CSVStatesCode> csvToBean = csvToBeanBuilder.build();
            Iterator<CSVStatesCode> censusCSVIterator = csvToBean.iterator();

            int numberOfEntries = 0;
            while (censusCSVIterator.hasNext()) {
                numberOfEntries++;
                censusCSVIterator.next();
            }
            return numberOfEntries;
        } catch (Exception e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
    }
}

