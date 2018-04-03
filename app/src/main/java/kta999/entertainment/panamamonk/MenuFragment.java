package kta999.entertainment.panamamonk;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.GraphRequest;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.widget.ProfilePictureView;

import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class MenuFragment extends Fragment {

    @BindView(R.id.logout)
    TextView tvLogout;
    private Unbinder unbinder;

    /*@BindView(R.id.profilePic)
    ImageView profilePic;*/
    Bitmap bitmap;
    URL img_url;

    @BindView(R.id.username)
    TextView username;

    String userid;
    @BindView(R.id.profilePic)
    ProfilePictureView profilePictureView;

    GraphRequest graphRequest;



    public MenuFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_menu, container, false);
        unbinder = ButterKnife.bind(this, v);

        username.setText( Profile.getCurrentProfile().getName());

        profilePictureView.setProfileId(Profile.getCurrentProfile().getId());

        return v;
    }

    @OnClick(R.id.logout)
    public void onClick(TextView tvLogout) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Log out")
                .setMessage("Are you sure to log out?")
                .setPositiveButton("Logout", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        LoginManager.getInstance().logOut();
                        startActivity(new Intent(getActivity(), LoginActivity.class));
                        getActivity().finish();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
