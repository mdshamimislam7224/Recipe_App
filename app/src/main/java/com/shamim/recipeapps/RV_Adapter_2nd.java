package com.shamim.recipeapps;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RV_Adapter_2nd extends RecyclerView.Adapter<RV_Adapter_2nd.RVViewHolderclass> {

    private Context context;
    ArrayList<Model_Class_Food_Item> model_classArrayList;
    private static final String TAG = "RV_Adapter_2nd";

    public RV_Adapter_2nd(Context context, ArrayList<Model_Class_Food_Item> model_classArrayList) {
        this.model_classArrayList = model_classArrayList;
        this.context = context;
    }


    @NonNull
    @Override
    public RVViewHolderclass onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {


        return new RVViewHolderclass(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.single_row_2nd_layout, viewGroup, false));


    }

    @Override
    public void onBindViewHolder(@NonNull final RVViewHolderclass holder, @SuppressLint("RecyclerView") final int position) {
        final Model_Class_Food_Item objectModel_class = model_classArrayList.get(position);
        holder.secondrecycleviewtext.setText(objectModel_class.getImagename());

        holder.secondrecycleviewtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ItemDetailsActivity.class);
                intent.putExtra("position", holder.getAdapterPosition());
                intent.putExtra("id", model_classArrayList.get(position).getId());
                intent.putExtra("Foodtitle", model_classArrayList.get(position).getFoodtitle());
                Log.d(TAG, "id=" + model_classArrayList.get(position).getId()+
                        "Equipment=" + model_classArrayList.get(position).getEquipment()+
                        "Processing =" + model_classArrayList.get(position).getProcessing()+
                        "Position=" + holder.getAdapterPosition());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return model_classArrayList.size();
    }

    public static class RVViewHolderclass extends RecyclerView.ViewHolder {
        TextView secondrecycleviewtext;

        public RVViewHolderclass(@NonNull View itemView) {
            super(itemView);
            secondrecycleviewtext = itemView.findViewById(R.id.secondrecycleviewtext);

        }
    }

}
