package com.foxappsbd.sandwichrecipe;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.NativeExpressAdView;

public class Example extends AppCompatActivity {
    String str,str2,count;
    int position;
    TextView text1,text2;
    private AdView mAdView;
    private Toolbar mToolbar;

    int[] covers = new int[]{
            R.drawable.chiken_kima,
            R.drawable. bif  ,
            R.drawable.chiken_club,
            R.drawable.chingri,
            R.drawable.brown_brad,
            R.drawable.mini,
            R.drawable.chik_and_chij,
            R.drawable.bekd_bin,
            R.drawable.anarosh,
            R.drawable.egg,
            R.drawable.eg_tometo,
            R.drawable.eg_salad,
            R.drawable.eg_sobji,
            R.drawable.agrabad,
            R.drawable.fish,
            R.drawable.tuna_salad,
            R.drawable.open_friyd,
            R.drawable.sabway,

    };
    String[] action={
            "চিকেন কিমা স্যান্ডউইচ", "বীফ স্যান্ডউইচ", "চিকেন ক্লাব স্যান্ডউইচ", "চিংড়ি স্যান্ডউইচ", "ব্রাউন ব্রেড স্যান্ডউইচ",
            "মিনি স্যান্ডউইচ", "চিক অ্যান্ড চিজ স্যান্ডউইচ", "বেকড বীন ও চীজ স্যান্ডউইচ", "মুরগী ও আনারসের স্যান্ডউইচ",
            "ডিমের স্যান্ডউইচ", "ডিম আর টমেটোর স্যান্ডউইচ", "এগ সালাদ স্যান্ডউইচ", "সবজি ও ডিমের স্যান্ডউইচ", "আগ্রাবাদের চিকেন স্যান্ডউইচ",
            "ফিস স্যান্ডউইচ", "টুনা সালসা গ্রিলড স্যান্ডউইচ", "ওপেন ফ্রাইড স্যান্ডউইচ", "সাবওয়ে স্যান্ডউইচ",


    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        NativeExpressAdView adView = (NativeExpressAdView) findViewById(R.id.adView);
        adView.loadAd(new AdRequest.Builder().build());
//for add..................................................
        mAdView = (AdView) findViewById(R.id.ad_view);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
        //add end.......................................................
        Context context=this;
        CollapsingToolbarLayout collapsingToolbarLayout =(CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        collapsingToolbarLayout.setContentScrimColor(ContextCompat.getColor(context,R.color.colorPrimary));

        text1=(TextView)findViewById(R.id.textView);
        text2=(TextView)findViewById(R.id.textView2);
        Intent intent=getIntent();
        str=intent.getStringExtra("text");
        str2=intent.getStringExtra("text2");
        count=intent.getStringExtra("position");
        position= Integer.valueOf(count);
        collapsingToolbarLayout.setBackgroundResource(covers[position]);
        collapsingToolbarLayout.setTitle(action[position]);
        text1.setText(str);
        text2.setText(str2);
        //mToolbar.setTitle(action[position]);


    }



}
