package com.manturf.manturf.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.manturf.manturf.AppController;
import com.manturf.manturf.BitmapLruCache;
import com.manturf.manturf.Item.NomikaiList;
import com.manturf.manturf.R;

import java.util.List;

/**
 * Created by RyoSakaguchi on 15/01/22.
 */
public class TopTimeLineAdapter extends ArrayAdapter<NomikaiList> {
    private ImageLoader mImageLoader;

    public TopTimeLineAdapter(Context context){
        super(context, R.layout.home_time_line);
        mImageLoader = new ImageLoader(AppController.getInstance().getRequestQueue(),new BitmapLruCache());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.home_time_line,parent,false);
        }
        NetworkImageView imageView = (NetworkImageView) convertView.findViewById(R.id.image1);
        TextView id = (TextView) convertView.findViewById(R.id.id);
        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView content = (TextView) convertView.findViewById(R.id.content);
        TextView occupation = (TextView) convertView.findViewById(R.id.occupation);
        TextView place = (TextView) convertView.findViewById(R.id.place);
        TextView date = (TextView) convertView.findViewById(R.id.date);
        TextView time = (TextView) convertView.findViewById(R.id.time);

        NomikaiList nomikaiList = getItem(position);
        imageView.setImageUrl(String.valueOf(nomikaiList.getId()),mImageLoader);
        id.setText(String.valueOf(nomikaiList.getEventId()));
        title.setText(nomikaiList.getTitle());
        content.setText(nomikaiList.getContent());
        occupation.setText(nomikaiList.getOccupation());
        place.setText(nomikaiList.getPlace());
        date.setText(nomikaiList.getDate());
        time.setText(nomikaiList.getTime());
        return convertView;
    }

    public void swapNomikaiList(List<NomikaiList> objects){
        clear();

        for(NomikaiList object : objects){
            add(object);
        }
        notifyDataSetChanged();
    }
}
