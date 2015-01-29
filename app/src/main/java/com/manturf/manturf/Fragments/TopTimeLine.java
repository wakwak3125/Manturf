package com.manturf.manturf.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.manturf.manturf.AppController;
import com.manturf.manturf.Item.NomikaiList;
import com.manturf.manturf.NomikaiDetail;
import com.manturf.manturf.R;
import com.manturf.manturf.adapter.TopTimeLineAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class TopTimeLine extends Fragment {

    private TopTimeLineAdapter mAdapter;
    public static final String TAG = TopTimeLine.class.getSimpleName();

    /*
    public static TopTimeLine newInstance() {
        TopTimeLine fragment = new TopTimeLine();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    */

    public TopTimeLine() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Delete().from(NomikaiList.class).execute();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_top_time_line, container, false);

        return view;
    }
    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mAdapter = new TopTimeLineAdapter(getActivity());
        ListView listView = (ListView)getView().findViewById(R.id.list1);
        listView.setAdapter(mAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListView listView = (ListView) parent;
                NomikaiList nomikaiList = (NomikaiList) listView.getItemAtPosition(position);

                Intent detailActivity = new Intent(getActivity(), NomikaiDetail.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id", nomikaiList.getEventId());
                bundle.putString("飲み会のタイトル", nomikaiList.getTitle());
                bundle.putString("飲み会の説明文", nomikaiList.getContent());
                bundle.putString("業界", nomikaiList.getOccupation());
                bundle.putString("場所", nomikaiList.getPlace());
                bundle.putString("日時", nomikaiList.getDate());
                bundle.putString("時間", nomikaiList.getTime());
                detailActivity.putExtras(bundle);
                startActivity(detailActivity);

                Log.i(TAG, "onItemClick:");
                Log.i(TAG, "position = " + position);
                Log.i(TAG, "id = " + id);
                Log.i(TAG, "Api側のid = " + nomikaiList.getEventId());
                Log.i(TAG, "飲み会タイトル = " + nomikaiList.getTitle());
                Log.i(TAG, "飲み会の説明文 = " + nomikaiList.getContent());
                Log.i(TAG, "作成日 = " + nomikaiList.getCreated_at());
                Log.i(TAG, "更新日 = " + nomikaiList.getUpdate_at());
                Log.i(TAG, "日時 = " + nomikaiList.getDate());
                Log.i(TAG, "場所 = " + nomikaiList.getPlace());
                Log.i(TAG, "時間 = " + nomikaiList.getTime());
                Log.i(TAG, "業界 = " + nomikaiList.getOccupation());

                List<NomikaiList> list = new Select().from(NomikaiList.class).where("place = ?","渋谷").execute();
                for (NomikaiList ii : list ) {
                    Log.i(TAG, "場所が渋谷のEVENT_ID" + ii.getEventId());
                    Log.i(TAG, "場所が渋谷のTITLE" + ii.getTitle());
                    Log.i(TAG, "場所が渋谷の業界" + ii.getOccupation());
                }



            }
        });
        fetch();
    }

    private void fetch(){
        JsonObjectRequest request = new JsonObjectRequest(
                "http://manturf2.herokuapp.com/api/v1/events",
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        // TODO:Jsonパースやで！
                        try {
                            List<NomikaiList> nomikaiLists = parse(jsonObject);
                            mAdapter.swapNomikaiList(nomikaiLists);
                        }

                        catch (JSONException e){
                            Toast.makeText(getActivity(),"フェッチできないね。"+e.getMessage(),
                                    Toast.LENGTH_SHORT).show();
                            Log.d("tag","フェッチできないね。"+ e.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "パースできませんでした" + error.getMessage(),
                                Toast.LENGTH_SHORT).show();
                        //Log.d("tag","パースできませんでした" + error.getMessage());
                    }
                }
        );
        AppController.getInstance().getRequestQueue().add(request);
    }

    private List<NomikaiList> parse(JSONObject json)throws JSONException{
        ArrayList<NomikaiList> records = new ArrayList<>();
        JSONArray jsonimages = json.getJSONArray("all_events");
        for (int i = 0; i < jsonimages.length(); i++){
            JSONObject jsonimage = jsonimages.getJSONObject(i);
            int id = jsonimage.getInt("id");
            String title = jsonimage.getString("title");
            String content = jsonimage.getString("content");
            String created_at = jsonimage.getString("created_at");
            String updated_at = jsonimage.getString("updated_at");
            String date = jsonimage.getString("date");
            String place = jsonimage.getString("place");
            String time = jsonimage.getString("time");
            String occupation = jsonimage.getString("occupation");
            NomikaiList record = new NomikaiList(id,title,content,created_at,updated_at,date,place,time,occupation);
            records.add(record);

            List<NomikaiList> list = new Select().from(NomikaiList.class).where("event_id = ?",5).execute();
            for (NomikaiList ii : list ){
                Log.i(TAG,"DB内のタイトルを取得" + ii.getOccupation());
            }
            record.save();


        }
        for (int i = 0; i < records.size(); i++)
        Log.i(TAG,"タイトルをforぶんまわし。" + records.get(i).getEventId());
        return records;
    }






    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
       // public void onFragmentInteraction(Uri uri);

    }


}
