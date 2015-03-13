package net.pawelhajduk.daggerdemo.data;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;

import net.pawelhajduk.daggerdemo.data.prefs.BooleanPreference;

import java.io.File;
import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {
    static final int DISK_CACHE_SIZE = 50 * 1024 * 1024; // 50MB

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences(Application app) {
        return app.getSharedPreferences("daggerdemo", Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    @UseMockBackend
    BooleanPreference provideUseMockBackend(SharedPreferences sharedPreferences) {
        return new BooleanPreference(sharedPreferences, "use_mock", false);
    }

    public static OkHttpClient createOkHttpClient(Application app) {
        OkHttpClient client = new OkHttpClient();
        // Install an HTTP cache in the application cache directory.
        try {
            File cacheDir = new File(app.getCacheDir(), "http");

            Cache cache = new Cache(cacheDir, DISK_CACHE_SIZE);
            client.setCache(cache);
        } catch(IOException e) {
            Log.e("DatModule", "Unable to install disk cache.", e);
        }

        return client;
    }
}
