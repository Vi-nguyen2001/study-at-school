package lap4.Screen;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fpoly.vinv01.fragmentapplication.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ScreenLap4_2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScreenLap4_2 extends Fragment {


    public ScreenLap4_2() {
        // Required empty public constructor
    }


    public static ScreenLap4_2 newInstance(String param1, String param2) {
        ScreenLap4_2 fragment = new ScreenLap4_2();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_screen_lap4_2, container, false);
    }
}