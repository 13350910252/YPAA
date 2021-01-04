package com.example.ypaa.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ypaa.R;
import com.lunger.draglistview.DragListAdapter;
import com.lunger.draglistview.DragListView;

import java.util.ArrayList;
import java.util.List;

public class TestDragListActivity extends AppCompatActivity {
    DragListView mDragListView;
    private ArrayList<String> mDataList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_drag_listview);

        mDragListView = findViewById(R.id.dlv_test);
        initData();
        initDragListView();
    }

    private void initData() {
        mDataList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            mDataList.add("Category" + i);
        }
    }

    private void initDragListView() {
        mDragListView.setDragListAdapter(new MyAdapter(this, mDataList));
        //设置点击item哪个部位可触发拖拽（可不设置，默认是item任意位置长按可拖拽）
        mDragListView.setDragger(R.id.tv_move);
        //设置item悬浮背景色
//        mDragListView.setItemFloatColor(getString(R.string.float_color));
        //设置item悬浮透明度
        mDragListView.setItemFloatAlpha(0.65f);
        //设置拖拽响应回调
        mDragListView.setMyDragListener(new DragListView.MyDragListener() {
            @Override
            public void onDragFinish(int srcPositon, int finalPosition) {
                Toast.makeText(TestDragListActivity.this,
                        "beginPosition : " + srcPositon + "...endPosition : " + finalPosition,
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    public class MyAdapter extends DragListAdapter {
        private List<String> title;
        public MyAdapter(Context context, ArrayList<String> arrayTitles) {
            super(context, arrayTitles);
            title = arrayTitles;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            /***
             * 在这里尽可能每次都进行实例化新的，这样在拖拽ListView的时候不会出现错乱.
             * 具体原因不明，不过这样经过测试，目前没有发现错乱。虽说效率不高，但是做拖拽LisView足够了。
             */
            Holder holder = null;
            if (convertView == null) {
                holder = new MyAdapter.Holder();
                convertView = View.inflate(TestDragListActivity.this, R.layout.list_view_item, null);
                holder.tv_move = (TextView) convertView.findViewById(R.id.tv_move);
                holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
                convertView.setTag(holder);
            } else {
                holder = (Holder) convertView.getTag();
            }
//            holder.tv_name.setText(title.get(position));
            return convertView;
        }

        @Override
        public void update(int start, int end) {
            super.update(start, end);

        }

        @Override
        public int getCount() {
            return title.size();
        }

        @Override
        public Object getItem(int position) {
            return title.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        private class Holder {
            TextView tv_name;
            TextView tv_move;
        }
    }
}
