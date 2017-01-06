package com.qf.projectone.fragement;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qf.projectone.R;
import com.qf.projectone.adapter.AbsAdapter;
import com.qf.projectone.bean.BannerBean;
import com.qf.projectone.bean.ShouYeBean;
import com.qf.projectone.interfaceclass.ShouYeInterFace;
import com.qf.projectone.ui.CityActivity;
import com.qf.projectone.ui.SaoMaActivity;
import com.qf.projectone.ui.XianqingActivity;
import com.qf.projectone.utils.ApiManger;
import com.qf.projectone.utils.IntentUtils;
import com.qf.projectone.utils.SharedUtils;
import com.qf.projectone.widget.BannerView;
import com.qf.projectone.widget.MyPullFrsh;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Administrator on 2016/11/21.
 */
public class ShouyeFragament extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    TextView textView_name;
    ListView listView;
    public static final int REQUESTCODE = 0x001;
    public static final int REQUESTCODE_SAOMA = 0x002;
    String cityName;
    String cityId = "1";

    List<ShouYeBean.DataBean> datas;
    AbsAdapter<ShouYeBean.DataBean> adapter;

    //广告轮播
    BannerView banner;

    //下拉刷新
    PtrFrameLayout pullFrsh;

    //二维码调转
    ImageView button;

    //上拉加载
    int page = 1;
    boolean addView = false;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        datas = new ArrayList<>();
        adapter = new AbsAdapter<ShouYeBean.DataBean>(getActivity(), datas, R.layout.shouye_adater_layout, R.layout.shouye_mor_adpter) {
            @Override
            protected void bindData(int position, ViewHolder holder) {
                //绑定数据
                //得到数据类型，知道布局类型，根据不同布局，加载不同数据
                int tpye = Integer.valueOf(datas.get(position).getType());
                ShouYeBean.DataBean entity = datas.get(position);

                if (tpye == 0) {
                    //图片
                    ImageView img = (ImageView) holder.findViewById(R.id.img);
                    Glide.with(ShouyeFragament.this).load(entity.getGroupthumbnail()).into(img);
                    //标题
                    TextView tvTitle = (TextView) holder.findViewById(R.id.item_title);
                    tvTitle.setText(entity.getTitle());
                    //摘要
                    TextView tvSummary = (TextView) holder.findViewById(R.id.item_summary);
                    tvSummary.setText(entity.getSummary());
                } else {
                    //图片
                    ImageView img = (ImageView) holder.findViewById(R.id.img_more);
                    Glide.with(ShouyeFragament.this).load(entity.getGroupthumbnail()).into(img);
                    //标题
                    TextView tvTitle = (TextView) holder.findViewById(R.id.tv_more);
                    tvTitle.setText(entity.getTitle());

                }
            }

            @Override
            public int getItemViewType(int position) {
                ShouYeBean.DataBean bean = datas.get(position);
                int tpye = Integer.valueOf(bean.getType());
                return tpye;
            }
        };


        banner = new BannerView(getActivity());

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.shouye_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findView(view);
        SharedUtils.isFirstRun(getActivity());
        //恢复界面
        resaveView();
        //获取数据
        loadDatas();

    }

    private void resaveView() {
        if (cityName != null) {
            textView_name.setText(cityName);
        }
    }

    private void findView(View view) {
        textView_name = (TextView) view.findViewById(R.id.tv_cityname);
        listView = (ListView) view.findViewById(R.id.lv_shouye);

        //初始化刷新控件
        shupView(view);


        textView_name.setOnClickListener(this);

        View view1 = LayoutInflater.from(getActivity()).inflate(R.layout.shouye_head_layout, null);
        //添加头部广告
        listView.addHeaderView(banner);
        listView.addHeaderView(view1);
        listView.setAdapter(adapter);

        //ListView 的监听
        listView.setOnItemClickListener(this);

        //下拉刷新监听
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (addView && scrollState == 0) {
                    page++;

                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem + visibleItemCount == totalItemCount) {
                    addView = true;
                }
            }
        });

        //点击二维码
        button = (ImageView) view.findViewById(R.id.saoma);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SaoMaActivity.class);
                startActivityForResult(intent, REQUESTCODE_SAOMA);
            }
        });
    }

    private void shupView(View view) {
        pullFrsh = (PtrFrameLayout) view.findViewById(R.id.pust);
        MyPullFrsh myPullFrsh = new MyPullFrsh(getActivity());
        //添加刷新头
        pullFrsh.setHeaderView(myPullFrsh);
        //添加刷新头控制
        pullFrsh.addPtrUIHandler(myPullFrsh);
        //=======================功能部分
        //设置刷新事件
        pullFrsh.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                loadDatas();
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return super.checkCanDoRefresh(frame, listView, header);

            }
        });


    }


    //城市监听
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), CityActivity.class);
        startActivityForResult(intent, REQUESTCODE);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUESTCODE: {
                if (data != null) {
                    cityName = data.getStringExtra(IntentUtils.CITYNAME);
                    cityId = data.getStringExtra(IntentUtils.CITYID);
                    textView_name.setText(cityName);
                    banner.setCityId(cityId);
                    loadDatas();
                }
            }
            break;
            case REQUESTCODE_SAOMA: {
                if (data != null) {
                    //取出data中的Bundle
                    Bundle bundle = data.getExtras();
                    if (bundle != null) {
                        //取出结果
                        String value = bundle.getString(CodeUtils.RESULT_STRING);
                        Toast.makeText(getActivity(), "扫码结果：" + value, Toast.LENGTH_LONG).show();
                    }

                }
            }
            break;
        }
    }

    private void loadDatas() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ApiManger.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        ShouYeInterFace inter = retrofit.create(ShouYeInterFace.class);
        Call<String> call = inter.getListViewContent("0", "0", cityId);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d("print", " ----->" + response.body());
                String value = response.body();
                Gson gson = new Gson();
                TypeToken<ShouYeBean> typeToken = new TypeToken<ShouYeBean>() {
                };
                ShouYeBean bean = gson.fromJson(value, typeToken.getType());

                //得到bean中的集合，再添加到data中
                datas.clear();
                datas.addAll(bean.getData());

                //
                banner.setCityId(cityId);
                //更新界面
                adapter.notifyDataSetChanged();

                pullFrsh.refreshComplete();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

                pullFrsh.refreshComplete();
            }
        });
    }

    //listView点击事件
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d("print", " position" + position);
        Log.d("print", " id" + id);
        String itId = datas.get(position - listView.getHeaderViewsCount()).getId();
        Log.d("print", "itId" + itId);
        Intent intent = new Intent(getActivity(), XianqingActivity.class);
        intent.putExtra("id", itId);
        startActivity(intent);
    }

    private void loadMore(String page ,String cityId) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ApiManger.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        ShouYeInterFace inter = retrofit.create(ShouYeInterFace.class);
        Call<BannerBean> call = inter.getbannerBeanMore(page,cityId);
        call.enqueue(new Callback<BannerBean>() {
            @Override
            public void onResponse(Call<BannerBean> call, Response<BannerBean> response) {
                BannerBean bean=response.body();
                datas.add((ShouYeBean.DataBean) bean.getData());
                Log.d("print", " 555555555");
                Log.d("print", " 444"+bean.getData().toString());
                //更新界面
                adapter.notifyDataSetChanged();



            }

            @Override
            public void onFailure(Call<BannerBean> call, Throwable t) {

            }
        });
    }

}
