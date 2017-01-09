//package com.example.administrator.tf_phone;
//
//import android.Manifest;
//import android.app.Dialog;
//import android.content.ContentResolver;
//import android.content.ContentUris;
//import android.content.ContentValues;
//import android.content.Context;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.database.Cursor;
//import android.graphics.drawable.BitmapDrawable;
//import android.net.Uri;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.provider.ContactsContract;
//import android.support.v4.app.ActivityCompat;
//import android.support.v4.content.ContextCompat;
//import android.support.v7.app.AppCompatActivity;
//import android.text.Editable;
//import android.text.TextUtils;
//import android.text.TextWatcher;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.WindowManager;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageButton;
//import android.widget.LinearLayout;
//import android.widget.ListView;
//import android.widget.PopupWindow;
//import android.widget.TextView;
//
//import com.example.administrator.tf_phone.adapter.ContactsListAdapter;
//import com.example.administrator.tf_phone.bean.ContactsModelBean;
//import com.example.administrator.tf_phone.view.HasSimCard;
//import com.example.administrator.tf_phone.view.ToastUtil;
//import com.example.space.test.R;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Locale;
//
///**
// * 通讯录界面
// */
//public class ContactsActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {
//
//    private static final String PHONE_BOOK_LABLE = "phonebook_label";
//    //查询字段
//    private static final String[] PHONES_PROJECTION = {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
//            ContactsContract.CommonDataKinds.Phone.NUMBER, PHONE_BOOK_LABLE};
//    //联系人名称
//    private static final int PHONES_DISPLAY_NAME_INDEX = 0;
//    //电话号码
//    private static final int PHONES_NUMBER_INDEX = 1;
//    //权限码
//    private static final int PERMISSIONS_CALL_PHONE = 1;
//    private List<ContactsModelBean> list = new ArrayList<>();
//    private ContactsListAdapter mAdapter;
//    private ListView contacts_list;
//    private static final int HANLER_INFO = 0X123;
//    private static final String TAG = "ContactsActivity";
//    private TextView contact_phone1, contact_phone, contact_username;
//    private LinearLayout liner_info_start, liner_info_end, linear_backage;
//    private ImageButton delete_image, line_image, contacts_back;
//    private PopupWindow popupWindow;
//    private String username, number_phone;
//    private Button message_btn, message_callphone, contacts_news, call_phone;
//    private Button btn_one, btn_two, btn_three, btn_four, btn_five,
//            btn_six, btn_seven, btn_eight, btn_nine, btn_zero, btn_jin, btn_xin;
//
//    private EditText contacts_edit;
//    private String temp;
////    private SideBar sideBar;
////    private TextView dialog;
////    private SortAdapter adapter;
////
////    //汉字转换成拼音的类
////    private CharacterParser characterParser;
////    private List<SortModel> SourceDateList;
////    //根据拼音来排列ListView里面的数据类
////    private PinyinComparator pinyinComparator;
//
//    private Handler handler = new Handler() {
//
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            switch (msg.what) {
//                case HANLER_INFO:
//                    setVisibility();
//                    username = (String) msg.getData().get("username");
//                    number_phone = (String) msg.getData().get("number");
//                    contact_username.setText(username);
//                    contact_phone.setText(number_phone);
//                    contact_phone1.setText(number_phone);
//                    Log.e(TAG, username + number_phone);
//                    break;
//                default:
//                    break;
//            }
//        }
//    };
//
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_contacts);
//
//        initView();
//        initShow();
//        //initData();
//        getContacts(this);
//    }
//
//
//    private void getContacts(Context context) {
//
////        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.READ_CONTACTS)
////                != PackageManager.PERMISSION_GRANTED) {
////            ActivityCompat.requestPermissions((Activity) context,
////                    new String[]{android.Manifest.permission.READ_CONTACTS}, PHONES_NUMBER_INDEX);
//        //} else {
//        new Thread() {
//            public void run() {
//                try {
//                    ContentResolver mResolver = getContentResolver();
//                    //查询联系人数据，query的参数Phone.SORT_KEY_PRIMARY表示将结果集按Phone.SORT_KEY_PRIMARY排序
//                    Cursor cursor = mResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI
//                            , PHONES_PROJECTION, null, null, ContactsContract.CommonDataKinds.Phone.SORT_KEY_PRIMARY);
//                    if (cursor != null) {
//                        while (cursor.moveToNext()) {
//                            ContactsModelBean model = new ContactsModelBean();
//                            model.setPhone(cursor.getString(PHONES_NUMBER_INDEX));
//                            if (TextUtils.isEmpty(model.getPhone())) {
//                                continue;
//                            }
//                            model.setName(cursor.getString(PHONES_DISPLAY_NAME_INDEX));
//                            //model.setPhonebook_label(cursor.getString(cursor.getColumnIndex(PHONE_BOOK_LABLE)));
//                            list.add(model);
//                        }
//                        cursor.close();
//                    }
//                    runOnUiThread(new Runnable() {
//                        public void run() {
//                            mAdapter = new ContactsListAdapter(ContactsActivity.this, list, handler);
//                            contacts_list.setAdapter(mAdapter);
//                        }
//                    });
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }.start();
//    }
//
//    private void initView() {
//        contacts_list = (ListView) this.findViewById(R.id.contacts_list);
//        liner_info_start = (LinearLayout) this.findViewById(R.id.liner_info_start);
//        liner_info_end = (LinearLayout) this.findViewById(R.id.liner_info_end);
//        contact_username = (TextView) this.findViewById(R.id.contact_username);
//        contact_phone = (TextView) this.findViewById(R.id.contact_phone);
//        contact_phone1 = (TextView) this.findViewById(R.id.contact_phone1);
//        linear_backage = (LinearLayout) this.findViewById(R.id.linear_backage);
//        delete_image = (ImageButton) this.findViewById(R.id.delete_image);
//        line_image = (ImageButton) this.findViewById(R.id.line_image);
//        contacts_back = (ImageButton) this.findViewById(R.id.contacts_back);
//        contacts_news = (Button) this.findViewById(R.id.contacts_news);
//        message_btn = (Button) this.findViewById(R.id.message_btn);
//        message_callphone = (Button) this.findViewById(R.id.message_callphone);
//        call_phone = (Button) this.findViewById(R.id.call_phone);
//        contacts_edit = (EditText) this.findViewById(R.id.contacts_edit);
//        btn_one = (Button) this.findViewById(R.id.btn_one);
//        btn_two = (Button) this.findViewById(R.id.btn_two);
//        btn_three = (Button) this.findViewById(R.id.btn_three);
//        btn_four = (Button) this.findViewById(R.id.btn_four);
//        btn_five = (Button) this.findViewById(R.id.btn_five);
//        btn_six = (Button) this.findViewById(R.id.btn_six);
//        btn_seven = (Button) this.findViewById(R.id.btn_seven);
//        btn_eight = (Button) this.findViewById(R.id.btn_eight);
//        btn_nine = (Button) this.findViewById(R.id.btn_nine);
//        btn_zero = (Button) this.findViewById(R.id.btn_zero);
//        btn_jin = (Button) this.findViewById(R.id.btn_jin);
//        btn_xin = (Button) this.findViewById(R.id.btn_xin);
//
//        contacts_edit.addTextChangedListener(new TextWatcher() {
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                //当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
//                //getContacts(getApplicationContext());
//            }
//
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//            }
//
//            public void afterTextChanged(Editable editable) {
//                temp = contacts_edit.getText().toString().trim();
//                //清空list内容
//                list.clear();
//                GetPinyinByHanzi(temp);
//                mAdapter = new ContactsListAdapter(ContactsActivity.this, list, handler);
//                contacts_list.setAdapter(mAdapter);
//                ToastUtil.showToast(getApplicationContext(), "名字为：" + temp);
//            }
//        });
//    }
//
//    private void initShow() {
//        contacts_edit.setOnClickListener(this);
//        line_image.setOnClickListener(this);
//        message_btn.setOnClickListener(this);
//        contacts_back.setOnClickListener(this);
//        message_callphone.setOnClickListener(this);
//        contacts_news.setOnClickListener(this);
//        delete_image.setOnClickListener(this);
//        delete_image.setOnLongClickListener(this);
//        btn_one.setOnClickListener(this);
//        btn_two.setOnClickListener(this);
//        btn_three.setOnClickListener(this);
//        btn_four.setOnClickListener(this);
//        btn_five.setOnClickListener(this);
//        btn_six.setOnClickListener(this);
//        btn_seven.setOnClickListener(this);
//        btn_eight.setOnClickListener(this);
//        btn_nine.setOnClickListener(this);
//        btn_zero.setOnClickListener(this);
//        btn_jin.setOnClickListener(this);
//        btn_xin.setOnClickListener(this);
//        call_phone.setOnClickListener(this);
//    }
//
//    public void onBackPressed() {
//        list.clear();
//        super.onBackPressed();
//    }
//
//    private void setVisibility() {
//        liner_info_start.setVisibility(View.GONE);
//        liner_info_end.setVisibility(View.VISIBLE);
//        linear_backage.setBackgroundResource(R.drawable.contacts_end);
//        delete_image.setVisibility(View.GONE);
//        line_image.setVisibility(View.VISIBLE);
//    }
//
//    public void onClick(View v) {
//
//        switch (v.getId()) {
//            case R.id.line_image:
//                ShowPopWindow();
//                if (popupWindow.isShowing()) {
//                    popupWindow.dismiss();
//                } else {
//                    popupWindow.showAsDropDown(v);
//                }
//                break;
//            case R.id.message_btn:
//                StartIntentMessage();
//                break;
//            case R.id.contacts_back:
//                ContactsActivity.this.finish();
//                break;
//            case R.id.message_callphone:
//                CallPhone();
//                break;
//            case R.id.contacts_news:
//                startActivity(new Intent(ContactsActivity.this, AddContactsActivity.class));
//                break;
//            case R.id.btn_one:
//                String str1 = contacts_edit.getText().toString().trim();
//                str1 += "1";
//                contacts_edit.setText(str1);
//                break;
//            case R.id.btn_two:
//                String str2 = contacts_edit.getText().toString().trim();
//                str2 += "2";
//                contacts_edit.setText(str2);
//                break;
//            case R.id.btn_three:
//                String str3 = contacts_edit.getText().toString().trim();
//                str3 += "3";
//                contacts_edit.setText(str3);
//                break;
//            case R.id.btn_four:
//                String str4 = contacts_edit.getText().toString().trim();
//                str4 += "4";
//                contacts_edit.setText(str4);
//                break;
//            case R.id.btn_five:
//                String str5 = contacts_edit.getText().toString().trim();
//                str5 += "5";
//                contacts_edit.setText(str5);
//                break;
//            case R.id.btn_six:
//                String str6 = contacts_edit.getText().toString().trim();
//                str6 += "6";
//                contacts_edit.setText(str6);
//                break;
//            case R.id.btn_seven:
//                String str7 = contacts_edit.getText().toString().trim();
//                str7 += "7";
//                contacts_edit.setText(str7);
//                break;
//            case R.id.btn_eight:
//                String str8 = contacts_edit.getText().toString().trim();
//                str8 += "8";
//                contacts_edit.setText(str8);
//                break;
//            case R.id.btn_nine:
//                String str9 = contacts_edit.getText().toString().trim();
//                str9 += "9";
//                contacts_edit.setText(str9);
//                break;
//            case R.id.btn_zero:
//                String str0 = contacts_edit.getText().toString().trim();
//                str0 += "0";
//                contacts_edit.setText(str0);
//                break;
//            case R.id.btn_jin:
//                String strjin = contacts_edit.getText().toString().trim();
//                strjin += "#";
//                contacts_edit.setText(strjin);
//                break;
//            case R.id.btn_xin:
//                String strxin = contacts_edit.getText().toString().trim();
//                strxin += "*";
//                contacts_edit.setText(strxin);
//                break;
//            case R.id.delete_image:
//                String phoneStr = contacts_edit.getText().toString().trim();
//                if (TextUtils.isEmpty(phoneStr)) {
//                } else {
//                    contacts_edit.setText(phoneStr.substring(0, phoneStr.length() - 1));
//                }
//                break;
//            case R.id.call_phone:
//                HasSimCard.hasSimCard(getApplicationContext());
//                CallPhone();
//                break;
//            default:
//                break;
//        }
//    }
//
//    private void ShowPopWindow() {
//        LayoutInflater inflater = LayoutInflater.from(this);
//        View view = inflater.inflate(R.layout.contacts_popupwindow, null);
//        Button compile_btn = (Button) view.findViewById(R.id.compile_btn);
//        Button delete_contacts_btn = (Button) view.findViewById(R.id.delete_contacts_btn);
//        Button message_btn = (Button) view.findViewById(R.id.message_btn);
//        message_btn.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//                ToastUtil.showToast(getApplicationContext(), "短信分享");
//            }
//        });
//        compile_btn.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                StartIntentCompile();
//                popupWindow.dismiss();
//            }
//        });
//        delete_contacts_btn.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//                ShowDialog();
//                popupWindow.dismiss();
//            }
//        });
//        popupWindow = new PopupWindow(view, 450, 350);
//        popupWindow.setBackgroundDrawable(new BitmapDrawable());
//        popupWindow.setOutsideTouchable(true);
//        popupWindow.setFocusable(true);
//
//        //popupWindow.showAtLocation(line_image, Gravity.BOTTOM, 0, 0);
//        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
//        layoutParams.alpha = 0.7f;
//        getWindow().setAttributes(layoutParams);
//        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
//            public void onDismiss() {
//                WindowManager.LayoutParams layoutParams1 = getWindow().getAttributes();
//                layoutParams1.alpha = 1f;
//                getWindow().setAttributes(layoutParams1);
//            }
//        });
//    }
//
//    private void ShowDialog() {
//
//        final Dialog dialog = new Dialog(ContactsActivity.this);
//        LayoutInflater inflater = LayoutInflater.from(ContactsActivity.this);
//        View vieww = inflater.inflate(R.layout.contacts_dialog, null);
//        dialog.setContentView(vieww);
//        ImageButton dialog_cancel = (ImageButton) vieww.findViewById(R.id.dialog_cancel);
//        ImageButton dialog_delete = (ImageButton) vieww.findViewById(R.id.dialog_delete);
//        dialog_cancel.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });
//        dialog_delete.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                try {
//                    testDelete();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                mAdapter.notifyDataSetChanged();
//                dialog.dismiss();
//            }
//        });
//        dialog.show();
//    }
//
//    private void StartIntentMessage() {
//
//        Intent intent = new Intent();
//        intent.setClass(ContactsActivity.this, MessageActivity.class);
////        Bundle bundle = new Bundle();
////        bundle.putString("name", username);
////        bundle.putString("phone", number_phone);
////        intent.putExtras(bundle);
//        startActivity(intent);
//    }
//
//    private void StartIntentCompile() {
//        Intent intent = new Intent();
//        intent.setClass(ContactsActivity.this, CompileActivity.class);
//        Bundle bundle = new Bundle();
//        bundle.putString("name", username);
//        bundle.putString("phone", number_phone);
//        intent.putExtras(bundle);
//        startActivity(intent);
//    }
//
//    private void CallPhone() {
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
//                != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, PERMISSIONS_CALL_PHONE);
//        } else {
//            Intent intent = new Intent(Intent.ACTION_CALL);
//            Uri data = Uri.parse("tel:" + contacts_edit.getText().toString().trim() + "");
//            intent.setData(data);
//            startActivity(intent);
//            contacts_edit.setText("");
//        }
//    }
//
//    /**
//     * 删除联系人
//     */
//    public void testDelete() throws Exception {
//        //根据姓名删除联系人
//        String name = username;
//        //根据姓名求id
//        Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
//        ContentResolver resolver = getApplicationContext().getContentResolver();
//        Cursor cursor = resolver.query(uri, new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME}, "display_name=?", new String[]{name}, null);
//        // if (cursor.moveToFirst()) {
//        //      int id = cursor.getInt(0);
//        //根据id删除data中的相应数据
//        resolver.delete(uri, "display_name=?", new String[]{name});
//        // uri = Uri.parse("content://com.android.contacts/data");
//        // resolver.delete(uri, "raw_contact_id=?", new String[]{id + ""});
//        ToastUtil.showToast(getApplicationContext(), "删除成功姓名为:" + name);
//        // }
//    }
//
//    private void GetPinyinByHanzi(String name) {
//        ContentValues values = new ContentValues();
//        ContentResolver cr = getContentResolver();
//        Uri rawContactUri = cr.insert(ContactsContract.RawContacts.CONTENT_URI, values);
//        long rawContactId = ContentUris.parseId(rawContactUri);
//        if (name.length() > 0) {
//            values.clear();
//            values.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId);
//            values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
//            values.put(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME, name);
//            cr.insert(ContactsContract.Data.CONTENT_URI, values);
//            String[] projection = {"sort_key"};
//            String where = ContactsContract.RawContacts.CONTACT_ID + "=" + rawContactId;
//            Cursor cursor = cr.query(ContactsContract.RawContacts.CONTENT_URI, projection, where, null, null);
//            if (cursor != null) {
//                cursor.moveToFirst();
//                String pinyin = cursor.getString(cursor.getColumnIndex("sort_key"));
//                String res = temp;
//                for (int i = 0; i < pinyin.length(); i++) {
//                    String temp = pinyin.substring(i, i + 1);
//                    if (temp.matches("[a-zA-Z ]")) {
//                        res += temp;
//                    }
//                }
//                res = res.substring(0, res.length() - 1);
//                Log.e(TAG, name + " translate = \"" + res.toLowerCase(Locale.getDefault()) + "\"");
//            }
//        }
//        cr.delete(ContentUris.withAppendedId(ContactsContract.RawContacts.CONTENT_URI, rawContactId), null, null);
//    }
//
//
////    private void initData() {
////
////        //实例化汉字转拼音类
////        characterParser = CharacterParser.getInstance();
////        pinyinComparator = new PinyinComparator();
////        sideBar = (SideBar) findViewById(R.id.sidrbar);
////        dialog = (TextView) findViewById(R.id.dialog);
////        sideBar.setTextView(dialog);
////        //设置右侧触摸监听
////        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
////            public void onTouchingLetterChanged(String s) {
////                //该字母首次出现的位置
////                int position = adapter.getPositionForSection(s.charAt(0));
////                if (position != -1) {
////                    contacts_list.setSelection(position);
////                }
////            }
////        });
////
//////        contacts_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//////            public void onItemClick(AdapterView<?> parent, View view,
//////                                    int position, long id) {
//////                //这里要利用adapter.getItem(position)来获取当前position所对应的对象
//////                Toast.makeText(getApplication(), ((SortModel) adapter.getItem(position)).getName(), Toast.LENGTH_SHORT).show();
//////            }
//////        });
////
////        //SourceDateList = filledData(getResources().getStringArray(R.array.date));
////        // 根据a-z进行排序源数据
//////        Collections.sort(SourceDateList, pinyinComparator);
//////        adapter = new SortAdapter(this, SourceDateList);
//////        contacts_list.setAdapter(adapter);
////        //根据输入框输入值的改变来过滤搜索
////        contacts_edit.addTextChangedListener(new TextWatcher() {
////            public void onTextChanged(CharSequence s, int start, int before, int count) {
////                //当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
////                filterData(s.toString());
////            }
////
////            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
////            }
////
////            public void afterTextChanged(Editable s) {
////            }
////        });
////    }
////
////    /**
////     * 为ListView填充数据
////     */
////    private List<SortModel> filledData(String[] date) {
////        List<SortModel> mSortList = new ArrayList<SortModel>();
////
////        for (int i = 0; i < date.length; i++) {
////            SortModel sortModel = new SortModel();
////            sortModel.setName(date[i]);
////            //汉字转换成拼音
////            String pinyin = characterParser.getSelling(date[i]);
////            String sortString = pinyin.substring(0, 1).toUpperCase();
////            // 正则表达式，判断首字母是否是英文字母
////            if (sortString.matches("[A-Z]")) {
////                sortModel.setSortLetters(sortString.toUpperCase());
////            } else {
////                sortModel.setSortLetters("#");
////            }
////            mSortList.add(sortModel);
////        }
////        return mSortList;
////    }
////
////    /**
////     * 根据输入框中的值来过滤数据并更新ListView
////     */
////    private void filterData(String filterStr) {
////        List<SortModel> filterDateList = new ArrayList<SortModel>();
////        if (TextUtils.isEmpty(filterStr)) {
////            filterDateList = SourceDateList;
////        } else {
////            filterDateList.clear();
////            for (SortModel sortModel : SourceDateList) {
////                String name = sortModel.getName();
////                if (name.indexOf(filterStr.toString()) != -1 || characterParser.getSelling(name).startsWith(filterStr.toString())) {
////                    filterDateList.add(sortModel);
////                }
////            }
////        }
////        // 根据a-z进行排序
////        Collections.sort(filterDateList, pinyinComparator);
////        adapter.updateListView(filterDateList);
////    }
//
//    /**
//     * 删除按钮长按事件
//     */
//    public boolean onLongClick(View view) {
//        contacts_edit.setText("");
//        return true;
//    }
//}
//
//
