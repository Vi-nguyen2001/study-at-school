package Assignment.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import Assignment.Adapter.AssmAdapter;
import Assignment.DAO.SanPhamDAO;
import Assignment.model.SanPhamAssm;
import fpoly.vinv01.sqlitedemo.R;

public class QLSanPhamActivity_Assm extends AppCompatActivity {
    private RecyclerView danhSachSP;
    private SanPhamDAO dao;
    private ArrayList<SanPhamAssm> list;
    private AssmAdapter adapter;
    private FloatingActionButton themSpAssm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qlsan_pham_assm);

        DrawerLayout drawerLayout = findViewById(R.id.main);
        NavigationView navigationView = findViewById(R.id.nav_view);

        Toolbar toolbar = findViewById(R.id.toolbarSp);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_san_pham) {
                // Đang ở chính nó thì không cần start lại activity
                drawerLayout.closeDrawers();
            } else if (id == R.id.nav_dang_xuat) {
                finish();
            }
            drawerLayout.closeDrawers();
            return true;
        });

        danhSachSP = findViewById(R.id.danhSachSP);
        danhSachSP.setLayoutManager(new LinearLayoutManager(this));

        dao = new SanPhamDAO(this);
        list = dao.getAllSanPham();

        if (list.size() == 0) {
            dao.insertSanPham(new SanPhamAssm(0, "Bút bi", "2000", 10));
            dao.insertSanPham(new SanPhamAssm(0, "Bút chì", "1000", 20));
            dao.insertSanPham(new SanPhamAssm(0, "Bút mực", "5000", 30));
            list = dao.getAllSanPham();
        }

        adapter = new AssmAdapter(this, list);
        danhSachSP.setAdapter(adapter);

        themSpAssm = findViewById(R.id.themSpAssm);
        themSpAssm.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View view = View.inflate(this, R.layout.dialog_them_sp_assm, null);
            builder.setView(view);
            AlertDialog dialog = builder.create();

            EditText edtName = view.findViewById(R.id.edtName);
            EditText edtGiaSp = view.findViewById(R.id.edtGiaSp);
            EditText edtSoLuongSp = view.findViewById(R.id.edtSoLuongSp);
            Button btnT = view.findViewById(R.id.btnT);

            btnT.setOnClickListener(v1 -> {
                String name = edtName.getText().toString().trim();
                String giaSp = edtGiaSp.getText().toString().trim();
                String soLuongStr = edtSoLuongSp.getText().toString().trim();

                if (name.isEmpty() || giaSp.isEmpty() || soLuongStr.isEmpty()) {
                    Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }

                int soLuong = Integer.parseInt(soLuongStr);
                SanPhamAssm sanPhamAssm = new SanPhamAssm(0, name, giaSp, soLuong);
                long newId = dao.insertSanPham(sanPhamAssm);
                if (newId > 0) {
                    // Cập nhật ID thực tế từ database cho đối tượng
                    sanPhamAssm.setMaSp((int) newId);
                    list.add(sanPhamAssm);
                    adapter.notifyItemInserted(list.size() - 1);
                    danhSachSP.scrollToPosition(list.size() - 1);

                    Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                } else {
                    Toast.makeText(this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            });
            dialog.show();
        });
    }
}
