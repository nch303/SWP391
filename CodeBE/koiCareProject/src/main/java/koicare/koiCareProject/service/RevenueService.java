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

    public List<PackageNumberResponse> getShopPackage() {

        List<TransactionResponse> transactionResponses = transactionService.viewAllTransaction();
        // Khởi tạo bản đồ để lưu tổng số lượng package theo tên package
        Map<String, PackageNumberResponse> packageCountMap = new HashMap<>();

        for (TransactionResponse transactionResponse : transactionResponses) {
            String packageName = transactionResponse.getApackage(); // Lấy tên package

            // Kiểm tra nếu tên package chứa "-sh"
            if (packageName.contains("-sh")) {
                // Nếu tên package chưa tồn tại trong bản đồ, khởi tạo một PackageNumberResponse mới
                packageCountMap.putIfAbsent(packageName, new PackageNumberResponse(packageName, 0));

                // Lấy PackageNumberResponse hiện tại và tăng số lượng package lên 1
                PackageNumberResponse packageResponse = packageCountMap.get(packageName);
                packageResponse.setNumberOfPackage(packageResponse.getNumberOfPackage() + 1);
            }
        }

        // Trả về danh sách các PackageNumberResponse chỉ chứa các package có "-sh" trong tên
        return new ArrayList<>(packageCountMap.values());
    }

    public List<PackageNumberResponse> getMemberPackage() {

        List<TransactionResponse> transactionResponses = transactionService.viewAllTransaction();
        // Khởi tạo bản đồ để lưu tổng số lượng package theo tên package
        Map<String, PackageNumberResponse> packageCountMap = new HashMap<>();

        for (TransactionResponse transactionResponse : transactionResponses) {
            String packageName = transactionResponse.getApackage(); // Lấy tên package

            // Kiểm tra nếu tên package chứa "-sh"
            if (packageName.contains("-me")) {
                // Nếu tên package chưa tồn tại trong bản đồ, khởi tạo một PackageNumberResponse mới
                packageCountMap.putIfAbsent(packageName, new PackageNumberResponse(packageName, 0));

                // Lấy PackageNumberResponse hiện tại và tăng số lượng package lên 1
                PackageNumberResponse packageResponse = packageCountMap.get(packageName);
                packageResponse.setNumberOfPackage(packageResponse.getNumberOfPackage() + 1);
            }
        }

        // Trả về danh sách các PackageNumberResponse chỉ chứa các package có "-sh" trong tên
        return new ArrayList<>(packageCountMap.values());
    }


}
