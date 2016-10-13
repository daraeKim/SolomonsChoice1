package mbc.fonfon.sun.solomonschoice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by soldesk on 2016-10-07.
 */
public class Mypage extends Fragment {
    Button LoginBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mypage, container, false);

        LoginBtn = (Button) view.findViewById(R.id.LoginBtn);
        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Intent intent = new Intent(getActivity() , LoginPage.class);
                 startActivity(intent);
            }
        });
        return view;
    }
}
