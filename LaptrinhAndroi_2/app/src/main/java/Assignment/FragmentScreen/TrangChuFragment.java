package Assignment.FragmentScreen;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import fpoly.vinv01.sqlitedemo.R;

public class TrangChuFragment extends Fragment {
    private TextView tvUser;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,@Nullable ViewGroup container,
                            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trang_chu, container, false);
        tvUser = view.findViewById(R.id.tvUser);
        
        // Đọc tên người dùng từ bộ nhớ đệm
        SharedPreferences pref = requireActivity().getSharedPreferences("USER_INFO", android.content.Context.MODE_PRIVATE);
        String hoTen = pref.getString("hoTen", "Khách");
        tvUser.setText(hoTen);
        
        return view;
    }
}