package mbc.fonfon.sun.solomonschoice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SlidingDrawer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView homebtn;
    ImageView bestbtn;
    ImageView alrambtn;
    ImageView mypagebtn;
    Button btnClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homebtn = (ImageView) findViewById(R.id.homebtn);
        homebtn.setOnClickListener(this);

        bestbtn = (ImageView) findViewById(R.id.bestbtn);
        bestbtn.setOnClickListener(this);

        alrambtn = (ImageView) findViewById(R.id.alrambtn);
        alrambtn.setOnClickListener(this);

        mypagebtn = (ImageView) findViewById(R.id.mypagebtn);
        mypagebtn.setOnClickListener(this);

        btnClose = (Button)findViewById(R.id.btnclose);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SlidingDrawer drawer = (SlidingDrawer)findViewById(R.id.slide);
                drawer.animateClose();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.homebtn:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frag_container_, new Photoboard())
                        .commit();
                break;

            case R.id.bestbtn:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frag_container_, new Bestboard())
                        .commit();
                break;

            case R.id.alrambtn:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frag_container_, new Alramboard())
                        .commit();
                break;

            case R.id.mypagebtn:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frag_container_, new Mypage())
                        .commit();
                break;
        }
    }
}


