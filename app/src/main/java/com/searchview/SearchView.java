package com.searchview;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.constraint.ConstraintLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 项目名：SearchView
 * 包名：  com.searchview
 * 文件名：SearchView
 * 创建者：HY
 * 创建时间：2019/1/31 14:26
 * 描述：  自定义控件SearchView
 */

@SuppressWarnings("unused")
public class SearchView extends RelativeLayout implements ISearcher {

    private TextWatcher textWatcher;
    private ISearcher.OnImageViewClickListener onSearchImageViewClickListener;
    private ISearcher.OnImageButtonClickListener onClearImageButtonClickListener;
    private ISearcher.OnImageButtonClickListener onVoiceImageButtonClickListener;
    private OnSearchTextViewClickListener onSearchTextViewClickListener;


    EditText et_input;

    /**
     * 点击搜索后是否自动隐藏虚拟键盘
     */
    private boolean enableAutoHideSoftKey;
    /**
     * 点击了搜索(不是搜索图标)后是否清除EditText的焦点
     */
    private boolean enableClearFocusAfterSearch;

    ImageButton imgBtn_voice;  //语音图标
    ImageButton imgBtn_cancel; //清除图标
    ImageView iv_search;  //搜索图标
    TextView tv_search;
    ConstraintLayout constraintLayout;
    View view;

