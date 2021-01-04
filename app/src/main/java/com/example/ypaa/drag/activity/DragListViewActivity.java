package com.example.ypaa.drag.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.ypaa.R;
import com.example.ypaa.activity.C;
import com.example.ypaa.drag.adapter.DragAdapter;
import com.example.ypaa.drag.view.DragListView;
import com.example.ypaa.drag.view.TextSView;

import java.util.ArrayList;
import java.util.List;

public class DragListViewActivity extends Activity {
    private DragListView dragListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_list_view);

        dragListView = (DragListView) findViewById(R.id.dragListView);
        dragListView.setDragViewId(R.id.tvDrag);
        List<String> data = initData();
        MyAdapter dragAdapter = new MyAdapter(data, this, handler);
        dragListView.setAdapter(dragAdapter);

        dragListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("abcd", "onItemClick: " + position);
            }
        });
    }


    private List<String> initData() {
        List<String> data = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            data.add("标题xxx" + i);
        }
        return data;
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                List<String> size = (List<String>) msg.obj;
            }
            if (msg.what == 1) {
                startActivity(new Intent(DragListViewActivity.this, C.class));
            }
        }
    };

    private class MyAdapter extends DragAdapter {

        private List<String> data;
        private Context context;
        private Handler mHandler;

        public MyAdapter(List<String> data, Context context, Handler handler) {
            super(data);
            this.data = data;
            this.context = context;
            mHandler = handler;
        }

        public List<String> getData() {
            return data;
        }

        @Override
        public void change(int start, int end) {
            super.change(start, end);
            Message message = Message.obtain();
            message.obj = getData();
            mHandler.sendMessage(message);
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }


        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            Holder holder = null;
            if (convertView == null) {
                holder = new Holder();
                convertView = View.inflate(context, R.layout.list_view_item, null);
                holder.tvDrag = (TextView) convertView.findViewById(R.id.tvDrag);
                holder.tvName = (TextView) convertView.findViewById(R.id.tvName);
                holder.textSView = (TextSView) convertView.findViewById(R.id.textSView);
                convertView.setTag(holder);
            } else {
                holder = (Holder) convertView.getTag();
            }
            holder.tvName.setText(data.get(position));
            holder.textSView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Message message = Message.obtain();
                    message.what = 1;
                    message.arg1 = position;
                    handler.sendMessage(message);
                }
            });
            return convertView;
        }

        private class Holder {
            TextView tvName;
            TextView tvDrag;
            TextSView textSView;
        }
    }

}