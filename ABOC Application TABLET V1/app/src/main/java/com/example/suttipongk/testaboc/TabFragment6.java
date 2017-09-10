package com.example.suttipongk.testaboc;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Created by TOPPEE on 7/23/2017.
 */
public class TabFragment6 extends Fragment implements View.OnClickListener {
    private IFragmentToActivity mCallback;
    private ImageButton btnFtoA;
    private ImageButton btnFtoB;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_fragment_6, container, false);

        //Add >>> Data
        btnFtoA = (ImageButton) view.findViewById(R.id.elderlybutton);
        btnFtoA.setOnClickListener(this);

        //Select / Update / Delete >>> Data
        btnFtoB = (ImageButton) view.findViewById(R.id.elderlybutton2);
        btnFtoB.setOnClickListener(this);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (IFragmentToActivity) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement IFragmentToActivity");
        }
    }

    @Override
    public void onDetach() {
        mCallback = null;
        super.onDetach();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.elderlybutton:
                mCallback.showToast("Hello from Fragment 6.1");
                Intent mainSaveAndReadDataFirebase = new Intent(getContext(),MainFirebaseReadDataActivity.class);
                startActivity(mainSaveAndReadDataFirebase);
                break;
            case R.id.elderlybutton2:
                mCallback.showToast("Hello from Fragment 6.2");
                Intent mainActivityUpdateAndDeleteFirebaseData = new Intent(getContext(),MainActivityUpdateAndDeleteFirebaseData.class);
                startActivity(mainActivityUpdateAndDeleteFirebaseData);
                break;
        }
    }
}
