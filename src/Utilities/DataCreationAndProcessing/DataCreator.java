package Utilities.DataCreationAndProcessing;

import java.util.ArrayList;

/**
 * Created by jamie on 4/9/2017.
 * DataCreator class contains the singleDataUnitCreator method. THis is where the processed lines of raw data, which are passed in
 * as a rawDataArray are then broken up and processed based on completeness, and which ArrayList they will be stored in
 *
 * complete sets of data (id, input value, output value) are stored in the dataSets arraylist list which, upon completion will be later sorted
 * incomplete sets of data, those missing 1,2, or all 3 of the values will be stored in the problematicDataSets arraylist
 * to prevent their loss (so the scientist can know what went wrong or what was excluded from modeling)
 * and also to prevent them from causing null pointer issues during sorting or excel sheet construction.
 *
 */
public class DataCreator {
    public static void singleDataUnitCreator(ArrayList<SingleDataUnit> dataSets, ArrayList<ProblematicSingleDataUnit> problematicDataSets, String[] rawDataArray) {
        //if the rawDataArray is a complete set of three raw data strings(id, input, output)
        //then they will be passed to the constructor for the SingleDataUnit, to create the SingleDataUnit
        //and added to the dataSets ArrayList which will then be sorted by input data, and passed to ToExcel methods in the main method.

        //if the raw data array is incomplete in anyway
        //then they will be passed to the constructor for the ProblematicSingleDataUnit, to create the ProblematicSingleDataUnit
        //with appropriate Warning Strings as padding. Then added to the problematicDataSets ArrayList and passed to ToExcel methods in the main method.

        //Since the rawDataArray is actually two data sets long (indexes 0,1,2 and 4,5,6)
        //two sets of conditionals process them separately:

        //rawDataArray indexes 0,1,2 are checked to see if they're empty, if all 3 have data, then they are processed.
        //else they are dealt with as problematic Data
        if (!rawDataArray[0].isEmpty() && !rawDataArray[1].isEmpty() && !rawDataArray[2].isEmpty()) {
            SingleDataUnit dataUnit1 = new SingleDataUnit(rawDataArray[0], rawDataArray[1], rawDataArray[2]);
            dataSets.add(dataUnit1);
        } else {
            ProblematicSingleDataUnit problemDataUnit1 = new ProblematicSingleDataUnit(rawDataArray[0], rawDataArray[1], rawDataArray[2]);
            problematicDataSets.add(problemDataUnit1);
        }
        //rawDataArray indexes 4,5,6 are checked to see if they're empty, if all 3 have data, then they are processed.
        //else they are dealt with as problematic Data
        if (!rawDataArray[4].isEmpty() && !rawDataArray[5].isEmpty() && !rawDataArray[6].isEmpty()) {
            SingleDataUnit dataUnit2 = new SingleDataUnit(rawDataArray[4], rawDataArray[5], rawDataArray[6]);
            dataSets.add(dataUnit2);
        } else {
            ProblematicSingleDataUnit problemDataUnit2 = new ProblematicSingleDataUnit(rawDataArray[4], rawDataArray[5], rawDataArray[6]);
            problematicDataSets.add(problemDataUnit2);
        }
    }
}
