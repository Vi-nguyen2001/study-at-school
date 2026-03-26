package lap4.Screen;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fpoly.vinv01.fragmentapplication.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Screenlap4_1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Screenlap4_1 extends Fragment {


    public Screenlap4_1() {
        // Required empty public constructor
    }

    public static Screenlap4_1 newInstance() {
        Screenlap4_1 fragment = new Screenlap4_1();
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
        return inflater.inflate(R.layout.fragment_screenlap4_1, container, false);
    }
}