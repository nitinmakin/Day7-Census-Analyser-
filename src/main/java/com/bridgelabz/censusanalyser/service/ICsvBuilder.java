package com.bridgelabz.censusanalyser.service;

import com.bridgelabz.censusanalyser.exception.CsvBuilderException;

import java.io.Reader;
import java.util.Iterator;

public interface ICsvBuilder<E> {
    public Iterator getCSVFileIterator(Reader reader,
                                       Class csvClass) throws CsvBuilderException;
}
