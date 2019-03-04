package com.searchview;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

/**
 * 项目名：SearchView
 * 包名：  com.searchview
 * 文件名：ISearcher
 * 创建者：HY
 * 创建时间：2019/1/31 14:25
 * 描述：  SearchView功能相关接口
 */

@SuppressWarnings("unused")
public interface ISearcher {

    /**
     * 搜索按钮点击事件
     *
     * @param listener 回调接口listener
     */
    void setOnSearchImageViewClickListener(OnImageViewClickListener listener);

    /**
     * 清除按钮点击事件
     *
     * @param listener 回调接口listener
     */
    void setOnClearImageButtonClickListener(OnImageButtonClickListener listener);

    /**
     * 语音按钮点击事件
     *
     * @param listener 回调接口listener
     */
    void setOnVoiceImageButtonClickListener(OnImageButtonClickListener listener);


    /**
     * “搜索”点击事件
     *
     * @param listener 回调接口listener
     */
    void setOnSearchTextViewClickListener(OnSearchTextViewClickListener listener);

    /**
     * 获取EditText的内容
     *
     * @return EditText的内容
     */
    String getSearchContent();

    /**
     * 设置EditText的内容
     *
     * @param content content
     */
    void setSearchContent(String content);

    /**
     * 获取EditText
     *
     * @return EditText
     */
    EditText getEditText();

    /**
     * 点击搜索时的回调接口
     */

    interface OnSearchTextViewClickListener {
        /**
         * onTextViewClick
         *
         * @param input 输入框
         * @param view  view
         */
        void onSearchClick(EditText input, View view);

    }


    /**
     * 点击ImageButton回调接口
     */
    interface OnImageButtonClickListener {

        /**
         * onImageViewClick
         *
         * @param input       输入框
         * @param imageButton imageButton
         * @param view        view
         */
        void onImageButtonClick(EditText input, ImageButton imageButton, View view);

    }

    /**
     * 点击ImageView回调接口
     */
    interface OnImageViewClickListener {

        /**
         * onImageViewClick
         *
         * @param input     输入框
         * @param imageView imageView
         * @param view      view
         */
        void onImageViewClick(EditText input, ImageView imageView, View view);

    }

}

