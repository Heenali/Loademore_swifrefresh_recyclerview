package com.example.heenali.loademore_recyclerview;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class Order_paging extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    private TextView tvEmptyView;
    private RecyclerView mRecyclerView;
    private DataAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<Order_method> method_List;
    String json_save;
    UserFunctions UF;
    protected Handler handler;
    int pageno=1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UF = new UserFunctions(Order_paging.this);


        tvEmptyView = (TextView) findViewById(R.id.empty_view);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        method_List = new ArrayList<Order_method>();
        handler = new Handler();
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_refresh_layout);


        swipeRefreshLayout.setOnRefreshListener(Order_paging.this);
        swipeRefreshLayout.post(new Runnable()
                                {
                                    @Override
                                    public void run()
                                    {

                                        new GetJson_save().execute();
                                        mRecyclerView.setHasFixedSize(true);
                                        mLayoutManager = new LinearLayoutManager(getApplicationContext());
                                        mRecyclerView.setLayoutManager(mLayoutManager);
                                        mAdapter = new DataAdapter(method_List, mRecyclerView);


                                        mAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
                                            @Override
                                            public void onLoadMore()
                                            {

                                                method_List.add(null);
                                                mAdapter.notifyItemInserted(method_List.size() - 1);

                                                handler.postDelayed(new Runnable()
                                                {
                                                    @Override
                                                    public void run()
                                                    {

                                                        method_List.remove(method_List.size() - 1);
                                                        mAdapter.notifyItemRemoved(method_List.size());

                                                        new  GetJson_save_lodemore().execute();

                                                    }
                                                }, 2000);

                                            }
                                        });


                                    }
                                }
        );

    }



    private void loadData()
    {
        //deftault calling data

        mRecyclerView.setAdapter(mAdapter);

        if (method_List.isEmpty()) {
            mRecyclerView.setVisibility(View.GONE);
            tvEmptyView.setVisibility(View.VISIBLE);

        } else {
            mRecyclerView.setVisibility(View.VISIBLE);
            tvEmptyView.setVisibility(View.GONE);
        }

    }

    @Override
    public void onRefresh()
    {
        pageno=1;
        method_List.clear();
        new GetJson_save().execute();
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new DataAdapter(method_List, mRecyclerView);

        mAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore()
            {

                method_List.add(null);
                mAdapter.notifyItemInserted(method_List.size() - 1);

                handler.postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {

                        method_List.remove(method_List.size() - 1);
                        mAdapter.notifyItemRemoved(method_List.size());

                        new GetJson_save_lodemore().execute();

                    }
                }, 2000);

            }
        });


    }

    private class GetJson_save extends AsyncTask<Void, Void, String>
    {

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            swipeRefreshLayout.setRefreshing(true);

        }

        @Override
        protected String doInBackground(Void... params) {

            JSONObject prms = new JSONObject();
            JSONObject prmsLogin = new JSONObject();
            JSONArray jsonArray = new JSONArray();


            try {

                prmsLogin.put("status","");
                prmsLogin.put("fromdate", "");
                prmsLogin.put("todate","");
                prmsLogin.put("inquiry_source_city","");
                prmsLogin.put("inquiry_destination_city","");
                prmsLogin.put("mover_id","M1819000080");
                prmsLogin.put("username","");
                prmsLogin.put("OrderBy","");
                prmsLogin.put("SortBy","ASC");
                prmsLogin.put("load_inquiry_no", "");
                prmsLogin.put("truckType","");
                prmsLogin.put("RowsPerPage","2");
                prmsLogin.put("PageNo","1");
                jsonArray.put(prmsLogin);

                prms.put("orders", jsonArray);


            } catch (JSONException e)
            {

                e.printStackTrace();
            }

            json_save = UF.RegisterUser("postorder/GetOrdersForVendor", prms);


            return json_save;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            swipeRefreshLayout.setRefreshing(false);
            if (json_save.equals("lost"))
            {
                UF.msg("Connection Problem.");

            }
            else
            {
                if (json_save.equalsIgnoreCase("0"))
                {
                    UF.msg("Invalid");
                }
                else
                {
                    try
                    {

                        JSONObject jobj = new JSONObject(json_save);
                        Log.e("Home Get--", json_save.toString());
                        String status = jobj.getString("status");
                        String message = jobj.getString("message").toString();

                        Log.e("Myorder Home status >",status);
                        Log.e("--------------------", "----------------------------------");
                        if (status.equalsIgnoreCase("1"))
                        {

                            JSONArray array = new JSONArray();
                            array = jobj.getJSONArray("message");

                            for(int i=0;i<array.length();i++)
                            {

                                String s= array.getJSONObject(i).getString("truck_body_desc");
                                method_List.add(new Order_method("IDNo "+s, i+" Recorder No"));

                            }
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run()
                                {

                                    loadData();
                                }
                            }, 1000);

                        }

                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }


                }
            }


        }
    }
    private class GetJson_save_lodemore extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            swipeRefreshLayout.setRefreshing(true);

        }

        @Override
        protected String doInBackground(Void... params) {

            JSONObject prms = new JSONObject();
            JSONObject prmsLogin = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            pageno=pageno+1;

            try {

                prmsLogin.put("status","");
                prmsLogin.put("fromdate", "");
                prmsLogin.put("todate","");
                prmsLogin.put("inquiry_source_city","");
                prmsLogin.put("inquiry_destination_city","");
                prmsLogin.put("mover_id","M1819000080");
                prmsLogin.put("username","");
                prmsLogin.put("OrderBy","");
                prmsLogin.put("SortBy","ASC");
                prmsLogin.put("load_inquiry_no", "");
                prmsLogin.put("truckType","");
                prmsLogin.put("RowsPerPage","2");
                prmsLogin.put("PageNo",pageno);
                jsonArray.put(prmsLogin);

                prms.put("orders", jsonArray);


            } catch (JSONException e)
            {

                e.printStackTrace();
            }

            json_save = UF.RegisterUser("postorder/GetOrdersForVendor", prms);
            Log.e("--------------------", "----------------------------------");
            Log.e("Home Post--", prms.toString());
            Log.e("Home url---", "shipper/GetShipperOrdersDetails");

            return json_save;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            swipeRefreshLayout.setRefreshing(false);

            if (json_save.equals("lost"))
            {
                UF.msg("Connection Problem.");

            }
            else
            {
                if (json_save.equalsIgnoreCase("0"))
                {
                    UF.msg("Invalid");
                } else
                {
                    try
                    {

                        JSONObject jobj = new JSONObject(json_save);
                        Log.e("Home Get--", json_save.toString());
                        String status = jobj.getString("status");
                        String message = jobj.getString("message").toString();

                        Log.e("Myorder Home status >",status);
                        Log.e("--------------------", "-------------------    ---------------");
                        if (status.equalsIgnoreCase("1"))
                        {

                            JSONArray array = new JSONArray();
                            array = jobj.getJSONArray("message");

                            int start=method_List.size();

                            for(int i=0;i<array.length();i++)
                            {

                                String s= array.getJSONObject(i).getString("created_date");
                                method_List.add(new Order_method("IDNo "+s, i+start+" Recorder No"));
                                mAdapter.notifyItemInserted(method_List.size());

                            }
                            mAdapter.setLoaded();

                        }

                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();

                    }


                }
            }


        }
    }

    public static class DataAdapter extends RecyclerView.Adapter {
        private final int VIEW_ITEM = 1;
        private final int VIEW_PROG = 0;

        private List<Order_method> studentList;

        // The minimum amount of items to have below your current scroll position
        // before loading more.
        private int visibleThreshold = 5;
        private int lastVisibleItem, totalItemCount;
        private boolean loading;
        private OnLoadMoreListener onLoadMoreListener;

        public DataAdapter(List<Order_method> students, RecyclerView recyclerView) {
            studentList = students;

            if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {

                final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView
                        .getLayoutManager();


                recyclerView
                        .addOnScrollListener(new RecyclerView.OnScrollListener() {
                            @Override
                            public void onScrolled(RecyclerView recyclerView,
                                                   int dx, int dy) {
                                super.onScrolled(recyclerView, dx, dy);

                                totalItemCount = linearLayoutManager.getItemCount();
                                lastVisibleItem = linearLayoutManager
                                        .findLastVisibleItemPosition();
                                if (!loading
                                        && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                                    // End has been reached
                                    // Do something
                                    if (onLoadMoreListener != null)
                                    {
                                        onLoadMoreListener.onLoadMore();
                                    }
                                    loading = true;
                                }
                            }
                        });
            }
        }

        @Override
        public int getItemViewType(int position) {
            return studentList.get(position) != null ? VIEW_ITEM : VIEW_PROG;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType)
        {
            RecyclerView.ViewHolder vh;
            if (viewType == VIEW_ITEM) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row, parent, false);

                vh = new StudentViewHolder(v);
            } else {
                View v = LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.progress_item, parent, false);

                vh = new ProgressViewHolder(v);
            }
            return vh;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof StudentViewHolder) {

                Order_method singleStudent= (Order_method) studentList.get(position);

                ((StudentViewHolder) holder).tvName.setText(singleStudent.gettxt1());

                ((StudentViewHolder) holder).tvEmailId.setText(singleStudent.gettxt2());

                ((StudentViewHolder) holder).student= singleStudent;

            } else {
                ((ProgressViewHolder) holder).progressBar.setIndeterminate(true);
            }
        }

        public void setLoaded() {
            loading = false;
        }

        @Override
        public int getItemCount() {
            return studentList.size();
        }

        public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
            this.onLoadMoreListener = onLoadMoreListener;
        }


        //
        public static class StudentViewHolder extends RecyclerView.ViewHolder {
            public TextView tvName;

            public TextView tvEmailId;

            public Order_method student;

            public StudentViewHolder(View v) {
                super(v);
                tvName = (TextView) v.findViewById(R.id.tvName);

                tvEmailId = (TextView) v.findViewById(R.id.tvEmailId);

                v.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Toast.makeText(v.getContext(),
                                "OnClick :" + student.gettxt1() + " \n "+student.gettxt2(),
                                Toast.LENGTH_SHORT).show();

                    }
                });
            }
        }

        public static class ProgressViewHolder extends RecyclerView.ViewHolder {
            public ProgressBar progressBar;

            public ProgressViewHolder(View v) {
                super(v);
                progressBar = (ProgressBar) v.findViewById(R.id.progressBar1);
            }
        }
    }

}
