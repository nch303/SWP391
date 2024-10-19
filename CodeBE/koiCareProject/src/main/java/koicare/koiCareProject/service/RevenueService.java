package koicare.koiCareProject.service;

import koicare.koiCareProject.dto.response.PackageNumberResponse;
import koicare.koiCareProject.dto.response.RevenueResponse;
import koicare.koiCareProject.dto.response.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RevenueService {

    @Autowired
    TransactionService transactionService;

    public List<RevenueResponse> getRevenue() {

        List<TransactionResponse> transactionResponses = transactionService.viewAllTransaction();
        Map<String, Float> monthlyRevenue = new HashMap<>();

        for (TransactionResponse transactionResponse : transactionResponses) {
            Date transactionDate = transactionResponse.getDate();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(transactionDate);

            int month = calendar.get(Calendar.MONTH); // Lấy tháng từ ngày giao dịch
            int year = calendar.get(Calendar.YEAR);   // Lấy năm từ ngày giao dịch

            float price = transactionResponse.getPrice(); // Lấy giá trị giao dịch

            // Tạo key cho bản đồ theo định dạng "yyyy-MM"
            String key = year + "-" + (month + 1); // Tháng trong Calendar là 0-11, thêm 1 để chuyển đổi thành 1-12

            // Cộng dồn doanh thu vào bản đồ theo tháng và năm
            monthlyRevenue.put(key, monthlyRevenue.getOrDefault(key, 0f) + price);
        }

        // Tạo danh sách RevenueResponse để lưu doanh thu cho từng tháng
        List<RevenueResponse> revenueResponses = new ArrayList<>();

        for (Map.Entry<String, Float> entry : monthlyRevenue.entrySet()) {
            RevenueResponse revenueResponse = new RevenueResponse();
            String[] parts = entry.getKey().split("-");
            revenueResponse.setMonth(Integer.parseInt(parts[1])); // Lấy tháng
            revenueResponse.setYear(Integer.parseInt(parts[0]));  // Lấy năm
            revenueResponse.setRevenue(entry.getValue());
            revenueResponses.add(revenueResponse);
        }
        return revenueResponses;
    }

    public List<PackageNumberResponse> getPackage() {

        List<TransactionResponse> transactionResponses = transactionService.viewAllTransaction();
        // Khởi tạo bản đồ để lưu số lượng package theo năm-tháng và tên package
        Map<String, Map<String, PackageNumberResponse>> monthlyPackageCount = new HashMap<>();

        for (TransactionResponse transactionResponse : transactionResponses) {
            Date transactionDate = transactionResponse.getDate();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(transactionDate);

            int month = calendar.get(Calendar.MONTH) + 1; // Tháng trong Calendar là 0-11, thêm 1 để chuyển đổi thành 1-12
            int year = calendar.get(Calendar.YEAR);
            String packageName = transactionResponse.getApackage(); // Lấy tên package

            // Tạo key cho bản đồ theo định dạng "yyyy-MM"
            String yearMonthKey = year + "-" + month;

            // Nếu key chưa tồn tại trong bản đồ, khởi tạo một Map cho package
            monthlyPackageCount.putIfAbsent(yearMonthKey, new HashMap<>());

            // Lấy bản đồ của tháng và năm hiện tại
            Map<String, PackageNumberResponse> packageMap = monthlyPackageCount.get(yearMonthKey);

            // Nếu tên package chưa tồn tại trong bản đồ, khởi tạo một PackageNumberResponse mới
            if (!packageMap.containsKey(packageName)) {
                PackageNumberResponse packageResponse = new PackageNumberResponse();
                packageResponse.setMonth(month);
                packageResponse.setYear(year);
                packageResponse.setNumberOfPackage(0); // Khởi tạo số lượng package bằng 0
                packageResponse.setNameOfPackage(packageName); // Lưu tên package

                packageMap.put(packageName, packageResponse);
            }

            // Cộng dồn số lượng package vào bản đồ theo tháng, năm và tên package
            PackageNumberResponse packageResponse = packageMap.get(packageName);
            packageResponse.setNumberOfPackage(packageResponse.getNumberOfPackage() + 1); // Tăng số lượng package lên 1
        }

// Tạo danh sách để lưu kết quả
        List<PackageNumberResponse> packageResponses = new ArrayList<>();

// Duyệt qua bản đồ để thu thập kết quả
        for (Map<String, PackageNumberResponse> packageMap : monthlyPackageCount.values()) {
            packageResponses.addAll(packageMap.values());
        }
        return packageResponses;
    }

}
