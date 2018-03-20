/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frd.prestamos.service;

import java.util.Date;
import java.util.List;
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
import javax.ws.rs.core.MediaType;
import utn.frd.prestamos.Prestamos;

/**
 *
 * @author Sergio
 */
@Stateless
@Path("prestamo")
public class PrestamosFacadeREST extends AbstractFacade<Prestamos> {

    @PersistenceContext(unitName = "utn.frd.prestamos_lsi_prestamos_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public PrestamosFacadeREST() {
        super(Prestamos.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Prestamos entity) {
        entity.setFechaInicio(new Date());
        super.create(entity);
    }

    @GET
    @Path("{idEquipo}")
    @Produces({MediaType.TEXT_PLAIN})
    public String devolver(@PathParam("idEquipo") Integer idEquipo) {
        try{
            Prestamos p = em.createNamedQuery("Prestamos.findEquipoPrestado", Prestamos.class).setParameter("idEquipo", idEquipo).getSingleResult();
            p.setFechaFin(new Date());
            super.edit(p);
            return "Equipo devuelto!";
        }catch(Exception e){
            return "ERROR";
        }
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Prestamos> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Prestamos> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
