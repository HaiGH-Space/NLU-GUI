package org.haigh.model;

import com.google.gson.Gson;
import haigh.api.nlu.API;
import haigh.api.nlu.util.ConstantUtil;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Instance {
    public static final Gson gson = new Gson();
    public static final API API = new API();
    public static final Account ACCOUNT = readAccountJSON();

    public static boolean writeAccountJSON(String username,String password) {
        ACCOUNT.setUsername(username);
        ACCOUNT.setPassword(password);
        String dbDir = "db";
        String outputFilePath = Paths.get(dbDir, "account.json").toString();

        // Ghi nội dung vào file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            writer.write(gson.toJson(ACCOUNT));
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    public static Account readAccountJSON() {
        String dbDir = "db";
       try {
            String json = new String(Files.readAllBytes(Paths.get(dbDir, "account.json")));
            return gson.fromJson(json,Account.class);
        }catch (Exception e) {
           e.printStackTrace();
       }
        return null;
    }
}
