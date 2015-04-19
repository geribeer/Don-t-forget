package de.simon_tenbeitel.dhbw.se.dontforget.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import de.simon_tenbeitel.dhbw.se.dontforget.R;
import de.simon_tenbeitel.dhbw.se.dontforget.ui.activity.MainActivity;

/**
 * Created by Simon on 19.04.2015.
 */
public class SignupFragment extends Fragment {

    @InjectView(R.id.username)
    EditText usernameEditText;
    @InjectView(R.id.password) EditText passwordEditText;
    @InjectView(R.id.passwordConfirm) EditText passwordConfirmEditText;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @OnClick(R.id.registerButton)
    public void register() {
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
        if (!password.equals(passwordConfirmEditText.getText().toString())) {
            Toast.makeText(getActivity(), R.string.passwordNoMatch, Toast.LENGTH_LONG).show();
            return;
        }

        // All checks were successful, so sign up.
        signUp(username, password);
    }

    private void signUp(String username, String password) {
        ParseUser user = new ParseUser();
        user.setUsername(username);
        user.setPassword(password);

        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    // Hooray! Let them use the app now.
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                } else {
                    // Sign up didn't succeed. Look at the ParseException to figure out what went wrong
                    showError(e.getCode());
                }
            }
        });
    }

    private void showError(int errorCode) {
        if (errorCode == ParseException.USERNAME_TAKEN || errorCode == ParseException.EMAIL_TAKEN) {
            Toast.makeText(getActivity(), R.string.usernameTaken, Toast.LENGTH_LONG).show();
        } else
            Toast.makeText(getActivity(), R.string.unknownError, Toast.LENGTH_LONG).show();
    }

}