package com.flipkart;

import com.flipkart.rest.AdminRestAPI;
import com.flipkart.rest.ProfessorRestAPI;
import com.flipkart.rest.StudentRestAPI;
import com.flipkart.rest.UserRestAPI;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;


public class App extends Application<Configuration> {
    @Override
    public void initialize(Bootstrap<Configuration> b) {
    }
    @Override
    public void run(Configuration c, Environment e) {
        System.out.println("Registering REST resources");
        //registering all the RESTful service classes.
        e.jersey().register(new AdminRestAPI());
        e.jersey().register(new ProfessorRestAPI());
        e.jersey().register(new StudentRestAPI());
        e.jersey().register(new UserRestAPI());
    }

    public static void main(String[] args) throws Exception {
        new App().run(args);
    }
}