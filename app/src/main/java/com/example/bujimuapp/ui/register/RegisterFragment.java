package com.example.bujimuapp.ui.register;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProviders;

import android.app.ActionBar;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.bujimuapp.R;
import com.example.bujimuapp.models.User;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterFragment extends Fragment {

    private RegisterViewModel mViewModel;
    private TextInputEditText mEmail;
    private TextInputEditText mPassword;
    private TextInputEditText mUserName;
    private ConstraintLayout mLayout;

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mLayout = view.findViewById(R.id.login_layout);
        mEmail = view.findViewById(R.id.email_text);
        mPassword = view.findViewById(R.id.password_text);
        mUserName = view.findViewById(R.id.username_text);

        view.findViewById(R.id.sign_in_progress_bar).setVisibility(View.GONE);
        mViewModel = ViewModelProviders.of(this).get(RegisterViewModel.class);
        view.findViewById(R.id.sign_up_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getActivity()
                        .getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(mLayout.getWindowToken(), 0);
                if (TextUtils.isEmpty(mEmail.getText()) || TextUtils.isEmpty(mPassword.getText())) {
                    return;
                }
                getView().findViewById(R.id.sign_in_panel).setVisibility(View.GONE);
                getView().findViewById(R.id.sign_in_progress_bar).setVisibility(View.VISIBLE);
                User user = new User(mEmail.getText().toString(),
                        mPassword.getText().toString(),
                        mUserName.getText().toString());
                mViewModel.insert(user);
                Toast.makeText(getActivity(), "User Created", Toast.LENGTH_SHORT).show();
                Navigation.findNavController(getView())
                        .navigate(R.id.action_registerFragment_to_loginFragment);
            }
        });
    }
}