package com.bridgelabz.censusanalyser.utility;

import com.bridgelabz.censusanalyser.exception.CsvBuilderException;
import com.bridgelabz.censusanalyser.service.ICsvBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.util.Iterator;

public class OpenCsvBuilder implements ICsvBuilder {
    public  Iterator getCSVFileIterator(Reader reader,
                                              Class csvClass) throws CsvBuilderException {
        try {
            CsvToBeanBuilder csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(csvClass);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean csvToBean = csvToBeanBuilder.build();
            return csvToBean.iterator();
        } catch (IllegalStateException e) {
            throw new CsvBuilderException(e.getMessage(),
                    CsvBuilderException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
    }
}
