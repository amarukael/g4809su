package utility;

import java.util.ArrayList;
import java.util.List;

public class StringUtil {
    public String leftPad(String s, int width) {
        return String.format("%" + width + "s", s).replace(" ", "0");
    }

    public String leftPadSpasi(String s, int width) {
        return String.format("%" + width + "s", s);
    }

    public String rightPad(String s, int width) {
        return String.format("%-" + width + "s", s);
    }

    public String rightPadZero(String s, int width) {
        return String.format("%-" + width + "s", s).replace(" ", "0");
    }

    public String justifyCenter(String str,
                                final int width,
                                char padWithChar) {
        // Trim the leading and trailing whitespace ...
        str = str != null ? str.trim() : "";

        int addChars = width - str.length();
        if (addChars < 0) {
            // truncate
            return str.subSequence(0, width).toString();
        }

        // Write the content ...
        int prependNumber = addChars / 2;
        int appendNumber = prependNumber;
        if ((prependNumber + appendNumber) != addChars) {
            ++prependNumber;
        }

        final StringBuilder sb = new StringBuilder();

        // Prepend the pad character(s) ...
        while (prependNumber > 0) {
            sb.append(padWithChar);
            --prependNumber;
        }

        // Add the actual content
        sb.append(str);

        // Append the pad character(s) ...
        while (appendNumber > 0) {
            sb.append(padWithChar);
            --appendNumber;
        }

        return sb.toString();
    }

    public String splitStringBy4(String inputData) {
        String result = "";
        List<String> strings = new ArrayList<String>();
        int index = 0;
        while (index < inputData.length()) {
            if (index == 0) result += inputData.substring(index, Math.min(index + 4, inputData.length()));
            else result += " " + inputData.substring(index, Math.min(index + 4, inputData.length()));
            index += 4;
        }

        return result;
    }
}
