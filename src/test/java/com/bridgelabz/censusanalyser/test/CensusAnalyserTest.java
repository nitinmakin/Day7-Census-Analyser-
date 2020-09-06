package com.bridgelabz.censusanalyser.test;
import com.bridgelabz.censusanalyser.exception.CensusAnalyserException;
import com.bridgelabz.censusanalyser.model.CSVStateCensus;
import com.bridgelabz.censusanalyser.service.StateCensusAnalyser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CensusAnalyserTest {
    private static final String INDIA_CENSUS_CSV_FILE_PATH = "D:\\fellowship\\Day8-Census-Analyser\\" +
            "src\\test\\resources\\IndiaStateCensusData.csv";

    @Test
    public void givenIndianCensusCSVFileReturnsCorrectRecords() {
        try {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            int numOfRecords = censusAnalyser.loadCsvData(INDIA_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(29, numOfRecords);
            System.out.println("TRY BLOCK");
        } catch (CensusAnalyserException e) {
            System.out.println("CATCH BLOCK");
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
            System.out.println("TRY BLOCK");
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
            System.out.println("CATCH BLOCK");
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
            System.out.println("try block");
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
            System.out.println("catch block");
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
            System.out.println("try block");
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
            System.out.println("catch block ");
        }

    }
}


