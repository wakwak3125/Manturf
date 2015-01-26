package com.manturf.manturf;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;


public class NomikaiDetail extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nomikai_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitle(getIntent().getExtras().getString("飲み会のタイトル"));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //TopTimeLineから受け取った値
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int id = bundle.getInt("id");
        String title = bundle.getString("飲み会のタイトル");
        String content = bundle.getString("飲み会の説明文");
        String occupation = bundle.getString("業界");
        String place = bundle.getString("場所");
        String date = bundle.getString("日時");
        String time = bundle.getString("時間");
        //のログをここで出力
        Log.d("id", "id=" + id);
        Log.d("飲み会のタイトル", "結果=" + title);
        Log.d("飲み会の説明文", "結果=" + content);
        Log.d("業界", "結果=" + occupation);
        Log.d("場所", "結果=" + place);
        Log.d("日時", "結果=" + date);
        Log.d("時間", "結果=" + time);

        TextView ntitle = (TextView)findViewById(R.id.title);
        TextView ncontent = (TextView)findViewById(R.id.content);
        TextView noccupation = (TextView)findViewById(R.id.occupation);
        TextView nplace = (TextView)findViewById(R.id.place);
        TextView ndate = (TextView)findViewById(R.id.date);
        TextView ntime = (TextView)findViewById(R.id.time);

        ntitle.setText(getIntent().getExtras().getString("飲み会のタイトル"));
        ncontent.setText(getIntent().getExtras().getString("飲み会の説明文"));
        noccupation.setText(getIntent().getExtras().getString("業界"));
        nplace.setText(getIntent().getExtras().getString("場所"));
        ndate.setText(getIntent().getExtras().getString("日時"));
        ntime.setText(getIntent().getExtras().getString("時間"));

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
