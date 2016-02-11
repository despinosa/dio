/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package mx.ipn.escom.polofos.dio.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
    @NamedQuery(name = "Diagram.findAll", query = "SELECT d FROM Diagram d"),
    @NamedQuery(name = "Diagram.findByIdDiagram",
        query = "SELECT d FROM Diagram d WHERE d.idDiagram = :idDiagram"),
    @NamedQuery(name = "Diagram.findByName",
        query = "SELECT d FROM Diagram d WHERE d.name = :name"),
    @NamedQuery(name = "Diagram.findByPattern",
        query = "SELECT d FROM Diagram d WHERE d.pattern = :pattern")})
public class Diagram implements Serializable {

  public enum Pattern {
    ABSTRACT_FACTORY, BUILDER, FACTORY_METHOD, PROTOTYPE, SINGLETON
  }

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id_diagram", nullable = false)
  private Integer idDiagram;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 50)
  @Column(nullable = false, length = 50)
  private String name;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 17)
  @Column(nullable = false, length = 17)
  @Enumerated(EnumType.ORDINAL)
  private Pattern pattern;
  @JoinColumn(name = "user", referencedColumnName = "id_user", nullable = false)
  @ManyToOne(optional = false)
  private User user;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "diagram")
  private List<Relationship> relationshipList;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "diagram")
  private List<Class> classList;

  public Diagram() {}

  public Diagram(Integer idDiagram) {
    this.idDiagram = idDiagram;
  }

  public Diagram(Integer idDiagram, String name, Pattern pattern) {
    this.idDiagram = idDiagram;
    this.name = name;
    this.pattern = pattern;
  }

  public Integer getIdDiagram() {
    return idDiagram;
  }

  public void setIdDiagram(Integer idDiagram) {
    this.idDiagram = idDiagram;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Pattern getPattern() {
    return pattern;
  }

  public void setPattern(Pattern pattern) {
    this.pattern = pattern;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @XmlTransient
  public List<Relationship> getRelationshipList() {
    return relationshipList;
  }

  public void setRelationshipList(List<Relationship> relationshipList) {
    this.relationshipList = relationshipList;
  }

  @XmlTransient
  public List<Class> getClassList() {
    return classList;
  }

  public void setClassList(List<Class> classList) {
    this.classList = classList;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (idDiagram != null ? idDiagram.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Diagram)) {
      return false;
    }
    Diagram other = (Diagram) object;
    if ((this.idDiagram == null && other.idDiagram != null)
        || (this.idDiagram != null
            && !this.idDiagram.equals(other.idDiagram))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "mx.ipn.escom.polofos.dio.model.Diagram[ idDiagram=" + idDiagram
        + " ]";
  }

}
