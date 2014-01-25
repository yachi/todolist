package com.example.todolist;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by yachi on 25/1/14.
 */
public class HomeFragment extends Fragment {
    AQuery aq;
    ArrayList<String> todos;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        aq = new AQuery(getActivity());

        String url = "http://www.mocky.io/v2/52e3803546c16f5d0091f7ed";
        aq.ajax(url, JSONObject.class, new AjaxCallback<JSONObject>(){
            @Override
            public void callback(String url, JSONObject object, AjaxStatus status) {
                JSONArray list = object.optJSONArray("list");
                todos = new ArrayList<String>();
                for(int i = 0; i < list.length(); i++){
                    String string = null;
                    string = (String) list.opt(i);
                    todos.add(string);
                    Log.d("string", string);
                }
            }
        });
    }
}
