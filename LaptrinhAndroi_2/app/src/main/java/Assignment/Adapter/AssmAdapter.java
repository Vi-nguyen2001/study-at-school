package Assignment.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;

import Assignment.DAO.SanPhamDAO;
import Assignment.model.SanPhamAssm;
import fpoly.vinv01.sqlitedemo.R;

public class AssmAdapter extends RecyclerView.Adapter<AssmAdapter.ViewHolder> {

    private Context context;
    private ArrayList<SanPhamAssm> list;

    public AssmAdapter(Context context, ArrayList<SanPhamAssm> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public AssmAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_sanpham_assm,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AssmAdapter.ViewHolder holder, int position) {
        SanPhamAssm sanPhamAssm = list.get(position);
        holder.tvName.setText(sanPhamAssm.getTenSp());
        holder.tvGiaSp.setText(sanPhamAssm.getGiaSp()+" VNĐ");
        holder.tvSoLuong.setText("SL: "+sanPhamAssm.getSoLuong());
        holder.tvChinhSua.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setCancelable(false);
            View view = View.inflate(context,R.layout.dialog_sua_assm,null);
            EditText edtTSP = view.findViewById(R.id.edtTSP);
            EditText edtGiaban = view.findViewById(R.id.edtGiaban);
            EditText edtSol = view.findViewById(R.id.edtSol);
            Button btnSua = view.findViewById(R.id.btnSua);
            edtTSP.setText(sanPhamAssm.getTenSp());
            edtGiaban.setText(sanPhamAssm.getGiaSp()+"");
            edtSol.setText(sanPhamAssm.getSoLuong()+"");
            builder.setView(view);
            AlertDialog dialog = builder.create();
            btnSua.setOnClickListener(v1 -> {
                sanPhamAssm.setTenSp(edtTSP.getText().toString());
                sanPhamAssm.setGiaSp(Double.parseDouble(edtGiaban.getText().toString()));
                sanPhamAssm.setSoLuong(Integer.parseInt(edtSol.getText().toString()));
                SanPhamDAO dao = new SanPhamDAO(context);
                if(dao.update(sanPhamAssm)>0){
                    list.set(position,sanPhamAssm);
                    notifyItemChanged(position);
                    Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }else{
                    dialog.dismiss();
                    Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                }
            });
                builder.show();
        });
        holder.tvXoa.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setCancelable(true);
            builder.setTitle("Xác nhận xóa");
            builder.setMessage("Bạn có chắc chắn muốn xóa?");
            builder.setPositiveButton("Có", (dialog, which) -> {
                SanPhamDAO dao = new SanPhamDAO(context);
                if(dao.delete(sanPhamAssm.getMaSp())>0){
                    list.remove(position);
                    notifyItemRemoved(position);
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                }

            });
            builder.setNegativeButton("Không", (dialog, which) -> {
                dialog.dismiss();
            });
            builder.show();
        });

    }

    @Override
    public int getItemCount() {
        if (list!=null)
            return list.size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgAvata;
        TextView tvName,tvGiaSp,tvSoLuong,tvChinhSua,tvXoa;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAvata = itemView.findViewById(R.id.avataAssm);
            tvName = itemView.findViewById(R.id.tvSp);
            tvGiaSp = itemView.findViewById(R.id.tvGiaSoLuongSp);
            tvSoLuong = itemView.findViewById(R.id.tvGiaSoLuongSp);
            tvChinhSua = itemView.findViewById(R.id.tvChinhSua);
            tvXoa = itemView.findViewById(R.id.tvXoa);
        }
    }
}
