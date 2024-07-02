package uz.project.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.project.service.conditon.AdminStatisticInterface;
import uz.project.util.AppConstants;

import java.io.IOException;
import java.util.List;

/**
 * Authors: Hamdamboy & Hudoberdi
 * Date: 01.07.2024
 * Time: 9:45
 * Project: service
 */
@Getter
@RestController
@RequestMapping("/api/get/")
@RequiredArgsConstructor
public class EgovStatsController {
    private final AdminStatisticInterface adminStatisticInterface;

    @PostMapping("daily")
    public ResponseEntity<List<String[]>> getDailyStats(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") String repDate,
                                                        @RequestParam(value = "api_name") String api_name,
                                                        @RequestParam(value = "pageNo",
                                                                defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
                                                        @RequestParam(value = "pageSize",
                                                                defaultValue = AppConstants.DEFAULT_PAGE_SIZE,
                                                                required = false) int pageSize) {
        System.out.println(repDate);
        return ResponseEntity.ok(adminStatisticInterface.getDailyStatsEgov(repDate, api_name, pageNo, pageSize).getEgovResponses());
    }

    @PostMapping("monthly")
    public ResponseEntity<List<String[]>> getMonthlyStats(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") String fromDate,
                                                          @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") String toDate,
                                                          @RequestParam(value = "pageNo",
                                                                  defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
                                                          @RequestParam(value = "pageSize",
                                                                  defaultValue = AppConstants.DEFAULT_PAGE_SIZE,
                                                                  required = false) int pageSize) throws IOException {
        return ResponseEntity.ok(adminStatisticInterface.getMonthlyAllServiceStatsEgov(fromDate, toDate, pageNo, pageSize).getEgovResponses());
    }
}