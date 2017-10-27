package fernandohenry.githubusers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private EditText etUsername;
    private ImageView ivFoto;
    private TextView tvNome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etUsername = (EditText)findViewById(R.id.etUsername);
        ivFoto = (ImageView)findViewById(R.id.ivFoto);
        tvNome = (TextView)findViewById(R.id.tvNome);
    }

    public void pesquisar(View view) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GitHubService service = retrofit.create(GitHubService.class);

        service.buscarUsuario(etUsername.getText().toString())
                .enqueue(new Callback<Usuario>() {
                    @Override
                    public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                        Picasso.with(MainActivity.this)
                                .load(response.body().getFoto()).into(ivFoto);
                        tvNome.setText(response.body().getNome());

                    }

                    @Override
                    public void onFailure(Call<Usuario> call, Throwable t) {
                        Toast.makeText(MainActivity.this,
                                "Ocorreu um erro na requisicao",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
