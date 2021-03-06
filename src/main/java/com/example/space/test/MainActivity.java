package com.example.space.test;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.space.test.module.viewDragHelper.ViewDragHelperActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


public class MainActivity extends Activity implements DialogInterface.OnClickListener{

    @Bind(R.id.button)
    Button button;

    @Bind(R.id.textView)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setTitle("xxx");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, ViewDragHelperActivity.class);
//                startActivity(intent);

                tvTimer(textView);
            }
        });

    }

    public void tvTimer(final View view){
        ValueAnimator valueAnimator=ValueAnimator.ofInt(0,100,900);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                ((TextView)view).setText((Integer) animation.getAnimatedValue()+"");
            }
        });

        valueAnimator.setDuration(1000);
        valueAnimator.start();

    }













    public void showDialog1(){
        AlertDialog builder=new AlertDialog.Builder(this)
                .setPositiveButton("positive",MainActivity.this)
                .create();
    }

    public void test2() {
        final Observable observable = Observable.create(new Observable.OnSubscribe<Subscriber>() {
            @Override
            public void call(Subscriber subscriber) {
                subscriber.onNext(longRunOperation());
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                v.setClickable(false);
                observable.subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        v.setClickable(true);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String o) {
                        Log.i("onNext", o);
                    }
                });
            }
        });
    }

    public String longRunOperation() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "Complete!!!!";
    }


    public void test1() {
        Observable.just(1, 2, 3, 4, 5, 6).filter(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return integer % 2 == 1;
            }
        }).map(new Func1<Integer, Double>() {
            @Override
            public Double call(Integer integer) {
                return Math.sqrt(integer);
            }
        }).subscribe(new Subscriber<Double>() {
            @Override
            public void onCompleted() {
                Log.i("onCompleted", "-----");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Double integer) {
                Log.i("onNext", integer + "");
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * This method will be invoked when a button in the dialog is clicked.
     *
     * @param dialog The dialog that received the click.
     * @param which  The button that was clicked (e.g.
     *               {@link DialogInterface#BUTTON1}) or the position
     */
    @Override
    public void onClick(DialogInterface dialog, int which) {

    }
}
