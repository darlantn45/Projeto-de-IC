package br.edu.utfpr.geladeira_v5;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import br.edu.utfpr.geladeira_v5.ui.antropometricos.AntropometricoFragment;
import br.edu.utfpr.geladeira_v5.ui.laudo.LaudoFragment;
import br.edu.utfpr.geladeira_v5.ui.nutricional.NutricionalFragment;
import br.edu.utfpr.geladeira_v5.ui.prescricao.PrescricaoFragment;
import br.edu.utfpr.geladeira_v5.ui.receitas.ReceitasFragment;

public class BotoesFragment extends Fragment {

    private Button botaoAntropometrico;
    private Button botaoLaudo;
    private Button botaoNutricional;
    private Button botaoPrescricao;
    private Button botaoReceitas;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_botoes, container, false);
        botaoAntropometrico = (Button) v.findViewById(R.id.button_antropometrico);
        botaoAntropometrico.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                AntropometricoFragment a = new AntropometricoFragment();
                FragmentManager m = getFragmentManager();
                m.beginTransaction().replace(R.id.main_activity,a,a.getTag()).
                        addToBackStack(null).
                        commit();
            }
        });

        botaoLaudo = (Button) v.findViewById(R.id.button_laudo);
        botaoLaudo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                LaudoFragment a = new LaudoFragment();
                FragmentManager m = getFragmentManager();
                m.beginTransaction().replace(R.id.main_activity,a,a.getTag()).
                        addToBackStack(null).
                        commit();
            }
        });

        botaoNutricional = (Button) v.findViewById(R.id.button_nutricional);
        botaoNutricional.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                NutricionalFragment a = new NutricionalFragment();
                FragmentManager m = getFragmentManager();
                m.beginTransaction().replace(R.id.main_activity,a,a.getTag()).
                        addToBackStack(null).
                        commit();
            }
        });

        botaoPrescricao = (Button) v.findViewById(R.id.button_prescricao);
        botaoPrescricao.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                PrescricaoFragment a = new PrescricaoFragment();
                FragmentManager m = getFragmentManager();
                m.beginTransaction().replace(R.id.main_activity,a,a.getTag()).
                        addToBackStack(null).
                        commit();
            }
        });
        botaoReceitas = (Button) v.findViewById(R.id.button_receitas);
        botaoReceitas.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                ReceitasFragment a = new ReceitasFragment();
                FragmentManager m = getFragmentManager();
                m.beginTransaction().replace(R.id.main_activity,a,a.getTag()).
                        addToBackStack(null).
                        commit();
            }
        });

        return v;
    }

    /*OnHeadlineSelectedListener callback;

    public void setOnHeadlineSelectedListener(OnHeadlineSelectedListener callback) {
        this.callback = callback;
    }

    // This interface can be implemented by the Activity, parent Fragment,
    // or a separate test implementation.
    public interface OnHeadlineSelectedListener {
        public void dadosDoFragment(int position);
    }


    public void tratarBotao() {

        //Fragmento acessa a Activity
        /*botao = getActivity().findViewById(R.id.button1);
        botao.setOnClickListener(new View.OnClickListener() {


            //  The onClick method is triggered when this button (mDoSomethingCoolButton) is clicked.

            //  @param v The view that is clicked. In this case, it's mDoSomethingCoolButton.

            @Override
            public void onClick(View v) {

                AntropometricoFragment f = new AntropometricoFragment();
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.content,f,f.getTag()).commit();


                //android.content.Context context = BotoesFragment.this;
                String message = "Botão pressionado!\nTODO: Iniciar uma nova Activity e passar alguns dados.";
                Log.d("TXT", "Lifecycle Event: " + message);
                //Toast.makeText(context, message, Toast.LENGTH_LONG).show();

                // Send the event to the host activity
                //callback.dadosDoFragment(R.id.botaoDadosAntropometricos);
                callback.dadosDoFragment(10);
            }
        });


    }
*/

}
