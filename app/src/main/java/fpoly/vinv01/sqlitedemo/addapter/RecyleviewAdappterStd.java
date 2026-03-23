package fpoly.vinv01.sqlitedemo.addapter;

import android.content.Context;
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

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import fpoly.vinv01.sqlitedemo.R;
import fpoly.vinv01.sqlitedemo.dao.StudentDAO;
import fpoly.vinv01.sqlitedemo.model.Student;

public class RecyleviewAdappterStd extends RecyclerView.Adapter<RecyleviewAdappterStd.ViewHolder> {
    private Context context;
    private ArrayList<Student> list;

    public RecyleviewAdappterStd(Context context, ArrayList<Student> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.layout_item_student, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //set dữ liệu cho từng view ở đây
        Student student = list.get(position);
        holder.tvName.setText(student.getName());
        holder.tvAddress.setText(student.getAddress());
        Glide.with(context)
                .load(student.getAvata())
                .into(holder.imgAvata);
        holder.imgThungrac.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setCancelable(true);
            builder.setTitle("Xác nhận xóa");
            builder.setMessage("Bạn có chắc chắn muốn xóa?");
            builder.setPositiveButton("Có", (dialog, which) -> {
                //xóa
                StudentDAO dao = new StudentDAO(context);
                if(dao.delete(student.getId())){
                    //thành công
                    list.remove(position);
                    notifyDataSetChanged();
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                    //thất bại
                };
            });
            builder.setNegativeButton("Không", (dialog, which) -> {
                dialog.dismiss();
                //hủy
            });
            builder.show();
            //xóa
        });
        holder.imgthem.setOnClickListener(v -> {
            //sửa
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setCancelable(false);
            View view = View.inflate(context,R.layout.layout_dialog_edit,null);
            EditText edtName = view.findViewById(R.id.edtName);
            EditText edtAddress = view.findViewById(R.id.edtAddress);
            EditText edtAvata = view.findViewById(R.id.edtAvata);
            Button btnOK = view.findViewById(R.id.btnOK);
            Button btnH = view.findViewById(R.id.btnH);
            edtName.setText(student.getName());
            edtAddress.setText(student.getAddress());
            edtAvata.setText(student.getAvata());
            builder.setView(view);
            AlertDialog dialog = builder.create();
            btnOK.setOnClickListener(v1 -> {
                student.setName(edtName.getText().toString());
                student.setAddress(edtAddress.getText().toString());
                student.setAvata(edtAvata.getText().toString());
                StudentDAO dao = new StudentDAO(context);
                if(dao.update(student)>0){
                    list.set(position,student);
                    notifyItemChanged(position);
                    Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }else{
                    Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                }
                });

            dialog.show();
        });
    }

    @Override
    public int getItemCount() {
        if (list!=null)
            return list.size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvAddress;
        ImageView imgAvata,imgThungrac,imgthem;


        public ViewHolder( View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvAddress = itemView.findViewById(R.id.tvAddress);
            imgAvata = itemView.findViewById(R.id.imgAvata);
            imgThungrac = itemView.findViewById(R.id.thungrac);
            imgthem = itemView.findViewById(R.id.imgthem);
        }
    }
}
