package com.udacity.gradle.builditbigger.backend;

import com.example.telljokeslib.TellJokes;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import javax.inject.Named;

/** An endpoint class we are exposing */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.builditbigger.gradle.udacity.com",
                ownerName   = "backend.builditbigger.gradle.udacity.com",
                packagePath = ""
        )
)
public class MyEndpoint {

    @ApiMethod(name = "getApiJoke")
    public  MyBean getApiJoke() {
        MyBean response = new MyBean();
        TellJokes tj= new TellJokes();
        response.setData(tj.getAJoke());

        return  response;
    }


}
