package uz.shakhobiddin.service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.opencsv.CSVWriter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uz.shakhobiddin.entity.ResponseSaveEntity;
import uz.shakhobiddin.exp.AppBadException;
import uz.shakhobiddin.repository.ResponseSaveRepository;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Author: Shakhobiddin N.
 * Date: 02.07.2024
 * Project: Saving Response Data to the Several Files
 */
@Service
public class ResponseSaveService {
    private final ResponseSaveRepository responseSaveRepository;
    private final RestTemplate restTemplate;

    public ResponseSaveService(ResponseSaveRepository responseSaveRepository, RestTemplate restTemplate) {
        this.responseSaveRepository = responseSaveRepository;
        this.restTemplate = restTemplate;
    }

    public void fetchAndSaveData(String url) {
        //Get data from given url via RestTemplate
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            url = "http://" + url;
        }
        List<Map<String, Object>> responseData = restTemplate.getForObject(url, List.class);
        //check request
        if (responseData != null && !responseData.isEmpty()) {
            //csv file save
            saveDataToCsv(responseData);
            //date base save
            saveDataToDataBase(responseData);
            //pdf file save
            saveDataToPdf(responseData);
            //xlsx file save
            saveDataToExcel(responseData);
        } else {
            throw new AppBadException("No data found at the provided URL.");
        }
    }

    private void saveDataToDataBase(List<Map<String, Object>> responseData) {
        //Save to date base
        for (Map<String, Object> item : responseData) {
            ResponseSaveEntity entity = new ResponseSaveEntity();
            entity.setFromDate((LocalDate) item.get("fromDate"));    //fromDate, toDate, description => keylar
            entity.setToDate((LocalDate) item.get("toDate"));
            entity.setDescription((String) item.get("description"));
            responseSaveRepository.save(entity);
        }
    }

    private void saveDataToCsv(List<Map<String, Object>> data) {
        //write data to csv file
        String[] headers = data.get(0).keySet().toArray(new String[0]);
        try (CSVWriter writer = new CSVWriter(new FileWriter("allservices-" + LocalDate.now() + ".csv"))) {
            writer.writeNext(headers);
            for (Map<String, Object> row : data) {
                String[] values = row.values().stream()
                        .map(Object::toString)
                        .toArray(String[]::new);
                writer.writeNext(values);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveDataToPdf(List<Map<String, Object>> data) {
        PdfWriter writer;
        try {
            writer = new PdfWriter("allservices-" + LocalDate.now() + ".pdf");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        if (!data.isEmpty()) {
            // Headerlar
            String[] headers = data.get(0).keySet().toArray(new String[0]);
            Table table = new Table(headers.length);
            for (String header : headers) {
                table.addCell(new Paragraph(header));
            }

            // Ma'lumotlar
            for (Map<String, Object> row : data) {
                for (String header : headers) {
                    table.addCell(new Paragraph(String.valueOf(row.get(header))));
                }
            }
            document.add(table);
        } else {
            document.add(new Paragraph("No data available"));
        }
        document.close();
    }

    public void saveDataToExcel(List<Map<String, Object>> data) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("allservices-" + LocalDate.now() + ".xlsx");
        if (!data.isEmpty()) {
            String[] headers = data.get(0).keySet().toArray(new String[0]);
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }
            for (int i = 0; i < data.size(); i++) {
                Row dataRow = sheet.createRow(i + 1);
                Map<String, Object> rowData = data.get(i);
                for (int j = 0; j < headers.length; j++) {
                    Cell cell = dataRow.createCell(j);
                    cell.setCellValue(String.valueOf(rowData.get(headers[j])));
                }
            }
        }
        try (FileOutputStream fileOut = new FileOutputStream("allservices-" + LocalDate.now() + ".xlsx")) {
            workbook.write(fileOut);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            workbook.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
