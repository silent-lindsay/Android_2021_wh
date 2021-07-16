package com.bytedance.practice5.socket;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.net.Socket;

public class ClientSocketThread extends Thread {
    public ClientSocketThread(SocketActivity.SocketCallback callback) {
        this.callback = callback;
    }

    private SocketActivity.SocketCallback callback;

    private synchronized void clearContent() {
        content = "";
    }
    //head请求内容
    private static String content = "HEAD / HTTP/1.1\r\nHost:www.zju.edu.cn\r\n\r\n";

    @Override
    public void run() {
        // TODO 6 用socket实现简单的HEAD请求（发送content）
        //  将返回结果用callback.onresponse(result)进行展示
        try {
            Socket sock = new Socket("www.zju.edu.cn",80) ;
            BufferedOutputStream bos = new BufferedOutputStream(sock.getOutputStream()) ;
            BufferedInputStream bis = new BufferedInputStream(sock.getInputStream()) ;
            byte[] data = new byte[1024*5] ;
            if ( sock.isConnected() ) {
                if (!content.isEmpty()) {
                    Log.i("socket", "客户端发送 " + content);
                    bos.write(content.getBytes());
                    bos.flush();
                    clearContent();
                    int Len = bis.read(data);
                    if ( Len != -1 ){
                        String result = new String(data, 0, Len);
                        Log.i("socket", "客户端收到\n" + result);
                        callback.onResponse(result);
                    }else {
                        Log.i("socket", "客户端收到-1");
                    }
                }
            }
            Log.i("socket", "客户端断开 ");
            bos.flush();
            bos.close();
            bis.close();
            sock.close(); //关闭socket
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}