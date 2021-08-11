package com.nandan.modernlibraryusingfirebase;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class BookCategory extends AppCompatActivity {

    TabLayout tabLayout;
    TabItem tab1, tab2;
    ViewPager viewPager;
    PageAdapter pageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_book_category);
        
       tabLayout = findViewById(R.id.tabLayout);
       tab1 = findViewById(R.id.tabsem);
       tab2 = findViewById(R.id.tabtopics);
       viewPager = findViewById(R.id.viewPager);
       pageAdapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
       viewPager.setAdapter(pageAdapter);

       tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
           @Override
           public void onTabSelected(TabLayout.Tab tab) {
               viewPager.setCurrentItem(tab.getPosition());

               if(tab.getPosition()==0 || tab.getPosition()==1 )
                   pageAdapter.notifyDataSetChanged();


           }

           @Override
           public void onTabUnselected(TabLayout.Tab tab) {

           }

           @Override
           public void onTabReselected(TabLayout.Tab tab) {

           }
       });

       viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }
}