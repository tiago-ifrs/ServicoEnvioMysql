/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entidades.Leitura;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.xml.ws.WebServiceContext;

/**
 *
 * @author ti
 */
@Stateless
@Path("entidades.leitura")
public class LeituraFacadeREST extends AbstractFacade<Leitura> {

    @PersistenceContext(unitName = "ServicoEnvioMysqlPU")
    private EntityManager em;

    @Resource
    WebServiceContext webServiceContext;

    public LeituraFacadeREST() {
        super(Leitura.class);
    }
    /*
     @POST
     @Override
     @Consumes({"application/xml", "application/json"})
     public void create(Leitura entity) {
     super.create(entity);
     }
     */

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, Leitura entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Leitura find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/json"})
    public List<Leitura> findAll() {
        return super.findAll();
    }

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Leitura saida(@Context HttpServletRequest requestContext, Leitura entity) {
        if (entity == null) {
            return null;
        } else {
            Calendar calendar = Calendar.getInstance();
            Date now = calendar.getTime();
            Timestamp currentTimestamp = new Timestamp(now.getTime());
            entity.setDataLeitura(currentTimestamp);
            entity.setIp(requestContext.getRemoteAddr());
            super.create(entity);
            return entity;
        }
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Leitura> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
