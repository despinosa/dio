package mx.ipn.escom.polofos.dio.controller;

import mx.ipn.escom.polofos.dio.model.User;
import mx.ipn.escom.polofos.dio.controller.util.JsfUtil;
import mx.ipn.escom.polofos.dio.controller.util.JsfUtil.PersistAction;
import mx.ipn.escom.polofos.dio.facade.UserFacade;

import java.io.Serializable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@ManagedBean(name = "userController")
@SessionScoped
public class UserController implements Serializable {

  @EJB
  private UserFacade userFacade;
  private User user;

  public UserController() {}

  public User getUser() {
    return user;
  }

  protected void setEmbeddableKeys() {}

  protected void initializeEmbeddableKey() {}

  private UserFacade getUserFacade() {
    return userFacade;
  }

  public void prepareCreate() {
    user = new User();
    initializeEmbeddableKey();
  }

  public void create() {
    persist(PersistAction.CREATE,
        ResourceBundle.getBundle("/Bundle").getString("UserCreated"));
    JsfUtil.redirect("/login.xhtml");
  }

  public void update() {
    persist(PersistAction.UPDATE,
        ResourceBundle.getBundle("/Bundle").getString("UserUpdated"));
  }

  public void destroy() {
    persist(PersistAction.DELETE,
        ResourceBundle.getBundle("/Bundle").getString("UserDeleted"));
    if (!JsfUtil.isValidationFailed()) {
      user = null; // Remove selection
    }
  }

  private void persist(PersistAction persistAction, String successMessage) {
    if (user != null) {
      setEmbeddableKeys();
      try {
        if (persistAction != PersistAction.DELETE) {
          getUserFacade().edit(user);
        } else {
          getUserFacade().remove(user);
        }
        JsfUtil.addSuccessMessage(successMessage);
      } catch (EJBException ex) {
        String msg = "";
        Throwable cause = ex.getCause();
        if (cause != null) {
          msg = cause.getLocalizedMessage();
        }
        if (msg.length() > 0) {
          JsfUtil.addErrorMessage(msg);
        } else {
          JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle")
              .getString("PersistenceErrorOccured"));
        }
      } catch (Exception ex) {
        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle")
            .getString("PersistenceErrorOccured"));
      }
    }
  }

  @FacesConverter(forClass = User.class)
  public static class UserControllerConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component,
        String value) {
      if (value == null || value.length() == 0) {
        return null;
      }
      UserController controller =
          (UserController) facesContext.getApplication().getELResolver()
              .getValue(facesContext.getELContext(), null, "userController");
      return controller.getUserFacade().find(getKey(value));
    }

    java.lang.Integer getKey(String value) {
      java.lang.Integer key;
      key = Integer.valueOf(value);
      return key;
    }

    String getStringKey(java.lang.Integer value) {
      StringBuilder sb = new StringBuilder();
      sb.append(value);
      return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component,
        Object object) {
      if (object == null) {
        return null;
      }
      if (object instanceof User) {
        User o = (User) object;
        return getStringKey(o.getIdUser());
      } else {
        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,
            "object {0} is of type {1}; expected type: {2}", new Object[] {
                object, object.getClass().getName(), User.class.getName()});
        return null;
      }
    }

  }

}
