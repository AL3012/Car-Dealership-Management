package com.example.cardealershipmanagement.pdf;

import android.content.Context;
import android.widget.Toast;

import com.example.cardealershipmanagement.Database.BuyerTable;
import com.example.cardealershipmanagement.Database.CarModel;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MyPdf {
    //write method takes two parameter pdf name and content
    //return true if pdf successfully created
    public Boolean write(Context context, String fname, ArrayList<CarModel> myProducts) {
        try {
            //Create file path for Pdf
            String fpath =  "/sdcard/Download/"+ fname;
            File file = new File(fpath);
            /*if (!file.createNewFile()) {
                Toast.makeText(context, "File not created", Toast.LENGTH_LONG).show();
                return false;
            }*/
            // To customise the text of the pdf
            // we can use FontFamily
            // create an instance of itext document
            Document document = new Document();

            PdfPTable table = new PdfPTable(new float[] { 2, 3, 1, 2});
            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell("Make");
            table.addCell("Model");
            table.addCell("Year");
            /*table.addCell("Selling Price");*/
            table.addCell("Color");
            table.setHeaderRows(1);
            PdfPCell[] cells = table.getRow(0).getCells();
            for (int j=0;j<cells.length;j++){
                cells[j].setBackgroundColor(BaseColor.GRAY);
            }
            for (int i=0;i<myProducts.size();i++){

                table.addCell(myProducts.get(i).getMake());
                table.addCell(myProducts.get(i).getModel());
                table.addCell(myProducts.get(i).getYear());
                /*table.addCell(myProducts.get(i).getSellingPrice());*/
                table.addCell(myProducts.get(i).getColor());
            }

            FileOutputStream fileOutputStream = new FileOutputStream(file.getAbsoluteFile());
            PdfWriter.getInstance(document, fileOutputStream);

            document.open();
            document.add(table);
            document.close();

            Toast.makeText(context,"pdf created.",Toast.LENGTH_LONG).show();
            return true;

        }
        catch (IOException e) {
            Toast.makeText(context,"IO Exception",Toast.LENGTH_LONG).show();
            return false;
        }
        catch (DocumentException e) {
            Toast.makeText(context,"Document Exception",Toast.LENGTH_LONG).show();
            return false;
        }
    }
}
