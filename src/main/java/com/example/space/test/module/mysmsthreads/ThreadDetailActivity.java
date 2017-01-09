package com.example.space.test.module.mysmsthreads;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract.PhoneLookup;
import android.telephony.gsm.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.space.test.R;

import java.util.ArrayList;

/**
 * ͬ־�ǻ�ȥ�ܽ���
 * ���Ͷ��ŵ�2�ַ���
 * ���͹㲥���Է��ܵ��㲥
 * �������ݿ�仯�Ĺ㲥���Լ����ݹ۲���
 * �Ƽ� http://www.itnose.net/detail/6132802.html
 * @author wyf
 *
 */
public class ThreadDetailActivity extends Activity implements OnClickListener {
	private static final String TAG = "ThreadDetailActivity";
	ListView mListView;
	private String thread_id;
	private String phone;
	private MyThreadDetailAdapter mThreadDetailAdapter;
	private ArrayList<ThreadDetailBean> mDatas;
	private Uri uri = Uri.parse("content://sms/");
	private String[] projection = new String[]{ "_id", "address", "person",
			"body", "type", "date" };
	private String[] contactProjection = new String[]{PhoneLookup._ID,PhoneLookup.DISPLAY_NAME};
	
	EditText sms_content;
	Button send_sms;
	
	MySendBr sendBr = new MySendBr();
	MyDeliverBr deliverBr = new MyDeliverBr();
	private mySmsContentObserver observer = new mySmsContentObserver(new Handler());
	class mySmsContentObserver extends ContentObserver{

