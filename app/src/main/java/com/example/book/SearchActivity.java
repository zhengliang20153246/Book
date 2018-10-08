package com.example.book;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SearchActivity extends AppCompatActivity {
    private EditText searchedit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchedit=(EditText)findViewById(R.id.searchedit);

        Button searchbookname=(Button)findViewById(R.id.searchbookname);
        searchbookname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchedit.setHint("请输入书名");

            }
        });

        Button searchISBN=(Button)findViewById(R.id.searchISBN);
        searchISBN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchedit.setHint("请输入ISBN");

            }
        });
        Button searchzuozhe=(Button)findViewById(R.id.searchzuozhe);
        searchzuozhe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchedit.setHint("请输入作者");

            }
        });
        Button searchxiaochu=(Button)findViewById(R.id.searchxiaochu);
        searchxiaochu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchedit.setText("");
            }
        });
        Button searchbutton=(Button)findViewById(R.id.searchsousuo);
        searchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search=searchedit.getText().toString();
                if(search.equals(""))
                {
                    Toast.makeText(SearchActivity.this, "请输入内容！", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (searchedit.getHint().toString().equals("请输入书名")) {
                        Myuser.toolbartitle = "书籍信息";
                        String searchtiaojian = "1";
                        Intent intent = new Intent(SearchActivity.this, bookActivity.class);
                        intent.putExtra("searchname", search);
                        intent.putExtra("searchtiaojian", searchtiaojian);
                        startActivity(intent);
                    } else if (searchedit.getHint().toString().equals("请输入ISBN")) {
                        Myuser.toolbartitle = "书籍信息";
                        String searchtiaojian = "2";
                        Intent intent = new Intent(SearchActivity.this, bookActivity.class);
                        intent.putExtra("searchname", search);
                        intent.putExtra("searchtiaojian", searchtiaojian);
                        startActivity(intent);

                    } else if (searchedit.getHint().toString().equals("请输入作者")) {
                        Myuser.toolbartitle = "书籍信息";
                        String searchtiaojian = "3";
                        Intent intent = new Intent(SearchActivity.this, bookActivity.class);
                        intent.putExtra("searchname", search);
                        intent.putExtra("searchtiaojian", searchtiaojian);
                        startActivity(intent);

                    } else {
                        Myuser.toolbartitle = "书籍信息";
                        String searchtiaojian = "1";
                        Intent intent = new Intent(SearchActivity.this, bookActivity.class);
                        intent.putExtra("searchname", search);
                        intent.putExtra("searchtiaojian", searchtiaojian);
                        startActivity(intent);

                    }
                }


            }
        });
    }
}
