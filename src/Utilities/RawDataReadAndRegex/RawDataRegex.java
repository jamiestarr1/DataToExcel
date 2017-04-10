package Utilities.RawDataReadAndRegex;

import Utilities.DataCreationAndProcessing.DataCreator;
import Utilities.DataCreationAndProcessing.ProblematicSingleDataUnit;
import Utilities.DataCreationAndProcessing.SingleDataUnit;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jamie on 4/9/2017.
 * This class contains the linchecker method which uses multiple Regex's to find the raw data on a line by line basis.
 * It is then processed, without changing the original datafile in anyway, and calls are made to create SingleDataUnits.
 *
 * Creator(Jamie) design notes:
 *the delimiter is |<->|, or |<>| and always comes in 7 per line (think Turing machine encoding of simulated DFA)
 *#|<->|#|<->|#.#E+#|<->||<->|#|<->|#|<->|#.#E+#|<->|
 */

public class RawDataRegex {
    public static void lineChecker(String singleLineOfRawData, ArrayList<SingleDataUnit> dataSets, ArrayList<ProblematicSingleDataUnit> problematicDataSets) {
        //first, look for the standard delimiter as a sign the singleLineOfRawData contains the data we seek.
        String delimiter = "(\\|<->\\|)";
        Pattern pattern = Pattern.compile(delimiter);
        Matcher matcher = pattern.matcher(singleLineOfRawData);
        //when a match is found, utilize Regex.replaceAll method, and String.split method (both are regex's)
        //first we remove white spaces which could potentially cause issues later, so replace with ""
        //then we look for incomplete or irregular delimiter of the form |<>|, replace with a comma ","
        //then we look for the regular delimiter of the form |<->|, replace with a comma as well ","
        //this was done because regularDelimiterReplace.split(delimiter or delimiter) is easier to run on a comma,
        //and allowed for easy adhoc of other irregular delimiters to the file as they were discovered.
        //once all possible delimiters were replaced with comma's, the singleLineOfRawData will be split into a String[] and passed to DataCreator.
        if (matcher.find()) {
            String noWhiteSpace = singleLineOfRawData.replaceAll("\\s+", "");
            String irregularDelimiterReplace = noWhiteSpace.replaceAll("(\\|<>\\|)", ",");
            //add other irregular delimiters as found here of the form string.replaceAll("(delimiter)", ",");
            String regularDelimiterReplace = irregularDelimiterReplace.replaceAll("(\\|<->\\|)", ",");
            String[] rawDataArray = regularDelimiterReplace.split(",");
            DataCreator.singleDataUnitCreator(dataSets, problematicDataSets, rawDataArray);
        }
    }
}
