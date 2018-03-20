/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frd.prestamos.service;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Sergio
 */
@javax.ws.rs.ApplicationPath("api")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(utn.frd.prestamos.service.CodigosFacadeREST.class);
        resources.add(utn.frd.prestamos.service.EquiposFacadeREST.class);
        resources.add(utn.frd.prestamos.service.PrestamosFacadeREST.class);
        resources.add(utn.frd.prestamos.service.UsuariosFacadeREST.class);
    }
    
}
