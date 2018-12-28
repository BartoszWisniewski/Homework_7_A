package com.infoshareacademy.service;

import com.infoshareacademy.dao.ComputerDao;
import com.infoshareacademy.dao.StudentDao;
import com.infoshareacademy.model.Computer;
import com.infoshareacademy.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/api")
@Transactional
public class appService {

    private Logger LOG = LoggerFactory.getLogger(appService.class);

    @Inject
    private StudentDao studentDao;

    @Inject
    private ComputerDao computerDao;


    @GET
    @Path("/students")
    @Produces(MediaType.TEXT_PLAIN)
    public Response listAllStudents() {

        String resultList = new String();
        final List<Student> result = studentDao.findAll();

        LOG.info("Found {} objects", result.size());
        for (Student s : result) {
            resultList += (s.toString() + "\n");
        }
        return Response.ok(resultList).build();
    }

    @GET
    @Path("students/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response sortSudentsByName(@PathParam("name") String n){

        String resultList = new String();
        final List<Student> result = studentDao.sortedByName(n);

        LOG.info("Found {} objects", result.size());
        for (Student s : result) {
            resultList += (s.toString() + "\n");
        }
        return Response.ok(resultList).build();
    }

    @POST
    @Path("computers")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response addComputer(Computer computer){

        Computer newComputer = new Computer(computer.getName(),
                computer.getOperatingSystem());

        computerDao.update(newComputer);

        String resultList = new String();
        final List<Computer> result = computerDao.findAll();

        LOG.info("Found {} objects", result.size());
        for (Computer c : result) {
            resultList += (c.toString() + "\n");
        }
        return Response.ok(resultList).build();
    }

    @GET
    @Path("computers")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findComputer(){
        final List<Computer> result = computerDao.findAll();
        return Response.ok(result).build();
    }

}
