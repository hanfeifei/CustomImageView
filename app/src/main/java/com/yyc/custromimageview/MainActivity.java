package com.yyc.custromimageview;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by HFF on 16/7/11.
 */
public class MainActivity extends Activity {
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        lv = (ListView) findViewById(R.id.lv);
        lv.setAdapter(new MyAdapter(this));
    }

    class MyAdapter extends BaseAdapter {
        Context context;

        public MyAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return 40;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = View.inflate(context, R.layout.main, null);
                holder.iv = (CustomImageView) convertView.findViewById(R.id.iv);
                holder.tv = (TextView) convertView.findViewById(R.id.tv);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            Log.e("lzw","holder.iv " + (holder.iv==null));
            Log.e("lzw","drawable " + (context.getResources().getDrawable(R.drawable.v_small)==null));

            if (position % 5 == 0) {
                holder.iv.setImage(context.getResources().getDrawable(R.drawable.v_small));
            } else {
                holder.iv.setImage(null);
            }
            return convertView;
        }

        class ViewHolder {
            CustomImageView iv;
            TextView tv;
        }
    }
}
