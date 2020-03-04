package cs4720.cs.virginia.edu.coreskills;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**

 Assignment Notes: This helper class is provided if you decide to use
 the Retrofit REST API.  It is not required.  If you choose to use this,
 you do not need to edit this code.

 */

public class LousListAPIClient {
    public static final String BASE_URL = "http://stardock.cs.virginia.edu/louslist/Courses/view/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}