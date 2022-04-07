package com.example.epos_projekat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    ServerThread newThread = null;

    public void connection(View view){

        //Prikupljamo IP adresu iz polja za unos teksta

        EditText ipInput = (EditText) findViewById(R.id.userIPInput);
        String stringIPInput = ipInput.getText().toString();

        //Izbacujemo poruku korisniku u kojoj će biti ispisana IP adresa za koju se konektuje

        Toast.makeText(MainActivity.this, stringIPInput, Toast.LENGTH_SHORT).show();

        //Iniciliziramo i pokrećemo nit koja omogućava da se povežemo sa serverom

        newThread = new ServerThread(stringIPInput);
        newThread.start();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void prethodni(View view){

        if(newThread != null) {

            //Postavlja se vrednost promenljive komanda na -1
            newThread.podesiKomandu(-1);

        }else{

            //U koliko nije uspostavljena konekcija tj. nije kreirana nit za konekciju
            //Aplikacija ce reci korisniku da mora prvo da se poveze sa serverom
            Toast.makeText(MainActivity.this,"PRVO POKRENI KONEKCIJU!",Toast.LENGTH_SHORT).show();

        }
    }

    public void sledeci(View view){

        if(newThread != null) {

            //Postavlja se vrednost promenljive komanda na 1
            newThread.podesiKomandu(1);

        }else{

            //U koliko nije uspostavljena konekcija tj. nije kreirana nit za konekciju
            //Aplikacija ce reci korisniku da mora prvo da se poveze sa serverom
            Toast.makeText(MainActivity.this,"PRVO POKRENI KONEKCIJU!",Toast.LENGTH_SHORT).show();

        }

    }

    public void gasi(View view){

        if(newThread != null) {

            //Postavlja se vrednost promenljive komanda na 0 i gasi se veza sa serverom
            newThread.podesiKomandu(0);

        }else{

            //U koliko nije uspostavljena konekcija tj. nije kreirana nit za konekciju
            //Aplikacija ce reci korisniku da mora prvo da se poveze sa serverom
            Toast.makeText(MainActivity.this,"PRVO POKRENI KONEKCIJU!",Toast.LENGTH_SHORT).show();

        }

    }

}