package com.example.allpracticebeforedatabasestart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class adapter extends BaseAdapter {

    private Context context;
    private List<Model> modelList;
    private ArrayList<Model> modelArrayList;
    private LayoutInflater layoutInflater;

    public adapter(Context context, List<Model> modelList) {
        this.context = context;
        this.modelList = modelList;
        this.layoutInflater = LayoutInflater.from(context);
        this.modelArrayList = new ArrayList<Model>();
        this.modelArrayList.addAll(modelList);
    }

    public class viewHolder{
        TextView title_Text_View, description_Text_view;
        ImageView icon_image;
    }

    @Override
    public int getCount() {
        return modelList.size();
    }

    @Override
    public Object getItem(int i) {
        return modelList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        viewHolder holder;
        if (view == null){
            holder = new viewHolder();
            view = layoutInflater.inflate(R.layout.row,null);

            /*locate the view in raw xml*/
            holder.title_Text_View = view.findViewById(R.id.title_id);
            holder.description_Text_view = view.findViewById(R.id.description_id);
            holder.icon_image = view.findViewById(R.id.icon_id);

            view.setTag(holder);
        }else {
            holder = (viewHolder)view.getTag();
        }

        holder.title_Text_View.setText(modelList.get(position).getTitle());
        holder.description_Text_view.setText(modelList.get(position).getDescription());
        holder.icon_image.setImageResource(modelList.get(position).getIcon());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = modelList.get(position).getDescription().toString();
                Toast.makeText(context,value,Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    public void find(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        modelList.clear();
        if (charText.length()==0){
            modelList.addAll(modelArrayList);
        }else {
            for (Model model : modelArrayList){
                if (model.getTitle().toLowerCase(Locale.getDefault()).contains(charText)){
                    modelList.add(model);
                }
            }
        }




        notifyDataSetChanged();

    }

}
