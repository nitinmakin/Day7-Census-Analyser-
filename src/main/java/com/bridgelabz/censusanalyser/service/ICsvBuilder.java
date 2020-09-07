package com.bridgelabz.censusanalyser.service;

import com.bridgelabz.censusanalyser.exception.CensusAnalyserException;

import java.io.Reader;
import java.util.Iterator;

public interface ICsvBuilder<E> {
    public Iterator getCSVFileIterator(Reader reader,
                                       Class csvClass) throws CensusAnalyserException;
}
