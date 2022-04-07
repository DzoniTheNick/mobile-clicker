package com.example.epos_projekat;

import android.util.Log;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class ServerThread extends Thread{

    String ipAdrress;

    PrintStream clientOut;

    int komanda = 20;

    public ServerThread(String ipAdrress) {

        this.ipAdrress = ipAdrress;

    }

    public void podesiKomandu(int komanda){

        this.komanda = komanda;

    }

    @Override
    public void run() {

        try {

            Log.i("IP",ipAdrress);

            Socket communicationSocket = new Socket(ipAdrress,9912);

            clientOut = new PrintStream(communicationSocket.getOutputStream());

            Log.i("Status","Doslo je do konekcije");

            while(true){

                if(komanda == -1){

                    clientOut.println("-1");
                    komanda = 10;

                }else if(komanda == 1){

                    clientOut.println("1");
                    komanda = 10;

                }else if(komanda == 0){

                    clientOut.println("0");
                    communicationSocket.close();
                    Log.i("Status","Prekinuta je veza");
                    break;

                }

            }

        } catch (IOException e) {

            Log.i("Status","Greska pri povezivanju");
            e.printStackTrace();

        }

    }
}
