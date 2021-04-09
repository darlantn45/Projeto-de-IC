package br.edu.utfpr.geladeira_v5;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class MainActivity extends AppCompatActivity {

    private androidx.appcompat.widget.AppCompatButton botao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        BotoesFragment fragment = new BotoesFragment();
        fragmentTransaction.add(R.id.main_activity, fragment);
        fragmentTransaction.commit();
    }
}
