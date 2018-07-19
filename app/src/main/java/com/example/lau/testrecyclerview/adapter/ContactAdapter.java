package com.example.lau.testrecyclerview.adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lau.testrecyclerview.R;
import com.example.lau.testrecyclerview.activity.MainActivity;
import com.example.lau.testrecyclerview.model.Contact;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.viewHolder> {
    ArrayList<Contact> dataContact;
    Context context;
    Dialog dialog;

    public ContactAdapter(ArrayList<Contact> dataContact, Context context) {
        this.dataContact = dataContact;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View itemview = layoutInflater.inflate(R.layout.item_contact,viewGroup,false);
        return new viewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder viewHolder, int i) {
        viewHolder.tv_name.setText(dataContact.get(i).getName());
        viewHolder.tv_phoneNumber.setText(dataContact.get(i).getPhoneNumber());
    }

    @Override
    public int getItemCount() {
        return dataContact.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        TextView tv_name, tv_phoneNumber;
        EditText et_updateName, et_updateNumber;
        Button bt_delete, bt_saveUpdate;
        public viewHolder(@NonNull final View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_phoneNumber = (TextView) itemView.findViewById(R.id.tv_phoneNumber);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Dialog dialog = new Dialog(itemView.getContext());
                    dialog.setContentView(R.layout.dialog_update);
                    dialog.show();
                    bt_delete = dialog.findViewById(R.id.bt_delete);
                    bt_saveUpdate = dialog.findViewById(R.id.bt_saveUpdate);
                    et_updateName = dialog.findViewById(R.id.et_updateName);
                    et_updateNumber = dialog.findViewById(R.id.et_updateNumber);
                    et_updateName.setText(dataContact.get(getAdapterPosition()).getName());
                    et_updateNumber.setText(dataContact.get(getAdapterPosition()).getPhoneNumber());

                    bt_delete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            removeItem(getAdapterPosition());
                            dialog.dismiss();
                            notifyDataSetChanged();
                        }
                    });

                    bt_saveUpdate.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            updateItem(getAdapterPosition(),et_updateName.getText().toString(),et_updateNumber.getText().toString());
                            dialog.dismiss();
                        }
                    });
                }
            });

        }

    }

    private void removeItem (int i){
        dataContact.remove(i);
    }

    private void updateItem(int i, String name, String phoneNumber){
        Contact data = new Contact(name,phoneNumber);
        dataContact.set(i,data);
        notifyDataSetChanged();

    }

}
