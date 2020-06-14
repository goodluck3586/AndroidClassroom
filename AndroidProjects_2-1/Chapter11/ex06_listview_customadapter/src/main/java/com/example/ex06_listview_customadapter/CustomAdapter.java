package com.example.ex06_listview_customadapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;

class CustomAdapter extends BaseAdapter {

    Context context;
    int resource;
    ArrayList<String> dataList;

    public CustomAdapter(Context context, int resource, ArrayList<String> dataList) {
        this.context = context;
        this.resource = resource;
        this.dataList = dataList;
    }

    // 리스트뷰에 표시할 항목 개수 반환
    @Override
    public int getCount() {
        return dataList.size();
    }

    // 지정한 데이터 반환
    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    // 리스트뷰에 출력될 항목의 View 생성
    // 화면에 보여지는 개수만큼 View를 생성
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // 재사용 가능한 convertView가 없으면 새로 생성
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(resource, null);
        }

        // converView 세팅하기
        TextView textView = convertView.findViewById(R.id.textViewRow);
        textView.setText(dataList.get(position));
        Button btnModify = convertView.findViewById(R.id.btnModify);
        Button btnDelete = convertView.findViewById(R.id.btnDelete);

        //region 수정 버튼 이벤트 등록
        btnModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText editText = new EditText(context);
                new AlertDialog.Builder(context)
                    .setTitle("리스트 아이템 수정")
                    .setMessage("현재 데이터: "+dataList.get(position))
                    .setIcon(R.mipmap.ic_launcher)
                    .setCancelable(false)
                    .setView(editText)
                    .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dataList.set(position, editText.getText().toString());
                            notifyDataSetChanged();
                        }
                    })
                    .setNegativeButton("취소", null)
                    .show();
            }

        });
        //endregion

        //region 삭제 버튼 이벤트 등록
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context)
                    .setTitle("리스트 아이템 삭제")
                    .setMessage("정말 삭제하시겠습니까?")
                    .setIcon(R.mipmap.ic_launcher_remove)
                    .setCancelable(false)
                    .setPositiveButton("예", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dataList.remove(position);
                            notifyDataSetChanged();
                        }
                    })
                    .setNegativeButton("아니오", null)
                    .show();
            }
        });

        return convertView;
    }
}
