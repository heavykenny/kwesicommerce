package com.example.kwesicommerce.utils;

import static com.example.kwesicommerce.utils.FunctionUtil.generateProfileURL;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;

import com.example.kwesicommerce.data.model.Order;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.util.List;

public class FileUtil {
    public static void exportToFile(String filename, List<Order> orders) throws IOException {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), filename);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

        for (Order order : orders) {
            bufferedWriter.write("Order ID: " + order.getId() + ", User ID: " + order.getUserId() + ", Status: " + order.getStatus() + ", Date Created: " + order.getDateCreated() + ", Date Updated: " + order.getDateUpdated());
            bufferedWriter.newLine();
        }

        bufferedWriter.close();
        outputStreamWriter.close();
        fileOutputStream.close();
    }

    public static String saveImageToFile(Context context, Uri imageUri) {
        try {
            // Create a File object to represent the image file
            File imageFile = new File(context.getFilesDir(), generateProfileURL());

            // Create an InputStream from the image URI
            InputStream inputStream = context.getContentResolver().openInputStream(imageUri);

            // Create an OutputStream to write the image data to the file
            OutputStream outputStream = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                outputStream = Files.newOutputStream(imageFile.toPath());
            }

            // Copy the image data from the input stream to the output stream
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }

            // Close the streams
            outputStream.flush();
            outputStream.close();
            inputStream.close();

            return imageFile.getAbsolutePath();

            // Display a success message or perform further actions
        } catch (IOException e) {
            e.printStackTrace();
            // Handle any errors that occur during the saving process
        }

        return null;
    }


}