package br.edu.utfpr.geladeira_v5.ui.antropometricos;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;

import br.edu.utfpr.geladeira_v5.LoginActivity;
import br.edu.utfpr.geladeira_v5.NetworkUtils;
import br.edu.utfpr.geladeira_v5.R;

public class AntropometricoFragment extends Fragment {

    private TextView mSearchResultsTextView;

    private TextView mErrorMessageDisplay;

    private AntropometricoViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(AntropometricoViewModel.class);
        View root = inflater.inflate(R.layout.fragment_antropometrico, container, false);
        final TextView textView = root.findViewById(R.id.tv_github_search_results_json);
        dashboardViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        mErrorMessageDisplay = root.findViewById(R.id.tv_error_message_display);
        mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        mSearchResultsTextView = (TextView) root.findViewById(R.id.tv_github_search_results_json);
        URL loginSite = null;
        String username = new String("admin@admin") ;
        String password = new String("123admin123");

        loginSite = NetworkUtils.appalimentos(username, password);
        new AppLogin().execute(loginSite);

        return root;
    }
    public class AppLogin extends AsyncTask<URL, Void, String> {

        URL searchUrl;
        // COMPLETED (26) Override onPreExecute to set the loading indicator to visible
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(URL... params) {
            searchUrl = params[0];
            String xmlFileResponse = null;
            try {
                xmlFileResponse = NetworkUtils.getResponseFromHttpUrl(searchUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return xmlFileResponse;
        }

        @Override
        protected void onPostExecute(String xmlFileResponse) {
            // COMPLETED (27) As soon as the loading is complete, hide the loading indicator
            if (xmlFileResponse != null && !xmlFileResponse.equals("")) {
                // COMPLETED (17) Call showJsonDataView if we have valid, non-null results
                mSearchResultsTextView.setVisibility(View.VISIBLE);
                //xmlFileResponse += searchUrl;
                ArrayList<String> alimentos = new ArrayList();
                parserXML(xmlFileResponse, alimentos);
               String lista = new String();
                int tam = alimentos.size();
                for (int i = 0; i < tam; i++)
                   lista += alimentos.get(i) + "\n";
                mSearchResultsTextView.setText(lista);
            } else {
                // COMPLETED (16) Call showErrorMessage if the result is null in onPostExecute
                mErrorMessageDisplay.setVisibility(View.VISIBLE);
            }
        }

    }
    private void parserXML(String xmlFileResponse, ArrayList<String> alimentos) {



        //Convert xmlFileResponse to InputStream
        InputStream inputStream = getInputStream(xmlFileResponse,"UTF-8");

        try {
            //Prepara as classes leitoras
            XmlPullParserFactory xmlFactoryObject = XmlPullParserFactory.newInstance();
            XmlPullParser myparser = xmlFactoryObject.newPullParser();

            //Define o parser do stream
            myparser.setInput(inputStream, null);

            //Faz o parser do XML
            int event = myparser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT)  {
                //Retorna o nome da tag
                String name=myparser.getName();
                switch (event){
                    case XmlPullParser.START_TAG:
                        if(name.equals("gaveta")){
                            //Toast.makeText(getApplicationContext(), myparser.getAttributeValue(null,"value"), Toast.LENGTH_LONG).show();
                            alimentos.add(myparser.getAttributeValue(null,"num"));
                            alimentos.add(myparser.getAttributeValue(null,"status"));
                            alimentos.add(myparser.getAttributeValue(null,"validade"));
                            alimentos.add(myparser.getAttributeValue(null,"restante"));
                            alimentos.add(myparser.nextText());


                        }
                        break;

                    case XmlPullParser.END_TAG:

                        break;
                }
                event = myparser.next();
            }


        } catch(Exception e){
            //Toast.makeText(getApplicationContext(), getString(R.string.login_failed), Toast.LENGTH_LONG).show();
            //Toast.makeText(getApplicationContext(), "Passei por aqui", Toast.LENGTH_LONG).show();
        }
    }

    private InputStream getInputStream(String str, String encoding) {

        InputStream inputStream = null;
        try {
            inputStream = new ByteArrayInputStream(str.getBytes(encoding));
        } catch (UnsupportedEncodingException e){
            //Toast.makeText(getApplicationContext(), getString(R.string.error_coding), Toast.LENGTH_LONG).show();
        }
        return inputStream;
    }
}
