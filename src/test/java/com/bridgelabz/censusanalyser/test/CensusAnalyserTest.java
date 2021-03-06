package com.bridgelabz.censusanalyser.test;

import com.bridgelabz.censusanalyser.exception.CensusAnalyserException;
import com.bridgelabz.censusanalyser.model.CSVStateCensus;
import com.bridgelabz.censusanalyser.model.CSVStateCensusDao;
import com.bridgelabz.censusanalyser.model.CSVStatesCode;
import com.bridgelabz.censusanalyser.model.CsvUSData;
import com.bridgelabz.censusanalyser.service.StateCensusAnalyser;
import com.bridgelabz.censusanalyser.utility.EnumSorting;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Map;

public class CensusAnalyserTest {
    private final String INDIA_CENSUS_CSV_FILE_PATH = "D:\\fellowship\\Day8-Census-Analyser\\" +
            "src\\test\\resources\\IndiaStateCensusData.csv";
    private final String INDIA_CENSUS_CSV_FILE_PATH_FOR_STATE_CODE = "D:\\fellowship\\" +
            "Day7-Census-Analyser-\\src\\test\\resources\\IndiaStatesCodes.csv";
    private final String US_CENSUS_DATA_PATH = "D:\\fellowship\\Day7-Census-Analyser-" +
            "\\src\\test\\resources\\USCensusData1.csv";
    Map<String, CSVStateCensusDao> map;

