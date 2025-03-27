import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Json {
    public static void main(String[] args) {
        try {
            // Read the JSON file
            String content = new String(Files.readAllBytes(Paths.get("input.json")));
            JSONObject jsonObject = new JSONObject(content);

            // Extract first_name and roll_number
            String firstName = jsonObject.getJSONObject("student").getString("first_name").toLowerCase();
            String rollNumber = jsonObject.getJSONObject("student").getString("roll_number").toLowerCase();

            // Concatenate first_name and roll_number
            String concatenatedString = firstName + rollNumber;

            // Generate MD5 hash
            String md5Hash = generateMD5(concatenatedString);

            // Write the output to output.txt
            try (FileWriter writer = new FileWriter("output.txt")) {
                writer.write(md5Hash);
            }

            System.out.println("MD5 Hash generated and written to output.txt: " + md5Hash);
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private static String generateMD5(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(input.getBytes());
        
        // Convert byte array to hexadecimal format
        StringBuilder hexString = new StringBuilder();
        for (byte b : messageDigest) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}