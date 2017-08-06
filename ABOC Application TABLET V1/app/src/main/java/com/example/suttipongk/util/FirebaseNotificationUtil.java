package com.example.suttipongk.util;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import android.os.StrictMode;

/**
 * Created by Suttipong.k on 3/31/2017.
 */

public class FirebaseNotificationUtil {

    String applicationTag = "Aboc";
    private Context context;

    public String pushFCMNotification(String reqNumber, String tokenId, String authKeyFcm, String apiUrlFcm, String queueNo, String nameAndSurname, String tel, String email, String office, String date) {
        String responsePushFCMNotification = "";
        try {

            if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }

            String authKey = authKeyFcm;      // You FCM AUTH key
            String FMCurl = apiUrlFcm;        // URL SERVER

            URL url = new URL(FMCurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setUseCaches(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "key=" + authKey);
            conn.setRequestProperty("Content-Type", "application/json");

            //JSON REQUEST
            JSONObject data = new JSONObject();

            //HEADER
            JSONObject info = new JSONObject();
            info.put("title", "ABOC Customer Service");                 //หัวข้อการแจ้งซ่อม
            info.put("body", "Request Number : " + queueNo);            //หมายเลขคำขอแจ้งซ่อม

            //DATA
//            JSONObject subInfo = new JSONObject();
//            subInfo.put("queueNo", queueNo);
//            subInfo.put("nameAndSurname", nameAndSurname);
//            subInfo.put("tel", tel);
//            subInfo.put("email", email);
//            subInfo.put("office", office);
//            subInfo.put("date", date);

//            JSONObject jso1 = new JSONObject();
//            jso1.put("icon", "test");
//            jso1.put("title", "ยืนยัน");
//            jso1.put("callback", "window.actions_left");
//
//            JSONObject jso2 = new JSONObject();
//            jso2.put("icon", "test");
//            jso2.put("title", "ยกเลิก");
//            jso2.put("callback", "window.actions_right");
//
//            JSONArray json = new JSONArray();
//            json.put(jso1);
//            json.put(jso2);
//
//            info.put("actions",json);
            data.put("notification", info);
            data.put("to", tokenId);          //ได้จากการส่อง QR Code ของบริษัท เพื่อส่งข้อมูลไปหาแผนกช่าง

            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(data.toString());
            wr.flush();
            wr.close();

            int responseCode = conn.getResponseCode();
            Log.d(applicationTag, "Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
                Log.d(applicationTag, "Response Message : " + response);
                responsePushFCMNotification = response.toString();
            }
            in.close();
        } catch (Exception e){
            e.printStackTrace();
        }

        return responsePushFCMNotification;
    }
}
