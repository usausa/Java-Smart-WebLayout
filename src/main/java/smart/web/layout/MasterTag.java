package smart.web.layout;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 *
 */
public class MasterTag extends BodyTagSupport {

    private static final long serialVersionUID = 1L;

    private String template;

    /**
     *
     * @param template
     */
    public void setTemplate(final String template) {
        this.template = template;
    }

    @Override
    public int doStartTag() throws JspException {
        LayoutHelper.backupLayoutParameters(pageContext);
        return super.doStartTag();
    }

    @Override
    public int doEndTag() throws JspException {
        try {
            pageContext.include(LayoutHelper.resolvePath(pageContext, template));
            LayoutHelper.restoreLayoutParameters(pageContext);
        } catch (ServletException e) {
            throw new JspException(e);
        } catch (IOException e) {
            throw new JspException(e);
        }
        return EVAL_PAGE;
    }
}
