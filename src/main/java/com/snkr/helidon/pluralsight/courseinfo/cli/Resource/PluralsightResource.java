package com.snkr.helidon.pluralsight.courseinfo.cli.Resource;

import com.snkr.helidon.pluralsight.courseinfo.cli.entity.PluralsightCourse;
import com.snkr.helidon.pluralsight.courseinfo.cli.service.CourseRetreivalService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

import static java.util.function.Predicate.not;

@Path("/courses")
@RequestScoped
public class PluralsightResource {


    private final CourseRetreivalService courseRetreivalService;

    @Inject
    public PluralsightResource(CourseRetreivalService config){
        this.courseRetreivalService = config;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<PluralsightCourse> getDefaultMessage() {
        return courseRetreivalService.getCoursesFor("sander-mak")
                .stream()
                .filter(not(PluralsightCourse::isRetired))
                .toList();
    }
}
