package Assignment.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import Assignment.DAO.SanPhamDAO;
import Assignment.model.SanPhamAssm;
import fpoly.vinv01.sqlitedemo.R;

public class AssmAdapter extends RecyclerView.Adapter<AssmAdapter.ViewHolder> {

    private Context context;
    private ArrayList<SanPhamAssm> list;
    private SanPhamDAO dao;

    public AssmAdapter(Context context, ArrayList<SanPhamAssm> list) {
        this.context = context;
        this.list = list;
        this.dao = new SanPhamDAO(context);
    }

    @NonNull
    @Override
    public AssmAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_sanpham_assm, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AssmAdapter.ViewHolder holder, int position) {
        SanPhamAssm sanPhamAssm = list.get(position);
        holder.tvName.setText("Tên: "+sanPhamAssm.getTenSp());
        holder.tvGiaSp.setText("Giá: "+sanPhamAssm.getGiaSp() + " VNĐ");
        holder.tvSoLuong.setText("Số lượng: "+String.valueOf(sanPhamAssm.getSoLuong()));

        holder.tvChinhSua.setOnClickListener(v -> {
            showDialogSua(position);
        });

        holder.tvXoa.setOnClickListener(v -> {
            showDialogXoa(position);
        });
    }

    @Override
    public int getItemCount() {
        return (list != null) ? list.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgAvata;
        TextView tvName, tvGiaSp, tvSoLuong, tvChinhSua, tvXoa;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAvata = itemView.findViewById(R.id.avataAssm);
            tvName = itemView.findViewById(R.id.tvSp);
            tvGiaSp = itemView.findViewById(R.id.tvGiaSp);
            tvSoLuong = itemView.findViewById(R.id.tvSoLuongSp);
            tvChinhSua = itemView.findViewById(R.id.tvChinhSua);
            tvXoa = itemView.findViewById(R.id.tvXoa);
        }
    }

    private void showDialogSua(int position) {
        SanPhamAssm sp = list.get(position);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_sua_assm, null);
        builder.setView(view);

        EditText edtTSP = view.findViewById(R.id.edtTSP);
        EditText edtGiaban = view.findViewById(R.id.edtGiaban);
        EditText edtSol = view.findViewById(R.id.edtSol);
        Button btnSua = view.findViewById(R.id.btnSua);

        edtTSP.setText(sp.getTenSp());
        edtGiaban.setText(sp.getGiaSp());
        edtSol.setText(String.valueOf(sp.getSoLuong()));

        AlertDialog dialog = builder.create();
        btnSua.setOnClickListener(v -> {
            String ten = edtTSP.getText().toString().trim();
            String gia = edtGiaban.getText().toString().trim();
            String slStr = edtSol.getText().toString().trim();

            if (ten.isEmpty() || gia.isEmpty() || slStr.isEmpty()) {
                Toast.makeText(context, "Không được để trống", Toast.LENGTH_SHORT).show();
                return;
            }

            sp.setTenSp(ten);
            sp.setGiaSp(gia);
            sp.setSoLuong(Integer.parseInt(slStr));

            if (dao.update(sp) > 0) {
                list.set(position, sp);
                notifyItemChanged(position);
                Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            } else {
                Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.show();
    }

    private void showDialogXoa(int position) {
        SanPhamAssm sp = list.get(position);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Xác nhận xóa");
        builder.setMessage("Bạn có chắc chắn muốn xóa " + sp.getTenSp() + " không?");
        builder.setPositiveButton("Xóa", (dialog, which) -> {
            if (dao.delete(sp.getMaSp()) > 0) {
                list.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, list.size());
                Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Hủy", null);
        builder.show();
    }
}
