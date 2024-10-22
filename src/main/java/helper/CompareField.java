package helper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
            if (fieldNameLowerCase.equals(fieldName.toLowerCase())) {
                return field;
            }
        }
        return null;
    }

    public static List<String> compareObjects(Object obj1, Object obj2, List<String> excludedFields,
            String nameValidate) {
        List<String> tmpMessage = new ArrayList<>();
        String tmpStr = "";
        Field[] fields1;
        Field[] fields2;
        tmpMessage.add("• Validation " + nameValidate + " : ");
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
                } else if (nameValidate.contains("Database")) {
                    if (fieldName1.equals("productid")) {
                        fieldName1 = "switchid";
                    } else if (fieldName1.equals("totalamount")) {
                        fieldName1 = "amount";
                    } else if (fieldName1.equals("additionaldatanew")) {
                        fieldName1 = "additionaldata";
                    }
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

    public static <T> String compare_value(List<T> list, String nameValidate) {
        if (list.isEmpty()) {
            return "- " +nameValidate + " is empty";
        }

        T firstElement = list.get(0);
        for (T element : list) {
            if (!element.equals(firstElement)) {
                return "- " +nameValidate + " is different";
            }
        }
        return "- " + nameValidate + " is equal";
    }

}
