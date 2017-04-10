package Utilities.DataCreationAndProcessing;

/**
 * Created by jamie on 4/8/2017.
 * data unit for storing complete rows of data
 * they're passed to the constructor as Strings, where sampleId and sampleInput are converted to Integers
 */
public class SingleDataUnit implements Comparable<SingleDataUnit> {
    private Integer sampleId;       //private instances to contain the processed data.
    private Integer sampleInput;    //both sampleId sampleInput are Integer wrapper classes
    private String sampleOutput;    //sampleOutput is kept as sting to assist with BigDecimal formatting
                                    //allows for easy population and formatting of the sampleOutput cell in excel file

    //constructor for the SingleDataUnit
    public SingleDataUnit(String sampleIDString, String sampleInputString, String sampleOutput) {
            this.sampleId= Integer.valueOf(sampleIDString); //creates the Integer wrappers during construction.
            this.sampleInput = Integer.valueOf(sampleInputString);
            this.sampleOutput = sampleOutput;
    }

    //getter for sampleId
    public Integer getSampleId() {
        return sampleId;
    }

    //setter for sampleId
    public void setSampleId(Integer sampleId) {
        this.sampleId = sampleId;
    }

    //getter for sampleInput
    public Integer getSampleInput() {
        return sampleInput;
    }

    //setter for sampleInput
    public void setSampleInput(Integer sampleInput) {
        this.sampleInput = sampleInput;
    }

    //getter for sampleOutput
    public String getSampleOutput() {
        return sampleOutput;
    }

    //setter for sampleOutput
    public void setSampleOutput(String sampleOutput) {
        this.sampleOutput = sampleOutput;
    }

    //toString method for providing information on the content of the SingleDataUnit object
    @Override
    public String toString() {
        return "Utilities.DataCreationAndProcessing.SingleDataUnit{" +
                "sampleId=" + sampleId +
                ", sampleInput=" + sampleInput +
                ", sampleOutput=" + sampleOutput +
                '}';
    }

    //equals method for providing comparison between SingleDataUnit objects
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SingleDataUnit that = (SingleDataUnit) o;
        if (!getSampleId().equals(that.getSampleId())) return false;
        if (!getSampleInput().equals(that.getSampleInput())) return false;
        return getSampleOutput().equals(that.getSampleOutput());
    }
    //compare method to be called from compareTO overridden method. Compares input values instead of Id's or output values.
    //calls the Integer compareTo method.
    public int compare(SingleDataUnit o1, SingleDataUnit o2) {
        return o1.getSampleInput().compareTo(o2.getSampleInput());
    }

    //calls the compare method above. Will be utilized in Collections.sort() method
    @Override
    public int compareTo(SingleDataUnit d2) {
        return compare(this, d2);
    }

}
