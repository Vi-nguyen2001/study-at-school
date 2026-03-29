package Assignment.FragmentScreen;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import fpoly.vinv01.sqlitedemo.R;

public class GioiThieuFragment extends Fragment {
    private TextView tvTen;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gioi_thieu, container, false);
        tvTen = view.findViewById(R.id.tvTen);
        SharedPreferences pref = requireActivity().getSharedPreferences("USER_INFO", android.content.Context.MODE_PRIVATE);
        String hoTen = pref.getString("hoTen", "Khách");
        tvTen.setText(hoTen);
        return view;
    }
}