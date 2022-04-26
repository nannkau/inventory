package edu.sgu.inventory.utils;

import edu.sgu.inventory.dto.ProductImport;
import edu.sgu.inventory.entity.Product;
import edu.sgu.inventory.entity.Tag;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FileExcelUtil {
    public static List<ProductImport> readProductFromFile(MultipartFile file){
        List<ProductImport> productImports= new ArrayList<>();
        try
        {

            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());

            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);

            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext())
            {
                Row row = rowIterator.next();
                //For each row, iterate through all the columns
                String productId =row.getCell(0).getStringCellValue();
                String name=row.getCell(1).getStringCellValue();
                int price=(int) row.getCell(2).getNumericCellValue();
                int stock=(int)row.getCell(3).getNumericCellValue();
                String color=row.getCell(4).getStringCellValue();
                String  tagId=row.getCell(5).getStringCellValue();

                ProductImport productImport= new ProductImport(productId,name,price,stock,color,tagId);
                productImports.add(productImport);
                System.out.println("");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return productImports;
    }

}
