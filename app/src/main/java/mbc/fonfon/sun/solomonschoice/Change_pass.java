package mbc.fonfon.sun.solomonschoice;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by soldesk on 2016-10-20.
 */
public class Change_pass extends AppCompatActivity {

    TextView title;
    ImageView back_Btn;
    Button change_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_pass);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.rgb(216, 216, 216)));
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_style);
        title = (TextView) findViewById(R.id.actionbar_setting);
        title.setText("비밀번호 변경");

        // 설정 닫기 버튼
        back_Btn = (ImageView) findViewById(R.id.back);
        back_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        change_btn = (Button) findViewById(R.id.change_btn);
        change_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "비밀번호 변경이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}