package com.tommyputranto.photoiak;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tommy on 4/24/16.
 */
public class ThemovieDb {
    public byte[] getUrlBytes (String urlSpec) throws IOException {
            URL url = new URL(urlSpec);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();
            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead =in.read(buffer))>0){
                out.write(buffer, 0 ,bytesRead);
            }
            out.close();
            return out.toByteArray();
        }finally {
            connection.disconnect();
        }
    }
    public String getUrlString (String urlSpec) throws IOException {
        return new String(getUrlBytes(urlSpec));
    }

private static final String KEY = "YOUR KEY";

    public List<GalleryItem> fetchItem(){
        List<GalleryItem> items = new ArrayList<>();
        String url =
                Uri.parse("http://api.themoviedb.org/3/movie/popular")
                .buildUpon()
                .appendQueryParameter("api_key",KEY)
                .build().toString();
        try {
            String jsonString = getUrlString(url);
            Log.i("JsonString", jsonString);
            JSONObject jsonBody = new JSONObject(jsonString);
            parseItems(items, jsonBody);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return items;
    }

    private void parseItems (List<GalleryItem> items, JSONObject jsonBody)
            throws JSONException {
        JSONArray PhotoJsonArray = jsonBody.getJSONArray("results");
        for (int i = 0; i<PhotoJsonArray.length(); i++){
            JSONObject PhotoJsonObject = PhotoJsonArray.getJSONObject(i);
            GalleryItem item = new GalleryItem();
            item.setCaption(PhotoJsonObject.getString("poster_path"));
            Log.i("teslagi2 ", "http://image.tmdb.org/t/p/w185"+String.valueOf(item));
            String stringGambar = "http://image.tmdb.org/t/p/w185"+String.valueOf(item);

            items.add(item);
        }
    }
}
