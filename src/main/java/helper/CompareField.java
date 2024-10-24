package helper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;

import com.fasterxml.jackson.databind.JsonNode;

import io.cucumber.java.Scenario;

public class CompareField {

    public static List<String> compareObjects(Object obj1, Object obj2, List<String> excludedFields,
            Scenario scenario) {
        List<String> listStringValidation = new ArrayList<>();
        Field[] fields1;
        Field[] fields2;

        try {

            // Get all fields from both objects
            fields1 = obj1.getClass().getDeclaredFields();
            fields2 = obj2.getClass().getDeclaredFields();

            // Iterate through fields of obj1
            for (Field field1 : fields1) {
                field1.setAccessible(true); // To access private fields

                String fieldName1 = field1.getName();

                // Skip fields in the exclusion list
                if (excludedFields != null && excludedFields.contains(fieldName1)) {
                    scenario.log("Skipping field: " + fieldName1);
                    continue; // Skip this field
                }

                Object value1 = field1.get(obj1);

                // Check if obj2 has the same field name
                Field field2 = getFieldByName(fields2, fieldName1);

                scenario.log("Starting to read " + fieldName1 + "-" + value1);
                if (field2 != null) {
                    field2.setAccessible(true);
                    Object value2 = field2.get(obj2);

                    scenario.log("Starting to COMPARE " + value2 + "-" + value1);

                    // assertEquals(value2, value1);

                    // Compare values if fields have the same name
                    if (value1 != null) {
                        String message = "Fields " + fieldName1 + " are equal: " + value1;
                        scenario.log(message);
                    } else {
                        String message = "Fields " + fieldName1 + " are different.";
                        scenario.log(message);
                        listStringValidation.add(message);

                    }
                }
            }
            return listStringValidation;
        } catch (IllegalAccessException e) {
            listStringValidation.add("error :" + e);
        }

        return listStringValidation;
    }

    // public static void compareObjects(Object obj1, Object obj2, Scenario
    // scenario) throws IllegalAccessException {
    // Field[] fields1;
    // Field[] fields2;
    //
    // // Get all fields from both objects
    // fields1 = obj1.getClass().getDeclaredFields();
    // fields2 = obj2.getClass().getDeclaredFields();
    //
    // // Iterate through fields of obj1
    // for (Field field1 : fields1) {
    // field1.setAccessible(true); // To access private fields
    //
    // String fieldName1 = field1.getName();
    // Object value1 = field1.get(obj1);
    //
    // // Check if obj2 has the same field name
    // Field field2 = getFieldByName(fields2, fieldName1);
    //
    // scenario.log("Starting to read " + fieldName1 + "-" + value1);
    // if (field2 != null) {
    // field2.setAccessible(true);
    // Object value2 = field2.get(obj2);
    //
    // scenario.log("Starting to COMPARE " + value2 + "-" + value1);
    //
    // assertEquals(value2, value1);
    //
    // // Compare values if fields have the same name
    // if (value1 != null) {
    // scenario.log("Fields " + fieldName1 + " are equal: " + value1);
    // } else {
    // scenario.log("Fields " + fieldName1 + " are different.");
    // }
    // }
    // }
    // }

    public static void compareObjectLists(List<?> listA, List<?> listB, Scenario scenario)
            throws IllegalAccessException {
        for (Object objA : listA) {
            Field labelFieldA = getFieldByName(objA, "label");
            Field valueFieldA = getFieldByName(objA, "value");

            if (labelFieldA == null || valueFieldA == null) {
                scenario.log("Fields 'label' or 'value' not found in object A.");
                continue;
            }

            labelFieldA.setAccessible(true);
            valueFieldA.setAccessible(true);
            Object labelA = labelFieldA.get(objA);
            Object valueA = valueFieldA.get(objA);

            scenario.log("Comparing object from List A with label: " + labelA);

            boolean foundMatch = false;

            for (Object objB : listB) {
                Field labelFieldB = getFieldByName(objB, "label");
                Field valueFieldB = getFieldByName(objB, "value");

                if (labelFieldB == null || valueFieldB == null) {
                    scenario.log("Fields 'label' or 'value' not found in object B.");
                    continue;
                }

                labelFieldB.setAccessible(true);
                valueFieldB.setAccessible(true);
                Object labelB = labelFieldB.get(objB);
                Object valueB = valueFieldB.get(objB);

                // Compare by 'label' first
                if (labelA != null && labelA.equals(labelB)) {
                    foundMatch = true;
                    // Now compare 'value'
                    scenario.log("Comparing 'value' field: " + valueA + " vs " + valueB);

                    if (valueA == null) {
                        if (valueB != null) {
                            scenario.log("Values are different: null vs " + valueB);
                        }
                    } else if (!valueA.equals(valueB)) {
                        scenario.log("Values are different: " + valueA + " vs " + valueB);
                    } else {
                        scenario.log("Values are equal: " + valueA);
                    }
                    break; // Exit the inner loop once a match is found
                }
            }

            if (!foundMatch) {
                scenario.log("No matching object found in List B with label: " + labelA);
            }
        }
    }

