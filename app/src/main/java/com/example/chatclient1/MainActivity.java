package com.example.chatclient1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.Buffer;



public class MainActivity extends AppCompatActivity {


    TextView textArea;
    EditText getMessageText;
    Button getMessageBtn;
    PrintWriter sendWriter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textArea = findViewById(R.id.TextArea);
        getMessageText = findViewById(R.id.getMessageText);
        getMessageBtn = findViewById(R.id.getMessageBtn);
        String host = "172.30.1.33";
        int port = 8888;

        new Thread() {
            public void run() {
                try {
                    Socket socket = new Socket(host, port);

                    sendWriter = new PrintWriter(socket.getOutputStream());

                    BufferedReader tmpbuf = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
                    String receiveString;

                    while (true) {
                        try {
                            receiveString = tmpbuf.readLine();
                            if (receiveString == null) {
                                textArea.setText("상대방 연결이 끊겼습니다.");
                            } else {
                                textArea.setText("상대방 :" + receiveString);
                            }
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }


                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        try {


        } catch (Exception e) {
            e.printStackTrace();
        }

        getMessageBtn.setOnClickListener(view -> {
            String tmptext = getMessageText.getText().toString();

            new Thread() {
                @Override
                public void run() {
                    super.run();
                    try {
                        sendWriter.println(tmptext);
                        sendWriter.flush();
                        getMessageBtn.setText("");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        });


    }
}