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
    @NamedQuery(name = "Method.findAll", query = "SELECT m FROM Method m"),
    @NamedQuery(name = "Method.findByIdMethod", query = "SELECT m FROM Method m WHERE m.idMethod = :idMethod"),
    @NamedQuery(name = "Method.findByName", query = "SELECT m FROM Method m WHERE m.name = :name"),
    @NamedQuery(name = "Method.findByScope", query = "SELECT m FROM Method m WHERE m.scope = :scope"),
    @NamedQuery(name = "Method.findByReturnType", query = "SELECT m FROM Method m WHERE m.returnType = :returnType")})
public class Method implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_method", nullable = false)
    private Integer idMethod;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(nullable = false, length = 50)
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(nullable = false, length = 9)
    private String scope;
    @Size(max = 50)
    @Column(name = "return_type", length = 50)
    private String returnType;
    @JoinColumn(name = "class", referencedColumnName = "id_class", nullable = false)
    @ManyToOne(optional = false)
    private Class class1;

    public Method() {
    }

    public Method(Integer idMethod) {
        this.idMethod = idMethod;
    }

    public Method(Integer idMethod, String name, String scope) {
        this.idMethod = idMethod;
        this.name = name;
        this.scope = scope;
    }

    public Integer getIdMethod() {
        return idMethod;
    }

    public void setIdMethod(Integer idMethod) {
        this.idMethod = idMethod;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
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
        hash += (idMethod != null ? idMethod.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Method)) {
            return false;
        }
        Method other = (Method) object;
        if ((this.idMethod == null && other.idMethod != null) || (this.idMethod != null && !this.idMethod.equals(other.idMethod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.ipn.escom.polofos.dio.model.Method[ idMethod=" + idMethod + " ]";
    }
    
}
