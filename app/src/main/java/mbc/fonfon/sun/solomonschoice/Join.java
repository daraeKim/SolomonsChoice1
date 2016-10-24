package mbc.fonfon.sun.solomonschoice;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class Join extends AppCompatActivity implements View.OnClickListener {

    TextView title;

    //회원가입의 멤버 변수
    EditText email, nickName, age, password, passwordConfirm;
    Button gender_m, gender_w;

    static String sex = "";     //성별 나누기 위한 수단


    //중복확인의 멤버 변수
    Button nickCheck, emailCheck;



    ImageView back_btn, user_image;
    Button  join_ok;




    LinearLayout layout_join;
    private static final int PICK_FROM_CAMERA = 0;
    private static final int PICK_FROM_ALBUM = 1;
    private static final int CROP_FROM_CAMERA = 2;


    private Uri mImageCaptureUri;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.rgb(216, 216, 216)));
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_style);
        title = (TextView) findViewById(R.id.actionbar_setting);
        title.setText("회원 가입");


        //회원가입 양식의 입력값들
        email = (EditText) findViewById(R.id.email);
        nickName = (EditText) findViewById(R.id.nickName);
        age = (EditText) findViewById(R.id.age);
        password = (EditText) findViewById(R.id.password);
        passwordConfirm =(EditText) findViewById(R.id.passwordConfirm);
        gender_m = (Button) findViewById(R.id.gender_m);
        gender_w = (Button) findViewById(R.id.gender_w);


        //중복확인
        emailCheck = (Button)findViewById(R.id.emailCheck);     //이메일 중복확인
        nickCheck = (Button) findViewById(R.id.nickCheck);     //닉네임 중복확인





        back_btn = (ImageView) findViewById(R.id.back);
        user_image = (ImageView) findViewById(R.id.user_image);

        join_ok = (Button) findViewById(R.id.join_ok);

        layout_join = (LinearLayout) findViewById(R.id.layout_join);


        //버튼을 눌렀을때
        layout_join.setOnClickListener(this);
        back_btn.setOnClickListener(this);
        user_image.setOnClickListener(this);
        gender_m.setOnClickListener(this);
        gender_w.setOnClickListener(this);
        join_ok.setOnClickListener(this);
        nickCheck.setOnClickListener(this);
        emailCheck.setOnClickListener(this);
    }

    /**
     * 카메라에서 이미지 가져오기
     */
    private void doTakePhotoAction() {
    /*
     * 참고 해볼곳
     * http://2009.hfoss.org/Tutorial:Camera_and_Gallery_Demo
     * http://stackoverflow.com/questions/1050297/how-to-get-the-url-of-the-captured-image
     * http://www.damonkohler.com/2009/02/android-recipes.html
     * http://www.firstclown.us/tag/android/
     */

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // 임시로 사용할 파일의 경로를 생성
        String url = "tmp_" + String.valueOf(System.currentTimeMillis()) + ".jpg";
        mImageCaptureUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), url));

        intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
        // 특정기기에서 사진을 저장못하는 문제가 있어 다음을 주석처리 합니다.
        //intent.putExtra("return-data", true);
        startActivityForResult(intent, PICK_FROM_CAMERA);
    }

    /**
     * 앨범에서 이미지 가져오기
     */
    private void doTakeAlbumAction() {
        // 앨범 호출
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, PICK_FROM_ALBUM);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        }

        switch (requestCode) {
            case CROP_FROM_CAMERA: {
                // 크롭이 된 이후의 이미지를 넘겨 받습니다.
                // 이미지뷰에 이미지를 보여준다거나 부가적인 작업 이후에
                // 임시 파일을 삭제합니다.
                final Bundle extras = data.getExtras();

                if (extras != null) {
                    Bitmap photo = extras.getParcelable("data");
                    user_image.setImageBitmap(photo);
                }

                // 임시 파일 삭제
                File f = new File(mImageCaptureUri.getPath());
                if (f.exists()) {
                    f.delete();
                }

                break;
            }

            case PICK_FROM_ALBUM: {
                // 이후의 처리가 카메라와 같으므로 일단  break없이 진행합니다.
                // 실제 코드에서는 좀더 합리적인 방법을 선택하시기 바랍니다.

                mImageCaptureUri = data.getData();
            }

            case PICK_FROM_CAMERA: {
                // 이미지를 가져온 이후의 리사이즈할 이미지 크기를 결정합니다.
                // 이후에 이미지 크롭 어플리케이션을 호출하게 됩니다.

                Intent intent = new Intent("com.android.camera.action.CROP");
                intent.setDataAndType(mImageCaptureUri, "image/*");

                intent.putExtra("outputX", 100);
                intent.putExtra("outputY", 100);
                intent.putExtra("aspectX", 1);
                intent.putExtra("aspectY", 1);
                intent.putExtra("scale", true);
                intent.putExtra("return-data", true);
                startActivityForResult(intent, CROP_FROM_CAMERA);

                break;
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.back:
                finish();
                break;

            case R.id.user_image:


                DialogInterface.OnClickListener cameraListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        doTakePhotoAction();
                    }
                };

                DialogInterface.OnClickListener albumListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        doTakeAlbumAction();
                    }
                };

                DialogInterface.OnClickListener cancelListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                };

                new AlertDialog.Builder(this)
                        .setTitle("업로드할 이미지 선택")
                        .setPositiveButton("사진촬영", cameraListener)
                        .setNeutralButton("앨범선택", albumListener)
                        .setNegativeButton("취소", cancelListener)
                        .show();
                break;



            case R.id.gender_m:      //남자 버튼을 눌렀을때
                    gender_m.setBackgroundResource(R.drawable.button_style2);   //남자 버튼이 노란 테두리
                    gender_w.setBackgroundResource(R.drawable.button_style3);  //여자 버튼이 회색 테두리
                 sex = "남자";         //sex 값을 남자로
                break;

            case R.id.gender_w:
                    gender_w.setBackgroundResource(R.drawable.button_style2);  //여자 버튼이 노란테두리
                    gender_m.setBackgroundResource(R.drawable.button_style3);  //남자 버튼이 회색태두리
                 sex = "여자";         //sex값을 여자로
                break;



            case R.id.join_ok:      //회원가입 버튼을 눌렀을때
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);      //쓰레드 정책

                String sMessage = email.getText().toString();      //email입력학 값을 sMessage로 저장

                String result = SendByHttp(sMessage);              //sMessage를 SendByHttp메소드로 호출 해 결과값얻어옴
                String[][] parsedData = jsonParserList(result);

                if(parsedData[0][0].equals("succed"))
                {
                    Toast.makeText(this, "가입 성공", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(this, LoginPage.class);
                    startActivity(intent);
                    finish();
                } else if (parsedData[0][0].equals("failed")) {
                    Toast.makeText(this, "가입 실패", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(this, Join.class);
                    startActivity(intent);
                    finish();
                }
                break;

            case R.id.layout_join:
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(nickName.getWindowToken(), 0);
                break;


            case R.id.emailCheck:     //이메일 중복 체크 확인
                StrictMode.ThreadPolicy policy1 = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy1);      //쓰레드 정책

                String emchk = email.getText().toString();      //email입력학 값을 sMessage로 저장

                String result1 = SendByHttp1(emchk);
                String[][] parsedData1 = jsonParserList1(result1);

                if(parsedData1[0][0].equals("succed"))
                {
                    Toast.makeText(this, "사용가능한 이메일입니다.", Toast.LENGTH_LONG).show();
                } else if (parsedData1[0][0].equals("failed"))
                {
                    Toast.makeText(this, "이미 사용하고 있는 이메일입니다.", Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.nickCheck:     //
                StrictMode.ThreadPolicy policy2 = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy2);      //쓰레드 정책

                String nkchk = nickName.getText().toString();      //email입력학 값을 sMessage로 저장

                String result2 = SendByHttp2(nkchk);
                String[][] parsedData2 = jsonParserList2(result2);

                if(parsedData2[0][0].equals("succed"))
                {
                    Toast.makeText(this, "사용가능한 닉네임입니다.", Toast.LENGTH_LONG).show();
                } else if (parsedData2[0][0].equals("failed"))
                {
                    Toast.makeText(this, "이미 사용하고 있는 닉네임입니다.", Toast.LENGTH_LONG).show();
                }
            break;


        }
    }

    //회원가입 insert SendByHttp
    private String SendByHttp(String msg)
    {
        if (msg == null) {
            msg = "";
        }
        String URL = "http://192.168.0.10:8088/solomonschoice/solomon/join.jsp";
        DefaultHttpClient client = new DefaultHttpClient();
        try {
            HttpPost post = new HttpPost(URL + "?email=" + email.getText().toString()+"&nickName="+nickName.getText().toString()
                    +"&age="+age.getText().toString()+"&password="+password.getText().toString()
                    +"&passwordConfirm="+passwordConfirm.getText().toString()+"&sex="+sex.toString());
            HttpResponse response = client.execute(post);
            BufferedReader bufreader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "utf-8"));
            String line = null;
            String result = "";

            while ((line = bufreader.readLine()) != null) {
                result += line;
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            client.getConnectionManager().shutdown();
            return "";
        }
    }
    private String[][] jsonParserList(String pRecvServerPage) {
        try {
            JSONObject json = new JSONObject(pRecvServerPage);
            JSONArray jArr = json.getJSONArray("ResultSet");

            String[] jsonName = {"check","email","nickName","age","password","passwordConfirm","sex"};
            String[][] parseredData = new String[jArr.length()][jsonName.length];
            for (int i = 0; i < jArr.length(); i++) {
                json = jArr.getJSONObject(i);
                for (int j = 0; j < jsonName.length; j++) {
                    parseredData[i][j] = json.getString(jsonName[j]);
                }
            }
            return parseredData;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }


    //email 중복 체크
    private String SendByHttp1(String msg)
    {
        if (msg == null) {
            msg = "";
        }
        String URL = "http://192.168.0.10:8088/solomonschoice/solomon/chk/emailchk.jsp";
        DefaultHttpClient client = new DefaultHttpClient();
        try {
            HttpPost post = new HttpPost(URL + "?email=" + email.getText().toString());
            HttpResponse response = client.execute(post);
            BufferedReader bufreader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "utf-8"));
            String line = null;
            String result = "";

            while ((line = bufreader.readLine()) != null) {
                result += line;
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            client.getConnectionManager().shutdown();
            return "";
        }
    }
    private String[][] jsonParserList1(String pRecvServerPage) {
        try {
            JSONObject json = new JSONObject(pRecvServerPage);
            JSONArray jArr = json.getJSONArray("ResultSet");

            String[] jsonName = {"check","email"};
            String[][] parseredData1 = new String[jArr.length()][jsonName.length];
            for (int i = 0; i < jArr.length(); i++) {
                json = jArr.getJSONObject(i);
                for (int j = 0; j < jsonName.length; j++) {
                    parseredData1[i][j] = json.getString(jsonName[j]);
                }
            }
            return parseredData1;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    //닉네임 중복 체크
    private String SendByHttp2(String msg)
    {
        if (msg == null) {
            msg = "";
        }
        String URL = "http://192.168.0.10:8088/solomonschoice/solomon/chk/nickchk.jsp";
        DefaultHttpClient client = new DefaultHttpClient();
        try {
            HttpPost post = new HttpPost(URL + "?nickName=" + nickName.getText().toString());
            HttpResponse response = client.execute(post);
            BufferedReader bufreader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "utf-8"));
            String line = null;
            String result = "";

            while ((line = bufreader.readLine()) != null) {
                result += line;
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            client.getConnectionManager().shutdown();
            return "";
        }
    }
    private String[][] jsonParserList2(String pRecvServerPage) {
        try {
            JSONObject json = new JSONObject(pRecvServerPage);
            JSONArray jArr = json.getJSONArray("ResultSet");

            String[] jsonName = {"check","nickName"};
            String[][] parseredData2 = new String[jArr.length()][jsonName.length];
            for (int i = 0; i < jArr.length(); i++) {
                json = jArr.getJSONObject(i);
                for (int j = 0; j < jsonName.length; j++) {
                    parseredData2[i][j] = json.getString(jsonName[j]);
                }
            }
            return parseredData2;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
