/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frd.prestamos.service;

import java.util.ArrayList;
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
import utn.frd.prestamos.domain.Equipo;
import utn.frd.prestamos.domain.Prestamo;
import utn.frd.prestamos.domain.Usuario;
import utn.frd.prestamos.service.bean.PrestamoBean;
import utn.frd.prestamos.service.bean.ResponseBean;

/**
 *
 * @author Sergio
 */
@Stateless
@Path("prestamo")
public class PrestamosFacadeREST extends AbstractFacade<Prestamo> {

    @PersistenceContext(unitName = "utn.frd.prestamos_lsi_prestamos_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public PrestamosFacadeREST() {
        super(Prestamo.class);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void createPrestamo(PrestamoBean prestamoBean) {
        //registra un nuevo prestamo
        Equipo equipo = em.find(Equipo.class, Integer.parseInt(prestamoBean.getIdEquipo()) );
        Usuario usuario = em.find(Usuario.class, Integer.parseInt( prestamoBean.getIdUsuario()) );
        
        Prestamo entity = new Prestamo( equipo, usuario );
        super.create(entity);
    }

    @GET
    @Path("{idEquipo}")
    @Produces({MediaType.APPLICATION_JSON})
    public ResponseBean devolver(@PathParam("idEquipo") Integer idEquipo) {
        ResponseBean resp = new ResponseBean();
        try{
            Prestamo p = em.createNamedQuery("Prestamo.findEquipoPrestado", Prestamo.class).setParameter("idEquipo", idEquipo).getSingleResult();
            p.setFechaFin(new Date());
            super.edit(p);
            resp.setCode("200");
            resp.setMessage("Equipo devuelto!");
        }catch(Exception e){
            resp.setCode("500");
            resp.setMessage(e.getMessage());
        }
        return resp;
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Prestamo> findPrestamos() {
        return em.createNamedQuery("Prestamo.findEquiposPrestados", Prestamo.class).getResultList();
    }
    
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
