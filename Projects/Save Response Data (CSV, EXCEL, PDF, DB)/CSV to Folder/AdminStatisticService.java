package uz.project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.project.exception.condition.NotFormatSuitableException;
import uz.project.service.caller.CallerService;
import uz.project.service.conditon.AdminStatisticInterface;
import uz.project.util.PostResponse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Authors: Hamdamboy & Hudoberdi
 * Date: 23.04.2024
 * Time: 9:45
 * Project: service
 */
@Service
@RequiredArgsConstructor
public class AdminStatisticService implements AdminStatisticInterface {

    private final CallerService callerService;
    final static String baseURL = "src\\main\\resources\\files";

    @Override
    public PostResponse getDailyStatsEgov(String repDate, String apiName, int pageNo, int pageSize) {
        PostResponse postResponse = new PostResponse();

        final String orgURL = "companyName";

        final String orgApiNameURL = apiName;

        final String specificYear = repDate.substring(0, 4);

        LocalDate parseMonth = LocalDate.parse(repDate);
        Month monthService = parseMonth.getMonth();


        final String csvFilePath = baseURL + "\\" + orgURL + "\\services\\" +orgApiNameURL + "\\" + specificYear
                +  "\\" + monthService + "\\" + "daily" + "\\";

        String addingName = repDate + "_" + apiName;
        File csvFile = new File(csvFilePath + addingName + ".csv");
        if (isValidDate(repDate)) {
            if (!csvFile.exists()) {
                File file = callerService.callToEgovServer("/getDaily?repDate=" + repDate + "&api_name="
                        + apiName, repDate, apiName);
                try {
                    saveCsvFile(file, new File(csvFilePath));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                List<String[]> strings = readCsvData(file.getPath());
                postResponse.setEgovResponses(strings);
            }
        } else {
            throw new NotFormatSuitableException("Date is Wrong ");
        }
        return postResponse;
    }


    @Override
    public PostResponse getMonthlyAllServiceStatsEgov(String fromDate, String toDate, int pageNo, int pageSize) throws IOException {
        List<String[]> strings = List.of();
        PostResponse postResponse = new PostResponse();

        // TODO: Month ---> Folder

        var existYear = fromDate.substring(0, 4);
        LocalDate parse = LocalDate.parse(fromDate);
        Month month = parse.getMonth();
        String csvFilePath = "src\\main\\resources\\files\\companyName\\allservices\\" + existYear + "\\"+month+"\\";

        String addedPath = fromDate + "_" + toDate;
        File csvFile = new File(csvFilePath + addedPath + ".csv");
        if (isValidDate(fromDate) && isValidDate(toDate)) {
            if (!csvFile.exists()) {
                File file = callerService.callToEgovServer("/get?fromDate=" + fromDate + "&toDate=" + toDate, fromDate, toDate);
                saveCsvFile(file, new File(csvFilePath));
                strings = readCsvData(file.getPath());
                postResponse.setEgovResponses(strings);
            }
        } else {
            throw new NotFormatSuitableException("Date is Wrong ");
        }

        return PostResponse
                .builder()
                .pageNo(pageNo)
                .pageSize(pageSize)
                .egovResponses(strings)
                .build();
    }

    public static boolean isValidDate(String dateString) {
        try {
            LocalDate.parse(dateString);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public void saveCsvFile(File csvFile, File outputFolder) throws IOException {
        if (!outputFolder.exists()) {
            outputFolder.mkdirs();
        }
        Path destinationPath = new File(outputFolder, csvFile.getName()).toPath();
        Files.copy(csvFile.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
    }

    /*  public List<String[]> convertCsvToXlsx(String csvFilePath, String xlsxFilePath) throws IOException {
          try{
          File csvFile = new File(csvFilePath);
          if (!csvFile.exists()) {
              throw new FileNotFoundException("CSV file not found: " + csvFilePath);
          }

          File xlsxFile = new File(xlsxFilePath);
          if (!xlsxFile.exists()) {
              xlsxFile.createNewFile();
          }
          try (CSVReader reader = new CSVReader(new FileReader(csvFilePath));
               Workbook workbook = new XSSFWorkbook()) {

              Sheet sheet = workbook.createSheet("Sheet1");
              List<String[]> allRows = reader.readAll();

              for (int i = 0; i < allRows.size(); i++) {
                  Row row = sheet.createRow(i);
                  String[] cells = allRows.get(i);
                  for (int j = 0; j < cells.length; j++) {
                      Cell cell = row.createCell(j);
                      cell.setCellValue(cells[j]);
                  }
              }

              FileOutputStream fos = new FileOutputStream(xlsxFilePath);
                  workbook.write(fos);
                  return allRows;
              }
          } catch (CsvException e) {
              throw new RuntimeException(e);
          }
      }
  */
    private static List<String[]> readCsvData(String csvFilePath) {
        List<String[]> csvData = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                csvData.add(line.split(","));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return csvData;
    }

}
