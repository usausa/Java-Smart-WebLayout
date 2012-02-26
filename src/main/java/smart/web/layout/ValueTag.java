package smart.web.layout;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

/**
 *
 */
public class ValueTag extends TagSupport {

    private static final long serialVersionUID = 1L;

    private String key;

    private String var;

    /**
     *
     * @param key
     */
    public void setKey(final String key) {
        this.key = key;
    }

    /**
     *
     * @param var
     */
    public void setVar(final String var) {
        this.var = var;
    }

    @Override
    public int doStartTag() throws JspException {
        if (var != null) {
            pageContext.setAttribute(var, LayoutHelper.getValue(pageContext, key), PageContext.PAGE_SCOPE);
        } else {
            try {
                pageContext.getOut().print(LayoutHelper.getValue(pageContext, key));
            } catch (IOException e) {
                throw new JspException(e);
            }
        }
        return SKIP_BODY;
    }

    @Override
    public void release() {
        super.release();
        key = null;
        var = null;
    }
}
