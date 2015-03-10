package org.guides.fragments.app;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SwitcherFragmentActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_fragment);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new OneFragment())
                    .commit();
        }
        findViewById(R.id.fragment_one).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOne();
            }
        });
        findViewById(R.id.fragment_two).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTwo();
            }
        });
    }

    private void showOne() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new OneFragment())
                .commit();
    }

    private void showTwo() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new TwoFragment())
                .commit();
    }

    public static class OneFragment extends Fragment {
        private static final String TAG = OneFragment.class.getSimpleName();

        public OneFragment() {
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            Log.d(TAG, "onAttach()");
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Log.d(TAG, "onCreate()");
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            Log.d(TAG, "onCreateView()");
            View rootView = inflater.inflate(R.layout.fragment_simple, container, false);
            rootView.setBackgroundColor(Color.GREEN);
            TextView textView = (TextView) rootView.findViewById(R.id.hello_text);
            textView.append(" - ");
            textView.append(TAG);
            return rootView;
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            Log.d(TAG, "onActivityCreated()");
        }

        @Override
        public void onStart() {
            super.onStart();
            Log.d(TAG, "onStart()");
        }

        @Override
        public void onResume() {
            super.onResume();
            Log.d(TAG, "onResume()");
        }

        @Override
        public void onPause() {
            super.onPause();
            Log.d(TAG, "onPause()");
        }

        @Override
        public void onStop() {
            super.onStop();
            Log.d(TAG, "onStop()");
        }

        @Override
        public void onDestroyView() {
            super.onDestroyView();
            Log.d(TAG, "onDestroyView()");
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            Log.d(TAG, "onDestroy()");
        }

        @Override
        public void onDetach() {
            super.onDetach();
            Log.d(TAG, "onDetach()");
        }
    }

    public static class TwoFragment extends Fragment {
        private static final String TAG = TwoFragment.class.getSimpleName();

        public TwoFragment() {
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            Log.d(TAG, "onAttach()");
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Log.d(TAG, "onCreate()");
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            Log.d(TAG, "onCreateView()");
            View rootView = inflater.inflate(R.layout.fragment_simple, container, false);
            rootView.setBackgroundColor(Color.BLUE);
            TextView textView = (TextView) rootView.findViewById(R.id.hello_text);
            textView.append(" - ");
            textView.append(TAG);
            return rootView;
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            Log.d(TAG, "onActivityCreated()");
        }

        @Override
        public void onStart() {
            super.onStart();
            Log.d(TAG, "onStart()");
        }

        @Override
        public void onResume() {
            super.onResume();
            Log.d(TAG, "onResume()");
        }

        @Override
        public void onPause() {
            super.onPause();
            Log.d(TAG, "onPause()");
        }

        @Override
        public void onStop() {
            super.onStop();
            Log.d(TAG, "onStop()");
        }

        @Override
        public void onDestroyView() {
            super.onDestroyView();
            Log.d(TAG, "onDestroyView()");
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            Log.d(TAG, "onDestroy()");
        }

        @Override
        public void onDetach() {
            super.onDetach();
            Log.d(TAG, "onDetach()");
        }
    }
}
