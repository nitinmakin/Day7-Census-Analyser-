package com.bridgelabz.censusanalyser.model;

import com.opencsv.bean.CsvBindByName;

public class CSVStatesCode {
    @CsvBindByName(column = "State Name", required = true)
    public String StateName;

    @CsvBindByName(column = "StateCode", required = true)
    public String StateCode;

    @CsvBindByName(column = "SrNo", required = true)
    public String SrNo;

    @CsvBindByName(column = "TIN", required = true)
    public String TIN;


    @Override
    public String toString() {
        return "CSVStatesCode{" +
                "stateName='" + StateName + '\'' +
                ", stateCode='" + StateCode + '\'' +
                ", SrNo='" + SrNo + '\'' +
                ", TIN='" + TIN + '\'' +
                '}';
    }
}
