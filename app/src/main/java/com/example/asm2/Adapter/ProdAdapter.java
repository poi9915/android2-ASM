package com.example.asm2.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asm2.Model.Product;
import com.example.asm2.R;
import com.example.asm2.prodDAO.ProdDAO;
import com.google.android.material.textfield.TextInputLayout;

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

        holder.tv_recName.setText("Tên sp:"+product.getName());
        holder.tv_recDes.setText("Giá:"+product.getPrice());
        holder.tv_recNum.setText("Số Lượng"+ product.getSoLuong());

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
                }
            });
            builder.show();
        });
        holder.iv_recUpdate.setOnClickListener(v ->{
            Product item = list.get(position);
            showUpdateDialog(item);
        });
    }

        public void showUpdateDialog(Product product) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.dialog_add_prod, null);

        builder.setView(v);
        TextView dialogTitle =  v.findViewById(R.id.dialogTitle);
        dialogTitle.setText("Update");
        TextInputLayout tpl_dia_Name = v.findViewById(R.id.tpl_dia_Name);
        TextInputLayout tpl_dia_Price = v.findViewById(R.id.tpl_dia_Price);
        TextInputLayout tpl_dia_Num = v.findViewById(R.id.tpl_dia_Num);

        tpl_dia_Name.getEditText().setText(product.getName());
        tpl_dia_Price.getEditText().setText(product.getPrice());
        tpl_dia_Num.getEditText().setText(product.getPrice());


        Button btnAddProd = v.findViewById(R.id.btnDialog);
        btnAddProd.setText("Update");
        AlertDialog dialog = builder.create();
        btnAddProd.setOnClickListener(view -> {
            //Add logic
            int id = product.getIdProd();
            String name = tpl_dia_Name.getEditText().getText().toString();
            String price = tpl_dia_Price.getEditText().getText().toString();
            String num = tpl_dia_Num.getEditText().getText().toString();
            Product newProduct = new Product(id,name, price, num);
            ProdDAO prodDAO = new ProdDAO(context);

            if (prodDAO.updateProdByObj(newProduct) > 0) {
                Toast.makeText(context, "Update thành công", Toast.LENGTH_SHORT).show();
                int itemPosition = list.indexOf(product);
                list.set(itemPosition , newProduct);
                notifyDataSetChanged();
                dialog.dismiss();
            } else {
                Toast.makeText(context, "Update Thất bại", Toast.LENGTH_SHORT).show();
            }

        });
        dialog.show();

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
        TextView tv_recName , tv_recDes ,tv_recNum;
        ImageView  iv_recRevome , iv_recUpdate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_recName = itemView.findViewById(R.id.tv_recName);
            tv_recDes = itemView.findViewById(R.id.tv_recDes);
            iv_recRevome = itemView.findViewById(R.id.iv_recRevome);
            iv_recUpdate = itemView.findViewById(R.id.iv_recUpdate);
            tv_recNum = itemView.findViewById(R.id.tv_recNum);
        }
    }
}
