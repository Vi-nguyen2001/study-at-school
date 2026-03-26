package lap2;

import android.app.Activity;
import android.content.Context;
import android.content.ReceiverCallNotAllowedException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fpoly.vinv01.sqlitedemo.R;
import lap2.dao.Lap2ToDoDAO;
import lap2.model.ToDoLap2;

public class Lap2ToDoAdapter extends RecyclerView.Adapter<Lap2ToDoAdapter.ViewHolder> {
    Context context;
    ArrayList<ToDoLap2> list;

    public Lap2ToDoAdapter(Context context, ArrayList<ToDoLap2> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Lap2ToDoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = android.view.LayoutInflater.from(context).inflate(R.layout.item_lap2_tudo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Lap2ToDoAdapter.ViewHolder holder, int position) {
        ToDoLap2 toDoLap2 = list.get(position);
        holder.txtMonHoc.setText(toDoLap2.getTitle());
        holder.txtNgay.setText(toDoLap2.getDate());

        holder.checkBox.setOnClickListener(null);
        holder.checkBox.setChecked(toDoLap2.getStatus() == 1);

        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            Lap2ToDoDAO dao = new Lap2ToDoDAO(context);
            boolean success = dao.updateStatus(toDoLap2.getId(), isChecked);
            if (success) {
                toDoLap2.setStatus(isChecked ? 1 : 0);
                updateTextDecoration(holder.txtMonHoc, isChecked);
                android.widget.Toast.makeText(context, "Đã cập nhật trạng thái", android.widget.Toast.LENGTH_SHORT).show();
            }
        });

