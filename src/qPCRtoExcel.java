/**
 * Created by jamie on 4/7/2017.
 *
 * MAIN CLASS, here you will specify the location and name of the datafile on lines 28 and 29 prospectively.
 *
 * Entire program designed to convert raw data into a usable excel file directly, without relying on CSV formatting.
 * Additionally, will save incomplete data sets in a separate area of the exel file in order to preserve them
 * for the scientist who utilizes the program.
 *
 * Does require Apache POI to output to excel, and this may require you to download and include the external library before use.
 * https://poi.apache.org/download.html  "21 September 2016 - POI 3.15 available" binary distro was used.
 *
 * written by Jamie Starr, contact at jaStarr@ucdavis.edu
 * Provided for ability demonstration only,
 * contact for commercial use.
 */
import Utilities.DataCreationAndProcessing.ProblematicSingleDataUnit;
import Utilities.DataCreationAndProcessing.SingleDataUnit;
import Utilities.RawDataReadAndRegex.FileRead;
import Utilities.ToExcel.ToExcel;
import java.util.ArrayList;
import java.util.Collections;
import java.io.*;
public class qPCRtoExcel {


    public static void main(String[] args) throws IOException {
        //Specify directory where the file will be located and Specify the file name.
        File directory = new File("C:/Users/jamie/Desktop");
        File fileName = new File(directory.getCanonicalPath() + File.separator + "data.txt");
        //create ArrayLists for the complete Single Data Units (ID, input, output)
        // and Incomplete(problematic) Single Data Units.
        ArrayList<SingleDataUnit> dataSets = new ArrayList(10000);
        ArrayList<ProblematicSingleDataUnit> problematicDataSets = new ArrayList(10000);
        //Read whole file, and proceed to process data into Single Data Units (id, input, output)
        FileRead.readWholeFile(fileName, dataSets, problematicDataSets);
        //Sort Single Data Units by input variable.
        Collections.sort(dataSets);
        //Create and export to Excel file directly (note: using Apache POI for actual Excel file, not using CSV format)
        ToExcel.createPopulateExcel(dataSets, problematicDataSets);
    }

}


