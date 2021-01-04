package com.shamim.recipeapps;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RVAdapter_Food_Item extends RecyclerView.Adapter<RVAdapter_Food_Item.RVViewHolderclass> {

    private Context context;
    ArrayList<Model_Class_Food_Item> model_classArrayList ;


    public RVAdapter_Food_Item(Context context, ArrayList<Model_Class_Food_Item> model_classArrayList)
    {
        this.model_classArrayList = model_classArrayList;
        this.context = context;
    }


    @NonNull
    @Override
    public RVViewHolderclass onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {


        return  new RVViewHolderclass(LayoutInflater.from(viewGroup.getContext())
       .inflate(R.layout.single_row_food_item,viewGroup,false));


    }

    @Override
    public void onBindViewHolder(@NonNull final RVViewHolderclass holder, final int position) {

        final Model_Class_Food_Item objectModel_class = model_classArrayList.get(position);
        holder.imagenameTV.setText(objectModel_class.getImagename());
        holder.objectImageView.setImageBitmap(objectModel_class.getImage());


        holder.objectImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "select", Toast.LENGTH_SHORT).show();
                Log.d("Log", String.valueOf(context));
                Intent intent = new Intent(context,Second_RecycleView.class);
                intent.putExtra("id",model_classArrayList.get(position).getId());
                intent.putExtra("Foodtitle",model_classArrayList.get(position).getFoodtitle());
                //intent.putExtra("id",model_classArrayList.indexOf(0));
                Log.d("Log", String.valueOf(intent));
                context.startActivity(intent);

            }
        });



    }

    @Override
    public int getItemCount() {
        return model_classArrayList.size();
    }

    public static  class  RVViewHolderclass extends RecyclerView.ViewHolder{
        TextView imagenameTV;
        ImageView objectImageView;

        public RVViewHolderclass(@NonNull View itemView) {
            super(itemView);
            imagenameTV=itemView.findViewById(R.id.imageDetailTV);
            objectImageView= itemView.findViewById(R.id.imageTV);

        }
    }

}
