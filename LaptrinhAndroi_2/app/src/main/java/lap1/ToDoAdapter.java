package lap1;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import fpoly.vinv01.sqlitedemo.R;

public class ToDoAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<ToDo> list;

    public ToDoAdapter(Context context, ArrayList<ToDo> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        if (list!=null)
            return list.size();
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
        if (convertView==null){
            convertView = View.inflate(context, R.layout.layout_item_lap1_tudo,null);
        }
        ToDo todo = list.get(position);
        TextView txtTitle = convertView.findViewById(R.id.txtTitle);
        TextView txtContent = convertView.findViewById(R.id.txtContent);
        TextView txtDate = convertView.findViewById(R.id.txtDate);

        txtTitle.setText(todo.getTitle());
        txtContent.setText(todo.getContent());
        txtDate.setText(todo.getDate());


        return convertView;


    }
}
