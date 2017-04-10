package Utilities.RawDataReadAndRegex;
import Utilities.DataCreationAndProcessing.ProblematicSingleDataUnit;
import Utilities.DataCreationAndProcessing.SingleDataUnit;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by jamie on 4/9/2017.
 * class for reading the whole raw data file, add sending each individual line to the RawDataRegex class and lineChecker method.
 */
public class FileRead {
    public static void readWholeFile(File fileInput, ArrayList<SingleDataUnit> dataSets, ArrayList<ProblematicSingleDataUnit> problematicDataSets) throws IOException {
        //Construct Input Stream Reader fileInputStream
        FileInputStream fileInputStream = new FileInputStream(fileInput);
        //Construct BufferedReader from the InputStreamReader
        BufferedReader bufferReader = new BufferedReader(new InputStreamReader(fileInputStream));
        //Create a string which will contain each line red from the raw data file.
        //While that string contains raw data, pass to RawDataRegex to check each for specific data.
        String singleLine = null;
        while ((singleLine = bufferReader.readLine()) != null) {
            RawDataRegex.lineChecker(singleLine, dataSets, problematicDataSets);
        }
        bufferReader.close(); //close buffer reader when done.
    }
}
