package com.example.repeatregistrationeventlistener;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // 지뢰찾기에 필요한 배열
    int mine[][] = {{0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0}};
    int isWin = 20;   // 내부 익명클래스에서 disCount 하기 위해

    TextView resultText;

    // ☆. 버튼을 연결하기 위한 객체 배열
    Button btn[][] = {new Button[5], new Button[5], new Button[5], new Button[5], new Button[5]};

    // ☆. 이벤트 리스너를 반복문의 형태로 처리하기 위한 id 배열
    int ids[][] = { {R.id.x0y0, R.id.x0y1, R.id.x0y2, R.id. x0y3, R.id.x0y4},
                    {R.id.x1y0, R.id.x1y1, R.id.x1y2, R.id. x1y3, R.id.x1y4},
                    {R.id.x2y0, R.id.x2y1, R.id.x2y2, R.id. x2y3, R.id.x2y4},
                    {R.id.x3y0, R.id.x3y1, R.id.x3y2, R.id. x3y3, R.id.x3y4},
                    {R.id.x4y0, R.id.x4y1, R.id.x4y2, R.id. x4y3, R.id.x4y4} };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultText = (TextView)findViewById(R.id.resultText);

        // ☆. 반복문의 형태로 xml ID와 Java 객체간 버튼 연결
        for(int x = 0; x < 5; x++) {
            for(int y = 0; y < 5; y++) {
                btn[x][y] = (Button)findViewById(ids[x][y]);
            }
        }

        // 지뢰 심기
        for(int i = 0; i < 5; i++) {
            while(true) {
                int x = new Random().nextInt(5);
                int y = new Random().nextInt(5);
                if (mine[x][y] == 0) {
                    mine[x][y] = -1;
                    break;
                }
            }
        }

        // 지뢰찾기 규칙에 따른 배열값 재설정
        for(int x = 0; x < 5; x++) {
            for(int y = 0; y < 5; y++) {
                if(mine[x][y] != -1) {
                    int mineCount = 0;
                    // 여덟 방향(12시부터)의 배열에 지뢰가 있는지 판단
                    try { if(mine[x-1][y] == -1) mineCount++; }
                    catch (ArrayIndexOutOfBoundsException e) {
                        Log.e("mineArray", "첨자범위 이탈"); }

                    try { if(mine[x-1][y+1] == -1) mineCount++; }
                    catch (ArrayIndexOutOfBoundsException e) {
                        Log.e("mineArray", "첨자범위 이탈"); }

                    try { if(mine[x][y+1] == -1) mineCount++; }
                    catch (ArrayIndexOutOfBoundsException e) {
                        Log.e("mineArray", "첨자범위 이탈"); }

                    try { if(mine[x+1][y+1] == -1) mineCount++; }
                    catch (ArrayIndexOutOfBoundsException e) {
                        Log.e("mineArray", "첨자범위 이탈"); }

                    try { if(mine[x+1][y] == -1) mineCount++; }
                    catch (ArrayIndexOutOfBoundsException e) {
                        Log.e("mineArray", "첨자범위 이탈"); }

                    try { if(mine[x+1][y-1] == -1) mineCount++; }
                    catch (ArrayIndexOutOfBoundsException e) {
                        Log.e("mineArray", "첨자범위 이탈"); }

                    try { if(mine[x][y-1] == -1) mineCount++; }
                    catch (ArrayIndexOutOfBoundsException e) {
                        Log.e("mineArray", "첨자범위 이탈"); }

                    try { if(mine[x-1][y-1] == -1) mineCount++; }
                    catch (ArrayIndexOutOfBoundsException e) {
                        Log.e("mineArray", "첨자범위 이탈"); }

                    mine[x][y] = mineCount;
                }
            }
        }

        // ☆. 동일한(비슷한) 작업의 리스너를 등록할 때
        // 반복문을 사용하여 일괄 등록하는 방법
        // (내부 익명 클래스 형태의 Listener)

        for(int x = 0; x < 5; x++) {
            for(int y = 0; y < 5; y++) {
                // ☆. 내부 익명 클래스에서 접근할 수 있도록 정적 변수의 형태로 인덱스 접근
                final int indexX, indexY;
                indexX = x;
                indexY = y;

                btn[indexX][indexY].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(mine[indexX][indexY] == 0) {
                            btn[indexX][indexY].setText("");
                            btn[indexX][indexY].setBackgroundColor(Color.GRAY);
                        }
                        else if (mine[indexX][indexY] == -1) {
                            btn[indexX][indexY].setText("♨");
                            resultText.setText("으앙쥬금 ㅠㅠ");
                            printMine();
                        }
                        else
                            btn[indexX][indexY].setText(String.valueOf(mine[indexX][indexY]));
                        isWin--;
                        btn[indexX][indexY].setClickable(false);

                        if(isWin == 0) {
                            resultText.setText("지뢰찾기 성공 ^!^");
                            printMine();
                        }
                    }

                    public void printMine() {
                        for(int x = 0; x < 5; x++) {
                            for(int y = 0; y < 5; y++) {
                                btn[x][y].setBackgroundColor(Color.GRAY);
                                btn[x][y].setClickable(false);
                                btn[x][y].setText(String.valueOf(mine[x][y]));
                                if(mine[x][y] == -1) {
                                    btn[x][y].setText("♨");
                                    btn[x][y].setBackgroundColor(Color.RED);
                                }
                            }
                        }
                    }
                });

            }
        }

    }
}
