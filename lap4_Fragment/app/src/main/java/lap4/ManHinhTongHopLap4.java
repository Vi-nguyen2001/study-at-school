package lap4;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

import fpoly.vinv01.fragmentapplication.R;

public class ManHinhTongHopLap4 extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager2 viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_man_hinh_tong_hop_lap4);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        inIt();
    }
    private void inIt() {
        tabLayout = findViewById(R.id.tapLayoutTong);
        viewPager = findViewById(R.id.viewPagerTong);

        viewPager.setAdapter(new lap4.adapter.TongHopLap4Adapter(this));

        new com.google.android.material.tabs.TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Bài 1");
                    break;
                case 1:
                    tab.setText("Bài 2");
                    break;
                case 2:
                    tab.setText("Bài 3");
                    break;
            }
        }).attach();
    }


}