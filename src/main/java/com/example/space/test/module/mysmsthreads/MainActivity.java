package com.example.space.test.module.mysmsthreads;

import android.app.Activity;
import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.PhoneLookup;
import android.support.v4.widget.CursorAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.space.test.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * AsyncQueryHandler �첽���ݿ��ѯ CursorAdapter �������ݿ�����е������� ��ȡ���ŵ�url
 * 
 * @author wyf
 * 
 */
public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";
	ListView mListView;
	private CursorAdapter mAdapter;
	private MyAsyncQueryHandler asyncQueryHandler;
	private String[] projection = new String[] { "sms.thread_id AS _id",
			"sms.body AS snippet", "groups.msg_count AS msg_count",
			"sms.address AS address", "sms.date AS date" };

	private String[] contentProjection = new String[] { PhoneLookup._ID,
			PhoneLookup.DISPLAY_NAME };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main2);
		mListView = (ListView) findViewById(R.id.mListView);
		mAdapter = new MyAdatper(this, null);

		// ��ȡ���ݿ�����
		mListView.setAdapter(mAdapter);
		asyncQueryHandler = new MyAsyncQueryHandler(getContentResolver());
		asyncQueryHandler.startQuery(0, null,
				Uri.parse("content://sms/conversations/"), projection, null,
				null, " date desc");
	}

	class MyAsyncQueryHandler extends AsyncQueryHandler {

		public MyAsyncQueryHandler(ContentResolver cr) {
			super(cr);
			// TODO Auto-generated constructor stub
		}

		@Override
		protected void onQueryComplete(int token, Object cookie, Cursor cursor) {
			super.onQueryComplete(token, cookie, cursor);
			Log.i(TAG, "onQueryComplete" + (cursor == null));
			mAdapter.changeCursor(cursor);
		}
	}

	class MyAdatper extends CursorAdapter {

		private ViewHolder mHolder;

		public MyAdatper(Context context, Cursor c) {
			super(context, c);
			// TODO Auto-generated constructor stub
		}

		/**
		 * 
		 "sms.thread_id AS _id", "sms.body AS snippet",
		 * "groups.msg_count AS msg_count", "sms.address AS address",
		 * "sms.date AS date"
		 * 
		 * 	private String[] projection = new String[] { "_id", "address", "person",
			"body", "type", "date" };
		 */
		@Override
		public void bindView(View view, Context arg1, Cursor cursor) {
			if (view != null) {
				mHolder = (ViewHolder) view.getTag();
				TextView nameTv = mHolder.name;
				TextView contentTv = mHolder.content;

				final String thread_id = cursor.getString(0);
				String content = cursor.getString(1);
				int count = cursor.getInt(2);
				final String address = cursor.getString(3);
				long date = cursor.getLong(4);
				// ��������
				if (content != null && content.length() > 10) {
					contentTv.setText(content.substring(0, 10));
				} else {
					contentTv.setText(content);

				}
				// ����--ʱ��--java����֪ʶ����ȡʱ���
				Calendar calendar = Calendar.getInstance();
				calendar.setTimeInMillis(date);

				SimpleDateFormat format = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				String formatTime = format.format(calendar.getTime());

				/**
				 * ����ɾֲ���������Ҫ����ɳ�Ա����
				 */
				String phoneName = null;
				Uri uri = Uri.withAppendedPath(PhoneLookup.CONTENT_FILTER_URI,
						Uri.encode(address));
				Cursor concatCursor = getContentResolver().query(uri,
						contentProjection, null, null, null);
				if (concatCursor.moveToFirst()) {
					// ��ѯ������ϵ��
					phoneName = concatCursor.getString(1);
				}
				concatCursor.close();

				if (phoneName != null) {
					nameTv.setText(phoneName + "(" + count + ")" + formatTime);
					Log.i(TAG, "phoneName:" + phoneName);
				} else {
					nameTv.setText(address + "(" + count + ")" + formatTime);
					Log.i(TAG, "address:" + address);
				}

				
				view.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						Intent intent = new Intent(MainActivity.this, ThreadDetailActivity.class);
						intent.putExtra("thread_id",thread_id);
						intent.putExtra("phone", address);
						startActivity(intent);
					}
				});
			}

		}

		@Override
		public View newView(Context arg0, Cursor arg1, ViewGroup arg2) {
			View view = null;
			if (view == null) {
				mHolder = new ViewHolder();
				view = View.inflate(getApplicationContext(), R.layout.item,
						null);
			}

			mHolder.name = (TextView) view.findViewById(R.id.name);
			mHolder.content = (TextView) view.findViewById(R.id.content);

			view.setTag(mHolder);
			return view;

		}

	}

	class ViewHolder {
		TextView name;
		TextView content;
	}
}
