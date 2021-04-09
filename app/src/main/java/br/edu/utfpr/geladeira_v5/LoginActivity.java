package br.edu.utfpr.geladeira_v5;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;

    private TextView mUrlDisplayTextView;
    private TextView mSearchResultsTextView;

    private ProgressBar mLoadingIndicator;
    // COMPLETED (12) Create a variable to store a reference to the error message TextView
    private TextView mErrorMessageDisplay;

    private Button loginButton;
    private Button esqueciButton;
    private Button semsenhaButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.login);
        esqueciButton = findViewById(R.id.esqueci);
        semsenhaButton = findViewById(R.id.semsenha);

        mUrlDisplayTextView = (TextView) findViewById(R.id.tv_url_display);
        mSearchResultsTextView = (TextView) findViewById(R.id.tv_github_search_results_json);
        // COMPLETED (13) Get a reference to the error TextView using findViewById
        mErrorMessageDisplay = (TextView) findViewById(R.id.tv_error_message_display);
        // COMPLETED (25) Get a reference to the ProgressBar using findViewById
        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeLoginQuery();
            }
        });

        esqueciButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), getString(R.string.todo), Toast.LENGTH_LONG).show();
            }
        });

        semsenhaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirGeladeira();
            }
        });

    }

    private void makeLoginQuery() {
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        URL loginSite = null;
        if (username.isEmpty() || password.isEmpty())
            Toast.makeText(getApplicationContext(), getString(R.string.login_failed), Toast.LENGTH_LONG).show();
        else {
            loginSite = NetworkUtils.buildUrl(username, password);

            new AppLogin().execute(loginSite);
        }
    }

    public class AppLogin extends AsyncTask<URL, Void, String> {

        // COMPLETED (26) Override onPreExecute to set the loading indicator to visible
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingIndicator.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(URL... params) {
            URL searchUrl = params[0];
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
            mLoadingIndicator.setVisibility(View.INVISIBLE);
            if (xmlFileResponse != null && !xmlFileResponse.equals("")) {
                // COMPLETED (17) Call showJsonDataView if we have valid, non-null results
                showJsonDataView();
                mSearchResultsTextView.setText(xmlFileResponse);

                if (validateLogin(xmlFileResponse).equals("ALLOW"))
                    abrirGeladeira();
                else
                    Toast.makeText(getApplicationContext(), getString(R.string.login_failed), Toast.LENGTH_LONG).show();

            } else {
                // COMPLETED (16) Call showErrorMessage if the result is null in onPostExecute
                showErrorMessage();
            }
        }
    }

    private String validateLogin(String xmlFileResponse) {

        String permission = new String("DENY");

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
                        break;

                    case XmlPullParser.END_TAG:
                        if(name.equals("permission")){
                            //Toast.makeText(getApplicationContext(), myparser.getAttributeValue(null,"value"), Toast.LENGTH_LONG).show();
                            permission = myparser.getAttributeValue(null,"value");
                        }
                        break;
                }
                event = myparser.next();
            }


        } catch(Exception e){
            Toast.makeText(getApplicationContext(), getString(R.string.login_failed), Toast.LENGTH_LONG).show();
            //Toast.makeText(getApplicationContext(), "Passei por aqui", Toast.LENGTH_LONG).show();
        }

        return permission;
    }

    //Transforma a string do XML recebida do site em InputStream
    private InputStream getInputStream(String str, String encoding) {

        InputStream inputStream = null;
        try {
            inputStream = new ByteArrayInputStream(str.getBytes(encoding));
        } catch (UnsupportedEncodingException e){
            Toast.makeText(getApplicationContext(), getString(R.string.error_coding), Toast.LENGTH_LONG).show();
        }
        return inputStream;
    }


    // COMPLETED (14) Create a method called showJsonDataView to show the data and hide the error
    /**
     * This method will make the View for the JSON data visible and
     * hide the error message.
     * <p>
     * Since it is okay to redundantly set the visibility of a View, we don't
     * need to check whether each view is currently visible or invisible.
     */
    private void showJsonDataView() {
        // First, make sure the error is invisible
        mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        // Then, make sure the JSON data is visible
        mSearchResultsTextView.setVisibility(View.VISIBLE);
    }

    /**
     * This method will make the error message visible and hide the JSON
     * View.
     * <p>
     * Since it is okay to redundantly set the visibility of a View, we don't
     * need to check whether each view is currently visible or invisible.
     */
    private void showErrorMessage() {
        // First, hide the currently visible data
        mSearchResultsTextView.setVisibility(View.INVISIBLE);
        // Then, show the error
        mErrorMessageDisplay.setVisibility(View.VISIBLE);
    }

    private void abrirGeladeira(){
        Context context = this;
        Class destinationClass = MainActivity.class;
        //Class destinationClass = MenuActivity.class;
        Intent intentToStartDetailActivity = new Intent(context, destinationClass);
        startActivity(intentToStartDetailActivity);
    }


}
