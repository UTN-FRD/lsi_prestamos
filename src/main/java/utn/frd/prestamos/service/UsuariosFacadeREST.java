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
import javax.persistence.NoResultException;
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
import utn.frd.prestamos.domain.Usuario;
import utn.frd.prestamos.domain.Codigo;
import utn.frd.prestamos.service.bean.ResponseBean;

/**
 *
 * @author Sergio
 */
@Stateless
@Path("usuarios")
public class UsuariosFacadeREST extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "utn.frd.prestamos_lsi_prestamos_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public UsuariosFacadeREST() {
        super(Usuario.class);
    }

    @POST
    @Path("{codigo}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseBean create(@PathParam("codigo") String codigo, Usuario entity) {
        ResponseBean resp = new ResponseBean();
        try{
            Codigo c = em.createNamedQuery("Codigo.findByCodigo", Codigo.class).setParameter("codigo", codigo).getSingleResult();
            if(c.getFechaValidado()!=null){
                resp.setCode("202");
                resp.setMessage( "El codigo ya ha sido validado anteriormente" );
                return resp;
            }
            c.setFechaValidado(new Date());
            em.merge(c);
            entity.setHabilitado(true);
            entity.setFechaAlta(new Date());
            em.persist(entity);
            resp.setCode("200");
            resp.setMessage( "Usuario creado satisfactoriamente!" );
            resp.setObject( entity );
        }catch(NoResultException re){
            resp.setCode("201");
            resp.setMessage( "El código ingresado no existe." );
        }catch(Exception e){
            resp.setCode("500");
            resp.setMessage( e.getMessage() );
        }
        return resp;
    }
    
    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<Usuario> findAll() {
        return super.findAll();
    }

/*
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Usuario entity) {
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
    public Usuario find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Usuario> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
