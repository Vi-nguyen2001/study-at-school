package Lap5;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import fpoly.vinv01.drawernavigation.R;

public class MainScreenLap5 extends AppCompatActivity {
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private FrameLayout layoutContainer;
    private BottomNavigationView bottomNavigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen_lap5);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.lap5), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        InitUi();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                drawerLayout, toolbar, 0, 0);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportFragmentManager().beginTransaction()
                        .replace(R.id.lap5LayoutContainer, new TrangChu_lap5())
                        .commit();

        navigationView.setNavigationItemSelectedListener(item -> {
            Fragment fragment = null;
            String title = "";
            if(item.getItemId() == R.id.lap5Trangchu){
                fragment = new TrangChu_lap5();
                title = "Trang Chủ";
            }else if(item.getItemId() == R.id.lap5Dautrang){
                fragment = new DauTrang_lap5();
                title = "Dấu Trang";
            }else if(item.getItemId() == R.id.lap5Setting){
                fragment = new CaiDat_lap5();
                title = "Cài Đặt";
            } else if (item.getItemId() == R.id.lap5Logout) {
                finish();
            }
            if(fragment != null){
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.lap5LayoutContainer, fragment)
                        .commit();
                if (getSupportActionBar() != null) {
                    getSupportActionBar().setTitle(title);
                }
            }
            drawerLayout.closeDrawers();
            return true;
        });

        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment fragment = null;
            String title = "";
            if(item.getItemId() == R.id.chat){
                fragment = new TrangChu_lap5();
                title = "Trang Chủ";
            }else if(item.getItemId() == R.id.map){
                fragment = new MapFragment_lap5();
                title = "Bản Đồ";
            }else if(item.getItemId() == R.id.hinhAnh){
                fragment = new HinhAnhFragment_lap5();
                title = "Hình Ảnh";
            }
            if(fragment != null){
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.lap5LayoutContainer, fragment)
                        .commit();
               if (getSupportActionBar() != null) {
                   getSupportActionBar().setTitle(title);
               }
            }
            return true;
        });
    }
    private void InitUi(){
        toolbar = findViewById(R.id.lap5Toolbar);
        drawerLayout = findViewById(R.id.lap5);
        navigationView = findViewById(R.id.lap5NavView);
        layoutContainer = findViewById(R.id.lap5LayoutContainer);
        bottomNavigationView = findViewById(R.id.bottomNav);
    }
}