# SearchView--自定义搜索框

[![](https://jitpack.io/v/12313kaihuang/SearchView.svg)](https://jitpack.io/#12313kaihuang/SearchView) 
### 效果图：  
![](imgs/TIM图片20190131162357.png)      
![](imgs/TIM图片20190131162509.png)      

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
    implementation 'com.github.12313kaihuang:SearchView:2.0'
}
```
 
 ### 属性&方法  
| 名称 | 类型 | 作用 |
| ------ | ------ | ------ |
| setImageButtonVoiceClickListener | 方法 | 语音按钮点击事件 |
| setonTextViewSearchClickListener | 方法 | 搜索的点击事件 |
| setImageButtonCancelClickListener | 方法 | 清除按钮点击事件 |

**示例：**
```java
SearchView searchView = findViewById(R.id.searchView);

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


 
