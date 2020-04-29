package com.example.fragment02;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentOne extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // 매개변수1 : 팽창시키고자 하는 레이아웃의 리소스 ID.
        // 매개변수2 : 팽창된 레이아웃의 상위가 될 ViewGroup.
        // 매개변수3 : 팽창된 레이아웃이 팽창 중에 ViewGroup(두 번째 매개변수)에 첨부되어야 하는지를 나타내는 부울 값.
        return inflater.inflate(R.layout.fragment01, container, false);
    }
}
