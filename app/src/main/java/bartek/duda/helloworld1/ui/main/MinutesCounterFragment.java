package bartek.duda.helloworld1.ui.main;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import bartek.duda.helloworld1.databinding.FragmentMainBinding;


public class MinutesCounterFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private int num_minutes = 0;

    private PageViewModel pageViewModel;
    private FragmentMainBinding fragment_binding;

    public static MinutesCounterFragment newInstance(int index) {
        MinutesCounterFragment fragment = new MinutesCounterFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = new ViewModelProvider(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        fragment_binding = FragmentMainBinding.inflate(inflater, container, false);
        View root = fragment_binding.getRoot();

        final TextView textView = fragment_binding.sectionLabel;
        pageViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        EditText calc_field = fragment_binding.CalcField;
        CheckedTextView result_label = fragment_binding.CalcResultLabel;
        Button clear_res_button = fragment_binding.clearResButton;

        clear_res_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {

                num_minutes = 0;
                String res_msg = "Current value:\n" +
                        "Hours: " + "0" +
                        " Minutes: " + "0" +
                        "";
                result_label.setText(res_msg);

            }
        });


        calc_field.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int keyCode, KeyEvent event) {

                //if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER))
                if (keyCode == KeyEvent.KEYCODE_ENDCALL)
                {
                    int new_minutes_value;

                    try {
                        new_minutes_value = Integer.parseInt(calc_field.getText().toString());
                    }
                    catch (Exception e)
                    {
                        calc_field.setText("");
                        show_popup_message("Błąd", "Można wpisywać tylko pełne minuty. :) ");
                        return false;
                    }

                    if (num_minutes + new_minutes_value >= 0)
                    {
                        num_minutes += new_minutes_value;
                    }
                    else
                    {
                        calc_field.setText("");
                        show_popup_message("Błąd", "Wynik na minusie jest niedozwolony. :) ");
                        return false;
                    }

                    calc_field.setText("");


                    int res_mod = num_minutes % 60;
                    int res_div = (int) Math.floor(num_minutes / 60);


                    String res_msg = "Current value:\n" +
                            "Hours: " + res_div +
                            " Minutes: " + res_mod;
                    result_label.setText(res_msg);


                    return true;
                }
                return false;
            }
        });



        return root;
    }

    public void show_popup_message(String title, String message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
        builder.setTitle(title);
        builder.setMessage(message);

        builder.setPositiveButton("OK", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragment_binding = null;
    }
}