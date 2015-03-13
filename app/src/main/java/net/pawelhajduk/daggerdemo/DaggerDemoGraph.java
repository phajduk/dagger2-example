package net.pawelhajduk.daggerdemo;

public interface DaggerDemoGraph {
    void inject(MainActivity mainActivity);

    void inject(RepositoriesListActivity repositoriesListActivity);
}
