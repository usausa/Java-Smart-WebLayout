package smart.web.layout;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 *
 */
public class PartialTag extends TagSupport {

    private static final long serialVersionUID = 1L;

    private String include;

    /**
     *
     * @param include
     */
    public void setInclude(final String include) {
        this.include = include;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            pageContext.include(LayoutHelper.resolvePath(pageContext, include));
        } catch (ServletException e) {
            throw new JspException(e);
        } catch (IOException e) {
            throw new JspException(e);
        }
        return SKIP_BODY;
    }

    @Override
    public void release() {
        super.release();
        this.include = null;
    }
}
