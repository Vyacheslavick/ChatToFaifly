package com.example.slavick.chatforwork;

        import android.view.Window;

        import java.util.List;

        import retrofit.Callback;
        import retrofit.RestAdapter;
        import retrofit.http.Body;
        import retrofit.http.GET;
        import retrofit.http.POST;
        import retrofit.http.Path;
        import retrofit.http.Query;

public class Retrofit {

    private static final String ENDPOINT = "https://docs.google.com/document/d/1f4z8yMeQ0Dka244115cvEhvAtpUYCooO-RiZqZw4_Wg";
    private static ApiInterface apiInterface;
    static {
        initialize();
    }



    interface ApiInterface {

        @POST("/edit?usp=sharing")
        void sendMessage(@Body Message message,
                         Callback<Message> callback);

        @GET("/edit?usp=sharing")
        void getMessage(@Path("username") String username,
                        Callback<List<Message>> callback);
    }
    public static void initialize(){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(ENDPOINT)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        apiInterface = restAdapter.create(ApiInterface.class);
    }
    public static void getMessage(String name, Callback<List<Message>> callback){
        apiInterface.getMessage(name, callback);
    }
    public static void sendMessage(Message message, Callback<Message> callback){
        apiInterface.sendMessage(message, callback);
    }
}
