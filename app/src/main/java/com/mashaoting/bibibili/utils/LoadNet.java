package com.mashaoting.bibibili.utils;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

/**
 * Created by 麻少亭 on 2017/3/22.
 */

public class LoadNet {

    public static void getDataPost(String url, final LoadNetHttp http) {

        AsyncHttpClient httpClient = new AsyncHttpClient();



        httpClient.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
               if(http != null) {
               }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("TAG", "LoadNet ++++++++++++++++++ getDataPost()");
                Log.e("TAG", "LoadNet onSuccess()"+error);
                Log.e("TAG", "LoadNet ++++++++++++++++++ getDataPost()");
            }
        });
    }



    private LoadNetHttp loadNetHttp ;
    public interface LoadNetHttp {

        void success(String context);
        void failure(String error);
    }
    public void setLoadNetHttp(LoadNetHttp loadNetHttp){
        this.loadNetHttp = loadNetHttp ;
    }


}
