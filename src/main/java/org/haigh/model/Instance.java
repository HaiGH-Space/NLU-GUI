package org.haigh.model;

import com.google.gson.Gson;
import haigh.api.nlu.API;
import haigh.api.nlu.util.ConstantUtil;

import java.io.*;


public class Instance {
    public static final Gson gson = new Gson();
    public static final API API = new API();
    public static Account ACCOUNT = readAccountJSON();

    public static boolean writeAccountJSON(String username,String password) {
        if (ACCOUNT==null) {
            ACCOUNT = new Account();
        }
        ACCOUNT.setUsername(username);
        ACCOUNT.setPassword(password);
        String dbDir = "account.json";
        File file = new File(dbDir);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
            }
        }
        // Ghi nội dung vào file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(dbDir))) {
            writer.write(gson.toJson(ACCOUNT));
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    public static Account readAccountJSON() {
        String dbDir = "account.json";
        File file = new File(dbDir);
        if (file.exists()) {
            String json = "";
            try (BufferedReader reader = new BufferedReader(new FileReader(dbDir))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    json += line;
                }
                return gson.fromJson(json, Account.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
