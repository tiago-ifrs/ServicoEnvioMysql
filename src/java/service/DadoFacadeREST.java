/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.google.gson.Gson;
import entidades.Dado;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

/**
 *
 * @author ti
 */
@Stateless
@Path("entidades.dado")
public class DadoFacadeREST extends AbstractFacade<Dado> {

    @PersistenceContext(unitName = "ServicoEnvioMysqlPU")
    private EntityManager em;

    public DadoFacadeREST() {
        super(Dado.class);
    }
    /*
     @POST
     @Override
     @Consumes({"application/xml", "application/json"})
     public void create(Dado entity) {
     if (entity.getTempo() == null) {
     Calendar calendar = Calendar.getInstance();
     Date now = calendar.getTime();
     Timestamp currentTimestamp = new Timestamp(now.getTime());
     entity.setTempo(currentTimestamp);
     }
     super.create(entity);
     }
     */

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, Dado entity) {
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
    public Dado find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/json"})
    public List<Dado> findAll() {
        return super.findAll();
        /*
        List<Dado> d = em.createNamedQuery("Dado.findAll")
                .setMaxResults(10)
                .getResultList();
        return d;
        */
    }
    
    @GET
    @Path("/idLeitura/{id}")
    @Produces({"application/json"})
    public List<Dado> findByIdLeitura(@PathParam("id") Integer id) {      
        List<Dado> d = em.createNamedQuery("Dado.findByIdLeitura")
                .setParameter("idLeitura", id)
                .getResultList();
        return d;
    }

    @POST
    @Consumes({"application/json"})
    @Produces({TEXT_PLAIN})
    public String saida(Dado entity) {
        if (entity == null) {
            return null;
        } else {
            Gson gson = new Gson();
            Dado d2 = new Dado(entity.getSensor(), entity.getValor());
            String dadoJson = gson.toJson(d2);
            MessageDigest md;
            try {
                md = MessageDigest.getInstance("MD5");
                md.update(dadoJson.getBytes());
                BigInteger hashInt = new BigInteger(1, md.digest());

                //String hashGerada = hashInt.toString(16);
                //a comparação falha por causa dos zeros à esquerda
                String hashGerada = String.format("%032x", hashInt);
                if (entity.getHash().equals(hashGerada)) {
                    if (entity.getTempo() == null) {
                        Calendar calendar = Calendar.getInstance();
                        Date now = calendar.getTime();
                        Timestamp currentTimestamp = new Timestamp(now.getTime());
                        entity.setTempo(currentTimestamp);
                    }
                    super.create(entity);
                    return "OK\n";
                } else {
                    return "Erro de Hash:(" + hashGerada + ")-" + dadoJson + "-\n"
                            + "esperado:" + entity.getHash() + "\n";
                }
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(DadoFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
/*
    @POST
    @Consumes({"application/json"})
    @Produces({TEXT_PLAIN})
    public String saida(List<Dado> entity) {
        String retorno = null;
        if (entity == null) {
            return null;
        } else {
            ListIterator it = entity.listIterator();
            while (it.hasNext()) {
                Gson gson = new Gson();

                Dado d = (Dado) it.next();
                Dado d2 = new Dado(d.getSensor(), d.getValor());
                String dadoJson = gson.toJson(d2);
                MessageDigest md;
                try {
                    md = MessageDigest.getInstance("MD5");
                    md.update(dadoJson.getBytes());
                    BigInteger hashInt = new BigInteger(1, md.digest());

                    String hashGerada = hashInt.toString(16);
                    if (d.getHash().equals(hashGerada)) {
                        if (d.getTempo() == null) {
                            Calendar calendar = Calendar.getInstance();
                            Date now = calendar.getTime();
                            Timestamp currentTimestamp = new Timestamp(now.getTime());
                            d.setTempo(currentTimestamp);
                        }
                        super.create(d);
                    } else {
                        return "Erro de Hash:(" + hashGerada + ")-" + dadoJson + "-\n"
                                + "esperado:" + d.getHash() + "\n";
                    }
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(DadoFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return retorno;
    }
*/
    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Dado> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to
    ) {
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
