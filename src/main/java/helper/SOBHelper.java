package helper;

import constant.SOBConstant;
import org.apache.commons.lang.StringUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class SOBHelper {
    private String envTest = SOBConstant.envTes;
    private String pathExtendProperties = "src/test/resources/extent.properties";

    public Map<String, String> getUserCredentials(String user) {
        Map<String, Map<String, String>> userCredentialsMap = new HashMap<>();

        // Define multiple sets of user credentials
        userCredentialsMap.put("test", createUserCredentials("Test", "asd123"));
        userCredentialsMap.put("mattsup", createUserCredentials("MattSup", "asd123"));
        userCredentialsMap.put("matt", createUserCredentials("Matt", "asd123"));
        userCredentialsMap.put("admin", createUserCredentials("admin", "Test_1234"));
        userCredentialsMap.put("adminqa", createUserCredentials("adminqa", "Test_1234"));
        userCredentialsMap.put("adminqa2", createUserCredentials("adminqa2", "Test_1234"));
        userCredentialsMap.put("rhesa", createUserCredentials("RhesaSlay", "rhesa1234"));
        userCredentialsMap.put("ryormd", createUserCredentials("ryormd", "Ryo@1234"));
        userCredentialsMap.put("ryosls", createUserCredentials("ryosls", "S0lusip4y!"));
        userCredentialsMap.put("pettycashpartner", createUserCredentials("pettycashpartner", "Pettycash_123"));
        userCredentialsMap.put("aksesmu", createUserCredentials("aksesmubas", "Aksesmu!23"));
        userCredentialsMap.put("adminstg", createUserCredentials("admin@gmail.com", "Test_1234"));

        // Return the user credentials based on the provided user identifier
        return userCredentialsMap.get(user);
    }

    private Map<String, String> createUserCredentials(String username, String password) {
        Map<String, String> userCredentials = new HashMap<>();
        userCredentials.put("Username", username);
        userCredentials.put("Password", password);
        return userCredentials;
    }

    public String[] getConstructFields(String flg, String type, String fMessageBody) {
        String[] result = new String[10];
        // Title
        if (flg.equals("title")) {
            if (type.equals("3_button_static_dynamic")) {
                result[0] = "meriah14..!! 🤩🤩,  ";

                return result;
            } else {
                result[0] = "1.2.3. horee!! 🤩🤩,  ";

                return result;
            }
        }

        // Body
        if (flg.equals("body")) {
            if (type.equals("text")) {
                if (fMessageBody.equals("normal")) {
                    result[0] = "[" + envTest + "]2024 ";
                    result[1] = "!! ";
                    result[2] = "40 ";
                    result[3] = "70 ";
                    result[4] = "23 January 🤩🤩!! ";

                    return result;
                } else {
                    result[0] = "[" + envTest + "]*_2024_* ";
                    result[1] = "~*!!*~ ";
                    result[2] = "~_40_~ ";
                    result[3] = "*```70```* ";
                    result[4] = "*~_```23```_~* _*~```January```~*_ 🤩🤩!! ";

                    return result;
                }
            }

            if (type.equals("image")) {
                result[0] = "[" + envTest + "]🤩🤩 ";
                result[1] = "60 ";
                result[2] = "30.000 ";
                result[3] = "70.000 ";

                return result;
            }

            if (type.equals("video")) {
                result[0] = "[" + envTest + "]🤩🤩 ";

                return result;
            }

            if (type.equals("document")) {
                result[0] = "[" + envTest + "]2024 ";
                result[1] = "!! ";
                result[2] = "40 ";
                result[3] = "70 ";
                result[4] = "23 January 🤩🤩!! ";

                return result;
            }

            if (type.equals("4_button_static_dynamic")) {
                result[0] = "[" + envTest + "]RP. 10.000 ";
                result[1] = "3 orang ";

                return result;
            }

            if (type.equals("3_button_static_dynamic")) {
                if (fMessageBody.equals("normal")) {
                    result[0] = "[" + envTest + "]30.000 ";
                    result[1] = "18:00 ";

                    return result;
                } else {
                    result[0] = "[ ```" + envTest + "``` ] *30.000* ~,00~ ";
                    result[1] = "_18:00_ ";

                    return result;
                }
            }
        }

        // Button
        if (flg.equals("btnWeb")) {
            result[0] = "tes ";

            return result;
        }

        if (flg.equals("btnCopyCode")) {
            result[0] = "12345 ";

            return result;
        }

        return result;
    }

    public String[] getConstructInvalidFields(String flg, String type) {
        String[] result = new String[10];
        if (flg.equals("title")) {
            result[0] = StringUtils.repeat("a", 60);

            return result;
        }

        if (flg.equals("body")) {
            result[0] = StringUtils.repeat("a", 1024);
            if (type.equals("text") ||
                    type.equals("document")) {
                result[1] = " ";
                result[2] = " ";
                result[3] = " ";
                result[4] = " ";

                return result;
            }

            if (type.equals("image")) {
                result[1] = " ";
                result[2] = " ";
                result[3] = " ";

                return result;
            }

            if (type.equals("4_button_static_dynamic") ||
                    type.equals("3_button_static_dynamic")) {
                result[1] = " ";

                return result;
            }
        }

        // Button
        if (flg.equals("btnWeb") || flg.equals("btnCopyCode")) {
            result[0] = StringUtils.repeat("a", 1030);

            return result;
        }

        return result;
    }

    public void delay(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void writeProperties(String propertiesKey, String propertiesValue) {
        try {
            Properties properties = new Properties();
            FileInputStream inputStream = new FileInputStream(pathExtendProperties);
            properties.load(inputStream);
            inputStream.close();

            properties.setProperty(propertiesKey, propertiesValue);

            FileOutputStream outputStream = new FileOutputStream(pathExtendProperties);
            properties.store(outputStream, null);
            outputStream.close();

            System.out.println("Data Username berhasil ditambahkan ke extent.properties.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readProperties(String propertiesKey) {
        try {
            Properties properties = new Properties();

            FileInputStream inputStream = new FileInputStream(pathExtendProperties);
            properties.load(inputStream);
            inputStream.close();

            String value = properties.getProperty(propertiesKey);

            if (value != null) {
                return value;
            } else {
                return "";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
