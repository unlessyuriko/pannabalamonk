package kta999.entertainment.panamamonk;

import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.postText)
    EditText postText;

    JSONObject jsonObject;

    String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Post");
        LoginManager.getInstance().logInWithPublishPermissions(
                this,
                Arrays.asList("publish_pages"));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                finish();
                break;
            case R.id.action_post: {

                if (TextUtils.isEmpty(postText.getText().toString())) {


                }
                {
                    message = postText.getText().toString();

                   /* try {
                        jsonObject = new JSONObject("{\"message\":\"" + message + "\"}");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    GraphRequest request = GraphRequest.newPostRequest(AccessToken.getCurrentAccessToken(),
                            "/353120145199954/feed",
                            jsonObject,
                            new GraphRequest.Callback() {

                                @Override
                                public void onCompleted(GraphResponse response) {
                                    Toast.makeText(PostActivity.this, "Posted!", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            });

                    request.executeAsync();*/

                    Bundle params = new Bundle();
                    params.putString("message", message);
                    /* make the API call */
                    new GraphRequest(
                            AccessToken.getCurrentAccessToken(),
                            "/353120145199954/feed",
                            params,
                            HttpMethod.POST,
                            new GraphRequest.Callback() {
                                public void onCompleted(GraphResponse response) {
                                    Toast.makeText(PostActivity.this, "Posted!", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            }
                    ).executeAsync();

                }
            }

        }
        return super.onOptionsItemSelected(item);
    }
}
