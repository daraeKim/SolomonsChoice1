package mbc.fonfon.sun.solomonschoice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView homebtn;
    ImageView bestbtn;
    ImageView alrambtn;
    ImageView mypagebtn;
    LinearLayout selectTitle;
    FrameLayout content;
    boolean viewGroupIsVisible = false;
    TextView solmon;
    LinearLayout selectmenu;
    Button first, second;
//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        first = (Button) findViewById(R.id.first);
        second = (Button) findViewById(R.id.second);
        solmon = (TextView) findViewById(R.id.solmon);

        homebtn = (ImageView) findViewById(R.id.homebtn);
        homebtn.setOnClickListener(this);

        bestbtn = (ImageView) findViewById(R.id.bestbtn);
        bestbtn.setOnClickListener(this);

        alrambtn = (ImageView) findViewById(R.id.alrambtn);
        alrambtn.setOnClickListener(this);

        mypagebtn = (ImageView) findViewById(R.id.mypagebtn);
        mypagebtn.setOnClickListener(this);

        selectmenu = (LinearLayout) findViewById(R.id.selectmenu);

        selectmenu.setVisibility(View.GONE);

        content = (FrameLayout) findViewById(R.id.content);
        content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewGroupIsVisible) {
                    selectmenu.setVisibility(View.GONE);
                    viewGroupIsVisible = false;
                }
            }
        });
        selectTitle = (LinearLayout) findViewById(R.id.selectTitle);
        selectTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewGroupIsVisible) {
                    selectmenu.setVisibility(View.GONE);
                    viewGroupIsVisible = false;
                } else {
                    selectmenu.setVisibility(View.VISIBLE);
                    first.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String text = (String) first.getText();
                            first.setText(solmon.getText());
                            solmon.setText(text);
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
                            selectmenu.setVisibility(View.GONE);
                            viewGroupIsVisible = !viewGroupIsVisible;
                        }
                    });


                    viewGroupIsVisible = true;

                }

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.homebtn:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content, new Photoboard())
                        .commit();
                break;

            case R.id.bestbtn:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content, new Bestboard())
                        .commit();
                break;

            case R.id.alrambtn:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content, new Alramboard())
                        .commit();
                break;

            case R.id.mypagebtn:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content, new Mypage())
                        .commit();
                break;
        }
    }
}


