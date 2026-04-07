package Assignment.FragmentScreen;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import fpoly.vinv01.sqlitedemo.R;

public class GioiThieuFragment extends Fragment {
    private TextView tvTen, tvMaSv, tvLop, tvMonHoc;
    private ImageView imgAvatar;
    private Button btnSuaThongTin;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gioi_thieu, container, false);
        
        tvTen = view.findViewById(R.id.tvTen);
        tvMaSv = view.findViewById(R.id.maSv);
        tvLop = view.findViewById(R.id.lop);
        tvMonHoc = view.findViewById(R.id.monHoc);
        imgAvatar = view.findViewById(R.id.imgAvatar);
        btnSuaThongTin = view.findViewById(R.id.btnSuaThongTin);

        loadThongTin();

        btnSuaThongTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogEdit();
            }
        });

        return view;
    }

    private void loadThongTin() {
        SharedPreferences pref = requireActivity().getSharedPreferences("USER_INFO", Context.MODE_PRIVATE);
        
        String hoTen = pref.getString("hoTen", "Khách");
        String maSv = pref.getString("maSv", "PH63869");
        String lop = pref.getString("lop", "MD21201");
        String monHoc = pref.getString("monHoc", "Lập trình Android 2");
        String anhUrl = pref.getString("anhUrl", "");

        tvTen.setText(hoTen);
        tvMaSv.setText("\uD83C\uDD94 MSSV: " + maSv);
        tvLop.setText("\uD83C\uDF93 Lớp: " + lop);
        tvMonHoc.setText("\uD83D\uDCDA Môn: " + monHoc);

        if (!anhUrl.isEmpty()) {
            Glide.with(this)
                    .load(anhUrl)
                    .placeholder(R.drawable.img)
                    .error(R.drawable.img)
                    .into(imgAvatar);
        } else {
            imgAvatar.setImageResource(R.drawable.img);
        }
    }

    private void showDialogEdit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_edit_gioi_thieu, null);
        builder.setView(dialogView);

        EditText edtMaSv = dialogView.findViewById(R.id.edtMaSv);
        EditText edtLop = dialogView.findViewById(R.id.edtLop);
        EditText edtMonHoc = dialogView.findViewById(R.id.edtMonHoc);
        EditText edtAnhUrl = dialogView.findViewById(R.id.edtAnhUrl);

        // Lấy dữ liệu cũ hiện lên EditText
        SharedPreferences pref = requireActivity().getSharedPreferences("USER_INFO", Context.MODE_PRIVATE);
        edtMaSv.setText(pref.getString("maSv", "PH63869"));
        edtLop.setText(pref.getString("lop", "MD21201"));
        edtMonHoc.setText(pref.getString("monHoc", "Lập trình Android 2"));
        edtAnhUrl.setText(pref.getString("anhUrl", ""));

        builder.setPositiveButton("Lưu", (dialog, which) -> {
            String newMaSv = edtMaSv.getText().toString().trim();
            String newLop = edtLop.getText().toString().trim();
            String newMonHoc = edtMonHoc.getText().toString().trim();
            String newAnhUrl = edtAnhUrl.getText().toString().trim();

            if(newMaSv.isEmpty() || newLop.isEmpty() || newMonHoc.isEmpty()) {
                Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin (Không bắt buộc ảnh)", Toast.LENGTH_SHORT).show();
                return;
            }

            SharedPreferences.Editor editor = pref.edit();
            editor.putString("maSv", newMaSv);
            editor.putString("lop", newLop);
            editor.putString("monHoc", newMonHoc);
            editor.putString("anhUrl", newAnhUrl);
            editor.apply();

            loadThongTin(); // Cập nhật lại giao diện
            Toast.makeText(getContext(), "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
        });

        builder.setNegativeButton("Hủy", (dialog, which) -> dialog.dismiss());

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}