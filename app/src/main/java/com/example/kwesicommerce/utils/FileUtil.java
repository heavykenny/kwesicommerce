package com.example.kwesicommerce.utils;

import android.os.Environment;

import com.example.kwesicommerce.data.model.Order;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

public class FileUtil {
    public static void exportToFile(String filename, List<Order> orders) throws IOException {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), filename);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

        for (Order order : orders) {
            bufferedWriter.write("Order ID: " + order.getId() + ", User ID: " + order.getUserId() + ", Product ID: " + order.getProductId() + ", Quantity: " + order.getQuantity() + ", Status: " + order.getStatus() + ", Date Created: " + order.getDateCreated() + ", Date Updated: " + order.getDateUpdated());
            bufferedWriter.newLine();
        }

        bufferedWriter.close();
        outputStreamWriter.close();
        fileOutputStream.close();
    }
}