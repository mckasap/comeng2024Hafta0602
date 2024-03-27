package com.example.comeng2024hafta0602;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
ImageView iv;
Button btn1;
Button btn2;
Button btn3;
Button btn4;
Button[] btngrp= new Button[4];

String[] strNames= new String[100];
String[] strImgSrc= new String[100];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv=(ImageView)  findViewById(R.id.imageView);
        btn1=(Button) findViewById(R.id.button);
        btn2=(Button) findViewById(R.id.button2);
        btn3= (Button) findViewById(R.id.button3);
        btn4 = (Button) findViewById(R.id.button4);
        btngrp[0]=btn1;
        btngrp[1]=btn2;
        btngrp[2]=btn3;
        btngrp[3]=btn4;

        setup();
        Guncelle();






    }

    private class SetUp extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Document doc = Jsoup.connect("https://www.imdb.com/list/ls059786955/?sort=list_order,asc&mode=detail&page=1").get();
                Elements imgs= doc.select(".lister-item-image a img");
                int sayac=0;
                for(Element e:imgs){
                    strImgSrc[sayac]=e.attr("src");
                    strNames[sayac]=e.attr("alt");
                    sayac++;
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return null;
        }
    }

    private void setup() {
        SetUp st= new SetUp();
        st.execute();
    }

    public void Answer(View v){
        Guncelle();
    }

    public void Guncelle(){
    Random rnd =new Random();
        btngrp[0].setText(strNames[rnd.nextInt(100)]);
        btngrp[1].setText(strNames[rnd.nextInt(100)]);
        btngrp[2].setText(strNames[rnd.nextInt(100)]);
        btngrp[3].setText(strNames[rnd.nextInt(100)]);
    }
}