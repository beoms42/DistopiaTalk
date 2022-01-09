package com.example.chatclient1;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.Buffer;
import java.nio.charset.StandardCharsets;

public class SendThread extends Thread {

    private Socket m_Socket;
    Context mContext;
    String Apex;
    String sendString;



    @Override
        public void run() {
        // TODO Auto-generated method stub
        super.run();

        try {

            ((MainActivity)mContext).textArea.setText(null);

            Apex = ((MainActivity)mContext).textArea.getText().toString();

            InputStream Apex1 = new ByteArrayInputStream(Apex.getBytes(StandardCharsets.UTF_8));

            BufferedReader tmpbuf = new BufferedReader(new InputStreamReader(Apex1));

            PrintWriter sendWriter = new PrintWriter(m_Socket.getOutputStream());




            ((MainActivity)mContext).getMessageBtn.setOnClickListener(view ->{

                sendWriter.println(tmpbuf);

                sendWriter.flush();
                ((MainActivity)mContext).textArea.setText(null);
            });

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }


    public void setSocket(Socket _socket)
    {
        m_Socket = _socket;
    }

}
