package utility;

//import com.itextpdf.text.Document;
//import com.itextpdf.text.Image;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;

public class Helper {
    private static final String num = "0123456789";
    private static SecureRandom rnd = new SecureRandom();

    public static byte[] takeScreenshot(WebDriver driver) {
        // Convert WebDriver instance to TakeScreenshot
        TakesScreenshot screenshot = (TakesScreenshot) driver;

        // Capture screenshot as byte array
        byte[] srcFile = screenshot.getScreenshotAs(OutputType.BYTES);

        System.out.println("Screenshot captured successfully");

        return srcFile;
    }

//    public static void addImageToPDF(Document document, byte[] srcFile) {
//        try {
//            // Create an Image instance from the byte array
//            Image img = Image.getInstance(srcFile);
//            img.scaleToFit(650, 650); // Adjust image size as needed
//            img.setAbsolutePosition(110, 200);
//
//            document.add(img);
//            document.newPage(); // Add a new page for the rest of the content
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public static String getCurrentDirectory() {
        return System.getProperty("user.dir");
    }

    public static String buildFilePathInTargetPDF(String fileName) {
        Path currentDirectory = Paths.get(getCurrentDirectory());
        Path filePath = currentDirectory.resolve(Paths.get("target", "PDF", fileName));
        return filePath.toString();
    }

    public static String check_file_exist(String filename)
    {
        String home = System.getProperty("user.home");
        String file_name = filename;
        String file_with_location = home + "\\Downloads\\" + file_name;
        System.out.println("Function Name ===========================" + home + "\\Downloads\\" + file_name);
        File file = new File(file_with_location);
        if (file.exists()) {
            System.out.println(file_with_location + " is present");
            String result = "File Present";
            return result;
        } else {
            System.out.println(file_with_location + " is not present");
            String result = "File not Present";
            String result1 = result;
            return result1;
        }
    }

    public static String randomString2(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(num.charAt(rnd.nextInt(num.length())));

        return sb.toString();
    }

    public static String generateRandomString(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be positive.");
        }

        StringBuilder sb = new StringBuilder(length);
        SecureRandom random = new SecureRandom();

        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String symbols = "!@#$%^&*()-_=+[{]}\\|;:'\",<.>/?";

        sb.append(upper.charAt(random.nextInt(upper.length())));
        sb.append(lower.charAt(random.nextInt(lower.length())));
        sb.append(symbols.charAt(random.nextInt(symbols.length())));
        sb.append(digits.charAt(random.nextInt(digits.length())));

        for (int i = 4; i < length; i++) {
            String randomSource = getRandomSource(upper, lower, digits, symbols, random.toString());
            sb.append(randomSource.charAt(random.nextInt(randomSource.length())));
        }

        for (int i = sb.length() - 1; i > 0; i--) {
            int swapIndex = random.nextInt(i + 1);
            char temp = sb.charAt(swapIndex);
            sb.setCharAt(swapIndex, sb.charAt(i));
            sb.setCharAt(i, temp);
        }

        return sb.toString();
    }

    private static String getRandomSource(String... sources) {
        SecureRandom random = new SecureRandom();
        return sources[random.nextInt(sources.length)];
    }

    public static void sleep(int milliSecond){
        try {
            Thread.sleep(milliSecond);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