    @Test
    public void givenIndianCensusCSVFileReturnsCorrectRecords() {
        try {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            int numOfRecords = censusAnalyser.loadStateCsvData(StateCensusAnalyser.Country.INDIA, INDIA_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(29, numOfRecords);
        } catch (CensusAnalyserException e) {
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
            censusAnalyser.loadStateCsvData(StateCensusAnalyser.Country.INDIA, WRONG_CSV_FILE_PATH);
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
            censusAnalyser.loadStateCsvData(StateCensusAnalyser.Country.INDIA, WRONG_CSV_FILE_TYPE);
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
            censusAnalyser.loadStateCsvData(StateCensusAnalyser.Country.INDIA, WRONG_CSV_DELIMITER_TYPE);
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
            censusAnalyser.loadStateCsvData(StateCensusAnalyser.Country.INDIA, WRONG_CSV_HEADER);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndianStateCodeCensusCSVFileShouldReturnsCorrectRecords() {
        try {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            int numOfRecords = censusAnalyser.loadStateCsvData(StateCensusAnalyser.Country.INDIAN_STATE_CODE,
                    INDIA_CENSUS_CSV_FILE_PATH_FOR_STATE_CODE);
            Assert.assertEquals(36, numOfRecords);
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
            censusAnalyser.loadStateCsvData(StateCensusAnalyser.Country.INDIAN_STATE_CODE, WRONG_CSV_FILE_PATH);
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
            censusAnalyser.loadStateCsvData(StateCensusAnalyser.Country.INDIAN_STATE_CODE, WRONG_CSV_FILE_TYPE);
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
            censusAnalyser.loadStateCsvData(StateCensusAnalyser.Country.INDIAN_STATE_CODE, WRONG_CSV_DELIMITER_TYPE);
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
            censusAnalyser.loadStateCsvData(StateCensusAnalyser.Country.INDIAN_STATE_CODE, WRONG_CSV_HEADER);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }


    @Test
    public void givenIndianCensusData_WhenSortedOnState_ShouldReturnSortResult() {
        try {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            censusAnalyser.loadStateCsvData(StateCensusAnalyser.Country.INDIA, INDIA_CENSUS_CSV_FILE_PATH);
            String SortedCensusData = censusAnalyser.getStateSortedCensusData(EnumSorting.STATE);
            CSVStateCensus censusCsv[] = new Gson().fromJson(SortedCensusData, CSVStateCensus[].class);
            Assert.assertEquals("Andhra Pradesh", censusCsv[0].State);
            System.out.println(censusCsv[0]);
        } catch (CensusAnalyserException e) {

        }
    }

    @Test
    public void givenIndianCensusData_WhenSortedOnStateCode_ShouldReturnSortResult() {
        try {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            censusAnalyser.loadStateCsvData(StateCensusAnalyser.Country.INDIAN_STATE_CODE,
                    INDIA_CENSUS_CSV_FILE_PATH_FOR_STATE_CODE);


            String SortedCensusData = censusAnalyser.getStateSortedCensusData(EnumSorting.STATE_CODE);
            CSVStatesCode censusCsv[] = new Gson().fromJson(SortedCensusData, CSVStatesCode[].class);
            Assert.assertEquals("Andhra Pradesh", censusCsv[0].StateName);
            System.out.println(censusCsv[0]);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenIndianCensusData_WhenSortedOnPopulation_ShouldReturnSortResult() {
        try {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            censusAnalyser.loadStateCsvData(StateCensusAnalyser.Country.INDIA, INDIA_CENSUS_CSV_FILE_PATH);
            String SortedCensusData = censusAnalyser.getStateSortedCensusData(EnumSorting.POPULATION);
            CSVStateCensus censusCsv[] = new Gson().fromJson(SortedCensusData, CSVStateCensus[].class);
            System.out.println(censusCsv[0]);
            Assert.assertEquals("Uttar Pradesh", censusCsv[0].State);
        } catch (CensusAnalyserException e) {

        }
    }

    @Test
    public void givenIndianCensusData_WhenSortedOnPopulationDensity_ShouldReturnSortResult() {
        try {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            censusAnalyser.loadStateCsvData(StateCensusAnalyser.Country.INDIA, INDIA_CENSUS_CSV_FILE_PATH);
            String SortedCensusData = censusAnalyser.getStateSortedCensusData(EnumSorting.POPULATION_DENSITY);
            CSVStateCensus censusCsv[] = new Gson().fromJson(SortedCensusData, CSVStateCensus[].class);
            System.out.println(censusCsv[0]);
            Assert.assertEquals("Bihar", censusCsv[0].State);
        } catch (CensusAnalyserException e) {

        }
    }

    @Test
    public void givenIndianCensusData_WhenSortedOnStateArea_ShouldReturnSortResult() {
        try {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            censusAnalyser.loadStateCsvData(StateCensusAnalyser.Country.INDIA, INDIA_CENSUS_CSV_FILE_PATH);
            String SortedCensusData = censusAnalyser.getStateSortedCensusData(EnumSorting.AREA);
            CSVStateCensus censusCsv[] = new Gson().fromJson(SortedCensusData, CSVStateCensus[].class);
            System.out.println(censusCsv[0]);
            Assert.assertEquals("Bihar", censusCsv[0].State);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenUSCensusCSVFileReturnsCorrectRecords() {
        try {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            int numOfRecords = censusAnalyser.loadStateCsvData(StateCensusAnalyser.Country.US, US_CENSUS_DATA_PATH);
            Assert.assertEquals(51, numOfRecords);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenUSCensusData_WhenSortedOnState_ShouldReturnSortResult() {
        try {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            censusAnalyser.loadStateCsvData(StateCensusAnalyser.Country.US, US_CENSUS_DATA_PATH);
            String SortedCensusData = censusAnalyser.getStateSortedCensusData(EnumSorting.US_STATE);
            CsvUSData censusUSCsv[] = new Gson().fromJson(SortedCensusData, CsvUSData[].class);
            Assert.assertEquals("Alabama", censusUSCsv[0].State);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenUSCensusData_WhenSortedOnPopulation_ShouldReturnSortResult() {
        try {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            censusAnalyser.loadStateCsvData(StateCensusAnalyser.Country.US, US_CENSUS_DATA_PATH);
            String SortedCensusData = censusAnalyser.getStateSortedCensusData(EnumSorting.US_POPULATION);
            CsvUSData censusUSCsv[] = new Gson().fromJson(SortedCensusData, CsvUSData[].class);
            Assert.assertEquals("North Carolina", censusUSCsv[0].State);
            System.out.println(censusUSCsv[0]);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenUSCensusData_WhenSortedOnPopulationDensity_ShouldReturnSortResult() {
        try {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            censusAnalyser.loadStateCsvData(StateCensusAnalyser.Country.US, US_CENSUS_DATA_PATH);
            String SortedCensusData = censusAnalyser.getStateSortedCensusData(EnumSorting.POPULATION_DENSITY);
            CsvUSData censusUSCsv[] = new Gson().fromJson(SortedCensusData, CsvUSData[].class);
            Assert.assertEquals("District of Columbia", censusUSCsv[0].State);
            System.out.println(censusUSCsv[0]);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenUSCensusData_WhenSortedOnArea_ShouldReturnSortResult() {
        try {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            censusAnalyser.loadStateCsvData(StateCensusAnalyser.Country.US, US_CENSUS_DATA_PATH);
            String SortedCensusData = censusAnalyser.getStateSortedCensusData(EnumSorting.AREA_IN_SQKM);
            CsvUSData censusUSCsv[] = new Gson().fromJson(SortedCensusData, CsvUSData[].class);
            Assert.assertEquals("North Carolina", censusUSCsv[0].State);
            System.out.println(censusUSCsv[0]);
        } catch (CensusAnalyserException e) {
        }
    }
}

