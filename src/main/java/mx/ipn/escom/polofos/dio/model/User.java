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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import mx.ipn.escom.polofos.dio.model.util.PasswordStorage;

/**
 *
 * @author crypt
 */
@Entity
@Table(catalog = "dio", schema = "",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByIdUser",
        query = "SELECT u FROM User u WHERE u.idUser = :idUser"),
    @NamedQuery(name = "User.findByUsername",
        query = "SELECT u FROM User u WHERE u.username = :username"),
    @NamedQuery(name = "User.findByPassword",
        query = "SELECT u FROM User u WHERE u.password = :password"),
    @NamedQuery(name = "User.findByFirstName",
        query = "SELECT u FROM User u WHERE u.firstName = :firstName"),
    @NamedQuery(name = "User.findByLastName",
        query = "SELECT u FROM User u WHERE u.lastName = :lastName")})
public class User implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id_user", nullable = false)
  private Integer idUser;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 50)
  @Column(nullable = false, length = 50)
  private String username;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 100)
  @Column(nullable = false, length = 100)
  private String password;
  @Size(max = 100)
  @Column(name = "first_name", length = 100)
  private String firstName;
  @Size(max = 100)
  @Column(name = "last_name", length = 100)
  private String lastName;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
  private List<Diagram> diagramList;

  public User() {}

  public User(Integer idUser) {
    this.idUser = idUser;
  }

  public User(Integer idUser, String username, String password) {
    this.idUser = idUser;
    this.username = username;
    this.password = password;
  }

  public Integer getIdUser() {
    return idUser;
  }

  public void setIdUser(Integer idUser) {
    this.idUser = idUser;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = PasswordStorage.createHash(password);
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  @XmlTransient
  public List<Diagram> getDiagramList() {
    return diagramList;
  }

  public void setDiagramList(List<Diagram> diagramList) {
    this.diagramList = diagramList;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (idUser != null ? idUser.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof User)) {
      return false;
    }
    User other = (User) object;
    if ((this.idUser == null && other.idUser != null)
        || (this.idUser != null && !this.idUser.equals(other.idUser))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "mx.ipn.escom.polofos.dio.model.User[ idUser=" + idUser + " ]";
  }

}
