package br.unisc.redesii.tcpcliente.SocketModules;

import java.util.concurrent.ExecutionException;

import br.unisc.redesii.tcpcliente.model.Message;


public class Talk2Module {

    public static final String TYPE_SQL = "sql ";
    public static final String TYPE_FREE = "";
    public static final String TYPE_TEST = "teste";
    public static final String TYPE_CURSOR = "cursor ";

    private final String host;
    private final int port;

    private SocketTask socketTask;

    public String returnMessage;

    public Talk2Module(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public String request(String message, String type) {

        socketTask = new SocketTask(host, port, 5000) {
            @Override
            protected void onProgressUpdate(String... values) {
                returnMessage = values[0];
            }
        };

        message = type + message;

        socketTask.execute(message == null ? "" : message.toString());

        try {
            returnMessage = socketTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return returnMessage;
    }

    public void requestSetTextView(String message, String type, final Message m) {

        socketTask = new SocketTask(host, port, 5000, m) {
            @Override
            protected void onProgressUpdate(String... values) {
                m.add(values[0]);
            }
        };

        message = type + message;

        socketTask.execute(message == null ? "" : message.toString());
    }

    public void requestSetTextView(String type, final Message m) {
        requestSetTextView("", type, m);
    }

    public void requestWithoutResponse(String message, String type) {

        socketTask = new SocketTask(host, port, 5000) {};

        message = type + message;

        socketTask.execute(message == null ? "" : message.toString());
    }

}
