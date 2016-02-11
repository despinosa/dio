/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package mx.ipn.escom.polofos.dio.controller;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import mx.ipn.escom.polofos.dio.controller.util.JsfUtil;
import mx.ipn.escom.polofos.dio.facade.UserFacade;
import mx.ipn.escom.polofos.dio.model.User;
import mx.ipn.escom.polofos.dio.model.util.PasswordStorage;

/**
 *
 * @author crypt
 */

@ManagedBean(name = "sessionController")
@RequestScoped
public class SessionController implements Serializable {

  private String username;
  private String password;
  @EJB
  private UserFacade userFacade;

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  private UserFacade getUserFacade() {
    return userFacade;
  }

  public void validate() {
    try {
      User user = getUserFacade().findByUsername(username);
      if (PasswordStorage.verifyPassword(password, user.getPassword())) {
        getSessionFor(user);
        JsfUtil.redirect("/myaccount/index.xhtml");
      } else {
        JsfUtil.addErrorMessage(
            ResourceBundle.getBundle("/Bundle").getString("InvalidPassword"));
      }
    } catch (UserFacade.NoSuchUserException ex) {
      JsfUtil.addErrorMessage(
          ResourceBundle.getBundle("/Bundle").getString("NoSuchUser"));
    } catch (Exception ex) {
      JsfUtil.addErrorMessage(
          ResourceBundle.getBundle("/Bundle").getString("InternalError"));
    }
  }

  public void checkAlreadyLoggedin() {
    if (getSession() != null && getSession().getAttribute("user") != null) {
      logout();
    }
  }

  public void logout() {
    FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    JsfUtil.redirect("/login.xhtml?faces-redirect=true");
  }

  public SessionController() {}


  public static HttpSession getSession() {
    return (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
        .getSession(false);
  }
  
  public static HttpSession getSessionFor(User user) {
    HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
        .getExternalContext().getSession(false);
    session.setAttribute("user", user);
    return session;
  }

}

