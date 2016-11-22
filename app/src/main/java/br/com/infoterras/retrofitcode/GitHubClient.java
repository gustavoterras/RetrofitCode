package br.com.infoterras.retrofitcode;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Gustavo on 22/11/2016.
 */

public interface GitHubClient {

    @GET("/repos/{owner}/{repo}/contributors")
    Call<List<Contributor>> contributors(
            @Path("owner") String owner,
            @Path("repo") String repo
    );
}
