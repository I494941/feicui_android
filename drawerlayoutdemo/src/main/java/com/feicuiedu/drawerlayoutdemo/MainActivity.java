package com.feicuiedu.drawerlayoutdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ListView list_left_drawer;
    private ListView list_right_drawer;
    private ArrayList<String> list;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        list_left_drawer = (ListView) findViewById(R.id.list_left_drawer);
        list_right_drawer = (ListView) findViewById(R.id.list_right_drawer);

       /* Cursor cursor = getContentResolver()
                .query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);*/

        list = new ArrayList<String>();
        list.add("内容1");
        list.add("内容2");
        list.add("内容3");
        list.add("内容4");

        list_left_drawer.setAdapter(ba);
        list_left_drawer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ContentFragment contentFragment = new ContentFragment();
                Bundle args = new Bundle();
                args.putString("text", list.get(position));
                contentFragment.setArguments(args);

                FragmentManager fm = getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.ly_content,contentFragment).commit();

                //
                drawerLayout.closeDrawer(list_left_drawer);
            }
        });

        list_right_drawer.setAdapter(ba);
        list_right_drawer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ContentFragment contentFragment = new ContentFragment();
                Bundle args = new Bundle();
                args.putString("text", list.get(position));
                contentFragment.setArguments(args);

                FragmentManager fm = getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.ly_content,contentFragment).commit();
                drawerLayout.closeDrawer(list_right_drawer);
            }
        });
    }

    BaseAdapter ba = new BaseAdapter() {
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;

            if(convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
                holder = new ViewHolder();
                holder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
                convertView.setTag(holder);   //将Holder存储到convertView中
            }
            else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.tv_title.setText((String)getItem(position));
            return convertView;
        }
    };

    static class ViewHolder{
        TextView tv_title;
    }


}
