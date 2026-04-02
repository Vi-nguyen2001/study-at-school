package fpoly.vinv01.drawernavigation;

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

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private FrameLayout layoutContainer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
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
                .replace(R.id.layoutContainer, new HomeScreen())
                .commit();

        navigationView.setNavigationItemSelectedListener(item -> {
            Fragment fragment = null;
            if (item.getItemId() == R.id.trangchu) {
                fragment = new HomeScreen();
            } else if (item.getItemId() == R.id.dautrang) {
                fragment = new DauTrang();
            } else if (item.getItemId() == R.id.setting) {
                fragment = new SettingScreen();
            }
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.layoutContainer, fragment)
                    .commit();
            drawerLayout.closeDrawers();
            return true;
        });




    }

    private void InitUi() {
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.main);
        navigationView = findViewById(R.id.nav_view);
        layoutContainer = findViewById(R.id.layoutContainer);
    }




}