package iberotec.edu.pe.mylooks.fragment;


import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import iberotec.edu.pe.mylooks.R;

/**
 * Created by Margot on 26/12/2017.
 */

public class VestidoFragment extends Fragment {

    public VestidoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_vestido, container, false);


    }
}
