/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ipn.escom.polofos.dio.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author crypt
 */
@Entity
@Table(catalog = "dio", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Class.findAll", query = "SELECT c FROM Class c"),
    @NamedQuery(name = "Class.findByIdClass", query = "SELECT c FROM Class c WHERE c.idClass = :idClass"),
    @NamedQuery(name = "Class.findByName", query = "SELECT c FROM Class c WHERE c.name = :name"),
    @NamedQuery(name = "Class.findByStereotype", query = "SELECT c FROM Class c WHERE c.stereotype = :stereotype"),
    @NamedQuery(name = "Class.findByXPosition", query = "SELECT c FROM Class c WHERE c.xPosition = :xPosition"),
    @NamedQuery(name = "Class.findByYPosition", query = "SELECT c FROM Class c WHERE c.yPosition = :yPosition"),
    @NamedQuery(name = "Class.findByXSize", query = "SELECT c FROM Class c WHERE c.xSize = :xSize"),
    @NamedQuery(name = "Class.findByYSize", query = "SELECT c FROM Class c WHERE c.ySize = :ySize")})
public class Class implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_class", nullable = false)
    private Integer idClass;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(nullable = false, length = 50)
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(nullable = false, length = 10)
    private String stereotype;
    @Column(name = "x_position")
    private Short xPosition;
    @Column(name = "y_position")
    private Short yPosition;
    @Column(name = "x_size")
    private Short xSize;
    @Column(name = "y_size")
    private Short ySize;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "class1")
    private List<Method> methodList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "class1")
    private List<Attribute> attributeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "source")
    private List<Relationship> relationshipList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "target")
    private List<Relationship> relationshipList1;
    @JoinColumn(name = "diagram", referencedColumnName = "id_diagram", nullable = false)
    @ManyToOne(optional = false)
    private Diagram diagram;

    public Class() {
    }

    public Class(Integer idClass) {
        this.idClass = idClass;
    }

    public Class(Integer idClass, String name, String stereotype) {
        this.idClass = idClass;
        this.name = name;
        this.stereotype = stereotype;
    }

    public Integer getIdClass() {
        return idClass;
    }

    public void setIdClass(Integer idClass) {
        this.idClass = idClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStereotype() {
        return stereotype;
    }

    public void setStereotype(String stereotype) {
        this.stereotype = stereotype;
    }

    public Short getXPosition() {
        return xPosition;
    }

    public void setXPosition(Short xPosition) {
        this.xPosition = xPosition;
    }

    public Short getYPosition() {
        return yPosition;
    }

    public void setYPosition(Short yPosition) {
        this.yPosition = yPosition;
    }

    public Short getXSize() {
        return xSize;
    }

    public void setXSize(Short xSize) {
        this.xSize = xSize;
    }

    public Short getYSize() {
        return ySize;
    }

    public void setYSize(Short ySize) {
        this.ySize = ySize;
    }

    @XmlTransient
    public List<Method> getMethodList() {
        return methodList;
    }

    public void setMethodList(List<Method> methodList) {
        this.methodList = methodList;
    }

    @XmlTransient
    public List<Attribute> getAttributeList() {
        return attributeList;
    }

    public void setAttributeList(List<Attribute> attributeList) {
        this.attributeList = attributeList;
    }

    @XmlTransient
    public List<Relationship> getRelationshipList() {
        return relationshipList;
    }

    public void setRelationshipList(List<Relationship> relationshipList) {
        this.relationshipList = relationshipList;
    }

    @XmlTransient
    public List<Relationship> getRelationshipList1() {
        return relationshipList1;
    }

    public void setRelationshipList1(List<Relationship> relationshipList1) {
        this.relationshipList1 = relationshipList1;
    }

    public Diagram getDiagram() {
        return diagram;
    }

    public void setDiagram(Diagram diagram) {
        this.diagram = diagram;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idClass != null ? idClass.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Class)) {
            return false;
        }
        Class other = (Class) object;
        if ((this.idClass == null && other.idClass != null) || (this.idClass != null && !this.idClass.equals(other.idClass))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.ipn.escom.polofos.dio.model.Class[ idClass=" + idClass + " ]";
    }
    
}
