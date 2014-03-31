package de.example.exampletdd;

import android.app.ActionBar;
import android.app.Activity;
import android.app.DialogFragment;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import de.example.exampletdd.activityinterface.ErrorMessage;
import de.example.exampletdd.activityinterface.OnClickButtons;
import de.example.exampletdd.fragment.ErrorDialogFragment;
import de.example.exampletdd.fragment.WeatherDataFragment;

public class WeatherInformationActivity extends Activity implements ErrorMessage {
    private OnClickButtons onclickButtons;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
        
        final ActionBar actionBar = getActionBar();

        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_TITLE, ActionBar.DISPLAY_SHOW_TITLE);
        actionBar.setTitle(getResources().getString(R.string.header_action_bar));

        // Better using xml files.
        // final WeatherDataFragment weatherDataFragment = new WeatherDataFragment();
        //
        // if (savedInstanceState == null) {
        //      this.getFragmentManager().beginTransaction()
        //      .add(R.id.container, weatherDataFragment).commit();
        // }
        final WeatherDataFragment weatherDataFragment = (WeatherDataFragment) this
                .getFragmentManager().findFragmentById(R.id.weather_data_frag);

        this.onclickButtons = weatherDataFragment;
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        this.getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.weather_menu_settings:
                final Intent intent = new Intent("de.example.exampletdd.WEATHERINFO").
                setComponent(new ComponentName("de.example.exampletdd",
                        "de.example.exampletdd.WeatherInformationSettings"));
                this.startActivity(intent);
                return true;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void createErrorDialog(final int title) {
        final DialogFragment newFragment = ErrorDialogFragment
                .newInstance(title);
        newFragment.show(this.getFragmentManager(), "errorDialog");
    }

    public void onClickGetWeather(final View v) {
        this.onclickButtons.onClickGetWeather(v);
    }
}