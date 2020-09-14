package com.bridgelabz.censusanalyser.service;

import com.bridgelabz.censusanalyser.model.CSVStateCensusDao;
import com.bridgelabz.censusanalyser.utility.EnumSorting;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class ComparatorSort {
    static Map<EnumSorting, Comparator> map = new HashMap<>();

    public Comparator<CSVStateCensusDao> getSortedData(EnumSorting fields) {

        Comparator<CSVStateCensusDao> censusComparatorState = Comparator.comparing(census -> census.State);
        Comparator<CSVStateCensusDao> censusComparatorPopulation = Comparator.comparing(census -> census.population);
        Comparator<CSVStateCensusDao> censusComparatorPopulationDensity = Comparator.comparing(census -> census.Population_Density);
        Comparator<CSVStateCensusDao> censusComparatorArea = Comparator.comparing(census -> census.areaInSqKm);
        Comparator<CSVStateCensusDao> censusComparatorDensity = Comparator.comparing(census -> census.densityPerSqKm);
        Comparator<CSVStateCensusDao> censusComparatorName = Comparator.comparing(census -> census.StateName);
        Comparator<CSVStateCensusDao> censusComparatorCode = Comparator.comparing(census -> census.StateCode);
        Comparator<CSVStateCensusDao> censusComparatorUsState = Comparator.comparing(census -> census.State);
        Comparator<CSVStateCensusDao> censusComparatorUsPopulation = Comparator.comparing(census -> census.population);
        Comparator<CSVStateCensusDao> censusComparatorUsArea = Comparator.comparing(census -> census.area);

        map.put(EnumSorting.STATE, censusComparatorState);
        map.put(EnumSorting.POPULATION, censusComparatorPopulation.reversed());
        map.put(EnumSorting.POPULATION_DENSITY, censusComparatorPopulationDensity.reversed());
        map.put(EnumSorting.AREA_IN_SQKM, censusComparatorArea.reversed());
        map.put(EnumSorting.DENSITY_PER_SQKM, censusComparatorDensity.reversed());
        map.put(EnumSorting.STATE_NAME, censusComparatorName);
        map.put(EnumSorting.STATE_CODE, censusComparatorCode);
        map.put(EnumSorting.US_STATE, censusComparatorUsState);
        map.put(EnumSorting.US_POPULATION, censusComparatorUsPopulation.reversed());
        map.put(EnumSorting.AREA, censusComparatorUsArea.reversed());

        Comparator<CSVStateCensusDao> sortField = map.get(fields);

        return sortField;
    }

    public Comparator<CSVStateCensusDao> sortData(EnumSorting enumSorting) {
        return getSortedData(enumSorting);
    }

}
