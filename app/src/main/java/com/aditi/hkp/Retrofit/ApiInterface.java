package com.aditi.hkp.Retrofit;

import com.aditi.hkp.ModelClasses.AadharVerification;
import com.aditi.hkp.ModelClasses.Feedback;
import com.aditi.hkp.ModelClasses.GetAdharDetails;
import com.aditi.hkp.ModelClasses.GetHalkaNumber;
import com.aditi.hkp.ModelClasses.GetKhasraNumber;
import com.aditi.hkp.ModelClasses.KishanRequest;
import com.aditi.hkp.ModelClasses.Login;
import com.aditi.hkp.ModelClasses.PostRegistration;
import com.aditi.hkp.ModelClasses.UpdatePassword;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by dell on 2/21/2018.
 */

public interface ApiInterface {

    /*@GET("movie/{id}")
 Call<MoviesResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);*/

   /* @POST("Attendance/GetEmployeeListForAttendace")
    Call<Model> getEmployeeDetails(@Query("UserID") String UserID, @Query("CityID") String CityID, @Query("SiteID") String SiteID, @Query("ShiftID") String ShiftID, @Query("AttDate") String AttDate);

    @Multipart
    @POST("ProjectPlan/AddUpdateSiteProjectProgress")
    Call<Response> AddProgress(@Query("UserID") String UserID, @Query("site_project_progress_id") String site_project_progress_id,
                               @Query("site_project_id") String site_project_id, @Query("progress_date") String progress_date,
                               @Query("progress_value") String progress_value,
                               @Query("WorkerUsed") String WorkerUsed, @Query("longitute") String longitute, @Query("latitute") String latitute,
                               @Part("file\"; filename=\"pp.png\" ") RequestBody file);*/

   /*@QueryMap Map<String, String> queryMap*/

   @POST("GetKhasraNumber")
    Observable<List<GetKhasraNumber>> getkhasralist(@QueryMap Map<String, String> queryMap);

   @POST("GetHalkaNumber")
   Observable<List<GetHalkaNumber>> gethalkano(@QueryMap Map<String, String> queryMap);

   @POST("GetAdharDetails")
   Observable<GetAdharDetails> GetAdharDetails(@QueryMap Map<String, String> queryMap);

   @POST("PostRegistration")
   Observable<PostRegistration> PostRegistration(@QueryMap Map<String, String> queryMap);

   @POST("KisanRequest")
   Observable<KishanRequest> KisanRequest(@QueryMap Map<String, String> queryMap);

   @POST("AdharVarification")
   Observable<AadharVerification> adharverification(@QueryMap Map<String, String> queryMap);

   @POST("Login")
   Observable<Login> getLogin(@QueryMap Map<String, String> queryMap);

   @POST("UpdatePassword")
   Observable<UpdatePassword> updatePassword(@QueryMap Map<String, String> queryMap);

   @POST("Feedback")
   Observable<Feedback> postFeedback(@QueryMap Map<String, String> queryMap);


}
