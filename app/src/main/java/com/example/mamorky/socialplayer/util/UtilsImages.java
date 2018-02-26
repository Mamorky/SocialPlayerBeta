package com.example.mamorky.socialplayer.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.graphics.drawable.DrawableCompat;
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
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;

import static android.content.ContentValues.TAG;

import java.net.*;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.io.Reader.*;
import java.util.Random;

/**
 * Created by mamorky on 12/11/17.
 */

public class UtilsImages {
    static Random random = new Random();

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

        r = random.nextInt(200-100)+100;
        g = random.nextInt(200-100)+100;
        b = random.nextInt(200-100)+100;

        return Color.rgb(r,g,b);
    }

    public static byte[] getPictureByteOfArray(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static Bitmap getBitmapFromByte(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
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
