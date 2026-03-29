package Assignment.FragmentScreen;

import android.content.Context;
import android.content.Intent;
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
import android.widget.ShareActionProvider;
import android.widget.Toast;

import Assignment.Activity.LoginAssm;
import Assignment.DAO.NguoiDungDAO;
import fpoly.vinv01.sqlitedemo.R;


public class CaiDatFragment extends Fragment {
    private EditText edtMkHienTai;
    private EditText edtMkMoi;
    private EditText edtNhaplaiMk;
    private Button btnDoiMk;
    private NguoiDungDAO dao;




    public CaiDatFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static CaiDatFragment newInstance(String param1, String param2) {
        CaiDatFragment fragment = new CaiDatFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cai_dat, container, false);
        edtMkHienTai = view.findViewById(R.id.edtMkHienTai);
        edtMkMoi = view.findViewById(R.id.edtMkMoi);
        edtNhaplaiMk = view.findViewById(R.id.edtNhaplaiMk);
        btnDoiMk = view.findViewById(R.id.btnDoi);
        dao = new NguoiDungDAO(getContext());
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("USER_INFO",Context.MODE_PRIVATE);
        String tenDangNhap = sharedPreferences.getString("tenDangNhap", "");
        btnDoiMk.setOnClickListener(v -> {
            String mkHienTai = edtMkHienTai.getText().toString().trim();
            String mkMoi = edtMkMoi.getText().toString().trim();
            String nhaplaiMk = edtNhaplaiMk.getText().toString().trim();
            if (mkHienTai.isEmpty() || mkMoi.isEmpty() || nhaplaiMk.isEmpty()) {
                Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!dao.checkOldPassword(tenDangNhap, mkHienTai)) {
               Toast.makeText(getContext(), "Mật khẩu hiện tại không đúng", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!mkMoi.equals(nhaplaiMk)) {
                Toast.makeText(getContext(), "Mật khẩu mới không khớp", Toast.LENGTH_SHORT).show();
                return;
            }
            int kq = dao.updateMatKhau(tenDangNhap, mkMoi);
            if (kq > 0) {
                Toast.makeText(getContext(), "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                Intent intent = new Intent(getContext(), LoginAssm.class);
                startActivity(intent);
                requireActivity().finish();
            } else {
                Toast.makeText(getContext(), "Đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}