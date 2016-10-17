package mbc.fonfon.sun.solomonschoice;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;

/**
 * Created by soldesk on 2016-10-12.
 */
public class LoginPage extends AppCompatActivity {
    EditText etMail;
    LinearLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        mainLayout = (LinearLayout) findViewById(R.id.loginLayout);
        etMail = (EditText) findViewById(R.id.etMail);

        mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(etMail.getWindowToken(), 0);
            }
        });


    }
}
