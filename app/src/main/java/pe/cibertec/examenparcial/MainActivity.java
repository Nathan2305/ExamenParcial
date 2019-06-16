package pe.cibertec.examenparcial;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    EditText edt_nombreJob;
    Button btnBuscarJob;
    TextView txt_descJob;
    TextView title_job;
    TextView company;
    ImageView fotoCompany;
    ArrayList<Job> listJobs;

    RecyclerView recyclerJob;
    RecyclerView.LayoutManager layoutManager;

    public final String API_URL = "https://jobs.github.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerJob = findViewById(R.id.recyclerJob);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        btnBuscarJob = findViewById(R.id.buscarJob);
        edt_nombreJob = findViewById(R.id.nombreJob);
        btnBuscarJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String job = edt_nombreJob.getText().toString();
                if (!job.isEmpty()) {
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(API_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    JobInterface jobInterface = retrofit.create(JobInterface.class);
                    Call<ArrayList<Job>> resultJobCall = jobInterface.obtenerJobs(job);
                    resultJobCall.enqueue(new Callback<ArrayList<Job>>() {
                        @Override
                        public void onResponse(Call<ArrayList<Job>> call, Response<ArrayList<Job>> response) {
                            if (response.isSuccessful()) {
                                listJobs = new ArrayList<>();
                                ArrayList<Job> jobArrayList = response.body();
                                for (Job aux_job : jobArrayList) {
                                    if (aux_job.getTitle().contains(job)) {
                                        listJobs.add(aux_job);
                                    }
                                }
                                RecyclerView.Adapter adapter = new CustomAdapter(getApplicationContext(), listJobs);
                                recyclerJob.setAdapter(adapter);
                                recyclerJob.setLayoutManager(layoutManager);

                            }
                        }

                        @Override
                        public void onFailure(Call<ArrayList<Job>> call, Throwable throwable) {
                            System.out.println("ERROR CONSUMIENDO SERVICIO:" + throwable.getMessage());
                        }
                    });

                }
            }
        });
    }
}
