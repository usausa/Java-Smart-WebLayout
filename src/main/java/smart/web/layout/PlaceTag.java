package smart.web.layout;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/**
 *
 */
public class PlaceTag extends TagSupport {

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
    public int doStartTag() throws JspException {
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        JspWriter writer = pageContext.getOut();
        try {
            writer.print(LayoutHelper.getContent(pageContext, name));
        } catch (IOException e) {
            throw new JspException(e);
        }
        return EVAL_PAGE;
    }

    @Override
    public void release() {
        super.release();
        this.name = null;
    }
}
