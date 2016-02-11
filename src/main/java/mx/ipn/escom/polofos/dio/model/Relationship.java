/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ipn.escom.polofos.dio.model;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author crypt
 */
@Entity
@Table(catalog = "dio", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Relationship.findAll", query = "SELECT r FROM Relationship r"),
    @NamedQuery(name = "Relationship.findByIdRel", query = "SELECT r FROM Relationship r WHERE r.idRel = :idRel"),
    @NamedQuery(name = "Relationship.findByNature", query = "SELECT r FROM Relationship r WHERE r.nature = :nature"),
    @NamedQuery(name = "Relationship.findByLabel", query = "SELECT r FROM Relationship r WHERE r.label = :label")})
public class Relationship implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_rel", nullable = false)
    private Integer idRel;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(nullable = false, length = 12)
    private String nature;
    @Size(max = 50)
    @Column(length = 50)
    private String label;
    @JoinColumn(name = "diagram", referencedColumnName = "id_diagram", nullable = false)
    @ManyToOne(optional = false)
    private Diagram diagram;
    @JoinColumn(name = "source", referencedColumnName = "id_class", nullable = false)
    @ManyToOne(optional = false)
    private Class source;
    @JoinColumn(name = "target", referencedColumnName = "id_class", nullable = false)
    @ManyToOne(optional = false)
    private Class target;

    public Relationship() {
    }

    public Relationship(Integer idRel) {
        this.idRel = idRel;
    }

    public Relationship(Integer idRel, String nature) {
        this.idRel = idRel;
        this.nature = nature;
    }

    public Integer getIdRel() {
        return idRel;
    }

    public void setIdRel(Integer idRel) {
        this.idRel = idRel;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Diagram getDiagram() {
        return diagram;
    }

    public void setDiagram(Diagram diagram) {
        this.diagram = diagram;
    }

    public Class getSource() {
        return source;
    }

    public void setSource(Class source) {
        this.source = source;
    }

    public Class getTarget() {
        return target;
    }

    public void setTarget(Class target) {
        this.target = target;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRel != null ? idRel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Relationship)) {
            return false;
        }
        Relationship other = (Relationship) object;
        if ((this.idRel == null && other.idRel != null) || (this.idRel != null && !this.idRel.equals(other.idRel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.ipn.escom.polofos.dio.model.Relationship[ idRel=" + idRel + " ]";
    }
    
}
