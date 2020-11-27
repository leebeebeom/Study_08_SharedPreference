package com.beebeom.a08_sharedpreference;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText mEditTextEmail;
    private CheckBox mCheckBox;
    private SharedPreferences mPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditTextEmail = findViewById(R.id.et_email);
        mCheckBox = findViewById(R.id.cb_remember);

        //액티비티 생성시
        //프리퍼런스 객체 초기화
        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        //저장된 데이터 가져오기
        //defValue 는 디폴트 벨류로 저장된 값이 없을 시 불려오는 데이터.
        boolean remember = mPreferences.getBoolean("remember", false);
        //종료시 체크되고 종료됐다면
        if(remember){
            String email = mPreferences.getString("email", null);
            mEditTextEmail.setText(email);
            mCheckBox.setChecked(remember);
        }

    }

    //액티비티 종료시 호출됨
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //쉐어드프리퍼런스 객체를 편집할 수 있는 객체 얻기
        SharedPreferences.Editor editor = mPreferences.edit();
        //데이터 넣기
        editor.putString("email", mEditTextEmail.getText().toString());
        editor.putBoolean("remember", mCheckBox.isChecked());
        //저장
        editor.apply();
    }
}