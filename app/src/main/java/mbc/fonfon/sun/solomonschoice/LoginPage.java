package mbc.fonfon.sun.solomonschoice;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by soldesk on 2016-10-12.
 */
public class LoginPage extends AppCompatActivity implements View.OnClickListener{

    EditText etMail;
    LinearLayout mainLayout;
    ImageView back2;

    TextView search_pass;
    TextView member_join;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);     //로그인메인화면 xml 파일 불러옴

        mainLayout = (LinearLayout) findViewById(R.id.loginLayout);
        etMail = (EditText) findViewById(R.id.etMail);
        search_pass = (TextView) findViewById(R.id.search_pass);
        back2 = (ImageView) findViewById(R.id.back2);
        member_join = (TextView) findViewById(R.id.member_join);

        mainLayout.setOnClickListener(this);

        // 로그인 페이지 닫기
        back2.setOnClickListener(this);

        // 회원가입 버튼 클릭시
        member_join.setOnClickListener(this);

        search_pass.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.loginLayout:
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(etMail.getWindowToken(), 0);
                break;

            case R.id.back2:
                finish();
                break;

            case R.id.member_join:
                Intent intent = new Intent(getApplicationContext(), Join.class);
                startActivity(intent);
                break;

            case R.id.search_pass:
                intent = new Intent(getApplicationContext(), Search_Pass.class);
                startActivity(intent);
                break;
        }
    }
}
