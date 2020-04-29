package com.example.fragment02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn01, btn02;
    LinearLayout linearLayout;

    FragmentManager fragmentManager;
    FragmentTransaction transaction;
    FragmentOne fragmentOne;
    FragmentTwo fragmentTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn01 = findViewById(R.id.btn01);
        btn02 = findViewById(R.id.btn02);
        linearLayout = findViewById(R.id.linearLayout_container);

        btn01.setOnClickListener(this);
        btn02.setOnClickListener(this);

        fragmentManager = getSupportFragmentManager();  // 이 Activity와 관련된 Fragment와 상호작용하기 위한 FragmentManager를 반환
        fragmentOne = new FragmentOne();
        fragmentTwo = new FragmentTwo();

//        transaction = fragmentManager.beginTransaction();                   // Fragment가 화면에 출력되는 것을 제어
//        transaction.add(R.id.linearLayout_container, fragmentOne);
//        transaction.addToBackStack(null);                                // Back 버튼에 의해서 이전 Fragment가 나오는것 하지 않음.
//        transaction.commit();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn01:
                if(!fragmentOne.isVisible()){
                    transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.linearLayout_container, fragmentOne);
                    transaction.addToBackStack(null);
                    transaction.commitAllowingStateLoss();
                }
                break;
            case R.id.btn02:
                if(!fragmentTwo.isVisible()){
                    transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.linearLayout_container, fragmentTwo);
                    transaction.addToBackStack(null);
                    transaction.commitAllowingStateLoss();
                }
                break;
        }
    }
}
