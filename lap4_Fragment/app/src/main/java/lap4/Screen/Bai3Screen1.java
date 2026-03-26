package lap4.Screen;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fpoly.vinv01.fragmentapplication.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Bai3Screen1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Bai3Screen1 extends Fragment {
    public Bai3Screen1() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Bai3Screen1 newInstance(String param1, String param2) {
        Bai3Screen1 fragment = new Bai3Screen1();
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
        return inflater.inflate(R.layout.fragment_bai3_screen1, container, false);
    }
}