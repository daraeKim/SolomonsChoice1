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
public class Writeboard extends Fragment {

    Button write_LoginBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.write_board, container, false);

        // 로그인 하기 전 화면
        write_LoginBtn = (Button) view.findViewById(R.id.write_LoginBtn);
        write_LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LoginPage.class);
                startActivity(intent);
            }
        });

        return view;
    }
}


