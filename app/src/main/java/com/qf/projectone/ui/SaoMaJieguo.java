package com.qf.projectone.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.qf.projectone.R;

/**
 * Created by Administrator on 2016/11/24.
 */
public class SaoMaJieguo extends BaseNoActionBarActivity {

    TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saomajieguo);
        textView= (TextView) findViewById(R.id.tv_saomajieguo);
    }

}
