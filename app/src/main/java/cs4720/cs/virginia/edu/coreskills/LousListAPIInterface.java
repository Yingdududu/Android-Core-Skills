package cs4720.cs.virginia.edu.coreskills;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**

 Assignment Notes: This helper class is provided if you decide to use
 the Retrofit REST API.  It is not required to use Retrofit.  If you choose to use this,
 you do not need to edit the code below, but you can if desired.

 */

public interface LousListAPIInterface {

    @GET("{dept}/{num}?json")
    Call<List<Section>> sectionList(@Path("dept") String dept, @Path("num") String num);

}
