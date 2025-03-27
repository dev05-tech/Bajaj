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
         
            String content = new String(Files.readAllBytes(Paths.get("input.json")));
            JSONObject jsonObject = new JSONObject(content);

          
            String firstName = jsonObject.getJSONObject("student").getString("first_name").toLowerCase();
            String rollNumber = jsonObject.getJSONObject("student").getString("roll_number").toLowerCase();

            String concatenatedString = firstName + rollNumber;

        
            String md5Hash = generateMD5(concatenatedString);

         
            try (FileWriter writer = new FileWriter("output.txt")) {
                writer.write(md5Hash);
            }

            System.out.println("MD5 Hash generated and written to output.txt: " + md5Hash);
        } 
        catch (IOException | NoSuchAlgorithmException e) 
            {
            e.printStackTrace();
        }
    }

    private static String generateMD5(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(input.getBytes());
 
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
