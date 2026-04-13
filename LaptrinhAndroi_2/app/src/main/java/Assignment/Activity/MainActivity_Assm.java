package Assignment.Activity;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.content.Intent;

import android.view.View;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import androidx.viewpager2.widget.ViewPager2;
import Assignment.Adapter.MyViewAdapter;
import Assignment.FragmentScreen.QLSanPhamFragment;
import com.google.android.material.tabs.TabLayoutMediator;
import androidx.viewpager2.widget.ViewPager2;
import Assignment.Adapter.MyViewAdapter;
import fpoly.vinv01.sqlitedemo.R;

public class MainActivity_Assm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_assm);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPager2 viewPager = findViewById(R.id.view_pager);

        MyViewAdapter adapter = new MyViewAdapter(this);
        viewPager.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0: tab.setIcon(R.drawable.img_5); tab.setText("Cài đặt"); break;
                case 1: tab.setIcon(R.drawable.img_4); tab.setText("Trang chủ"); break;
                case 2: tab.setIcon(R.drawable.img_6); tab.setText("Giới thiệu"); break;
            }
        }).attach();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setVisibility(View.VISIBLE);
                findViewById(R.id.content_frame).setVisibility(View.GONE);
                
                if (tab.getPosition() == 0) {
                    getSupportActionBar().setTitle("Cài đặt");
                } else if (tab.getPosition() == 1) {
                    getSupportActionBar().setTitle("Trang Chủ");
                } else if (tab.getPosition() == 2) {
                    getSupportActionBar().setTitle("Giới thiệu");
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                viewPager.setVisibility(View.VISIBLE);
                findViewById(R.id.content_frame).setVisibility(View.GONE);
            }
        });

        // Đặt Mặc định Trang Chủ (Index 1) hiển thị đầu tiên khi mở App
        viewPager.setCurrentItem(1, false);

        View contentFrame = findViewById(R.id.content_frame);

        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            
            // Xử lý chuyển Tab trong ViewPager khi click Menu
            if (id == R.id.nav_trang_chu) {
                viewPager.setVisibility(View.VISIBLE);
                contentFrame.setVisibility(View.GONE);
                getSupportActionBar().setTitle("Trang Chủ");
                // Index 1 là Trang Chủ
                viewPager.setCurrentItem(1);
            } else if (id == R.id.nav_cai_dat) {
                viewPager.setVisibility(View.VISIBLE);
                contentFrame.setVisibility(View.GONE);
                getSupportActionBar().setTitle("Cài đặt");
                // Index 0 là Cài đặt
                viewPager.setCurrentItem(0);
            } else if (id == R.id.nav_gioi_thieu) {
                viewPager.setVisibility(View.VISIBLE);
                contentFrame.setVisibility(View.GONE);
                getSupportActionBar().setTitle("Giới thiệu");
                // Index 2 là Giới thiệu
                viewPager.setCurrentItem(2);
            } else if (id == R.id.nav_san_pham) {
                // Ẩn ViewPager, giữ lại TabLayout, hiển thị FrameLayout QLSP
                viewPager.setVisibility(View.GONE);
                contentFrame.setVisibility(View.VISIBLE);
                getSupportActionBar().setTitle("Quản lý sản phẩm");

                // Nạp QLSanPhamFragment vào FrameLayout
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_frame, new QLSanPhamFragment())
                        .commit();
            } else if (id == R.id.nav_dang_xuat) {
                Intent intent = new Intent(MainActivity_Assm.this, LoginAssm.class);
                startActivity(intent);
                finish();
            }else if (id == R.id.nav_thoat) {
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity_Assm.this)
                        .setTitle("Xác nhận thoát ứng dụng")
                        .setMessage("Bạn có muốn thoát ứng dụng không?")
                        .setPositiveButton("Có", (dialog, which) -> {
                           finishAffinity();
                           System.exit(0);
                        })
                        .setNegativeButton("Không", null)
                        .show();
            }
            drawerLayout.closeDrawers();
            return true;
        });


    }
}