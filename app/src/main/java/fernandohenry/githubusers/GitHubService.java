package fernandohenry.githubusers;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by logonrm on 27/10/2017.
 */

public interface GitHubService {
    @GET("/users/{username}")
    Call<Usuario> buscarUsuario(
            @Path(value = "username")String username);
}
