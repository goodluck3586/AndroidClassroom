package com.example.makeself_fragment;

import android.accounts.Account;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentAccount extends Fragment {

    String[] NTemp = new String[] {"44ºC", "41ºC", "38ºC", "25ºC", "15ºC"};
    String[] NWater = new String[] {"70%", "60%", "40%", "20%"};
    String[] NPrepars = new String[] {"O", "X"};

    View v;

   // public LinearLayout linearLayout = getAct().findViewById(R.id.bigLayout);
    int Cnt = 0;
    String[][] ArrOfSettings = new String[][]{{"O","2","3","1"},{"O","1","1","1"},{"X","0","0","0"},{"X","0","0","0"},{"X","0","0","0"},{"X","0","0","0"},
            {"X","0","0","0"},{"X","0","0","0"},{"X","0","0","0"},{"X","0","0","0"}};

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final MainActivity activity = (MainActivity)getActivity();
        //Toast.makeText(activity, "OnCreateView 실행", Toast.LENGTH_SHORT).show();
        v = inflater.inflate(R.layout.fragment_account, container, false);

        LinearLayout linearLayout = v.findViewById(R.id.bigLayout);
        int j = 2;
        //region Setting바? 생성하기




        //String LayoutID = linearLayout + String.valueOf(i) ;
        //String.valueOf(LayoutID).toString();


//        Temp.setText(NTemp[Integer.parseInt(String.valueOf(ArrOfSettings[1][1]))]);
//        Water.setText(NWater[Integer.parseInt(String.valueOf(ArrOfSettings[1][2]))]);
//        Prepar.setText(NPrepars[Integer.parseInt(String.valueOf(ArrOfSettings[1][3]))]);
//        linearLayout.addView(newLayout);

        //nLayout.addView(Temp);
       // nLayout.addView(Water);
       // nLayout.addView(Prepar);





        //endregion


        showSettings();

        final LinearLayout add = v.findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onFragmentChanged(1);
            }
        });
        showSettings();
            return v;
    }

    public void showSettings() {
        //FragmentAccount fragmentAccount = new FragmentAccount();
        for(int i = 0; i < 10; i++)
        {
            if(ArrOfSettings[i][0] == "O")
            {
                int D1 = Integer.parseInt(ArrOfSettings[i][1]);
                int D2 = Integer.parseInt(ArrOfSettings[i][2]);
                int D3 = Integer.parseInt(ArrOfSettings[i][3]);

                //FragmentAccount fragmentAccount = new FragmentAccount();
                TextView Temp = new TextView(getAct());
                TextView Water = new TextView(getAct());
                TextView Prepar = new TextView(getAct());


                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                params.weight = 1.0f;
                //params.gravity = Gravity.CENTER;


                //Temp.setText(NTemp[Integer.parseInt(String.valueOf(ArrOfSettings[i][1]))]);
                Temp.setTextSize(40);
                Temp.setLayoutParams(params);
                Temp.setGravity(Gravity.CENTER);


                //Water.setText(NWater[Integer.parseInt(String.valueOf(ArrOfSettings[i][2]))]);
                Water.setTextSize(40);
                Water.setLayoutParams(params);
                Water.setGravity(Gravity.CENTER);

                //Prepar.setText(NPrepars[Integer.parseInt(String.valueOf(ArrOfSettings[i][3]))]);
                Prepar.setTextSize(40);
                Prepar.setLayoutParams(params);
                Prepar.setGravity(Gravity.CENTER);

                LinearLayout newLayout = new LinearLayout(getAct());

                int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,120,getResources().getDisplayMetrics());
                LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,height);

                newLayout.setLayoutParams(param);
                newLayout.setBackgroundResource(R.drawable.border);

                Temp.setText(NTemp[D1]);
                Water.setText(NWater[D2]);
                Prepar.setText(NPrepars[D3]);

                newLayout.addView(Temp);
                newLayout.addView(Water);
                newLayout.addView(Prepar);

                //newLayout.removeView(newLayout);

                //newLayout.setLayoutParams(param);
                LinearLayout linearLayout = v.findViewById(R.id.bigLayout);
                linearLayout.addView(newLayout, 0);
                AccountSetting accountSetting = new AccountSetting();
                accountSetting.Count++;
            }
            else
            {
                String str = ArrOfSettings[2][0] + ArrOfSettings[2][1] + ArrOfSettings[2][2] + ArrOfSettings[2][3];
                //Toast.makeText(getActivity(),str,Toast.LENGTH_SHORT).show(); //선택된 온도 토스트 띄우기
            }

        }
    }
    public void actUpdate()
    {
        FragmentAccount fragmentAccount = new FragmentAccount();
        Activity aaa = getActivity();
        Activity AAA = getAct();
        Activity MA = new MainActivity();
        MainActivity mainActivity = new MainActivity();

        LinearLayout linearLayout = getActivity().findViewById(R.id.bigLayout);
        linearLayout.removeAllViews();
        showSettings();
    }

    public Activity getAct(){
        final MainActivity activity = (MainActivity)getActivity();
        return activity;
    }

}

