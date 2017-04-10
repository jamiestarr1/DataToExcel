package Utilities.DataCreationAndProcessing;

/**
 * Created by jamie on 4/9/2017.
 * data unit for storing incomplete rows of data. this allows them to be removed from the original data set
 * without their loss.
 *
 * Otherwise incomplete data would lead to difficult null pointer issues, and also make it
 * hard for those using the resulting excel file to model the data in a meaningful way.
 *
 * The removed data is stored in the excel file in column 4, row 2 and later as needed.
 */
public class ProblematicSingleDataUnit {
    private String sampleId; //private instances to contain the processed data.
    private String sampleInput; //All kept as strings, as incomplete data sets
    private String sampleOutput; // are not useful for data modeling and thus do not need to be converted.

    //Wherever missing data is discovered, it is replaced with "MISSING" in the constructor
    public ProblematicSingleDataUnit(String sampleId, String sampleInput, String sampleOutput) {
        if(sampleId.length() == 0){this.sampleId = "MISSING";}else{this.sampleId = sampleId;}
        if(sampleInput.length() == 0){this.sampleInput = "MISSING";}else{this.sampleInput = sampleId;}
        if(sampleOutput.length() == 0){this.sampleOutput = "MISSING";}else{this.sampleOutput = sampleId;}
    }
    //toString method for providing information on the content of the ProblematicSingleDataUnit object
    @Override
    public String toString() {
        return "Utilities.DataCreationAndProcessing.SingleDataUnit{" +
                "sampleId=" + sampleId +
                ", sampleInput=" + sampleInput +
                ", sampleOutput=" + sampleOutput +
                '}';
    }
}
