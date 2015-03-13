package net.pawelhajduk.daggerdemo.api;

import net.pawelhajduk.daggerdemo.data.UseMockBackend;
import net.pawelhajduk.daggerdemo.data.prefs.BooleanPreference;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.Endpoint;
import retrofit.Endpoints;
import retrofit.MockRestAdapter;
import retrofit.RestAdapter;

@Module(includes = ApiModule.class)
public class DebugApiModule {
    @Provides
    @Singleton
    Endpoint provideEndpoint() {
        return Endpoints.newFixedEndpoint(ApiModule.PRODUCTION_API_URL);
    }

    @Provides
    @Singleton
    GitHubService provideGitHubService(RestAdapter restAdapter, MockRestAdapter mockRestAdapter, MockGitHubService mockService,
                                       @UseMockBackend BooleanPreference useMockMode) {
        if(useMockMode.get()) {
            return mockRestAdapter.create(GitHubService.class, mockService);
        } else {
            return restAdapter.create(GitHubService.class);
        }
    }

    @Provides
    @Singleton
    MockRestAdapter provideMockRestAdapter(RestAdapter restAdapter) {
        MockRestAdapter mockRestAdapter = MockRestAdapter.from(restAdapter);
        return mockRestAdapter;
    }
}
