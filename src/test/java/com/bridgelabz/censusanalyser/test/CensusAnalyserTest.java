package com.bridgelabz.censusanalyser.test;

import com.bridgelabz.censusanalyser.exception.CensusAnalyserException;
import com.bridgelabz.censusanalyser.service.StateCensusAnalyser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CensusAnalyserTest {
    @Test
    public void givenIndianCensusCSVFileReturnsCorrectRecords() {
        try {
            final String INDIA_CENSUS_CSV_FILE_PATH = "D:\\fellowship\\Day8-Census-Analyser\\" +
                    "src\\test\\resources\\IndiaStateCensusData.csv";
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            int numOfRecords = censusAnalyser.loadCsvData(INDIA_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(29, numOfRecords);
        } catch (CensusAnalyserException e) {
           e.printStackTrace();
        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongFile_ShouldThrowException() {
        try {
            final String WRONG_CSV_FILE_PATH = "D:\\fellowship\\Day8-Census-Analyser\\" +
                    "src\\test\\resources";
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadCsvData(WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongFileType_ShouldThrowException() {
        try {
            final String WRONG_CSV_FILE_TYPE = "D:\\fellowship\\Day8-Census-Analyser\\" +
                    "src\\test\\resources\\IndiaStateCensusData.txt";
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadCsvData(WRONG_CSV_FILE_TYPE);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongDelimiter_ShouldThrowException() {
        try {
            final String WRONG_CSV_DELIMITER_TYPE = "D:\\fellowship\\Day8-Census-Analyser\\" +
                    "src\\test\\resources\\IncorrectDelimiterWithCorrectCsvFile.csv";
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadCsvData(WRONG_CSV_DELIMITER_TYPE);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongHeader_ShouldThrowException() {
        try {
            final String WRONG_CSV_HEADER = "D:\\fellowship\\Day8-Census-Analyser\\" +
                    "src\\test\\resources\\CorrectCsvFileWithWrongCsvHeader.csv";
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadCsvData(WRONG_CSV_HEADER);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndianStateCodeCensusCSVFileShouldReturnsCorrectRecords() {
        try {
            final String INDIA_CENSUS_CSV_FILE_PATH = "D:\\fellowship\\" +
                    "Day7-Census-Analyser-\\src\\test\\resources\\IndiaStatesCodes.csv";
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaStateCodeCsv(INDIA_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(37, numOfRecords);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenIndiaStateCodeCensusData_WithWrongFile_ShouldThrowException() {
        try {
            final String WRONG_CSV_FILE_PATH = "D:\\fellowship\\Day8-Census-Analyser\\" +
                    "src\\test\\resources";
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaStateCodeCsv(WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaStateCodeCensusData_WithWrongFileType_ShouldThrowException() {
        try {
            final String WRONG_CSV_FILE_TYPE = "D:\\fellowship\\Day8-Census-Analyser\\" +
                    "src\\test\\resources\\StateCodeFileWithWrongType.txt";
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaStateCodeCsv(WRONG_CSV_FILE_TYPE);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaStateCodeCensusData_WithWrongDelimiter_ShouldThrowException() {
        try {
            final String WRONG_CSV_DELIMITER_TYPE = "D:\\fellowship\\Day8-Census-Analyser\\" +
                    "src\\test\\resources\\IncorrectDelimiterOfStateCodeFile.csv";
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaStateCodeCsv(WRONG_CSV_DELIMITER_TYPE);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaStateCodeCensusData_WithWrongHeader_ShouldThrowException() {
        try {
            final String WRONG_CSV_HEADER = "D:\\fellowship\\Day8-Census-Analyser\\" +
                    "src\\test\\resources\\StateCodeWithWrongHeader.csv";
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaStateCodeCsv(WRONG_CSV_HEADER);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }
}

