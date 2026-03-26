package fpoly.vinv01.fragmentapplication.adapter;

;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import fpoly.vinv01.fragmentapplication.Screen.GioiThieuScreen;
import fpoly.vinv01.fragmentapplication.Screen.HomeScreen;

public class MyAdapter extends FragmentStateAdapter {


    public MyAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new HomeScreen();
        }
        return new GioiThieuScreen();
    }

    @Override
    public int getItemCount() {//return số lượng fragment
        return 2;
    }
}
