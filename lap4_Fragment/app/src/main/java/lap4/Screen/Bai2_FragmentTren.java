package lap4.Screen;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import fpoly.vinv01.fragmentapplication.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Bai2_FragmentTren#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Bai2_FragmentTren extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public TextView txtKetQua;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Bai2_FragmentTren() {
        // Required empty public constructor
    }

    /**
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Bai2_FragmentTren.
     */
    // TODO: Rename and change types and number of parameters
    public static Bai2_FragmentTren newInstance(String param1, String param2) {
        Bai2_FragmentTren fragment = new Bai2_FragmentTren();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bai2__tren, container, false);
        txtKetQua = view.findViewById(R.id.txtKetQua);
        return view;

    }
}