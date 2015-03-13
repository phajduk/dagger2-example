package net.pawelhajduk.daggerdemo.api;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.Endpoint;
import retrofit.Endpoints;
import retrofit.RestAdapter;

@Module(includes = ApiModule.class)
public class ReleaseApiModule {
    @Provides
    @Singleton
    Endpoint provideEndpoint() {
        return Endpoints.newFixedEndpoint(ApiModule.PRODUCTION_API_URL);
    }

    @Provides
    @Singleton
    GitHubService provideGitHubService(RestAdapter restAdapter) {
        return restAdapter.create(GitHubService.class);
    }
}
