package com.example.lau.testrecyclerview.activity;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.lau.testrecyclerview.R;
import com.example.lau.testrecyclerview.adapter.ContactAdapter;
import com.example.lau.testrecyclerview.model.Contact;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView rv_contact;
    Button bt_add;
    Dialog dialog;
    ArrayList<Contact> arrayList;
    ContactAdapter contactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initClick();
    }

    public void initView(){
        bt_add = (Button) findViewById(R.id.bt_add);
        rv_contact = (RecyclerView) findViewById(R.id.rv_contact);
        rv_contact.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rv_contact.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>();
        arrayList.add(new Contact("Hiệp_1","01"));
        arrayList.add(new Contact("Hiệp_2","02"));
        arrayList.add(new Contact("Hiệp_3","03"));
        arrayList.add(new Contact("Hiệp_4","04"));
        contactAdapter = new ContactAdapter(arrayList,getApplicationContext());
        rv_contact.setAdapter(contactAdapter);
    }

    public void initClick(){
        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              showDialogAdd();

                Button dialogbtsave = (Button) dialog.findViewById(R.id.bt_save);
                dialogbtsave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        EditText etName = (EditText) dialog.findViewById(R.id.et_addName);
                        EditText etPhoneNumber = (EditText) dialog.findViewById(R.id.et_addNumber);
                        Contact newContact = new Contact(etName.getText().toString(),etPhoneNumber.getText().toString());
                        arrayList.add(1, newContact);
                        contactAdapter.notifyItemInserted(1);
                        dialog.dismiss();
                    }
                });

                Button dialogbtclose = (Button) dialog.findViewById(R.id.bt_close);
                dialogbtclose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

            }
        });
    }
     public void showDialogAdd (){
         dialog = new Dialog(MainActivity.this);
         dialog.setTitle("Add contact");
         dialog.setContentView(R.layout.dialog_add);
         dialog.show();
    }
}
