package cn.stu.cusview.ruiz.customeviewdemo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.stu.cusview.ruiz.customeviewdemo.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LifeCycleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LifeCycleFragment extends Fragment {

    private static final String TAG = "LifeCycleFragment";

    public LifeCycleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment LifeCycleFragment.
     */
    public static LifeCycleFragment newInstance() {
        LifeCycleFragment fragment = new LifeCycleFragment();
        return fragment;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e(TAG,"onAttach()");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG,"onCreate()");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e(TAG,"onCreateView()");
        return inflater.inflate(R.layout.fragment_life_cycle, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e(TAG,"onActivityCreated()");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e(TAG,"onStart()");
    }


    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG,"onResume()");
    }


    @Override
    public void onPause() {
        super.onPause();
        Log.e(TAG,"onPause()");
    }


    @Override
    public void onStop() {
        super.onStop();
        Log.e(TAG,"onStop()");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e(TAG,"onDestroyView()");
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"onDestroy()");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e(TAG,"onDetach()");
    }

}
