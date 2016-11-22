package br.unisc.redesii.tcpcliente.Default;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;

import br.unisc.redesii.tcpcliente.R;

public class AppPreferences extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_simple);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        SettingsFragment settingsFragment = new SettingsFragment();
        fragmentTransaction.add(android.R.id.content, settingsFragment, "SETTINGS_FRAGMENT");
        fragmentTransaction.commit();

    }


    public static class SettingsFragment extends PreferenceFragment {

        @Override
        public void onCreate(Bundle saveInstanceState){
            super.onCreate(saveInstanceState);

            addPreferencesFromResource(R.xml.app_preferences);
        }
    }
}
