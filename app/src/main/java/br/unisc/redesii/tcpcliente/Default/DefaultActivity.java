package br.unisc.redesii.tcpcliente.Default;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import br.unisc.redesii.tcpcliente.R;
import br.unisc.redesii.tcpcliente.SocketModules.Talk2Module;

/**
 * Created by arthurhoch on 10/19/16.
 */
public class DefaultActivity extends AppCompatActivity {

    protected String host;
    protected int port;

    protected Talk2Module talk2Module;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadConfiguration();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings){
            Intent i = new Intent(this, AppPreferences.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadConfiguration();
    }

    private void loadConfiguration() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        host = sharedPreferences.getString("IP", "10.42.0.1");
        port = Integer.parseInt(sharedPreferences.getString("PORT", "8080"));
    }

}
