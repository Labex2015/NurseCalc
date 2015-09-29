package com.nursecalc;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.nursecalc.Util.view.TourItem;
import com.nursecalc.Util.view.utils.SharedPrefUtils;

public class Principal extends Fragment {


    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }
        view = inflater.inflate(R.layout.principal, container, false);
        SharedPrefUtils prefUtils = new SharedPrefUtils();
        if (!prefUtils.alredyPlayedTour(getActivity())) {
            TourItem.createTour(TourItem.loadItens(), getActivity(), view);
            prefUtils.saveTourExecuted(true, getActivity());
        }
        return view;
    }


}
