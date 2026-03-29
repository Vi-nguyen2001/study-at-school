package Assignment.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import Assignment.DAO.NguoiDungDAO;
import Assignment.DAO.SanPhamDAO;
import fpoly.vinv01.sqlitedemo.R;

public class LoginAssm extends AppCompatActivity {
    private EditText edtUsername;
    private EditText edtPassword;
    private Button btnLogin;
    private TextView dangKy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_assm);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        dangKy = findViewById(R.id.dangKy);

        // Nút Login
        btnLogin.setText("LOGIN");

        btnLogin.setOnClickListener(v -> {
            String username = edtUsername.getText().toString();
            String password = edtPassword.getText().toString();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(LoginAssm.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            NguoiDungDAO dao = new NguoiDungDAO(LoginAssm.this);
            if (dao.checkLogin(username,password)){
                String hoTen = dao.getHoTen(username);
                SharedPreferences pref = getSharedPreferences("USER_INFO", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("hoTen", hoTen);
                editor.putString("tenDangNhap", username);
                editor.apply();

                Toast.makeText(LoginAssm.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginAssm.this, MainActivity_Assm.class);
                startActivity(intent);
                finish();
            }else {
                Toast.makeText(LoginAssm.this, "Sai tên đăng nhập hoặc mật khẩu", Toast.LENGTH_SHORT).show();
            }
        });

        dangKy.setOnClickListener(v -> {
            Intent intent = new Intent(LoginAssm.this, DangKy_assm.class);
            startActivity(intent);
        });

    }
}