package br.com.manager.view;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class BaseBean implements Serializable {
    private static final long serialVersionUID = -5410886668050787843L;

    private static final String BUNDLE_MESSAGE = "message";

    /**
     *
     * Adiciona uma mensagem de nível FacesMessage.SEVERITY_ERROR às mensagens
     * do JSF.
     *
     * @param titulo
     *            - Título da mensagem a ser exibida
     */
    public void addErrorMessage(String titulo, Object... params) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, titulo, null));
    }

    /**
     *
     * Adiciona uma mensagem de nível FacesMessage.SEVERITY_ERROR às mensagens
     * do JSF.
     *
     * @param titulo
     *            - Título da mensagem a ser exibida
     * @param message
     *            - Descrição da mensagem a ser exibida
     */
    public void addErrorMessage(String titulo) {
        addErrorMessage(titulo, (Object[]) null);
    }

    /**
     *
     * Adiciona uma mensagem de nível FacesMessage.SEVERITY_INFO às mensagens
     * do JSF.
     *
     * @param titulo
     *            - Título da mensagem a ser exibida
     * @param message
     *            - Descrição da mensagem a ser exibida
     */
    public void addInfoMessage(String titulo, String message) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, message));
    }

    /**
     *
     * Adiciona uma mensagem de nível FacesMessage.SEVERITY_INFO às mensagens
     * do JSF.
     *
     * @param titulo
     *            - Título da mensagem a ser exibida
     */
    public void addInfoMessage(String titulo) {
        addInfoMessage(titulo, null);
    }

    /**
     *
     * Adiciona uma mensagem de nível FacesMessage.SEVERITY_WARN às mensagens
     * do JSF.
     *
     * @param titulo
     *            - Título da mensagem a ser exibida
     * @param message
     *            - Descrição da mensagem a ser exibida
     */
    public void addWarnMessage(String titulo, String message) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_WARN, titulo, message));
    }

    /**
     *
     * Adiciona uma mensagem de nível FacesMessage.SEVERITY_WARN às mensagens
     * do JSF.
     *
     * @param titulo
     *            - Título da mensagem a ser exibida
     */
    public void addWarnMessage(String titulo) {
        addWarnMessage(titulo, null);
    }

    /**
     *
     * Exibe a mensagem "Message_Sucesso" do Resource Bundle "message" do JSF. <br />
     * Esta mensagem é exibida sob o nível FacesMessage.SEVERITY_INFO do JSF.
     *
     */
    public void showSuccessMessage() {
        FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                        getMessage("Message_Sucesso"), null));
    }

    /**
     *
     * Procura por uma mensagem no Resource Bundle "message" do JSF.
     *
     * @param chave
     *            - Chave para ser procurada dentro do Resource Bundle
     *            "message".
     * @return - Valor da chave mapeada dentro do Resource Bundle ou a própria
     *         chave de consulta caso nada seja encontrado.
     */
    public String getMessage(String chave) {
        return getMessage(BUNDLE_MESSAGE, chave);
    }

    public String getMessage(String chave, Object[] params) {
        return getMessage(BUNDLE_MESSAGE, chave, params);
    }

    /**
     *
     * Procura por uma mensagem nos Resource Bundles do JSF.
     *
     * @param resource
     *            - Nome mapeado do Resource Bundle no JSF.
     * @param chave
     *            - Chave para ser procurada dentro do Resource Bundle.
     * @return - Valor da chave mapeada dentro do Resource Bundle ou a própria
     *         chave de consulta caso nada seja encontrado.
     */
    private String getMessage(String resource, String chave) {
        return getMessage(resource, chave, (Object[]) null);
    }

    private static String getMessage(String resource, String chave,
                                     Object... params) {

        String message;
        ResourceBundle bundle = FacesContext.getCurrentInstance()
                .getApplication()
                .getResourceBundle(FacesContext.getCurrentInstance(), resource);

        try {
            message = bundle.getString(chave);
        } catch (MissingResourceException e) {
            return chave;
        }

        if (params != null) {
            MessageFormat mf = new MessageFormat(message);
            message = mf.format(params, new StringBuffer(), null).toString();
        }

        return message;
    }

}
