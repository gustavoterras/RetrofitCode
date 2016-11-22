package br.com.infoterras.retrofitcode;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Gustavo on 22/11/2016.
 */

class ConsumerService {

    private OnTaskCompleted listener;

    interface OnTaskCompleted<T> {
        void onSuccess(T response, int requestCode);
        void onFailure(Throwable error);
    }

    void setOnTaskCompleted(OnTaskCompleted onTaskCompleted){
        listener = onTaskCompleted;
    }

    void getContributors(String owner, String repo, final int requestCode){
        GitHubClient client = ServiceGenerator.createService(GitHubClient.class);
        client.contributors(owner, repo).enqueue(new Callback<List<Contributor>>() {
            @Override
            public void onResponse(Call<List<Contributor>> call, Response<List<Contributor>> response) {
                listener.onSuccess(response.body(), requestCode);
            }

            @Override
            public void onFailure(Call<List<Contributor>> call, Throwable throwable) {
                listener.onFailure(throwable);
            }
        });
    }
}
