package mbc.fonfon.sun.solomonschoice;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by soldesk on 2016-10-20.
 */
public class Search_Pass extends AppCompatActivity {

    TextView title;
    ImageView back_Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_pass);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.rgb(216, 216, 216)));
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_style);
        title = (TextView) findViewById(R.id.actionbar_setting);
        title.setText("비밀번호 찾기");

        // 설정 닫기 버튼
        back_Btn = (ImageView) findViewById(R.id.back);
        back_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}