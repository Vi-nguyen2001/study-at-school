package Assignment.Activity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qlsan_pham_assm);

        // 1. Cài đặt Toolbar để hiển thị title đúng chuẩn Material3
        Toolbar toolbar = findViewById(R.id.toolbarSp);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false); // Ẩn title mặc định, chỉ dùng TextView
        }

        // 2. Cài đặt LayoutManager cho RecyclerView
        danhSachSP = findViewById(R.id.danhSachSP);
        danhSachSP.setLayoutManager(new LinearLayoutManager(this));

        // 3. Khởi tạo DAO và lấy danh sách sản phẩm từ DB
        dao = new SanPhamDAO(this);
        list = dao.getAllSanPham();

        // 4. Nếu DB trống, tiến hành thêm một vài dữ liệu mẫu để hiển thị cho màn hình
        if (list.size() == 0) {
            dao.insertSanPham(new SanPhamAssm(0, "Áo sơ mi nam", 250000, 15));
            dao.insertSanPham(new SanPhamAssm(0, "Quần jean ống rộng", 350000, 20));
            dao.insertSanPham(new SanPhamAssm(0, "Giày thể thao", 450000, 10));
            dao.insertSanPham(new SanPhamAssm(0, "Áo thun cotton", 150000, 30));
            
            // Lấy lại danh sách sau khi đã insert
            list = dao.getAllSanPham();
        }

        // 5. Khởi tạo Adapter và gán vào RecyclerView để hiển thị
        adapter = new AssmAdapter(this, list);
        danhSachSP.setAdapter(adapter);
    }
}