    public SearchView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        view = LayoutInflater.from(context).inflate(R.layout.layout_searchview, this);
        initTextWatcher();
        initView();
        initParams(context, attrs);
    }

    public SearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        view = LayoutInflater.from(context).inflate(R.layout.layout_searchview, this);
        initTextWatcher();
        initView();
        initParams(context, attrs);
    }

    /**
     * 初始化自定义属性
     *
     * @param context context
     * @param attrs   attrs
     */
    private void initParams(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SearchView);
        if (typedArray != null) {
            String hint = typedArray.getString(R.styleable.SearchView_hint);
            if (hint != null) {
                et_input.setHint(hint);
            }

            Drawable clear_ico = typedArray.getDrawable(R.styleable.SearchView_svClearIcon);
            if (clear_ico != null) {
                imgBtn_cancel.setBackground(clear_ico);
            }

            Drawable voice_ico = typedArray.getDrawable(R.styleable.SearchView_svVoiceIcon);
            if (clear_ico != null) {
                imgBtn_voice.setBackground(voice_ico);
            }

            Drawable search_ico = typedArray.getDrawable(R.styleable.SearchView_svSearchIcon);
            if (clear_ico != null) {
                this.iv_search.setImageDrawable(search_ico);
            }

            this.enableAutoHideSoftKey = typedArray.getBoolean(R.styleable.SearchView_svEnableAutoHideSoftKey, true);
            this.enableClearFocusAfterSearch = typedArray.getBoolean(R.styleable.SearchView_svEnableClearFocusAfterSearch, true);

            typedArray.recycle();
        }
    }


    private void initView() {
        et_input = view.findViewById(R.id.et_input);
        imgBtn_voice = view.findViewById(R.id.ib_voice);
        imgBtn_cancel = view.findViewById(R.id.ib_cancle);
        iv_search = view.findViewById(R.id.iv_search);
        tv_search = view.findViewById(R.id.tv_search);
        constraintLayout = view.findViewById(R.id.rl_mapsearcher);

        et_input.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    constraintLayout.setSelected(true);
                    et_input.setHint("");
                    if (et_input.getText().length() > 0) {
                        imgBtn_cancel.setVisibility(View.VISIBLE);
                        tv_search.setVisibility(View.VISIBLE);
                        imgBtn_voice.setVisibility(View.GONE);
                    } else {
                        et_input.setSelected(false);
                        imgBtn_cancel.setVisibility(View.GONE);
                        tv_search.setVisibility(View.GONE);
                        imgBtn_voice.setVisibility(View.VISIBLE);
                    }
                } else {
                    et_input.setHint("请输入关键字");
                }
            }
        });

        if (textWatcher != null) {
            et_input.addTextChangedListener(textWatcher);
        }

        //搜索图标点击事件
        iv_search.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onSearchImageViewClickListener != null) {
                    onSearchImageViewClickListener.onImageViewClick(et_input, iv_search, v);
                }
            }
        });

        //语音图标
        imgBtn_voice.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onVoiceImageButtonClickListener != null) {
                    onVoiceImageButtonClickListener.onImageButtonClick(et_input, imgBtn_voice, SearchView.this);
                }
            }
        });

        //取消图标
        imgBtn_cancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                et_input.setText("");
                if (onClearImageButtonClickListener != null) {
                    onClearImageButtonClickListener.onImageButtonClick(et_input, imgBtn_cancel, v);
                }
            }
        });

        //搜索
        tv_search.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (enableClearFocusAfterSearch) {
                    et_input.clearFocus();
                }
                Context context = getContext();
                if (context != null && context instanceof Activity && enableAutoHideSoftKey) {
                    //收起软键盘
                    Activity activity = (Activity) context;
                    InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm != null && activity.getCurrentFocus() != null) {
                        imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0); //强制隐藏键盘
                    }
                }
                if (onSearchTextViewClickListener != null) {
                    onSearchTextViewClickListener.onSearchClick(et_input, v);
                }
            }
        });
    }

    /**
     * 设置点击搜索时是否自动隐藏虚拟键盘
     *
     * @param enableAutoHideSoftKey 点击搜索时是否自动隐藏虚拟键盘
     */
    public void setEnableAutoHideSoftKey(boolean enableAutoHideSoftKey) {
        this.enableAutoHideSoftKey = enableAutoHideSoftKey;
    }

    /**
     * 点击了搜索(不是搜索图标)后是否清除EditText的焦点
     *
     * @param enableClearFocusAfterSearch 点击了搜索(不是搜索图标)后是否清除EditText的焦点
     */
    public void setEnableClearFocusAfterSearch(boolean enableClearFocusAfterSearch) {
        this.enableClearFocusAfterSearch = enableClearFocusAfterSearch;
    }

    /**
     * 搜索图标点击事件
     *
     * @param listener 回调接口listener
     */
    @Override
    public void setOnSearchImageViewClickListener(OnImageViewClickListener listener) {
        this.onSearchImageViewClickListener = listener;
    }

    /**
     * 清除图标点击事件
     *
     * @param listener 回调接口listener
     */
    @Override
    public void setOnClearImageButtonClickListener(OnImageButtonClickListener listener) {
        this.onClearImageButtonClickListener = listener;
    }

    /**
     * 语音图标点击事件
     *
     * @param listener 回调接口listener
     */
    @Override
    public void setOnVoiceImageButtonClickListener(OnImageButtonClickListener listener) {
        this.onVoiceImageButtonClickListener = listener;
    }

    /**
     * "搜索"点击事件
     *
     * @param listener 回调接口listener
     */
    @Override
    public void setOnSearchTextViewClickListener(OnSearchTextViewClickListener listener) {
        this.onSearchTextViewClickListener = listener;
    }

    /**
     * 获取EditText的内容
     *
     * @return EditText的内容
     */
    @Override
    public String getSearchContent() {
        return et_input.getText().toString().trim();
    }

    /**
     * 设置EditText的内容
     *
     * @param content content
     */
    @Override
    public void setSearchContent(String content) {
        et_input.setText(content);
    }

    /**
     * 获取EditText
     *
     * @return EditText
     */
    @Override
    public EditText getEditText() {
        return et_input;
    }

    /**
     * 设置搜索图标
     *
     * @param resId DrawableRes
     */
    public void setSearchIcon(@DrawableRes int resId) {
        this.iv_search.setImageDrawable(getContext().getDrawable(resId));
    }


    /**
     * 设置语音图标
     *
     * @param resId DrawableRes
     */
    public void setVoiceIcon(@DrawableRes int resId) {
        imgBtn_voice.setBackground(getContext().getDrawable(resId));
    }

    /**
     * 设置清除图标
     *
     * @param resId DrawableRes
     */
    public void setClearIcon(@DrawableRes int resId) {
        imgBtn_cancel.setBackground(getContext().getDrawable(resId));
    }

    /**
     * 初始化textWatcher
     */
    private void initTextWatcher() {
        textWatcher = new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int length = s.length();
                if (length > 0 && et_input.isFocused()) {
                    imgBtn_voice.setVisibility(View.GONE);
                    imgBtn_cancel.setVisibility(View.VISIBLE);
                    tv_search.setVisibility(View.VISIBLE);
                } else {
                    imgBtn_cancel.setVisibility(View.GONE);
                    tv_search.setVisibility(View.GONE);
                    imgBtn_voice.setVisibility(View.VISIBLE);
                }
            }
        };
    }

}
