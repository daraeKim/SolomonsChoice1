package mbc.fonfon.sun.solomonschoice;

import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by soldesk on 2016-10-11.
 */
public class MainBoard extends Fragment {
    LinearLayout selectTitle;
    LinearLayout content;
    boolean viewGroupIsVisible = false;
    TextView solmon;
    LinearLayout selectmenu;
    Button first, second;
    ImageView icon;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_board, container, false);

        first = (Button) view.findViewById(R.id.first);
        second = (Button) view.findViewById(R.id.second);
        solmon = (TextView) view.findViewById(R.id.solmon);
        icon = (ImageView) view.findViewById(R.id.icon);

        selectmenu = (LinearLayout) view.findViewById(R.id.selectmenu);

        selectmenu.setVisibility(View.GONE);

        content = (LinearLayout) view.findViewById(R.id.content);
        content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewGroupIsVisible) {
                    selectmenu.setVisibility(View.GONE);
                    icon.setImageResource(android.R.drawable.arrow_down_float);
                    viewGroupIsVisible = false;
                }
            }
        });

        selectTitle = (LinearLayout) view.findViewById(R.id.selectTitle);
        selectTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewGroupIsVisible) {
                    selectmenu.setVisibility(View.GONE);
                    icon.setImageResource(android.R.drawable.arrow_down_float);
                    viewGroupIsVisible = false;
                } else {
                    selectmenu.setVisibility(View.VISIBLE);
                    icon.setImageResource(android.R.drawable.arrow_up_float);
                    first.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String text = (String) first.getText();
                            first.setText(solmon.getText());
                            solmon.setText(text);
                            icon.setImageResource(android.R.drawable.arrow_down_float);
                            selectmenu.setVisibility(View.GONE);
                            viewGroupIsVisible = !viewGroupIsVisible;
                        }
                    });

                    second.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String text = (String) second.getText();
                            second.setText(solmon.getText());
                            solmon.setText(text);
                            icon.setImageResource(android.R.drawable.arrow_down_float);
                            selectmenu.setVisibility(View.GONE);
                            viewGroupIsVisible = !viewGroupIsVisible;
                        }
                    });
                    viewGroupIsVisible = true;

                }

            }
        });

        return view;
    }
}
