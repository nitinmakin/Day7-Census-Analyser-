package com.bridgelabz.censusanalyser.test;

import com.bridgelabz.censusanalyser.exception.CensusAnalyserException;
import com.bridgelabz.censusanalyser.model.CSVStateCensus;
import com.bridgelabz.censusanalyser.model.CSVStatesCode;
import com.bridgelabz.censusanalyser.service.StateCensusAnalyser;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CensusAnalyserTest {
    private final String INDIA_CENSUS_CSV_FILE_PATH = "D:\\fellowship\\Day8-Census-Analyser\\" +
            "src\\test\\resources\\IndiaStateCensusData.csv";
    private final String INDIA_CENSUS_CSV_FILE_PATH_FOR_STATE_CODE = "D:\\fellowship\\" +
            "Day7-Census-Analyser-\\src\\test\\resources\\IndiaStatesCodes.csv";

    @Test
    public void givenIndianCensusCSVFileReturnsCorrectRecords() {
        try {
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
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaStateCodeCsv(INDIA_CENSUS_CSV_FILE_PATH_FOR_STATE_CODE);
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

    @Test
    public void givenIndianCensusData_WhenSortedOnState_ShouldReturnSortResult() {
        try {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            censusAnalyser.loadCsvData(INDIA_CENSUS_CSV_FILE_PATH);
            String SortedCensusData = censusAnalyser.getStateSortedCensusData();
            CSVStateCensus censusCsv[] = new Gson().fromJson(SortedCensusData, CSVStateCensus[].class);
            Assert.assertEquals("Andhra Pradesh", censusCsv[0].state);
            System.out.println("bghj");
        } catch (CensusAnalyserException e) {

        }
    }

    @Test
    public void givenIndianCensusData_WhenSortedOnStateCode_ShouldReturnSortResult() {
        try {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            censusAnalyser.loadCsvData(INDIA_CENSUS_CSV_FILE_PATH_FOR_STATE_CODE);
            String SortedCensusData = censusAnalyser.getStateCodeSortedCensusData();
            CSVStatesCode censusCsv[] = new Gson().fromJson(SortedCensusData, CSVStatesCode[].class);
            Assert.assertEquals("Andhra Pradesh", censusCsv[0].StateName);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenIndianCensusData_WhenSortedOnPopulation_ShouldReturnSortResult() {
        try {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            censusAnalyser.loadCsvData(INDIA_CENSUS_CSV_FILE_PATH);
            String SortedCensusData = censusAnalyser.getStatePopulationSortedCensusData();
            CSVStateCensus censusCsv[] = new Gson().fromJson(SortedCensusData, CSVStateCensus[].class);
            System.out.println(censusCsv[0]);
            Assert.assertEquals("Uttar Pradesh", censusCsv[0].state);
        } catch (CensusAnalyserException e) {

        }
    }

    @Test
    public void givenIndianCensusData_WhenSortedOnPopulationDensity_ShouldReturnSortResult() {
        try {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            censusAnalyser.loadCsvData(INDIA_CENSUS_CSV_FILE_PATH);
            String SortedCensusData = censusAnalyser.getStatePopulationDensitySortedCensusData();
            CSVStateCensus censusCsv[] = new Gson().fromJson(SortedCensusData, CSVStateCensus[].class);
            System.out.println(censusCsv[0]);
            Assert.assertEquals("Bihar", censusCsv[0].state);
        } catch (CensusAnalyserException e) {

        }
    }

    @Test
    public void givenIndianCensusData_WhenSortedOnStateArea_ShouldReturnSortResult() {
        try {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            censusAnalyser.loadCsvData(INDIA_CENSUS_CSV_FILE_PATH);
            String SortedCensusData = censusAnalyser.getStateAreaSortedCensusData();
            CSVStateCensus censusCsv[] = new Gson().fromJson(SortedCensusData, CSVStateCensus[].class);
            System.out.println(censusCsv[0]);
            Assert.assertEquals("Rajasthan", censusCsv[0].state);
        } catch (CensusAnalyserException e) {
        }
    }
}

