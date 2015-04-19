package de.simon_tenbeitel.dhbw.se.dontforget.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import de.simon_tenbeitel.dhbw.se.dontforget.DontforgetApplication;
import de.simon_tenbeitel.dhbw.se.dontforget.R;
import de.simon_tenbeitel.dhbw.se.dontforget.ui.fragment.LoginFragment;
import de.simon_tenbeitel.dhbw.se.dontforget.ui.fragment.SignupFragment;

/**
 * Created by Simon on 19.04.2015.
 */
public class LoginFragmentPagerAdapter extends FragmentPagerAdapter {

    public LoginFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new LoginFragment();
            case 1:
                return new SignupFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return DontforgetApplication.getContext().getString(R.string.loginPageTitle);
            case 1:
                return DontforgetApplication.getContext().getString(R.string.signupPageTitle);
        }
        return null;
    }

}