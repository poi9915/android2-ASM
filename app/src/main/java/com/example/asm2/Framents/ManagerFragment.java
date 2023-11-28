package com.example.asm2.Framents;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asm2.Adapter.ProdAdapter;
import com.example.asm2.Model.Product;
import com.example.asm2.R;
import com.example.asm2.prodDAO.ProdDAO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ManagerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ManagerFragment extends Fragment {
    private ProdDAO dao;
    private ArrayList<Product> list;
    private ProdAdapter adapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recView = view.findViewById(R.id.recView);
        FloatingActionButton fabAdd = view.findViewById(R.id.fabAdd);

        dao = new ProdDAO(getContext());
        list = dao.getAllProd();
        adapter = new ProdAdapter(getContext(), list);
        recView.setAdapter(adapter);
        recView.setLayoutManager(new LinearLayoutManager(getContext()));

        fabAdd.setOnClickListener(v -> {
            showAddDialog(getContext());

        });

    }

    private void reload(){
        ManagerFragment managerFragment = ManagerFragment.newInstance(null,null);
        FragmentTransaction fragmentTransaction = requireActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout , managerFragment);
        fragmentTransaction.commit();
    }


    private void showAddDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_add_prod, null);
        builder.setView(v);
        TextView dialogTitle = v.findViewById(R.id.dialogTitle);
        dialogTitle.setText("Thêm");
        TextInputLayout tpl_dia_Name = v.findViewById(R.id.tpl_dia_Name);
        TextInputLayout tpl_dia_Price = v.findViewById(R.id.tpl_dia_Price);
        TextInputLayout tpl_dia_Num = v.findViewById(R.id.tpl_dia_Num);


        Button btnAddProd = v.findViewById(R.id.btnDialog);
        btnAddProd.setText("Thêm");
        AlertDialog dialog = builder.create();
        btnAddProd.setOnClickListener(view -> {
            //Add logic
            String name = tpl_dia_Name.getEditText().getText().toString();
            String price = tpl_dia_Price.getEditText().getText().toString();
            String num = tpl_dia_Num.getEditText().getText().toString();
            Product product = new Product(name, price, num);
            ProdDAO prodDAO = new ProdDAO(context);

            if (prodDAO.insertProd(product) > 0) {
                Toast.makeText(context, "Insert thành công", Toast.LENGTH_SHORT).show();
                list.add(product);
                adapter.notifyDataSetChanged();
                dialog.dismiss();
                reload();
            } else {
                Toast.makeText(context, "Insert Thất bại", Toast.LENGTH_SHORT).show();
            }

        });
        dialog.show();

    }

    public ManagerFragment() {
        // Required empty public constructor
    }


    public static ManagerFragment newInstance(String param1, String param2) {
        ManagerFragment fragment = new ManagerFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_manager, container, false);
    }
}