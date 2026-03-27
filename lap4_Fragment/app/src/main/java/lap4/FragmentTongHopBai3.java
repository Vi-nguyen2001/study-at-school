package lap4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import fpoly.vinv01.fragmentapplication.R;
import lap4.adapter.lap4Adapter;

public class FragmentTongHopBai3 extends Fragment {

    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private lap4Adapter lap4Adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tong_hop_bai3, container, false);
        tabLayout = view.findViewById(R.id.tapLayoutBai3_frag);
        viewPager = view.findViewById(R.id.viewPagerBai3_frag);

        lap4Adapter = new lap4Adapter(this);
        viewPager.setAdapter(lap4Adapter);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("TablayOut1");
                    break;
                case 1:
                    tab.setText("TablayOut2");
                    break;
            }
        }).attach();

        return view;
    }
}
