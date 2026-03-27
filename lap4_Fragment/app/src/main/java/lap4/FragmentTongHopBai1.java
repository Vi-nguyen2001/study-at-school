package lap4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import fpoly.vinv01.fragmentapplication.R;
import lap4.Screen.ScreenLap4_2;
import lap4.Screen.Screenlap4_1;

public class FragmentTongHopBai1 extends Fragment {

    private Button btnFrag1, btnFrag2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tong_hop_bai1, container, false);

        btnFrag1 = view.findViewById(R.id.btnFrag1);
        btnFrag2 = view.findViewById(R.id.btnFrag2);

        // Hiển thị fragment mặc định
        if (savedInstanceState == null) {
            replaceFragment(new Screenlap4_1());
        }

        btnFrag1.setOnClickListener(v -> replaceFragment(new Screenlap4_1()));
        btnFrag2.setOnClickListener(v -> replaceFragment(new ScreenLap4_2()));

        return view;
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getChildFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}
