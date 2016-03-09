package net.pawelhajduk.daggerdemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import net.pawelhajduk.daggerdemo.data.UseMockBackend;
import net.pawelhajduk.daggerdemo.data.prefs.BooleanPreference;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class MainActivity extends ActionBarActivity {
    @Bind(R.id.main_tv_text)
    TextView text;

    @Bind(R.id.main_checkbox)
    CheckBox checkBox;

    @Inject
    @UseMockBackend
    BooleanPreference useMock;

    @Inject
    Resources resources;

    @Inject
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        DaggerDemoApplication.component().inject(this);

        text.setText(resources.getString(R.string.injected_text));
        checkBox.setChecked(useMock.get());
    }

    @OnCheckedChanged(R.id.main_checkbox)
    public void onRememberMeCheckChanged(CompoundButton button, boolean checked) {
        useMock.set(checked);

        // Need to rebuild graph due to changed setting which directly influences object creation (mock mode)
        DaggerDemoApplication.buildComponentAndInject();
    }

    @OnClick(R.id.goToRepositoriesList)
    public void onGoToRepositoriesListClicked() {
        startActivity(new Intent(this, RepositoriesListActivity.class));
    }
}
