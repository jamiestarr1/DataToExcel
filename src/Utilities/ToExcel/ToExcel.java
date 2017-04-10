package Utilities.ToExcel;

import Utilities.DataCreationAndProcessing.ProblematicSingleDataUnit;
import Utilities.DataCreationAndProcessing.SingleDataUnit;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by jamie on 4/9/2017.
 * This is where all creation and population of the excel file will occur with the processed data.
 */
public class ToExcel {
    public static void createPopulateExcel(ArrayList<SingleDataUnit> dataSets, ArrayList<ProblematicSingleDataUnit> problematicDataSets) {
        //First, Create a Blank workbook, and a new sheet within that workbook to populate with cells of data.
        //CreationHelper will be used to populate newly created cells with values.
        Workbook workbook = new HSSFWorkbook();
        CreationHelper createHelper = workbook.getCreationHelper();
        Sheet sheet = workbook.createSheet("Converted Data");
        //Rows are then created within the sheet, and cells within the row.
        //Specifically, row 0 will contain the labels, and those are created here:
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue(createHelper.createRichTextString("Sample ID"));
        row.createCell(1).setCellValue(createHelper.createRichTextString("Sample Input Value"));
        row.createCell(2).setCellValue(createHelper.createRichTextString("Sample output Value"));
        row.createCell(4).setCellValue(createHelper.createRichTextString("Problematic data"));
        // now, loop through both the dataSets and the problematicDataSets, and add them to their perspective row
        // performed this way, otherwise risk of overwriting row populated with data is possible when adding problematic data.
        int maxDataSetLength = Math.max(dataSets.size(),problematicDataSets.size());
        for (int i = 0; i < maxDataSetLength; i++) {
            //add good data as available, but once there are none remaining, add nothing else to the first 3 columns
            if(i < dataSets.size()) {
                row = sheet.createRow(i + 1);
                row.createCell(0).setCellValue(dataSets.get(i).getSampleId());
                row.createCell(1).setCellValue(dataSets.get(i).getSampleInput());
                row.createCell(2).setCellValue(new BigDecimal(dataSets.get(i).getSampleOutput()).doubleValue());
            }
            //add problematic data as available, but once there are none remaining, add nothing else to the 4th column
            if (i < problematicDataSets.size()) {
                row.createCell(4).setCellValue(problematicDataSets.get(i).toString());
            }
        }
        // Write the output to an excel file (note, did not use CSV file format)
        writeToExcel(workbook);
    }
    //FILE saved in src file, will try to do so, otherwise exception will be thrown and caught
    private static void writeToExcel(Workbook workbook) {
        FileOutputStream fileOut = null;
        try {
            fileOut = new FileOutputStream("Data Converted to Excel.xls");
            workbook.write(fileOut);
            fileOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
