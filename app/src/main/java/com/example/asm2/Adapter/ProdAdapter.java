package com.example.asm2.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asm2.Model.Product;
import com.example.asm2.R;
import com.example.asm2.prodDAO.ProdDAO;

import java.util.ArrayList;

public class ProdAdapter extends RecyclerView.Adapter<ProdAdapter.ViewHolder> {
    ArrayList<Product> list;
    Context context;
    ProdDAO prodDAO;


    public ProdAdapter(Context context,ArrayList<Product> list) {
        this.list = list;
        this.context = context;
        this.prodDAO = new ProdDAO(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rec_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = list.get(position);

        holder.tv_recName.setText(product.getName());
        holder.tv_recDes.setText(product.getPrice()+";" + product.getSoLuong());
        holder.img_recIcon.setImageResource(android.R.drawable.arrow_down_float);

        holder.iv_recRevome.setOnClickListener(view ->{
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Delete");
            builder.setMessage("You want to delete item id :" + product.getIdProd());
            builder.setNegativeButton("No ", (dialog, which) -> {
                // no button event
//                    auto dis
            });
            builder.setPositiveButton("Yes", (dialog, which) -> {
                //Yes button event
                ProdDAO dap = new ProdDAO(context);
                if (dap.deleteProdByID(product.getIdProd()) > 0 ){
                    list.remove(product);
                    notifyDataSetChanged();
//                        notifyItemInserted(position);
//                        ((Activity) context).runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                notifyDataSetChanged();
//                            }
//                        });
                }
            });
            builder.show();
        });
    }

    @Override
    public int getItemCount() {
        if (list != null){
            return list.size();
        }else {
            return -1;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_recName , tv_recDes;
        ImageView img_recIcon , iv_recRevome;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_recName = itemView.findViewById(R.id.tv_recName);
            tv_recDes = itemView.findViewById(R.id.tv_recDes);
            img_recIcon = itemView.findViewById(R.id.img_recIcon);
            iv_recRevome = itemView.findViewById(R.id.iv_recRevome);
        }
    }
}
