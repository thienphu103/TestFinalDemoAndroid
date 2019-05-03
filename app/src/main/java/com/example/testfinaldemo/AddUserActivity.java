package com.example.testfinaldemo;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.testfinaldemo.dao.UserDao;
import com.example.testfinaldemo.model.User;

public class AddUserActivity extends AppCompatActivity {

    private EditText mAddUsername;
    private EditText mAddAge;
    private EditText mAddAddress;
    private String nameImage = "";
    private Button mAddBtnImage;
    private TextView mAddTxtImage;
    private Button mAddBtnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        initBindViewId();
        initEvent();
    }

    private void initEvent() {
        mAddBtnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(AddUserActivity.this);
                dialog.setContentView(R.layout.custom_dialog_image);
                dialog.setTitle("Choose Image");

                ImageView imageViewAdnroid = dialog.findViewById(R.id.item_dialog_image_android);
                ImageView imageViewPeople = dialog.findViewById(R.id.item_dialog_image_user);
                imageViewAdnroid.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        nameImage = "andorid";
                        mAddTxtImage.setText(nameImage);
                        dialog.dismiss();
                    }
                });
                imageViewPeople.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        nameImage = "people";
                        mAddTxtImage.setText(nameImage);
                        dialog.dismiss();
                    }
                });
                if (!dialog.isShowing()) {
                    dialog.show();

                }
            }
        });
        mAddBtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserDao userDao = new UserDao(getApplicationContext());
                userDao.addDataUser(new User("", mAddUsername.getText().toString(),
                        mAddAge.getText().toString(),
                        mAddAddress.getText().toString(),
                        nameImage));
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
                overridePendingTransition(0, 0);
            }
        });
    }

    private void initBindViewId() {
        mAddUsername = findViewById(R.id.add_username);
        mAddAge = findViewById(R.id.add_age);
        mAddAddress = findViewById(R.id.add_address);
        mAddBtnImage = findViewById(R.id.add_btn_image);
        mAddTxtImage = findViewById(R.id.add_txt_image);
        mAddBtnSubmit = findViewById(R.id.add_btn_submit);
    }

}
