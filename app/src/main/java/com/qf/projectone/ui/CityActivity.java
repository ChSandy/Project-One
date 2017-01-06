package com.qf.projectone.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;

import com.qf.projectone.R;
import com.qf.projectone.adapter.MyAdapter;
import com.qf.projectone.bean.CityBean;
import com.qf.projectone.interfaceclass.CityInterface;
import com.qf.projectone.utils.ApiManger;
import com.qf.projectone.utils.CityUtils;
import com.qf.projectone.utils.IntentUtils;
import com.qf.projectone.utils.SharedUtils;
import com.qf.projectone.widget.LetterView;
import com.qf.projectone.widget.ShowText;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

/**
 * Created by Administrator on 2016/11/21.
 */
public class CityActivity extends BaseNoActionBarActivity implements LetterView.CallBack {

    List<CityBean> datas;
    StickyListHeadersListView listView;

    MyAdapter adapter;

    LetterView letterView;
    ShowText showText;

    EditText editText;


    //延迟搜索的Handler
    Handler handler = new Handler();
    String str_search;
    //搜索的Runnable
    Runnable searhRunable = new Runnable() {
        @Override
        public void run() {
            //搜索的代码
            adapter.serchCity(str_search);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.citychoice_layout);
        //找到控件
        findView();
        //初始化
        init();
        //适配器
        setAdapter();
        //下载联网
        loadDatas();
        //listView 监听
        setliter();

    }


    private void loadDatas() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ApiManger.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create()).build();

        CityInterface cityFace = retrofit.create(CityInterface.class);

        Call<String> call = cityFace.getCity();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String url = response.body();
                try {
                    List<CityBean> cityBeen = CityUtils.getCityByJson(url);
                    datas.clear();
                    datas.addAll(cityBeen);
                    adapter.setAllData(datas);
                    adapter.notifyDataSetChanged();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });


    }

    public void ivclick(View view) {
        //搜索
        Log.d("print", " -----====");
        String text = String.valueOf(editText.getText());
        for (int i = 0; i < datas.size(); i++) {
            if (datas.get(i).getCityname().equals(text) || datas.get(i).getCitypinyin().equals(text) || datas.get(i).getLetter().equalsIgnoreCase(text)) {
                listView.setSelection(i);
                Log.d("print", " ----->" + i);
            }
        }

    }


    private void setAdapter() {
        adapter = new MyAdapter(datas, this);
        listView.setAdapter(adapter);

    }

    private void init() {
        datas = new ArrayList<>();
    }

    private void findView() {
        listView = (StickyListHeadersListView) findViewById(R.id.cityChoiceLv);
        letterView = (LetterView) findViewById(R.id.letterView);
        showText = (ShowText) findViewById(R.id.showText);
        editText = (EditText) findViewById(R.id.ed_cityChoice);

        letterView.setOnLinsten(this);

        //editText监听
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //设置搜索字符串
                str_search = s.toString();
                //清掉前面的搜索
                handler.removeCallbacks(searhRunable);
                //延迟搜索
                handler.postDelayed(searhRunable, 500);
            }
        });
    }

    @Override
    public void setText(int postion, String str) {
        //侧滑时回调方法
        //letterView显示出来
        //设置letterView的text
        showText.setVisibility(View.VISIBLE);
        showText.setTextView(str);
        //ListView滑动到指定位置
        //从data集合中，找到第一条数据：以str开头的数据的位置
        for (int i = 0; i < datas.size(); i++) {
            if (datas.get(i).getLetter().equals(str)) {
                listView.setSelection(i);
                return;
            }
        }


    }

    @Override
    public void setView() {
        //侧滑控件手指抬起时
        showText.setVisibility(View.GONE);
    }

    //点击取消

    public void quexiao(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    private void setliter() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              if(SharedUtils.isFirstRun(view.getContext())){
                  Intent intent = new Intent(view.getContext(), HomeActivity.class);
                  startActivity(intent);
                  finish();
                  Log.d("print", "diyui.lll ");
              }else {
                  Intent intent = getIntent();
                  intent.putExtra(IntentUtils.CITYNAME, datas.get(position).getCityname());
                  intent.putExtra(IntentUtils.CITYID, datas.get(position).getCityid());
                  setResult(1, intent);
                  finish();
                  Log.d("print", " =======");
              }
            }
        });
    }

}
