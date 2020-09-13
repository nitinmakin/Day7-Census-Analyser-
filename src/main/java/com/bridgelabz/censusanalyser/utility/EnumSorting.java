package com.bridgelabz.censusanalyser.utility;

import com.bridgelabz.censusanalyser.model.CSVStateCensusDao;

import java.util.Comparator;

public enum EnumSorting {

    STATE {
        @Override
        public Comparator<CSVStateCensusDao> sortData() {
            Comparator<CSVStateCensusDao> censusComparator = Comparator.comparing(census -> census.State);
            return censusComparator;
        }
    },
    POPULATION {
        @Override
        public Comparator<CSVStateCensusDao> sortData() {
            Comparator<CSVStateCensusDao> censusComparator = Comparator.comparing(census -> census.population);
            return censusComparator;
        }
    },
    POPULATION_DENSITY {
        @Override
        public Comparator<CSVStateCensusDao> sortData() {
            Comparator<CSVStateCensusDao> censusComparator = Comparator.comparing(census -> census.Population_Density);
            return censusComparator;
        }
    },

    AREA_IN_SQKM {
        @Override
        public Comparator<CSVStateCensusDao> sortData() {
            Comparator<CSVStateCensusDao> censusComparator = Comparator.comparing(census -> census.areaInSqKm);
            return censusComparator;
        }
    },
    DENSITY_PER_SQKM {
        @Override
        public Comparator<CSVStateCensusDao> sortData() {
            Comparator<CSVStateCensusDao> censusComparator = Comparator.comparing(census -> census.densityPerSqKm);
            return censusComparator;
        }
    },
    STATE_NAME {
        @Override
        public Comparator<CSVStateCensusDao> sortData() {
            Comparator<CSVStateCensusDao> censusComparator = Comparator.comparing(census -> census.StateName);
            return censusComparator;
        }
    },
    STATE_CODE {
        @Override
        public Comparator<CSVStateCensusDao> sortData() {
            Comparator<CSVStateCensusDao> censusComparator = Comparator.comparing(census -> census.StateCode);
            return censusComparator;
        }
    },
    US_STATE {
        @Override
        public Comparator<CSVStateCensusDao> sortData() {
            Comparator<CSVStateCensusDao> censusComparator = Comparator.comparing(census -> census.State);
            return censusComparator;
        }
    },

    US_POPULATION {
        @Override
        public Comparator<CSVStateCensusDao> sortData() {
            Comparator<CSVStateCensusDao> censusComparator = Comparator.comparing(census -> census.Population);
            return censusComparator;
        }
    },
    AREA {
        @Override
        public Comparator<CSVStateCensusDao> sortData() {
            Comparator<CSVStateCensusDao> censusComparator = Comparator.comparing(census -> census.area);
            return censusComparator;
        }
    };


    public abstract Comparator<CSVStateCensusDao> sortData();

}
