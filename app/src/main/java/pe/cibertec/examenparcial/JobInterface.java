package pe.cibertec.examenparcial;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JobInterface {

    @GET("positions.json?")
    Call<ArrayList<Job>> obtenerJobs(@Query("description") String job);

}