    private static Field getFieldByName(Object obj, String fieldName) {
        try {
            return obj.getClass().getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            return null;
        }
    }

    private static Field getFieldByName(Field[] fields, String fieldName) {
        for (Field field : fields) {
            String fieldNameLowerCase = field.getName().toString().toLowerCase();
            if (fieldNameLowerCase.equals(fieldName.toString().toLowerCase())) {
                return field;
            }
        }
        return null;
    }

    private static String getFieldByName(JsonNode node, String fieldName) {
        Iterator<Entry<String, JsonNode>> fields = node.fields();
        while (fields.hasNext()) {
            Entry<String, JsonNode> field = fields.next();
            String fieldName2 = field.getKey().toString().toLowerCase();
            if (fieldName.equals(fieldName2)) {
                return field.getKey();
            }
        }
        return fieldName;
    }

    public static List<String> compareObjects(Object obj1, Object obj2, List<String> excludedFields) {
        List<String> tmpMessage = new ArrayList<>();
        String tmpStr = "";
        Field[] fields1;
        Field[] fields2;
        try {
            fields1 = obj1.getClass().getDeclaredFields();
            fields2 = obj2.getClass().getDeclaredFields();
            for (Field field1 : fields1) {
                field1.setAccessible(true);

                String fieldName1 = field1.getName().toString();
                String value1 = "";

                if (field1.get(obj1) != null) {
                    value1 = field1.get(obj1).toString();
                }

                if (excludedFields != null && excludedFields.contains(fieldName1)) {
                    continue;
                } else if (fieldName1.equals("additionaldata") &&
                        (value1.equals("[]") || value1.equals(""))) {
                    continue;
                }

                Field field2 = getFieldByName(fields2, fieldName1);
                if (field2 != null) {
                    field2.setAccessible(true);
                    String value2 = field2.get(obj2).toString();
                    tmpStr = " - Fields " + fieldName1 + " are equal";

                    if (!Objects.equals(value1, value2)) {
                        tmpStr = " - Fields " + fieldName1 + " are different.";
                        System.out.println("Different: " + value2 + " - " + value1);
                    }
                    tmpMessage.add(tmpStr);
                }
            }
        } catch (IllegalAccessException e) {
            tmpMessage.add("Error: " + e.getMessage());
        }
        return tmpMessage;
    }

    public static List<String> compareJson(JsonNode node1, JsonNode node2, List<String> excludedFields) {
        List<String> tmpMessage = new ArrayList<>();
        try {
            String tmpStr = "";
            Iterator<Entry<String, JsonNode>> fields = node1.fields();
            while (fields.hasNext()) {
                Entry<String, JsonNode> field = fields.next();
                String fieldName = field.getKey().toString().toLowerCase();
                String value1 = field.getValue().toString().replace("\"", "");

                if (excludedFields != null && excludedFields.contains(fieldName)) {
                    continue;
                }

                fieldName = getFieldByName(node2, fieldName);
                if (fieldName != null && node2.has(fieldName)) {
                    String value2 = node2.get(fieldName).toString().replace("\"", "");
                    if (value1.equals(value2)) {
                        tmpStr = " - Fields " + fieldName + " are equal";
                    } else {
                        tmpStr = " - Fields " + fieldName + " are different.";
                        System.out.println(
                                "Field '" + fieldName + "' memiliki nilai yang berbeda: " + value1 + " vs " +
                                        value2);
                    }
                    tmpMessage.add(tmpStr);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tmpMessage;
    }

    public static <T> String compare_value(List<T> list, String nameValidate) {
        if (list.isEmpty()) {
            return "- " + nameValidate + " is empty";
        }

        T firstElement = list.get(0);
        for (T element : list) {
            if (!element.equals(firstElement)) {
                return "- " + nameValidate + " is different";
            }
        }
        return "- " + nameValidate + " is equal";
    }

}
