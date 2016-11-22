package br.unisc.redesii.tcpcliente;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.LinkedList;
import java.util.List;

import br.unisc.redesii.tcpcliente.Default.DefaultActivity;
import br.unisc.redesii.tcpcliente.SocketModules.Talk2Module;
import br.unisc.redesii.tcpcliente.model.Message;

public class MainActivity extends DefaultActivity {


    private ListView listView;
    private Button btnSend;
    private EditText editText;

    private List<String> listl;
    private ListAdapter adapter;

    private Talk2Module talk2Module;

    private Message message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        btnSend = (Button) findViewById(R.id.btnSend);
        editText = (EditText) findViewById(R.id.editText);

        listl = new LinkedList<String>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, listl);
        listView.setAdapter(adapter);
        listView.setHorizontalFadingEdgeEnabled(true);
        message = new Message(listView, listl, adapter);

        btnSend.setOnClickListener(btnSendListener);
        talk2Module = new Talk2Module(host, port);

    }


    private View.OnClickListener btnSendListener = new View.OnClickListener() {
        public void onClick(View v) {
            String messageSend = String.valueOf(editText.getText());
            if (!messageSend.isEmpty()) {
                editText.setText("");

                message.add(messageSend);
                talk2Module.requestSetTextView(messageSend, Talk2Module.TYPE_FREE, message);

                //adapter.notifyAll();
            }
        }
    };
}
