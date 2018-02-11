package com.example.mamorky.socialplayer.util;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.mamorky.socialplayer.ui.base.BaseContext;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;

import static android.content.ContentValues.TAG;

import java.net.*;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.io.Reader.*;

/**
 * Created by mamorky on 12/11/17.
 */

public class UtilsImages {
    public static RoundedBitmapDrawable roundImages(Drawable drawable, int corner) {
        Bitmap bitmap = (((BitmapDrawable)drawable).getBitmap());
        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(BaseContext.getContext().getResources(),bitmap);
        roundedBitmapDrawable.setCornerRadius(bitmap.getHeight()/corner);

        return roundedBitmapDrawable;
    }

    public static RoundedBitmapDrawable roundImages(ImageView imgView,int imagePath){
        Drawable drawable = imgView.getResources().getDrawable(imagePath);
        Bitmap bitmap = (((BitmapDrawable)drawable).getBitmap());
        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(imgView.getResources(),bitmap);
        roundedBitmapDrawable.setCornerRadius(bitmap.getHeight());

        return roundedBitmapDrawable;
    }

    public static int colorAlea(){
        int r;
        int g;
        int b;

        r = (int) Math.round(Math.random()*255);
        g = (int) Math.round(Math.random()*255);
        b = (int) Math.round(Math.random()*255);

        return Color.rgb(r,b,g);
    }

    /*
    public static String ObtainStringImage(JSONObject texto) throws JSONException {
        JSONObject item;
        JSONArray jOImages = texto.getJSONArray("items");

        String imageUrl = new String();

        item = jOImages.getJSONObject(0);
        imageUrl = item.getString("link");

        return imageUrl;
    }


    public static JSONObject getJson(String url){
        InputStream is = null;
        String result = "";
        JSONObject jsonObject = null;

        // HTTP
        try {
            HttpClient httpclient = new DefaultHttpClient(); // for port 80 requests!
            HttpPost httppost = new HttpPost(url);
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
        } catch(Exception e) {
            return null;
        }

        // Read response to string
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is,"utf-8"),8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            result = sb.toString();
        } catch(Exception e) {
            return null;
        }

        // Convert string to object
        try {
            jsonObject = new JSONObject(result);
        } catch(JSONException e) {
            return null;
        }

        return jsonObject;
    }*/

    public static void putImageCover(String name, final ImageView imageView) {
        String CANAL = "https://www.googleapis.com/customsearch/v1?q="+name+"&key=AIzaSyDUnt2jiwl_ol1vCnPVk11BndsRAjfq4T8&cx=003846337453369369833:poxrllvkcwe&searchType=image";

        RequestQueue mRequestQueue = null;

        if(mRequestQueue == null)
            mRequestQueue = MySingleton.getInstance(BaseContext.getContext()).getRequestQueue();

        final JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, CANAL, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject item;
                            JSONArray jOImages = response.getJSONArray("items");

                            String imageUrl = new String();

                            item = jOImages.getJSONObject(0);
                            imageUrl = item.getString("link");

                            Picasso.with(BaseContext.getContext()).load(imageUrl).into(imageView);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(BaseContext.getContext(),error.hashCode()+":"+error.getCause(),Toast.LENGTH_SHORT).show();
                    }
                });

        // Set the tag on the request.
        jsObjRequest.setTag(TAG);
        // Set retry policy
        jsObjRequest.setRetryPolicy(new DefaultRetryPolicy(3000, 1, 1));

        // Add the request to the RequestQueue.
        mRequestQueue.add(jsObjRequest);
    }
}
