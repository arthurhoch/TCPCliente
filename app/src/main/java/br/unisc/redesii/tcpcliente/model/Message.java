package br.unisc.redesii.tcpcliente.model;

import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.List;

/**
 * Created by arthurhoch on 11/10/16.
 */
public class Message {


    private ListView listView;
    private List<String> listl;
    private ListAdapter adapter;

    public Message(ListView listView, List<String> listl, ListAdapter adapter) {
        this.listView = listView;
        this.listl = listl;
        this.adapter = adapter;
    }

    public void add(String messageSend) {
        listl.add(messageSend+"\n");
        listView.setSelection(listView.getCount() - 1);
    }
}
