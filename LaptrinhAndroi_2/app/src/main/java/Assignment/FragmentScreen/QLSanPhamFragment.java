package Assignment.FragmentScreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import Assignment.Adapter.AssmAdapter;
import Assignment.DAO.SanPhamDAO;
import Assignment.model.SanPhamAssm;
import fpoly.vinv01.sqlitedemo.R;

public class QLSanPhamFragment extends Fragment {

    private RecyclerView danhSachSP;
    private SanPhamDAO dao;
    private ArrayList<SanPhamAssm> list;
    private AssmAdapter adapter;
    private FloatingActionButton themSpAssm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ql_san_pham, container, false);

        danhSachSP = view.findViewById(R.id.danhSachSP);
        danhSachSP.setLayoutManager(new LinearLayoutManager(getContext()));

        dao = new SanPhamDAO(getContext());
        list = dao.getAllSanPham();

        if (list.size() == 0) {
            dao.insertSanPham(new SanPhamAssm(0, "Bút bi", "2000", 10));
            dao.insertSanPham(new SanPhamAssm(0, "Bút chì", "1000", 20));
            dao.insertSanPham(new SanPhamAssm(0, "Bút mực", "5000", 30));
            list = dao.getAllSanPham();
        }

        adapter = new AssmAdapter(getContext(), list);
        danhSachSP.setAdapter(adapter);

        themSpAssm = view.findViewById(R.id.themSpAssm);
        themSpAssm.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            View dialogView = View.inflate(getContext(), R.layout.dialog_them_sp_assm, null);
            builder.setView(dialogView);
            AlertDialog dialog = builder.create();

            EditText edtName = dialogView.findViewById(R.id.edtName);
            EditText edtGiaSp = dialogView.findViewById(R.id.edtGiaSp);
            EditText edtSoLuongSp = dialogView.findViewById(R.id.edtSoLuongSp);
            Button btnT = dialogView.findViewById(R.id.btnT);

            btnT.setOnClickListener(v1 -> {
                String name = edtName.getText().toString().trim();
                String giaSp = edtGiaSp.getText().toString().trim();
                String soLuongStr = edtSoLuongSp.getText().toString().trim();

                if (name.isEmpty() || giaSp.isEmpty() || soLuongStr.isEmpty()) {
                    Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }

                int soLuong = Integer.parseInt(soLuongStr);
                SanPhamAssm sanPhamAssm = new SanPhamAssm(0, name, giaSp, soLuong);
                long newId = dao.insertSanPham(sanPhamAssm);
                if (newId > 0) {
                    sanPhamAssm.setMaSp((int) newId);
                    list.add(sanPhamAssm);
                    adapter.notifyItemInserted(list.size() - 1);
                    danhSachSP.scrollToPosition(list.size() - 1);

                    Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                } else {
                    Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            });
            dialog.show();
        });

        return view;
    }
}
