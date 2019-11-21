package converter;

import java.io.Serializable;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import model.Endereco;

@FacesConverter("enderecoConverter")
public class EnderecoConverter implements Converter, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
        if (value != null) {
            Object o = this.getAttributesFrom(component).get(value);
        	return o;
        }
        return null;
    }

	@Override
    public String getAsString(FacesContext ctx, UIComponent component, Object value) {

        if (value != null && !"".equals(value)) {

        	Endereco entity = (Endereco) value;

            // adiciona item como atributo do componente
            this.addAttribute(component, entity);

            Integer codigo = entity.getId();
            if (codigo != null) {
                return String.valueOf(codigo);
            }
        }

        return (String) value;
    }

    protected void addAttribute(UIComponent component, Endereco o) {
        String key = Integer.toString(o.getId()); // codigo do Objeto para Converter como chave neste caso
        this.getAttributesFrom(component).put(key, o);
    }

    protected Map<String, Object> getAttributesFrom(UIComponent component) {
        return component.getAttributes();
    }
}
