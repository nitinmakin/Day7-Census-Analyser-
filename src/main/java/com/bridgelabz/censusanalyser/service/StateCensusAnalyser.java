package com.bridgelabz.censusanalyser.service;

import com.bridgelabz.censusanalyser.exception.CensusAnalyserException;
import com.bridgelabz.censusanalyser.model.CSVStateCensus;
import com.bridgelabz.censusanalyser.model.CSVStatesCode;
import com.bridgelabz.csvbuilder.exception.CsvBuilderException;
import com.bridgelabz.csvbuilder.service.CsvBuilderFactory;
import com.bridgelabz.csvbuilder.service.ICsvBuilder;
import com.google.gson.Gson;


import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

public class StateCensusAnalyser {

    List<CSVStateCensus>  censusCSVList = null;

    public int loadCsvData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICsvBuilder csvBuilder = CsvBuilderFactory.createCsvBuilder();
            censusCSVList = csvBuilder.getCSVFileList(reader, CSVStateCensus.class);
            return censusCSVList.size();
        } catch (Exception e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
    }
<<<<<<< HEAD

<<<<<<< HEAD
<<<<<<< HEAD
    public int loadIndiaStateCode(String csvFilePath) throws CensusAnalyserException {
=======
=======

>>>>>>> UC2_Load_indian_states_code_info
=======
>>>>>>> UC3_Sort_data_by_states
    public int loadIndiaStateCodeCsv(String csvFilePath) throws CensusAnalyserException {
>>>>>>> UC2_Load_indian_states_code_info
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICsvBuilder csvBuilder = CsvBuilderFactory.createCsvBuilder();
            Iterator<CSVStatesCode> censusCSVIterator = csvBuilder.getCSVFileIterator(reader, CSVStatesCode.class);
            return getCount(censusCSVIterator);
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (CsvBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(), e.type.name());
        }

    }

    private <E> int getCount(Iterator<E> iterator) {
        Iterable<E> statesCodeIterable = () -> iterator;
        int numberOfEntries = (int) StreamSupport.stream(statesCodeIterable.spliterator(), false).count();
        return numberOfEntries;
    }

    public String getStateSortedCensusData() throws CensusAnalyserException {
        if(censusCSVList == null || censusCSVList.size() == 0){
            throw new CensusAnalyserException("NO Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }

        Comparator<CSVStateCensus> censusComparator = Comparator.comparing(census -> census.state);
        this.sort(censusComparator);
        return new Gson().toJson(censusCSVList);

    }

    private void sort(Comparator<CSVStateCensus> censusComparator) {
        for(int i =0; i < censusCSVList.size()-1; i++){
            for (int j = 0; j < censusCSVList.size()-i-1; j++){
                CSVStateCensus csvStateCensus1 = censusCSVList.get(j);
                CSVStateCensus csvStateCensus2 = censusCSVList.get(j+1);
                if(censusComparator.compare(csvStateCensus1, csvStateCensus2) > 0)
                {
                    censusCSVList.set(j, csvStateCensus2);
                    censusCSVList.set(j+1, csvStateCensus1);
                }
            }
        }
    }
}

