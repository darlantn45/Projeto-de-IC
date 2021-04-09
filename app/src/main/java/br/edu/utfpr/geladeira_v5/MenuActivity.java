package br.edu.utfpr.geladeira_v5;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        final ImageButton b1 = findViewById(R.id.botaoDadosAntropometricos);
        final ImageButton b2 = findViewById(R.id.botaoPrescricao);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    abrirMain();
            }

        });

       /* b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirPrescricao();
            }
        });

        */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private void abrirMain(){

        Context context = this;
        Class destinationClass = MainActivity.class;
        Intent intentToStartDetailActivity = new Intent(context, destinationClass);

        intentToStartDetailActivity.putExtra(Intent.EXTRA_TEXT, "antropometricos");
        startActivity(intentToStartDetailActivity);
    }

    /*private void abrirDadosAntropometricos(){
        Context context = this;
        //Class destinationClass = MainActivity.class;
        Class destinationClass = AntroActivity.class;
        Intent intentToStartDetailActivity = new Intent(context, destinationClass);
        startActivity(intentToStartDetailActivity);
    }
    private void abrirPrescricao(){
        Context context = this;
        //Class destinationClass = MainActivity.class;
        Class destinationClass = PrescricaoFragment.class;
        Intent intentToStartDetailActivity = new Intent(context, destinationClass);
        startActivity(intentToStartDetailActivity);
    }


    }*/
}