		public mySmsContentObserver(Handler handler) {
			super(handler);
			// TODO Auto-generated constructor stub
		}
		@Override
		public void onChange(boolean selfChange) {
			// TODO Auto-generated method stub
			super.onChange(selfChange);
			init();
		}
	}
	class MySendBr extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			if (getResultCode() == Activity.RESULT_OK) {
				Log.e(TAG, "mysend_sms");
				Toast.makeText(context, "send ok", 0).show();
				//ˢ��ҳ����� TODO
				init();
				
			}
			/**
			 * ��������жϻ��ӡ����������  ��һ�ȥ����
			 */
			if (intent!=null && intent.equals("SENT_SMS_ACTION")) {
				
			}
		}
		
	}
	
	class MyDeliverBr extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			if (intent != null && intent.getAction().equals("DELIVERED_SMS_ACTION")) {
				Toast.makeText(context, "deliverd ok", 0).show();
				Log.e(TAG, "myDeliverd");
			}
		}
		
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.thread_detail);
		//��̬ע��2���㲥--���ͳɹ����Է����ճɹ�
		registerReceiver(sendBr, new IntentFilter("SENT_SMS_ACTION"));
		registerReceiver(deliverBr, new IntentFilter("DELIVERED_SMS_ACTION"));
		//�ܵ�ϵͳ�Ķ��� ע��������ݿ�仯֪ͨ�ġ���
		getContentResolver().registerContentObserver(uri, true, observer);
		
		init();
	}
	private void init() {
		Intent intent = getIntent();
		if (intent!=null) {
			thread_id = intent.getStringExtra("thread_id");
			phone = intent.getStringExtra("phone");
			Log.i(TAG, "thread_id"+thread_id+",phone::"+phone);
		}
		sms_content = (EditText) findViewById(R.id.sms_content);
		send_sms = (Button) findViewById(R.id.send_sms);
		send_sms.setOnClickListener(this);
		mListView = (ListView) findViewById(R.id.mThreadListView);
		//data--���ݿ��л�ȡ!!!!
		Cursor threadDetailCursor = getContentResolver().query(uri, projection, "thread_id=?",new String[]{thread_id} , "date desc");
		praseThreadDetailCursor(threadDetailCursor);
		//������
		mThreadDetailAdapter = new MyThreadDetailAdapter(this,mDatas);
		//����������
		mListView.setAdapter(mThreadDetailAdapter);
	}
	private void praseThreadDetailCursor(Cursor threadDetailCursor) {
		mDatas = new ArrayList<ThreadDetailBean>();
		if (threadDetailCursor!=null && threadDetailCursor.getCount()>0) {
			while (threadDetailCursor.moveToNext()) {
				String address = threadDetailCursor.getString(threadDetailCursor.getColumnIndex("address"));
				String body = threadDetailCursor.getString(threadDetailCursor.getColumnIndex("body"));
				String type = threadDetailCursor.getString(threadDetailCursor.getColumnIndex("type"));
				String date = threadDetailCursor.getString(threadDetailCursor.getColumnIndex("date"));
				
				//type��1�ǶԷ���item��Ϊ2���Լ���itme
				//ͨ���绰��ȡ��ϵ�˵�����
				String contactName = null;
				Uri contactNameUri = Uri.withAppendedPath(PhoneLookup.CONTENT_FILTER_URI, Uri.encode(address));
				Cursor contactNameCursor = getContentResolver().query(contactNameUri, contactProjection, null, null, null);
				if (contactNameCursor!=null&& contactNameCursor.moveToFirst()) {
					contactName = contactNameCursor.getString(1);
				}
				Log.i(TAG, "address"+address+",body"+body+",type"+type+",date"+date+",contactName"+contactName);
				if (type.equals("1")) {
					//�Է�
					ThreadDetailBean heiBean = null;
					if (contactName!=null) {
						heiBean = new ThreadDetailBean(contactName, date, body, R.layout.hei_item);
					}else {
						heiBean = new ThreadDetailBean(address, date, body, R.layout.hei_item);
					}
					mDatas.add(heiBean);
				}else {
					//�Լ�
					ThreadDetailBean mBean = new ThreadDetailBean(null, date, body, R.layout.me_item);
					mDatas.add(mBean);
					
				}
			}
			
			Log.i(TAG, "mDatas.size()>>>>"+mDatas.size());
		}
	}
	@SuppressWarnings("deprecation")
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.send_sms:
			String smsContent = sms_content.getText().toString().trim();
			if (smsContent.equals("")) {
				return ;
			}
			/**
			 * ����ϵͳ������Ҫһ����-smsManager
			 * 1�����ŷ��ͳɹ��Է����ܵ��Ļ����Լ����ܵ��㲥
			 * 2�����ͳɹ�Ҳ�����ܵ��㲥
			 * ����deliveryIntent  �Է��ܵ�����Ȼ��Է����ƶ�����������Ȼ���ƶ������ڻ������Լ�
			 * sentIntent ˵���Լ����͸��ƶ�������ͨѶ����
			 */
			
			SmsManager manager = SmsManager.getDefault();
			Intent mySendIntent = new Intent("SENT_SMS_ACTION");
			Intent myDeliverIntent = new Intent("DELIVERED_SMS_ACTION");
			PendingIntent sentIntent = PendingIntent.getBroadcast(ThreadDetailActivity.this, 0, mySendIntent, 0);
			PendingIntent deliveryIntent = PendingIntent.getBroadcast(ThreadDetailActivity.this, 0, myDeliverIntent, 0);
			if (smsContent.length()>70) {
				ArrayList<String> divideMessage = manager.divideMessage(smsContent);
				for (int i = 0; i < divideMessage.size(); i++) {
					manager.sendTextMessage(phone, null, divideMessage.get(i), sentIntent, deliveryIntent);
				}
			}else {
				manager.sendTextMessage(phone, null, smsContent, sentIntent, deliveryIntent);
			}
			
			ContentValues values = new ContentValues();
			values.put("address", phone); // ���͵�ַ
			values.put("body", smsContent); // ��Ϣ����
			values.put("date", System.currentTimeMillis()); // ����ʱ��
			values.put("read", 0); // 0��δ���� 1���Ѷ�
			values.put("type", 2); // 1�����գ� 2������
			getContentResolver().insert(Uri.parse("content://sms/sent"), values); // ��������

			break;

		default:
			break;
		}
	}

}
