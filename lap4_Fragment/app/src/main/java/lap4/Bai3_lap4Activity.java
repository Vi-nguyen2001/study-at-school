package lap4;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import fpoly.vinv01.fragmentapplication.R;
import lap4.adapter.lap4Adapter;

public class Bai3_lap4Activity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager2 viewPager;

    private lap4Adapter lap4Adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bai3_lap4);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
        lap4Adapter = new lap4Adapter(this);
        viewPager.setAdapter(lap4Adapter);
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position)
            {
                case 0:
                    tab.setText("TablayOut1");
                    break;
                case 1:
                    tab.setText("TablayOut2");
                    break;
            }

        }).attach();

    }
    private void init() {
        tabLayout = findViewById(R.id.tapLayoutBai3);
        viewPager = findViewById(R.id.viewPagerBai3);
    }
}