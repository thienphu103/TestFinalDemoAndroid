package com.example.testfinaldemo.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.testfinaldemo.R;
import com.example.testfinaldemo.model.User;

import java.util.ArrayList;

public class AdapterUser extends ArrayAdapter<User> {
    Context context;
    int resource;
    ArrayList<User> userArrayList;

    public AdapterUser(Context context, int resource, ArrayList<User> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.userArrayList = objects;
    }

    @Override
    public int getCount() {
        return userArrayList.size();
    }


    @Override
    public User getItem(int position) {
        return userArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView =  LayoutInflater.from(context).inflate(resource, null);

        ViewHolder viewHolder = new ViewHolder();
        User userData = userArrayList.get(position);

        viewHolder.txtUsername = convertView.findViewById(R.id.item_username);
        viewHolder.txtAddress = convertView.findViewById(R.id.item_address);
        viewHolder.txtAge = convertView.findViewById(R.id.item_age);
        viewHolder.imgAvatar = convertView.findViewById(R.id.item_image);

        viewHolder.txtUsername.setText(userData.getUsername());
        viewHolder.txtAge.setText(userData.getAge());
        viewHolder.txtAddress.setText(userData.getAddress());
        int resourceImage=context.getResources().getIdentifier(context.getPackageName()+":drawable/"+userData.getImage(),null,null);
        viewHolder.imgAvatar.setImageResource(resourceImage);
        return convertView;

    }

    class ViewHolder {
        TextView txtUsername;
        TextView txtAge;
        TextView txtAddress;
        ImageView imgAvatar;
    }
}
