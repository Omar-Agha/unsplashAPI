package com.example.android.test_application;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.android.test_application.api.UnsplashApi;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    private ProgressBar progressBarQuerying;
    private EditText editTextQuery;
    private RecyclerView recyclerViewPhotos;
    private PhotosAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBarQuerying = findViewById(R.id.progress_bar);
        editTextQuery = findViewById(R.id.query_et);
        recyclerViewPhotos = findViewById(R.id.photos_rc);
        recyclerViewPhotos.setLayoutManager(new GridLayoutManager(this,3));
        mAdapter = new PhotosAdapter();
        recyclerViewPhotos.setAdapter(mAdapter);
    }

    public void photoQueryClick(View view) {
        String query = editTextQuery.getText().toString().trim();
        URL url = UnsplashApi.lookForListOfPhotosUrl(query);
        new LoadPhotosTask().execute(url);

    }


    class LoadPhotosTask extends AsyncTask<URL, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBarQuerying.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(URL... urls) {
            try {
                Log.i(TAG, "doInBackground: url is " + urls[0].toString());
                return NetworkUtils.getResponse(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String responseJson) {
            super.onPostExecute(responseJson);
            progressBarQuerying.setVisibility(View.INVISIBLE);
            Log.i(TAG, "onPostExecute: response is  " + responseJson);
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Photo>>() {
            }.getType();

            List<Photo> response = gson.fromJson(responseJson, listType);

            mAdapter.setPhotos(response);
            Toast.makeText(MainActivity.this, "results : "+response.size(), Toast.LENGTH_SHORT).show();

        }

    }
}


