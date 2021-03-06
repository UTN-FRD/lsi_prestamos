/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frd.prestamos.service;

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
import utn.frd.prestamos.domain.Equipo;

/**
 *
 * @author Sergio
 */
@Stateless
@Path("equipo")
public class EquiposFacadeREST extends AbstractFacade<Equipo> {

    @PersistenceContext(unitName = "utn.frd.prestamos_lsi_prestamos_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public EquiposFacadeREST() {
        super(Equipo.class);
    }

    @GET
    @Path("find/{codigo}")
    @Consumes({MediaType.TEXT_PLAIN})
    @Produces({MediaType.TEXT_PLAIN})
    public String findByEquipo(@PathParam("codigo") String codigo) {
        try{
            Equipo e = em.createNamedQuery("Equipo.findByDetalle", Equipo.class).setParameter("detalle", codigo).getSingleResult();
            return ""+e.getId();
        }catch(Exception e){
            return "ERROR";
        }
        
    }

/*    
    
    
    
    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Equipo entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Equipo entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Equipo find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<Equipo> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Equipo> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
*/
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
