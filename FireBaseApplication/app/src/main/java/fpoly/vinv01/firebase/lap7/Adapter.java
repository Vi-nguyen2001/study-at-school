package fpoly.vinv01.firebase.lap7;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fpoly.vinv01.firebase.R;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{
    private Context context;
    ArrayList<ToDoModel> list;

    public Adapter(Context context, ArrayList<ToDoModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_todo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        ToDoModel toDoModel = list.get(position);
        DAO_lap7 dao = new DAO_lap7(this.context);
        holder.txtMonHoc.setText(toDoModel.getTitle());
        holder.txtNgay.setText(toDoModel.getDate());
        holder.chkDone.setOnCheckedChangeListener(null);
        holder.chkDone.setChecked(toDoModel.getStatus() == 1);
        holder.chkDone.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                toDoModel.setStatus(1);
            } else {
                toDoModel.setStatus(0);
            }
            dao.update(toDoModel);
        });
        holder.imgThungrac.setOnClickListener(v -> {
            dao.delete(toDoModel.getId());
        });
        holder.imgSua.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            View view = inflater.inflate(R.layout.dialog_lap7_sua, null);
            builder.setView(view);
            AlertDialog dialog = builder.create();
            EditText edtTieuDe = view.findViewById(R.id.edtTieuDe);
            EditText edtNoiDung = view.findViewById(R.id.edtNoiDung);
            EditText edtngay = view.findViewById(R.id.edtngay);
            EditText edtLoai = view.findViewById(R.id.edtLoai);
            Button btnUpdate = view.findViewById(R.id.btnUpdate);
            Button btnCancel = view.findViewById(R.id.btnCancel);
            edtTieuDe.setText(toDoModel.getTitle());
            edtNoiDung.setText(toDoModel.getComment());
            edtngay.setText(toDoModel.getDate());
            edtLoai.setText(toDoModel.getType());
            edtLoai.setOnClickListener(v1 -> {
                AlertDialog.Builder typeBuilder = new AlertDialog.Builder(context);
                String[] types = {"Dễ", "Trung bình", "Khó"};
                typeBuilder.setTitle("Chọn loại");
                typeBuilder.setItems(types, (dialogInterface, i) -> {
                    edtLoai.setText(types[i]);
                });
                typeBuilder.show();
            });
            btnUpdate.setOnClickListener(v1 -> {
                String title = edtTieuDe.getText().toString();
                String comment = edtNoiDung.getText().toString();
                String date = edtngay.getText().toString();
                String type = edtLoai.getText().toString();
                toDoModel.setTitle(title);
                toDoModel.setComment(comment);
                toDoModel.setDate(date);
                toDoModel.setType(type);
                dao.update(toDoModel);
                dialog.dismiss();
            });
            btnCancel.setOnClickListener(v12 -> dialog.dismiss());
            dialog.show();
        });

    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CheckBox chkDone;
        TextView txtMonHoc;
        ImageView imgThungrac;
        ImageView imgSua;
        TextView txtNgay;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            chkDone = itemView.findViewById(R.id.chkDone);
            txtMonHoc = itemView.findViewById(R.id.txtMonHoc);
            imgThungrac = itemView.findViewById(R.id.imgDelete);
            imgSua = itemView.findViewById(R.id.imgSua);
            txtNgay = itemView.findViewById(R.id.txtNgay);
        }
    }


}
