package layout;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.jepaynedev.coupsandbox.CoupActivity;
import com.jepaynedev.coupsandbox.GameFragment;
import com.jepaynedev.coupsandbox.R;

import com.jepaynedev.coupsandbox.databinding.FragmentLauncherBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class LauncherFragment extends Fragment implements View.OnClickListener, TextWatcher {

    private FragmentLauncherBinding binding;
    private View fragmentView;
    private EditText editScreenName;
    private Button buttonNewGame;
    private Button buttonJoinGame;

    public LauncherFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        CoupActivity activity = (CoupActivity)getActivity();

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_launcher, container, false);
        fragmentView = binding.getRoot();

        // Initialize variables
        editScreenName = (EditText)(fragmentView.findViewById(R.id.editScreenName));
        buttonNewGame = (Button)(fragmentView.findViewById(R.id.buttonNewGame));
        buttonJoinGame = (Button)(fragmentView.findViewById(R.id.buttonJoinGame));

        // Add listeners
        editScreenName.addTextChangedListener(this);
        buttonNewGame.setOnClickListener(this);
        buttonJoinGame.setOnClickListener(this);

        // By default, both buttons are disabled until conditions are met (input ScreenName)
        buttonNewGame.setEnabled(false);
        buttonJoinGame.setEnabled(false);

        return binding.getRoot();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {}

    @Override
    public void afterTextChanged(Editable s) {
        enableButtonsIfHasScreenName();
    }

    private void enableButtonsIfHasScreenName() {
        buttonNewGame.setEnabled(editScreenName.getText().toString().length() > 0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonJoinGame:
                // TODO: add join functionality when wifi is working
            case R.id.buttonNewGame:
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                GameFragment gameFragment = new GameFragment();
                Bundle bundle = new Bundle();
                bundle.putString(getString(R.string.keyScreenName), editScreenName.getText().toString());
                gameFragment.setArguments(bundle);
                ft.replace(R.id.fragment_container, gameFragment).commit();
        }
    }
}
