# SearchView--自定义搜索框

[![](https://jitpack.io/v/12313kaihuang/SearchView.svg)](https://jitpack.io/#12313kaihuang/SearchView)
### 效果图：  
***SearchView:***  
![](imgs/TIM图片20190131162357.png)      
![](imgs/TIM图片20190131162509.png)      

***ClearEditText:***  
![](imgs/TIM图片20190131162347.png)        
![](imgs/TIM图片20190131162549.png)    
### 使用：  
1. 在项目根目录的`build.gradle`中添加一句`maven { url 'https://jitpack.io' }`
```xml
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```  
2. 在**app**的`build.gradle`中添加依赖：
```xml
dependencies {
    ...
    implementation 'com.github.12313kaihuang:SearchView:2.2'
}
```
 
 ### 属性&方法  
| 名称 | 类型 | 作用 |
| ------ | ------ | ------ |
| hint | 属性 | 输入框的`hint`属性 |
| search_ico | 属性 | 搜索图标 |
| clear_ico | 属性 | 清空图标 |
| voice_ico | 属性 | 语音图标 |
| setSearchIcon | 方法 | 设置搜索图标 |
| setClearIcon | 方法 | 设置清除图标 |
| setVoiceIcon | 方法 | 设置语音图标 |
| getEt_input | 方法 | 获取输入框`EditText` |
| getSearchContent | 方法 | 获取输入框`EditText`内容 |
| setSearchContent | 方法 | 设置输入框`EditText`内容 |
| setImageButtonVoiceClickListener | 方法 | 语音按钮点击事件 |
| setonTextViewSearchClickListener | 方法 | 搜索的点击事件 |
| setImageButtonCancelClickListener | 方法 | 清除按钮点击事件 |

**[说明]**  
`ClearEditText`相当于是从`SearchView`中抽出来的一个带有清除按钮的输入框，其清除图标需通过`drawableEnd`或`drawableRight`设置。  

### 示例：
* 布局文件中使用:
```xml
 <com.searchview.SearchView
    android:id="@+id/searchView"
    android:layout_width="match_parent"
    android:layout_height="50dp"
    android:layout_marginEnd="8dp"
    android:layout_marginStart="8dp"
    android:layout_marginTop="8dp"
    app:hint="SearchView"
    app:voice_ico="@drawable/ic_voice_icon"
    app:clear_ico="@drawable/ic_clear"
    app:search_ico="@drawable/ic_search"
    app:layout_constraintEnd_toEndOf="parent"
    app:layout_constraintStart_toStartOf="parent"
    app:layout_constraintTop_toTopOf="parent" />
```
* java文件中：
```java
SearchView searchView = findViewById(R.id.searchView);

//设置清除图标,语音、搜素图标同理。
searchView.setClearIcon(getDrawable(R.drawable.ic_clear));

//语音按钮点击事件
searchView.setImageButtonVoiceClickListener(new ISearcher.onImageButtonVoiceClickListener() {
    @Override
    public void onClick(EditText input, ImageView voice, View view) {
        Toast.makeText(MainActivity.this,"语音",Toast.LENGTH_SHORT).show();
    }
});

//点击搜索
searchView.setonTextViewSearchClickListener(new ISearcher.onTextViewSearchClickListener() {
    @Override
    public void onClick(EditText input, TextView search, View view) {
        Toast.makeText(MainActivity.this,"搜索",Toast.LENGTH_SHORT).show();
    }
});

//清除事件
searchView.setImageButtonCancelClickListener(new ISearcher.onImageButtonCancelClickListener() {
    @Override
    public void onClick(EditText input, ImageView cancel, View view) {
        Toast.makeText(MainActivity.this,"清除",Toast.LENGTH_SHORT).show();
    }
});
```


 
