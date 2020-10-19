package com.ss.apitesting.util;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadJson {

    public String readOneObjectFromJsonArray(String path, int indexOfObject){
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = null;
        try {
                Object obj = jsonParser.parse(new FileReader(path));
                JSONArray jsonObjects = (JSONArray) obj;
                jsonObject = (JSONObject) jsonObjects.get(indexOfObject);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
            return jsonObject.toJSONString();
    }

    public String readAllObjectFromJsonArray(String path){
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = null;
        try {
                jsonArray = (JSONArray) jsonParser.parse(new FileReader(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
            return jsonArray.toJSONString();
    }
    /**
     * Get one user from json file
     * @param indexOfObject - user's index in json file
     * @return one user
     */
    public String getUser(int indexOfObject) {
        return readOneObjectFromJsonArray("src/test/java/com/ss/apitesting/Data/TestUsersData.json", indexOfObject);
    }
    /**
     * Get user's array from json file
     * @return array of user
     */
    public String getArrayOfUsers() {
        return readAllObjectFromJsonArray("src/test/java/com/ss/apitesting/Data/TestUsersData.json");
    }
}
