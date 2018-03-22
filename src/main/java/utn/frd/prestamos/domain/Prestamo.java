/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frd.prestamos.domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "prestamos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prestamo.findAll", query = "SELECT p FROM Prestamo p")
    , @NamedQuery(name = "Prestamo.findById", query = "SELECT p FROM Prestamo p WHERE p.id = :id")
    , @NamedQuery(name = "Prestamo.findByIdAlumno", query = "SELECT p FROM Prestamo p WHERE p.usuario.id = :idAlumno")
    , @NamedQuery(name = "Prestamo.findByIdEquipo", query = "SELECT p FROM Prestamo p WHERE p.equipo.id = :idEquipo")
    , @NamedQuery(name = "Prestamo.findEquipoPrestado", query = "SELECT p FROM Prestamo p WHERE p.equipo.id = :idEquipo and p.fechaFin is null")
    , @NamedQuery(name = "Prestamo.findEquiposPrestados", query = "SELECT p FROM Prestamo p WHERE p.fechaFin is null")
    , @NamedQuery(name = "Prestamo.findByComentario", query = "SELECT p FROM Prestamo p WHERE p.comentario = :comentario")
    , @NamedQuery(name = "Prestamo.findByFechaInicio", query = "SELECT p FROM Prestamo p WHERE p.fechaInicio = :fechaInicio")
    , @NamedQuery(name = "Prestamo.findByFechaFin", query = "SELECT p FROM Prestamo p WHERE p.fechaFin = :fechaFin")})
public class Prestamo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_alumno")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_equipo")
    private Equipo equipo;

    @Size(max = 255)
    @Column(name = "comentario")
    private String comentario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;

    public Prestamo() {
    }

    public Prestamo(Integer id) {
        this.id = id;
    }

    public Prestamo(Integer id, Usuario usuario, Equipo equipo, Date fechaInicio) {
        this.id = id;
        this.usuario = usuario;
        this.equipo = equipo;
        this.fechaInicio = fechaInicio;
    }

    public Prestamo(Equipo equipo, Usuario usuario) {
        this.fechaInicio = new Date();
        this.equipo = equipo;
        this.usuario = usuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public String getFechaInicioStr() {
        return (new SimpleDateFormat("dd/mm HH:MM")).format( fechaInicio );
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prestamo)) {
            return false;
        }
        Prestamo other = (Prestamo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "utn.frd.prestamos.Prestamo[ id=" + id + " ]";
    }

}
