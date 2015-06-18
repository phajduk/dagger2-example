package net.pawelhajduk.daggerdemo;

import net.pawelhajduk.daggerdemo.api.ReleaseApiModule;
import net.pawelhajduk.daggerdemo.data.ReleaseDataModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = { MainModule.class, ReleaseDataModule.class, ReleaseApiModule.class })
public interface DaggerDemoComponent extends DaggerDemoGraph {
    static final class Initializer {
        private Initializer() {
        } // No instances.

        public static DaggerDemoComponent init(DaggerDemoApplication app) {
            return DaggerDaggerDemoComponent.builder()
                                             .mainModule(new MainModule(app))
                                             .build();
        }
    }
}