package com.searchview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class SearchViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_search_view);

        SearchView searchView = findViewById(R.id.searchView);

        //下面示例中的值等于默认值
        searchView.setEnableAutoHideSoftKey(true);  //设置点击搜索后是否自动隐藏虚拟键盘
        searchView.setEnableClearFocusAfterSearch(true); //设置点击清除按钮后是否取消EditText的焦点

        searchView.setSearchIcon(R.drawable.sv_ic_search); //设置搜索图标
        searchView.setVoiceIcon(R.drawable.sv_ic_voice_icon); //设置语音图标
        searchView.setClearIcon(R.drawable.sv_ic_clear); //设置清除图标

        //设置搜索图标点击事件
        searchView.setOnSearchImageViewClickListener(new ISearcher.OnImageViewClickListener() {
            @Override
            public void onImageViewClick(EditText input, ImageView imageView, View view) {
                Toast.makeText(SearchViewActivity.this,"点击了搜索图标",Toast.LENGTH_SHORT).show();
            }
        });

        //设置语音图标点击事件
        searchView.setOnVoiceImageButtonClickListener(new ISearcher.OnImageButtonClickListener() {
            @Override
            public void onImageButtonClick(EditText input, ImageButton imageButton, View view) {
                Toast.makeText(SearchViewActivity.this,"点击了语音图标",Toast.LENGTH_SHORT).show();
            }
        });

        //设置清除图标点击事件
        searchView.setOnClearImageButtonClickListener(new ISearcher.OnImageButtonClickListener() {
            @Override
            public void onImageButtonClick(EditText input, ImageButton imageButton, View view) {
                Toast.makeText(SearchViewActivity.this,"点击了清除图标",Toast.LENGTH_SHORT).show();
            }
        });

        //设置“搜索”点击事件
        searchView.setOnSearchTextViewClickListener(new ISearcher.OnSearchTextViewClickListener() {
            @Override
            public void onSearchClick(EditText input, View view) {
                Toast.makeText(SearchViewActivity.this,"点击了搜索",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
