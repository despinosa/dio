/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package mx.ipn.escom.polofos.dio.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
    @NamedQuery(name = "Attribute.findAll",
        query = "SELECT a FROM Attribute a"),
    @NamedQuery(name = "Attribute.findByIdAttr",
        query = "SELECT a FROM Attribute a WHERE a.idAttr = :idAttr"),
    @NamedQuery(name = "Attribute.findByName",
        query = "SELECT a FROM Attribute a WHERE a.name = :name"),
    @NamedQuery(name = "Attribute.findByType",
        query = "SELECT a FROM Attribute a WHERE a.type = :type")})
public class Attribute implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @NotNull
  @Column(name = "id_attr", nullable = false)
  private Integer idAttr;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 50)
  @Column(nullable = false, length = 50)
  private String name;
  @Size(max = 50)
  @Column(length = 50)
  private String type;
  @JoinColumn(name = "class", referencedColumnName = "id_class",
      nullable = false)
  @ManyToOne(optional = false)
  private Class class1;

  public Attribute() {}

  public Attribute(Integer idAttr) {
    this.idAttr = idAttr;
  }

  public Attribute(Integer idAttr, String name) {
    this.idAttr = idAttr;
    this.name = name;
  }

  public Integer getIdAttr() {
    return idAttr;
  }

  public void setIdAttr(Integer idAttr) {
    this.idAttr = idAttr;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Class getClass1() {
    return class1;
  }

  public void setClass1(Class class1) {
    this.class1 = class1;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (idAttr != null ? idAttr.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Attribute)) {
      return false;
    }
    Attribute other = (Attribute) object;
    if ((this.idAttr == null && other.idAttr != null)
        || (this.idAttr != null && !this.idAttr.equals(other.idAttr))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "mx.ipn.escom.polofos.dio.model.Attribute[ idAttr=" + idAttr + " ]";
  }

}
