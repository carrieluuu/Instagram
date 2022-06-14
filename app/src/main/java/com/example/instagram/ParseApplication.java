package com.example.instagram;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {
    // Initializes Parse SDK as soon as the application is created
    @Override
    public void onCreate() {
        super.onCreate();

        // Register your parse models
        ParseObject.registerSubclass(Post.class);

        // ParseObject.registerSubclass(Post.class);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("nsK30vZT1H1sgnMe0u7Y7Q4HInbvrxzB8bif8AQ4")
                .clientKey("I9fLECdixaACajqaeZiG3Idxmz2Rm9dNSSRxQepr")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}