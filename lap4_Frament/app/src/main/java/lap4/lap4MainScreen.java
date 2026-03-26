package lap4;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import fpoly.vinv01.fragmentapplication.R;
import lap4.adapter.lap4Adapter;

public class lap4MainScreen extends AppCompatActivity {
    private Button btnFrag1, btnFrag2;
    private lap4Adapter lap4Adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lap4_main_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        inIt();
        replaceFragment(new lap4.Screen.Screenlap4_1());
        btnFrag1.setOnClickListener(v -> replaceFragment(new lap4.Screen.Screenlap4_1()));
        btnFrag2.setOnClickListener(v -> replaceFragment(new lap4.Screen.ScreenLap4_2()));
    }
    private void inIt() {
        btnFrag1 = findViewById(R.id.btnFrag1);
        btnFrag2 = findViewById(R.id.btnFrag2);
    }
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }

}