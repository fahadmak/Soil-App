package com.example.bujimuapp.ui.login;


import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.example.bujimuapp.R;
import com.example.bujimuapp.models.User;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;

import static com.example.bujimuapp.Constants.EMAIL;
import static com.example.bujimuapp.Constants.LOGGED_IN;
import static com.example.bujimuapp.Constants.MYPREF;
import static com.example.bujimuapp.Constants.PRIVATE_MODE;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    private LoginViewModel mLoginViewModel;

    private TextInputEditText mEmail;
    private TextInputEditText mPassword;
    private ConstraintLayout mLayout;
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mLayout = view.findViewById(R.id.login_layout);
        mEmail = view.findViewById(R.id.email_text);
        mPassword = view.findViewById(R.id.password_text);

        view.findViewById(R.id.login_progress_bar).setVisibility(View.GONE);

        mLoginViewModel = ViewModelProviders.of(getActivity()).get(LoginViewModel.class);

        view.findViewById(R.id.sign_in_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getActivity()
                        .getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(mLayout.getWindowToken(), 0);
                if (TextUtils.isEmpty(mEmail.getText()) || TextUtils.isEmpty(mPassword.getText())) {
                    return;
                }
                getView().findViewById(R.id.login_panel).setVisibility(View.GONE);
                getView().findViewById(R.id.login_progress_bar).setVisibility(View.VISIBLE);
                mLoginViewModel.isUserValid(mEmail.getText().toString(),
                        mPassword.getText().toString()).observe(getActivity(), new Observer<User>() {
                    @Override
                    public void onChanged(User user) {
                        pref = getActivity().getApplicationContext()
                                .getSharedPreferences(MYPREF,  PRIVATE_MODE);
                        editor = pref.edit();
                        if (user != null) {
                            Toast.makeText(getActivity(), "User are now logged in", Toast.LENGTH_SHORT).show();
                            editor.putLong("userId", user.getId());
                            editor.putBoolean(LOGGED_IN, true);
                            editor.putString(EMAIL, mEmail.getText().toString());
                            editor.commit(); // commit changes
                            Navigation.findNavController(getView())
                                    .navigate(R.id.action_loginFragment_to_splash_screen2);
                        } else {
//                            Toast.makeText(getActivity(), "User does not exist", Toast.LENGTH_SHORT).show();
                            getView().findViewById(R.id.login_progress_bar)
                                    .setVisibility(View.GONE);
                            new MaterialAlertDialogBuilder(getActivity())
                                    .setMessage("Incorrect email or password." + "\n" +
                                            "Please try again")
                                    .setPositiveButton("OK",
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog,
                                                                    int which) {
                                                    getView().findViewById(R.id.login_panel)
                                                            .setVisibility(View.VISIBLE);
                                                }
                                            }).show();
                        }
                    }
                });
            }
        });
    }
}
