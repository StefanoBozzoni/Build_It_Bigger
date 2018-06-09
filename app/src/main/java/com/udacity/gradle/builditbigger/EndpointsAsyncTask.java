package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Pair;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.IdlingResource.SimpleIdlingResource;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

class EndpointsAsyncTask extends AsyncTask<String, Void, String> {
    SimpleIdlingResource mIdlingResource;

    interface EndpointCallBack {
        public void onCallBack(String strToDisplay);
    }

    private static MyApi myApiService = null;
    //private Context mContext;
    private EndpointCallBack mCallBack;


    EndpointsAsyncTask(EndpointCallBack callBack,SimpleIdlingResource anIdlingResource) {
        mCallBack=callBack;
        mIdlingResource=anIdlingResource;
    }

    @SafeVarargs
    @Override
    protected final String doInBackground(String... string) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }


        try {
            //return myApiService.sayHi(name).execute().getData();
            //return myApiService.sayHi(name).execute().getData();
            mIdlingResource.setIdleState(false);
            return  myApiService.getApiJoke().execute().getData();


        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        mIdlingResource.setIdleState(true);
        //Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        mCallBack.onCallBack(result);
        mIdlingResource.setIdleState(true);
    }
}