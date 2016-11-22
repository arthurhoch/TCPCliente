package br.unisc.redesii.tcpcliente.SocketModules;

import android.os.AsyncTask;

import br.unisc.redesii.tcpcliente.model.Message;


public abstract class SocketTask extends AsyncTask<String, String, String> {


    private String host;
    private int port;
    private int timeout;
    private NetClient netClient;

    private Message m;


    public SocketTask(String host, int port, int timeout) {
        super();
        this.host = host;
        this.port = port;
        this.timeout = timeout;
    }

    public SocketTask(String host, int port, int timeout, Message m) {
        super();
        this.host = host;
        this.port = port;
        this.timeout = timeout;
        this.m = m;
    }


    @Override
    protected String doInBackground(String... params) {
        String result = "";

        try {

            netClient = new NetClient(host, port);
            /*
            for (String p: params) {
                netClient.sendDataWithString(p);
            }
            */
            netClient.sendDataWithString(params[0]);
            result = netClient.receiveDataFromServer2();

            publishProgress(result);

            } catch (Exception e) {
                //Log.e("Socket Android", "Erro ao fechar conexao", e);
                publishProgress("Conection problem.");
            }

        return result;
    }
}