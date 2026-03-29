package Assignment.Activity;

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

import fpoly.vinv01.sqlitedemo.R;

public class DangKy_assm extends AppCompatActivity {
    private EditText edtUsername, edtPassword, edtHoTen;
    private Button btnLogin;
    private android.widget.ImageView imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dangky_assm);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtUsername = findViewById(R.id.edtName);
        edtPassword = findViewById(R.id.edtPass);
        edtHoTen = findViewById(R.id.edtHoTen);
        btnLogin = findViewById(R.id.btnSignUp);
        imgBack = findViewById(R.id.imgBack);

        btnLogin.setOnClickListener(v -> {
            String user = edtUsername.getText().toString();
            String pass = edtPassword.getText().toString();
            String name = edtHoTen.getText().toString();
            
            if (user.isEmpty() || pass.isEmpty() || name.isEmpty()) {
                android.widget.Toast.makeText(this, "Vui lòng nhập đầy đủ", android.widget.Toast.LENGTH_SHORT).show();
                return;
            }
            Assignment.DAO.NguoiDungDAO dao = new Assignment.DAO.NguoiDungDAO(this);

            long res = dao.register(user, pass, name);
            if (res > 0) {
               Toast.makeText(this, "Đăng ký thành công", android.widget.Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Tài khoản đã tồn tại!", android.widget.Toast.LENGTH_SHORT).show();
            }
        });

        imgBack.setOnClickListener(v -> finish());
    }
}