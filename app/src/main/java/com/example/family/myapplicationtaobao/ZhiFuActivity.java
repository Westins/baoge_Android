package com.example.family.myapplicationtaobao;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
//支付界面
public class ZhiFuActivity extends AppCompatActivity implements View.OnClickListener{
    Context context;
    private ImageView img_zhifutc;
    private EditText zf_pwd1;
    private EditText zf_pwd2;
    private EditText zf_pwd3;
    private EditText zf_pwd4;
    private EditText zf_pwd5;
    private EditText zf_pwd6;
    private LinearLayout zhifu_srk;

    EditText [] editText ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhifu);
        initView();
        context = this;

    }

    private void initView() {
        img_zhifutc = (ImageView) findViewById(R.id.img_zhifutc);
        zf_pwd1 = (EditText) findViewById(R.id.zf_pwd1);
        zf_pwd2 = (EditText) findViewById(R.id.zf_pwd2);
        zf_pwd3 = (EditText) findViewById(R.id.zf_pwd3);
        zf_pwd4 = (EditText) findViewById(R.id.zf_pwd4);
        zf_pwd5 = (EditText) findViewById(R.id.zf_pwd5);
        zf_pwd6 = (EditText) findViewById(R.id.zf_pwd6);
        zhifu_srk = (LinearLayout) findViewById(R.id.zhifu_srk);


        img_zhifutc.setOnClickListener(this);
        editText = new EditText[]{zf_pwd1, zf_pwd2, zf_pwd3, zf_pwd4, zf_pwd5, zf_pwd6};

        showSoftInputFromWindow(this,zf_pwd1);
//        zf_pwd1.setFocusable(true);
//
//        zf_pwd1.setFocusableInTouchMode(true);
//
//        zf_pwd1.requestFocus();
        for (int i=0;i<editText.length;i++){
            if (i<editText.length-1) {
                qhjd(editText[i], editText[i + 1]);
            }else{
                editText[editText.length-1].addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void afterTextChanged(Editable editable2) {
                        if (editable2.length() != 0) {
                            submit();
                        }
                    }
                });
            }
        }
    }

    private void submit() {
        // validate
        String pwd1 = zf_pwd1.getText().toString().trim();
        if (TextUtils.isEmpty(pwd1)) {
            Toast.makeText(this, "pwd1不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String pwd2 = zf_pwd2.getText().toString().trim();
        if (TextUtils.isEmpty(pwd2)) {
            Toast.makeText(this, "pwd2不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String pwd3 = zf_pwd3.getText().toString().trim();
        if (TextUtils.isEmpty(pwd3)) {
            Toast.makeText(this, "pwd3不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String pwd4 = zf_pwd4.getText().toString().trim();
        if (TextUtils.isEmpty(pwd4)) {
            Toast.makeText(this, "pwd4不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String pwd5 = zf_pwd5.getText().toString().trim();
        if (TextUtils.isEmpty(pwd5)) {
            Toast.makeText(this, "pwd5不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String pwd6 = zf_pwd6.getText().toString().trim();
        if (TextUtils.isEmpty(pwd6)) {
            Toast.makeText(this, "pwd6不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        String pwdx = pwd1+pwd2+pwd3+pwd4+pwd5+pwd6;
        SharedPreferences sp = getSharedPreferences("yhdl",MODE_PRIVATE);
        String name = sp.getString("zhifumm","");//键，如果没有希望返回的值
        if("".equals(name)){
            name="123456";
        }
        if(pwdx.equals(name)){
            Toast.makeText(this, "支付成功", Toast.LENGTH_SHORT).show();
            finish();
        }else {
            Toast.makeText(this, "支付密码不正确", Toast.LENGTH_SHORT).show();
            showSoftInputFromWindow((Activity) context,zf_pwd1);
            zf_pwd1.setText("");
            zf_pwd2.setText("");
            zf_pwd3.setText("");
            zf_pwd4.setText("");
            zf_pwd5.setText("");
            zf_pwd6.setText("");
        }

        // TODO validate success, do something
    }
    public static void showSoftInputFromWindow(Activity activity, EditText editText) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }
    public void qhjd(final EditText e1, final EditText e2){
        e1.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable2) {
                    if (editable2.length() != 0) {
                        e2.setFocusable(true);
                        e2.setFocusableInTouchMode(true);
                        e2.requestFocus();
                    }
                }
            });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_zhifutc:
//                startActivity(new Intent(this,ZhuActivity.class));
                finish();
//                Toast.makeText(this, ""+zf_pwd1.getId(), Toast.LENGTH_SHORT).show();
                break;

        }
    }
}
