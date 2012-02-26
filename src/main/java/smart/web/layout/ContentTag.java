package smart.web.layout;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 *
 */
public class ContentTag extends BodyTagSupport {

    private static final long serialVersionUID = 1L;

    private String name;

    /**
     *
     * @param name
     */
    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public int doEndTag() throws JspException {
        LayoutHelper.setContent(pageContext, name, bodyContent.getString());
        return EVAL_PAGE;
    }

    @Override
    public void release() {
        super.release();
        this.name = null;
    }
}
