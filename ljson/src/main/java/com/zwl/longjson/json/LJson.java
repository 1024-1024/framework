package com.zwl.longjson.json;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by weilongzhang on 17/2/23.
 */

public class LJson {


    private static List<Field> allFileds;

    public static Object parseObject(String json, Class clazz) {
        Object object = null;
        Class<?> jsonClass = null;
        if (json.charAt(0) == '[') {

        } else if (json.charAt(0) == '{') {
            try {
                JSONObject jsonObject = new JSONObject(json);
                object = clazz.newInstance();
                Iterator<?> iterator = jsonObject.keys();
                while (iterator.hasNext()) {
                    String key = (String) iterator.next();
                    Object fieldValue = null;
                    List<Field> fields = getAllFileds(clazz, null);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }



    public static void toJson(Object o) {

        if (o instanceof List<?>) {
            StringBuilder json = new StringBuilder();
            List<?> list = (List<?>) o;
            json.append("[");
            for (int i = 0; i < list.size(); i++) {
                addObjectToJson(list.get(i));
            }
            json.append("]");
        } else {
            addObjectToJson(o);
        }
    }

    private static void addObjectToJson(Object o) {
        List<Field> fields = new ArrayList<Field>();
        while (!(o.getClass().getSuperclass() instanceof Object)) {
            for (Field field : o.getClass().getDeclaredFields()) {
                fields.add(field);
            }
            o = o.getClass().getSuperclass();
        }

        StringBuffer json = new StringBuffer();
        json.append("{");
        for (Field field : fields) {
            if (Modifier.isFinal(field.getModifiers())) {
                continue;
            }
            field.setAccessible(true);
            String fieldName = field.getName();
            String fieldType = field.getType().getName();
            Object value = new Object();
            try {
                value = field.get(o);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (String.class.getName().equals(fieldType)) {
                String fieldValue = (String) value;
                json.append(fieldName).append(":").append("\"").append(fieldValue).append("\"");
            } else if (Integer.class.getName().equals(fieldType) || Long.class.getName().equals
                    (fieldType) || Float.class.getName().equals(fieldType) || Double.class
                    .getName().equals(fieldType)) {
                Number number = (Number) value;
                json.append(fieldName).append(":").append(number);
            } else if (Boolean.class.getName().equals(fieldType)) {
                Boolean b = (Boolean) value;
                json.append(fieldName).append(":").append(b);
            } else if (List.class.getName().equals(fieldType)) {
                List<?> list = (List<?>) value;
                json.append("[");
                for (int i = 0; i < list.size(); i++) {
                    addObjectToJson(list.get(i));
                }
                json.append("]");
            } else if (fieldType.getClass().getSuperclass() instanceof Object) {
                addObjectToJson(value);
            }
            json.append(",");
        }
        json = (StringBuffer) json.subSequence(0, json.length() - 1);
        json.append("}");
    }

    public static List<Field> getAllFileds(Class clazz, Object o) {
        return allFileds;
    }
}
