package fpoly.vinv01.sqlitedemo.addapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import fpoly.vinv01.sqlitedemo.R;
import fpoly.vinv01.sqlitedemo.model.Student;

public class AdapterStudent extends BaseAdapter {

    private Context context;
    private ArrayList<Student> list;

    public AdapterStudent(Context context, ArrayList<Student> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.layout_item_student, null);
        }
        Student student = list.get(position);
        TextView tvName = convertView.findViewById(R.id.tvName);
        TextView tvAddress = convertView.findViewById(R.id.tvAddress);
        ImageView imgAvata = convertView.findViewById(R.id.imgAvata);
        tvName.setText(student.getName());
        Glide.with(context)
                .load(student.getAvata())
                .into(imgAvata);
        tvAddress.setText(student.getAddress());
        return convertView;
    }
}
