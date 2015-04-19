package de.simon_tenbeitel.dhbw.se.dontforget.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import de.simon_tenbeitel.dhbw.se.dontforget.R;
import de.simon_tenbeitel.dhbw.se.dontforget.ui.activity.MainActivity;

/**
 * Created by Simon on 19.04.2015.
 */
public class LoginFragment extends Fragment {

    @InjectView(R.id.username) EditText usernameEditText;
    @InjectView(R.id.password) EditText passwordEditText;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @OnClick(R.id.loginButton)
    public void login() {
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        if (username.equals("")) {
            Toast.makeText(getActivity(), R.string.usernameMissing, Toast.LENGTH_LONG).show();
            return;
        }
        if (password.equals("")) {
            Toast.makeText(getActivity(), R.string.passwordMissing, Toast.LENGTH_LONG).show();
            return;
        }

        ParseUser.logInInBackground(username, password, new LogInCallback() {
            public void done(ParseUser user, ParseException e) {
                if (user != null) {
                    // Hooray! The user is logged in.
                    goToMainActivity();
                } else {
                    // Log In failed. Look at the ParseException to see what happened.
                    showLoginError(e);
                }
            }
        });
    }

    private void goToMainActivity() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    private void showLoginError(ParseException exception) {
        if (exception == null) {
            Toast.makeText(getActivity(), R.string.unknownError, Toast.LENGTH_LONG).show();
        }
        int errorCode = exception.getCode();
        Log.d("Log In", "Log in failed with error code: " + errorCode);
        if (errorCode == ParseException.CONNECTION_FAILED
                || errorCode == ParseException.TIMEOUT) {
            Toast.makeText(getActivity(), R.string.networkError, Toast.LENGTH_LONG).show();
        }
    }

}