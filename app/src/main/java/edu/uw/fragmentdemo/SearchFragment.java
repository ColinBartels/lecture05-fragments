package edu.uw.fragmentdemo;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import static android.content.ContentValues.TAG;
import static edu.uw.fragmentdemo.R.id.listView;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    private OnSearchSubmittedListener callback; //context that we use for event callbacks

    public interface OnSearchSubmittedListener {
        public void onSearchSubmitted(String searchTerm);
    }

    public SearchFragment() {
        // Required empty public constructor
    }

    public static SearchFragment newInstance() {
        
        Bundle args = new Bundle();
        
        SearchFragment fragment = new SearchFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            callback = (OnSearchSubmittedListener)context;
        }catch(ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnMovieSelectedListend");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);

        Button button = (Button) rootView.findViewById(R.id.btnSearch);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchTerm = ((EditText) getView().findViewById(R.id.txtSearch)).getText().toString();
                callback.onSearchSubmitted(searchTerm);
            }
        });
        return rootView;
    }

}
