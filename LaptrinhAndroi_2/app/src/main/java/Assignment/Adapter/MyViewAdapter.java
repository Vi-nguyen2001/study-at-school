package Assignment.Adapter;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MyViewAdapter extends FragmentStateAdapter {
    public MyViewAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0: return new Assignment.FragmentScreen.CaiDatFragment();
            case 1: return new Assignment.FragmentScreen.TrangChuFragment();
            case 2: return new Assignment.FragmentScreen.GioiThieuFragment();
            default: return new Assignment.FragmentScreen.TrangChuFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
