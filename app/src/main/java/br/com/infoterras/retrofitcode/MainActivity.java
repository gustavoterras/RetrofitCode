package br.com.infoterras.retrofitcode;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements ConsumerService.OnTaskCompleted<List<Contributor>>{

    private String TAG = MainActivity.class.getSimpleName();
    private ConsumerService consumerService;

    @BindView(R.id.content_layout)
    TextView contentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        consumerService = new ConsumerService();
        consumerService.setOnTaskCompleted(this);
    }

    public void doGet(View view){
        consumerService.getContributors("gustavoterras", "materialdesign", 0);
    }

    @Override
    public void onSuccess(List<Contributor> response, int requestCode) {
        String content = "";
        for (Contributor contributor : response) {
            content = content.concat(
                    contributor.getLogin() + " (" + contributor.getContributions() + ")");
        }

        contentView.setText(content);
    }

    @Override
    public void onFailure(Throwable error) {
        Log.e(TAG, error.getMessage());
    }
}
