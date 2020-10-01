package com.thlapinha.lojagl.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;
import com.thlapinha.lojagl.R;



public class MainActivity extends IntroActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });


        //ocutar botao de passar slide
        setButtonBackVisible(false);
        setButtonNextVisible(false);
        //Slides01
        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.holo_orange_dark)
                .fragment(R.layout.intro_1)
                .build());

        //Slides02
        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.holo_orange_dark)
                .fragment(R.layout.intro_2)
                .build());
        //Slides03
        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.holo_orange_dark)
                .fragment(R.layout.intro_3)
                .build());
        //Slides04
        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.holo_orange_dark)
                .fragment(R.layout.intro_4)
                .build());
        //slide cadastro
        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.holo_orange_dark)
                .fragment(R.layout.intro_cadastro)
                .canGoBackward(true)//opção para volta o slide
                .canGoForward(false)//metodo para bloquea a passagem do ultimo slide
                .build());

    }
    public void btEntrar(View view){
        startActivity(new Intent(this, LoginActivity.class));

    }
    public void btCadastrar(View view){
        startActivity(new Intent(this, CadastroActivity.class));

    }

}