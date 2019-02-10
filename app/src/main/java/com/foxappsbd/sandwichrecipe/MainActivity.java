package com.foxappsbd.sandwichrecipe;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AlbumsAdapter adapter;
    private List<Album> albumList;
    String s,count;
    private long lastPressedTime;
    private PublisherInterstitialAd interstitialAd;
    private InterstitialAd interstitial;
    int i,j,k;

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
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initCollapsingToolbar();

        //recycalView.........
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        addListnerOnButton();
        prepareAlbums();

        try {
            Glide.with(this).load(R.drawable.cover).into((ImageView) findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void addListnerOnButton() {
        final Context context = this;
        interstitialAd = new PublisherInterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-3420675499646666/1921166278");

        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();

            }
        });
        requestNewInterstitial();
        albumList = new ArrayList<>();
        adapter = new AlbumsAdapter(this, albumList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        //for array string..............
        final String[] testArray = getResources().getStringArray(R.array.upkoron);
        final String[] testArray2 = getResources().getStringArray(R.array.pronali);

        //oneClickListener.......


        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {

            @Override
            public void onClick(View view, int position) {
                if (position == 0) {
                    Intent i = new Intent(getApplicationContext(), Example.class);
                    count= String.valueOf(position);
                    i.putExtra("text",testArray[position] );
                    i.putExtra("text2",testArray2[position] );
                    i.putExtra("position",count );
                    interstitialAd.isLoaded();
                    interstitialAd.show();
                    requestNewInterstitial();
                    startActivity(i);

                }
                if (position ==1 ) {
                    Intent i = new Intent(getApplicationContext(), Example.class);
                    count= String.valueOf(position);
                    i.putExtra("text",testArray[position] );
                    i.putExtra("text2",testArray2[position] );
                    i.putExtra("position",count );
                    startActivity(i);


                }
                if (position ==2 ) {
                    Intent i = new Intent(getApplicationContext(), Example.class);
                    count= String.valueOf(position);
                    i.putExtra("text",testArray[position] );
                    i.putExtra("text2",testArray2[position] );
                    i.putExtra("position",count );
                    startActivity(i);


                }
                if (position ==3 ) {
                    Intent i = new Intent(getApplicationContext(), Example.class);
                    count= String.valueOf(position);
                    i.putExtra("text",testArray[position] );
                    i.putExtra("text2",testArray2[position] );
                    i.putExtra("position",count );
                    interstitialAd.isLoaded();
                    interstitialAd.show();
                    requestNewInterstitial();
                    startActivity(i);


                }
                if (position == 4) {
                    Intent i = new Intent(getApplicationContext(), Example.class);
                    count= String.valueOf(position);
                    i.putExtra("text",testArray[position] );
                    i.putExtra("text2",testArray2[position] );
                    i.putExtra("position",count );
                    startActivity(i);


                }
                if (position == 5) {
                    Intent i = new Intent(getApplicationContext(), Example.class);
                    count= String.valueOf(position);
                    i.putExtra("text",testArray[position] );
                    i.putExtra("text2",testArray2[position] );
                    i.putExtra("position",count );
                    startActivity(i);


                }
                if (position == 6) {
                    Intent i = new Intent(getApplicationContext(), Example.class);
                    count= String.valueOf(position);
                    i.putExtra("text",testArray[position] );
                    i.putExtra("text2",testArray2[position] );
                    i.putExtra("position",count );
                    interstitialAd.isLoaded();
                    interstitialAd.show();
                    requestNewInterstitial();
                    startActivity(i);


                }
                if (position ==7 ) {
                    Intent i = new Intent(getApplicationContext(), Example.class);
                    count= String.valueOf(position);
                    i.putExtra("text",testArray[position] );
                    i.putExtra("text2",testArray2[position] );
                    i.putExtra("position",count );
                    startActivity(i);


                }
                if (position ==8 ) {
                    Intent i = new Intent(getApplicationContext(), Example.class);
                    count= String.valueOf(position);
                    i.putExtra("text",testArray[position] );
                    i.putExtra("text2",testArray2[position] );
                    i.putExtra("position",count );
                    startActivity(i);
                }
                if (position ==9 ) {
                    Intent i = new Intent(getApplicationContext(), Example.class);
                    count= String.valueOf(position);
                    i.putExtra("text",testArray[position] );
                    i.putExtra("text2",testArray2[position] );
                    i.putExtra("position",count );
                    interstitialAd.isLoaded();
                    interstitialAd.show();
                    requestNewInterstitial();
                    startActivity(i);


                }
                if (position == 10) {
                    Intent i = new Intent(getApplicationContext(), Example.class);
                    count= String.valueOf(position);
                    i.putExtra("text",testArray[position] );
                    i.putExtra("text2",testArray2[position] );
                    i.putExtra("position",count );
                    startActivity(i);


                }
                if (position == 11) {
                    Intent i = new Intent(getApplicationContext(), Example.class);
                    count= String.valueOf(position);
                    i.putExtra("text",testArray[position] );
                    i.putExtra("text2",testArray2[position] );
                    i.putExtra("position",count );
                    startActivity(i);


                }
                if (position ==12) {
                    Intent i = new Intent(getApplicationContext(), Example.class);
                    count= String.valueOf(position);
                    i.putExtra("text",testArray[position] );
                    i.putExtra("text2",testArray2[position] );
                    i.putExtra("position",count );
                    interstitialAd.isLoaded();
                    interstitialAd.show();
                    requestNewInterstitial();
                    startActivity(i);


                }
                if (position ==13) {
                    Intent i = new Intent(getApplicationContext(), Example.class);
                    count= String.valueOf(position);
                    i.putExtra("text",testArray[position] );
                    i.putExtra("text2",testArray2[position] );
                    i.putExtra("position",count );
                    startActivity(i);


                }
                if (position ==14) {
                    Intent i = new Intent(getApplicationContext(), Example.class);
                    count= String.valueOf(position);
                    i.putExtra("text",testArray[position] );
                    i.putExtra("text2",testArray2[position] );
                    i.putExtra("position",count );
                    interstitialAd.isLoaded();
                    interstitialAd.show();
                    requestNewInterstitial();
                    startActivity(i);
                    startActivity(i);


                }
                if (position ==15) {
                    Intent i = new Intent(getApplicationContext(), Example.class);
                    count= String.valueOf(position);
                    i.putExtra("text",testArray[position] );
                    i.putExtra("text2",testArray2[position] );
                    i.putExtra("position",count );
                    startActivity(i);


                }
                if (position ==16) {
                    Intent i = new Intent(getApplicationContext(), Example.class);
                    count= String.valueOf(position);
                    i.putExtra("text",testArray[position] );
                    i.putExtra("text2",testArray2[position] );
                    i.putExtra("position",count );
                    interstitialAd.isLoaded();
                    interstitialAd.show();
                    requestNewInterstitial();
                    startActivity(i);


                }
                if (position ==17) {
                    Intent i = new Intent(getApplicationContext(), Example.class);
                    count= String.valueOf(position);
                    i.putExtra("text",testArray[position] );
                    i.putExtra("text2",testArray2[position] );
                    i.putExtra("position",count );
                    startActivity(i);


                }

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }






    /**
     * Initializing collapsing toolbar
     * Will show and hide the toolbar title on scroll
     */
    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

    /**
     * Adding few albums for testing
     */

    private void prepareAlbums() {

for (i=0;i<action.length; i++ ){
            Album a = new Album(action[i],covers[i]);
            albumList.add(a);
        }
        adapter.notifyDataSetChanged();
    }
    //for add..........................
    private void requestNewInterstitial() {
        PublisherAdRequest adRequest = new PublisherAdRequest.Builder()
                .addTestDevice("")
                .build();

        interstitialAd.loadAd(adRequest);
    }
    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
    ///for clickListener..........
    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }
    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private MainActivity.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final MainActivity.ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

    public void alart_box(){
        int[] imageId = {
                R.drawable.icon1,
                R.drawable.icon2,
                R.drawable.icon3,
                R.drawable.icon4,
                R.drawable.icon5,
                R.drawable.icon6,
                R.drawable.icon7,
                R.drawable.icon8,
                R.drawable.icon9,
        };
        String[] name = {"প্রতিদিনের দোয়া","গুনাহ মাফের দোয়া","রাসূলুল্লাহ(সা:)উপদেশ","নবী রাসূলগণের জীবনী","কালোজিরা ও মধুর ব্যাবহার",
        "মাছ রেসিপি","রোমান্টিক ছন্দ","কষ্টের ছন্দ","প্রেমের উক্তি",} ;
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setTitle("Exit");
        alertDialog.setIcon(R.mipmap.ic_launcher);

        GridView modeList = new GridView(this);
        modeList.setNumColumns(3);
        CustomGrid adapter = new CustomGrid(MainActivity.this,name, imageId);
        modeList.setAdapter(adapter);
        alertDialog.setView(modeList);
        modeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                if (position == 0) {
                    startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.foxappsbd.protidinerdoya")));

                }
                if (position == 1) {
                    startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.foxappsbd.gunahmafferamal")));

                }
                if (position ==2) {
                    startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.foxappsbd.fourthyupodesh")));

                } if (position ==3) {
                    startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.foxappsbd.nobiderkahani")));

                }
                if (position ==4) {
                    startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.foxappsbd.kaliziramodhu")));

                }
                if (position ==5) {
                    startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.foxappsbd.fishrecipes")));

                }
                if (position ==6) {
                    startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.foxappsbd.romanticsts")));

                }
                if (position ==7) {
                    startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.foxappsbd.kosterchondo")));

                }
                if (position == 8) {
                    startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.foxappsbd.lovefamousquotes")));

                }


            }
        });
       // alertDialog.setMessage("More Apps ");
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                MainActivity.this.interstitial = new InterstitialAd(MainActivity.this);
                MainActivity.this.interstitial.setAdUnitId("ca-app-pub-3420675499646666/1921166278");
                AdRequest localAdRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).addTestDevice("CF2B5D5C45BA785670BBCAEA9269EA35").build();
                MainActivity.this.interstitial.loadAd(localAdRequest);
                MainActivity.this.interstitial.setAdListener(new AdListener()
                {
                    private void displayInterstitial()
                    {
                        if (MainActivity.this.interstitial.isLoaded()) {
                            MainActivity.this.interstitial.show();
                        }
                    }

                    public void onAdLoaded()
                    {
                        displayInterstitial();
                    }
                });
                MainActivity.this.finish();
            }

        });
        alertDialog.setNegativeButton("No", null);
        alertDialog.show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //Handle the back button
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            alart_box();
            return true;
        }
        else {
            return super.onKeyDown(keyCode, event);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu paramMenu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, paramMenu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem paramMenuItem)
    {

        switch (paramMenuItem.getItemId())
        {
            default:
                super.onOptionsItemSelected(paramMenuItem);
            case R.id.rate_app:
                Toast.makeText(getApplicationContext(), "Rate This App", Toast.LENGTH_SHORT).show();
                super.onOptionsItemSelected(paramMenuItem);
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.foxappsbd.sandwichrecipe")));
                break;
            case R.id.share_app:
                Intent localIntent = new Intent("android.intent.action.SEND");
                localIntent.setType("text/plain");
                localIntent.putExtra("android.intent.extra.TEXT", "Enjoy This Apps https://play.google.com/store/apps/details?id=com.foxappsbd.sandwichrecipe");
                startActivity(Intent.createChooser(localIntent, "Share This App Using"));
                break;
            case R.id.more_app:
                Toast.makeText(getApplicationContext(), "More Apps", Toast.LENGTH_SHORT).show();
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/developer?id=FoxAppsBd")));
                break;
            case R.id.exit:
                finish();
                System.exit(0);
        }
        return true;
    }
}