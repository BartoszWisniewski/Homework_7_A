package com.infoshareacademy.service;

import com.infoshareacademy.dao.StudentDao;
import com.infoshareacademy.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/api")
@Transactional
public class appService {

    private Logger LOG = LoggerFactory.getLogger(appService.class);

    @Inject
    private StudentDao studentDao;


    @GET
    @Path("/students")
    @Produces(MediaType.TEXT_PLAIN)
    public Response listAllStudents(){

        String resultList = new String();
        final List<Student> result = studentDao.findAll();

        LOG.info("Found {} objects", result.size());

        for (Student s : result) {
            resultList+=(s.toString() + "\n");
        }

        return Response.ok(resultList).build();
    }


}