        holder.imgThungRac.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setCancelable(true);
            builder.setTitle("Xác nhận xóa");
            builder.setMessage("Bạn có chắc chắn muốn xóa?");
            builder.setPositiveButton("Có", (dialog, which) -> {
                Lap2ToDoDAO dao = new Lap2ToDoDAO(context);
                if (dao.deleteToDo(toDoLap2.getId()) > 0) {
                    list.remove(position);
                    notifyDataSetChanged();
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                }
            });
            builder.setNegativeButton("Không", (dialog, which) -> {
                dialog.dismiss();

            });
            builder.show();
        });

        holder.imgSua.setOnClickListener(v -> {
            //cách 1 viết trực tiếp
//            AlertDialog.Builder builder = new AlertDialog.Builder(context);
//            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
//            View view = inflater.inflate(R.layout.dialog_sua_lap23, null);
//            builder.setView(view);

//            AlertDialog dialog = builder.create();
//
//            EditText edtTieuDeDL = view.findViewById(R.id.edtTieuDeDL);
//            EditText edtMonHocDL = view.findViewById(R.id.edtMonHocDL);
//            EditText edtNgayDL = view.findViewById(R.id.edtNgayDL);
//            EditText edtMucDoDL = view.findViewById(R.id.edtMucDoDL);
//            Button btnAddDL = view.findViewById(R.id.btnAddDL);
//            Button btnCancel = view.findViewById(R.id.btnCancel);

//            ToDoLap2 itemhientai = list.get(position);
//            edtTieuDeDL.setText(itemhientai.getTitle());
//            edtMonHocDL.setText(itemhientai.getContent());
//            edtNgayDL.setText(itemhientai.getDate());
//            edtMucDoDL.setText(itemhientai.getType());
//
//            edtMucDoDL.setOnClickListener(v1 -> {
//                edtMucDoDL.setFocusable(false);
//                edtMucDoDL.setClickable(true);
//                String[] mucDo = {"Dễ", "Trung Bình", "Khó"};
//                android.app.AlertDialog.Builder builder1 = new android.app.AlertDialog.Builder(context);
//                builder1.setTitle("Chọn mức độ");
//                builder1.setItems(mucDo, (dialog1, which) -> {
//                    edtMucDoDL.setText(mucDo[which]);
//                });
//                builder1.show();
//            });
//
//            btnAddDL.setOnClickListener(v12 -> {
//                itemhientai.setTitle(edtTieuDeDL.getText().toString());
//                itemhientai.setContent(edtMonHocDL.getText().toString());
//                itemhientai.setDate(edtNgayDL.getText().toString());
//                itemhientai.setType(edtMucDoDL.getText().toString());
//                Lap2ToDoDAO dao = new Lap2ToDoDAO(context);
//                if (dao.updateToDo(itemhientai) > 0) {
//                    list.set(position, itemhientai);
//                    notifyItemChanged(position);
//                    Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
//                    dialog.dismiss();
//                } else {
//                    Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
//                }
//            });
//            btnCancel.setOnClickListener(v13 -> {
//                dialog.dismiss();
//            });
//
//            dialog.show();

            //cách 2 viết bằng hàm
            showDialogUpdate(toDoLap2, position);
        });

    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;
        TextView txtMonHoc, txtNgay;
        ImageView imgThungRac, imgSua;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.chkDone);
            txtMonHoc = itemView.findViewById(R.id.txtMonHoc);
            txtNgay = itemView.findViewById(R.id.txtNgay);
            imgThungRac = itemView.findViewById(R.id.imgThungrac);
            imgSua = itemView.findViewById(R.id.imgSua);
        }
    }


    private void updateTextDecoration(TextView textView, boolean isChecked) {
        if (isChecked) {
            textView.setPaintFlags(textView.getPaintFlags() | android.graphics.Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            textView.setPaintFlags(textView.getPaintFlags() & ~android.graphics.Paint.STRIKE_THRU_TEXT_FLAG);
        }

    }

    private void showDialogUpdate(ToDoLap2 item, int pos) {
        // 1. Khởi tạo và nạp Layout
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_sua_lap23, null);
        builder.setView(view);
        AlertDialog dialog = builder.create();

        // 2. Ánh xạ View từ "view" của Dialog
        EditText edtTieuDe = view.findViewById(R.id.edtTieuDeDL);
        EditText edtNoiDung = view.findViewById(R.id.edtMonHocDL);
        EditText edtNgay = view.findViewById(R.id.edtNgayDL);
        EditText edtMucDo = view.findViewById(R.id.edtMucDoDL);
        Button btnSave = view.findViewById(R.id.btnAddDL);
        Button btnHuy = view.findViewById(R.id.btnCancel);

        // 3. Đổ dữ liệu cũ lên (Sử dụng đối tượng "item" truyền vào)
        edtTieuDe.setText(item.getTitle());
        edtNoiDung.setText(item.getContent());
        edtNgay.setText(item.getDate());
        edtMucDo.setText(item.getType());

        edtMucDo.setOnClickListener(v1 -> {
            edtMucDo.setFocusable(false);
            edtMucDo.setClickable(true);
            String[] mucDo = {"Dễ", "Trung Bình", "Khó"};
            android.app.AlertDialog.Builder builder1 = new android.app.AlertDialog.Builder(context);
            builder1.setTitle("Chọn mức độ");
            builder1.setItems(mucDo, (dialog1, which) -> {
                edtMucDo.setText(mucDo[which]);
            });
            builder1.show();
        });

        // 4. Xử lý nút Lưu
        btnSave.setOnClickListener(v -> {
            // Cập nhật giá trị mới vào đối tượng item
            item.setTitle(edtTieuDe.getText().toString());
            item.setContent(edtNoiDung.getText().toString());
            item.setDate(edtNgay.getText().toString());
            item.setType(edtMucDo.getText().toString());

            Lap2ToDoDAO dao = new Lap2ToDoDAO(context);
            if (dao.updateToDo(item) > 0) {
                list.set(pos, item); // Cập nhật vào list tại vị trí pos
                notifyItemChanged(pos); // Chỉ vẽ lại item tại vị trí đó
                Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        btnHuy.setOnClickListener(v -> dialog.dismiss());
        dialog.show();
    }
}
