package koicare.koiCareProject.service;

import koicare.koiCareProject.dto.response.PackageNumberResponse;
import koicare.koiCareProject.dto.response.RevenueResponse;
import koicare.koiCareProject.dto.response.TransactionResponse;
import koicare.koiCareProject.enums.TransactionsEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

import java.util.*;

@Service
public class RevenueService {

    @Autowired
    TransactionService transactionService;

    public List<RevenueResponse> getRevenue() {

        List<TransactionResponse> transactionResponses = transactionService.viewAllTransaction();
        Map<String, Float> monthlyRevenue = new HashMap<>();

        for (TransactionResponse transactionResponse : transactionResponses) {
            if (transactionResponse.getStatus().equals(TransactionsEnum.SUCCESS.toString())) {
                Date transactionDate = transactionResponse.getDate();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(transactionDate);

                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                float price = transactionResponse.getPrice();

                String key = year + "-" + (month + 1);

                monthlyRevenue.put(key, monthlyRevenue.getOrDefault(key, 0f) + price);
            }

        }

        // Tạo danh sách RevenueResponse để lưu doanh thu cho từng tháng
        List<RevenueResponse> revenueResponses = new ArrayList<>();

        for (Map.Entry<String, Float> entry : monthlyRevenue.entrySet()) {
            RevenueResponse revenueResponse = new RevenueResponse();
            String[] parts = entry.getKey().split("-");
            revenueResponse.setMonth(Integer.parseInt(parts[1]));
            revenueResponse.setYear(Integer.parseInt(parts[0]));
            revenueResponse.setRevenue(entry.getValue());
            revenueResponses.add(revenueResponse);
        }
        return revenueResponses;
    }

    public List<PackageNumberResponse> getShopPackage() {

        List<TransactionResponse> transactionResponses = transactionService.viewAllTransaction();
        Map<String, PackageNumberResponse> packageCountMap = new HashMap<>();

        for (TransactionResponse transactionResponse : transactionResponses) {
            String packageName = transactionResponse.getApackage();
            Date transactionDate = transactionResponse.getDate();

            //Lấy tháng và năm từ Date
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(transactionDate);
            int month = calendar.get(Calendar.MONTH) + 1;
            int year = calendar.get(Calendar.YEAR);

            //Tạo khóa dựa trên tên package, tháng và năm
            String key = packageName + "-" + month + "-" + year;

            if (packageName.contains("-sh")) {
                packageCountMap.putIfAbsent(key, new PackageNumberResponse(packageName, 0, month, year));

                PackageNumberResponse packageResponse = packageCountMap.get(key);
                packageResponse.setNumberOfPackage(packageResponse.getNumberOfPackage() + 1);
            }
        }

        return new ArrayList<>(packageCountMap.values());
    }


    public List<PackageNumberResponse> getMemberPackage() {

        List<TransactionResponse> transactionResponses = transactionService.viewAllTransaction();
        Map<String, PackageNumberResponse> packageCountMap = new HashMap<>();

        for (TransactionResponse transactionResponse : transactionResponses) {
            String packageName = transactionResponse.getApackage();
            Date transactionDate = transactionResponse.getDate();

            //Lấy tháng và năm từ Date
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(transactionDate);
            int month = calendar.get(Calendar.MONTH) + 1;
            int year = calendar.get(Calendar.YEAR);

            //Tạo khóa dựa trên tên package, tháng và năm
            String key = packageName + "-" + month + "-" + year;

            if (packageName.contains("-me")) {
                packageCountMap.putIfAbsent(key, new PackageNumberResponse(packageName, 0, month, year));

                PackageNumberResponse packageResponse = packageCountMap.get(key);
                packageResponse.setNumberOfPackage(packageResponse.getNumberOfPackage() + 1);
            }
        }

        return new ArrayList<>(packageCountMap.values());
    }
}
