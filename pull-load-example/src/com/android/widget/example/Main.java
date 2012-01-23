package com.android.widget.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Main extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);

		Button b1 = (Button) findViewById(R.id.button1);
		b1.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent i = new Intent(Main.this, LoadMoreExampleActivity.class);
				startActivity(i);
			}
		});

		Button b2 = (Button) findViewById(R.id.button2);
		b2.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent i = new Intent(Main.this,
						PullAndLoadExampleActivity.class);
				startActivity(i);
			}
		});
	}
}
