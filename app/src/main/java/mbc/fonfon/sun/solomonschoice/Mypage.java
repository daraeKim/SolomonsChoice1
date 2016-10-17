package mbc.fonfon.sun.solomonschoice;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TabHost;

/**
 * Created by soldesk on 2016-10-07.
 */
public class Mypage extends Fragment{
    Button LoginBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mypage_after_login, container, false);

        // mypage
/*        LoginBtn = (Button) view.findViewById(R.id.LoginBtn);
        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Intent intent = new Intent(getActivity() , LoginPage.class);
                 startActivity(intent);
            }
        });*/

        TabHost tabHost = (TabHost)view.findViewById(R.id.tabHost);

        tabHost.setup();

        TabHost.TabSpec spec;

        spec = tabHost.newTabSpec("Tab 00");
        spec.setIndicator("내가쓴글");
        spec.setContent(R.id.tabview1);
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("Tab 01");
        spec.setIndicator("내가 참여한 투표");
        spec.setContent(R.id.tabview2);
        tabHost.addTab(spec);

        tabHost.setCurrentTab(0);

        return view;
    }
}
