package smart.web.layout;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 *
 */
public class ParameterTag extends TagSupport {

    private static final long serialVersionUID = 1L;

    private String key;

    private Object value;

    /**
     *
     * @param key
     */
    public void setKey(final String key) {
        this.key = key;
    }

    /**
     *
     * @param value
     */
    public void setValue(final Object value) {
        this.value = value;
    }

    @Override
    public int doStartTag() throws JspException {
        LayoutHelper.setValue(pageContext, key, value);
        return SKIP_BODY;
    }

    @Override
    public void release() {
        super.release();
        key = null;
        value = null;
    }
}
