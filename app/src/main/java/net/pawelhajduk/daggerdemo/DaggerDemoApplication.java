package net.pawelhajduk.daggerdemo;

import android.app.Application;

public class DaggerDemoApplication extends Application {
    private static DaggerDemoGraph graph;
    private static DaggerDemoApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        buildComponentAndInject();
    }

    public static DaggerDemoGraph component() {
        return graph;
    }

    public static void buildComponentAndInject() {
        graph = DaggerDemoComponent.Initializer.init(instance);
    }
}
