package mbc.fonfon.sun.solomonschoice;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

/**
 * Created by soldesk on 2016-10-07.
 */
public class Mypage extends Fragment {

    Button LoginBtn;
    ImageView settingBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

       View view = inflater.inflate(R.layout.mypage_after_login, container, false);


     /*   // 로그인 하기 전 화면
        LoginBtn = (Button) view.findViewById(R.id.LoginBtn);
        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Intent intent = new Intent(getActivity() , LoginPage.class);\
                startActivity(intent);
            }
        });
*/


        // 로그인 한 후 화면

        settingBtn = (ImageView) view.findViewById(R.id.settingBtn);
        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity() , AccountSetting.class);
                startActivity(intent);
            }
        });

        final TabHost tabHost = (TabHost) view.findViewById(R.id.tabHost);

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {

            @Override
            public void onTabChanged(String s) {

                // 눌려지지 않은 탭 색깔 지정
                for(int i=0; i< tabHost.getTabWidget().getChildCount() ; i++){
                    tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#E6E6E6"));

                    TextView tv = (TextView)tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
                    tv.setTextColor(Color.parseColor("#9E9E9E"));
                }

                // 눌려진 탭 색깔 지정
                tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab())
                        .setBackgroundColor(Color.parseColor("#FFE400"));

                TextView tv = (TextView)tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).findViewById(android.R.id.title);
                tv.setTextColor(Color.parseColor("#000000"));
            }
        });

        tabHost.setup();

        TabHost.TabSpec spec;

        spec = tabHost.newTabSpec("Tab 00");
        spec.setIndicator("내가 쓴 글");
        spec.setContent(R.id.tabview1);
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("Tab 01");
        spec.setIndicator("내가 참여한 투표");
        spec.setContent(R.id.tabview2);
        tabHost.addTab(spec);

        tabHost.setCurrentTab(1);
        tabHost.setCurrentTab(0);

        return view;
    }
}