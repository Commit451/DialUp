package com.commit451.dialup.sample;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.TextView;

import com.commit451.dialup.DialUp;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ViewGroup root = (ViewGroup) findViewById(R.id.root);

        final TextView textView = (TextView) findViewById(R.id.status);
        DialUp.listen(this)
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        Snackbar.make(root, "Status changed to " + aBoolean, Snackbar.LENGTH_LONG)
                                .show();
                        textView.setText("Connected: " + aBoolean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAG", "fail", e);
                    }

                    @Override
                    public void onComplete() {
                    }
                });

    }
}
