package lap4.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class lap4Adapter extends FragmentStateAdapter {
    public lap4Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new lap4.Screen.Bai3Screen1();
        }
        return new lap4.Screen.Bai3Screen2();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
