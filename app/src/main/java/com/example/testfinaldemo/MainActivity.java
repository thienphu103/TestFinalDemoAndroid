package com.example.testfinaldemo;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.testfinaldemo.adapter.AdapterUser;
import com.example.testfinaldemo.dao.UserDao;
import com.example.testfinaldemo.model.User;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    AdapterUser adapterUser;
    ArrayList<User> userArrayList;
    UserDao userDao;
    private Button mBtnAddUser;
    private EditText mEdtDialogUsername;
    private EditText mEdtDialogAge;
    private EditText mEdtDialogAddress;
    private Button mBtnDialogChooseImage;
    private TextView mTxtDialogImage;
    private Button mBtnDialogUpdate;
    private Button mBtnDialogDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initBindViewId();
        initListView();
        initEvent();


    }

    private void initEvent() {
        mBtnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AddUserActivity.class ));
                finish();
                overridePendingTransition(0,0);
            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.custom_dialog_details);
                dialog.setTitle("Choose Image");
                final User userData=userArrayList.get(position);

                mEdtDialogUsername = dialog.findViewById(R.id.edt_dialog_username);
                mEdtDialogAge = dialog.findViewById(R.id.edt_dialog_age);
                mEdtDialogAddress = dialog.findViewById(R.id.edt_dialog_address);
                mTxtDialogImage = dialog.findViewById(R.id.txt_dialog_image);
                mBtnDialogUpdate = dialog.findViewById(R.id.btn_dialog_update);
                mBtnDialogDelete = dialog.findViewById(R.id.btn_dialog_delete);
                mEdtDialogUsername.setText(userData.getUsername());
                mEdtDialogAge.setText(userData.getAge());
                mEdtDialogAddress.setText(userData.getAddress());
                mTxtDialogImage.setText(userData.getImage());
                mBtnDialogDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        userDao.deleteDataUser(userData.getId());
                        dialog.dismiss();
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        finish();
                        overridePendingTransition(0,0);

                    }
                });
                mBtnDialogUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        userDao.updateDataUser( new User(userData.getId(), mEdtDialogUsername.getText().toString(),
                                mEdtDialogAge.getText().toString(),
                                mEdtDialogAddress.getText().toString(),
                                mTxtDialogImage.getText().toString()));
                        dialog.dismiss();
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        finish();
                        overridePendingTransition(0,0);
                    }
                });

                if (!dialog.isShowing()) {
                    dialog.show();

                }
            }
        });
    }

    private void initListView() {
        userDao = new UserDao(getApplicationContext());
        userArrayList = userDao.getAllDataUser();
        adapterUser = new AdapterUser(getApplicationContext(), R.layout.custom_item_list, userArrayList);
        mListView.setAdapter(adapterUser);
        adapterUser.notifyDataSetChanged();
    }

    private void initBindViewId() {
        mListView = findViewById(R.id.list_view);
        mBtnAddUser = findViewById(R.id.btn_add_user);
    }

}
