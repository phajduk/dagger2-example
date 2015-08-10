package net.pawelhajduk.daggerdemo.api;

import android.app.Application;

import net.pawelhajduk.daggerdemo.data.DataModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.Endpoint;
import retrofit.RestAdapter;
import retrofit.client.Client;
import retrofit.client.OkClient;

@Module
public class ApiModule {
    public static final String PRODUCTION_API_URL = "https://api.github.com";

    @Provides
    @Singleton
    Client provideClient(Application app) {
        return new OkClient(DataModule.createOkHttpClient(app));
    }

    /*
    Endpoint object is delivered by specific child module (Release or Debug)
     */
    @Provides
    @Singleton
    RestAdapter provideRestAdapter(Endpoint endpoint, Client client) {
        return new RestAdapter.Builder()
                .setClient(client)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(endpoint)
                .build();
    }
}
