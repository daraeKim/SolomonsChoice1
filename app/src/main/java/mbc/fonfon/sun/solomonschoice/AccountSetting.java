package mbc.fonfon.sun.solomonschoice;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by soldesk on 2016-10-17.
 */
public class AccountSetting extends AppCompatActivity {

    ListView setting_listview;
    ImageView back_Btn;
    SeparatedListAdapter madapter;
    TextView title;

    public final static String ITEM_TITLE = "title";
    public final static String ITEM_CAPTION = "caption";

    public Map<String, ?> createItem(String title, String caption) {
        Map<String, String> item = new HashMap<String, String>();
        item.put(ITEM_TITLE, title);
        item.put(ITEM_CAPTION, caption);
        return item;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_setting);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.rgb(216,216,216)));
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_style);
        title = (TextView) findViewById(R.id.actionbar_setting);
        title.setText("설      정");

        // 설정 닫기 버튼
        back_Btn = (ImageView) findViewById(R.id.back);
        back_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            finish();
        }
    });

        List<Map<String, ?>> info = new LinkedList<Map<String, ?>>();
        info.add(createItem("버전 정보", "3.1.0(최신버전)"));

        List<Map<String, ?>> alram = new LinkedList<Map<String, ?>>();
        alram.add(createItem("알림", "알림꺼짐"));

        // create our list and custom adapter
        /*final*/ madapter = new SeparatedListAdapter(this);

        madapter.addSection("개인정보 설정", new ArrayAdapter<String>(this,
                R.layout.list_item, new String[]{"프로필 변경", "비밀번호 변경"}));

        madapter.addSection("정보", new SimpleAdapter(this, info, R.layout.list_complex,
                new String[]{ITEM_TITLE, ITEM_CAPTION}, new int[]{R.id.list_complex_title, R.id.list_complex_caption}));

        madapter.addSection("서비스 & 문의", new ArrayAdapter<String>(this,
                R.layout.list_item, new String[]{"공지사항", "이용약관", "개인정보 취급 방침", "콘텐츠 제보", "문의 하기"}));

        madapter.addSection("기타", new ArrayAdapter<String>(this,
                R.layout.list_item, new String[]{"검색기록 삭제", "로그아웃"}));

        setting_listview = (ListView) findViewById(R.id.setting_listview);
        setting_listview.setAdapter(madapter);
        setting_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                if(position==1){
                    Intent intent = new Intent(getApplicationContext() , Change_profile.class);
                    startActivity(intent);
                }
                else if(position==2){
                    Intent intent = new Intent(getApplicationContext() , Change_pass.class);
                    startActivity(intent);
                }
            }
        });
    }
}

