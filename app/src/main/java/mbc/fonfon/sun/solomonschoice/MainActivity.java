package mbc.fonfon.sun.solomonschoice;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity implements OnClickListener{

    //import android.support.v4.app.Fragment;

    //import android.support.v4.app.FragmentTransaction;
    ImageView homebtn, bestbtn, alrambtn, mypagebtn;
    int selectFragment;

    public final static int FRAGMENT_HOME = 0;
    public final static int FRAGMENT_BEST = 1;
    public final static int FRAGMENT_ALRAM = 2;
    public final static int FRAGMENT_MYPAGE = 3;

    /*LinearLayout selectTitle;
    LinearLayout content;
    boolean viewGroupIsVisible = false;
    TextView solmon;
    LinearLayout selectmenu;
    Button first, second;
    //MainBoard mainboard;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homebtn = (ImageView) findViewById(R.id.homebtn);
        bestbtn = (ImageView) findViewById(R.id.bestbtn);
        alrambtn = (ImageView) findViewById(R.id.writebtn);
        mypagebtn = (ImageView) findViewById(R.id.mypagebtn);

        homebtn.setOnClickListener(this);
        bestbtn.setOnClickListener(this);
        alrambtn.setOnClickListener(this);
        mypagebtn.setOnClickListener(this);

        selectFragment = FRAGMENT_HOME;

        fragmentReplace(selectFragment);

    }

    public void fragmentReplace(int reqNewFragmentIndex) {

        Fragment newFragment = null;

        newFragment = getFragment(reqNewFragmentIndex);

        // replace fragment
        final FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();

        transaction.replace(R.id.content, newFragment);

        // Commit the transaction
        transaction.commit();

    }

    private Fragment getFragment(int idx) {
        Fragment newFragment = null;

        switch (idx) {
            case FRAGMENT_HOME:
                newFragment = new MainBoard();
                break;
            case FRAGMENT_BEST:
                newFragment = new Bestboard();
                break;
            case FRAGMENT_ALRAM:
                newFragment = new Alramboard();
                break;
            case FRAGMENT_MYPAGE:
                newFragment = new Mypage();
                break;
        }

        return newFragment;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.homebtn:
                selectFragment = FRAGMENT_HOME;
                fragmentReplace(selectFragment);
                break;

            case R.id.bestbtn:
                selectFragment = FRAGMENT_BEST;
                fragmentReplace(selectFragment);
                break;

            case R.id.writebtn:
                selectFragment = FRAGMENT_ALRAM;
                fragmentReplace(selectFragment);
                break;

            case R.id.mypagebtn:
                selectFragment = FRAGMENT_MYPAGE;
                fragmentReplace(selectFragment);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        String alertTitle = getResources().getString(R.string.app_name);
        String buttonMessage = getResources().getString(R.string.msg);
        String buttonYes = getResources().getString(R.string.btn_yes);
        String buttonNo = getResources().getString(R.string.btn_no);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(alertTitle);
        builder.setMessage(buttonMessage);
        builder.setNegativeButton(buttonNo, null);
        builder.setPositiveButton(buttonYes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                moveTaskToBack(true);
                finish();
            }
        });

        builder.show();
    }
}