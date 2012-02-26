package smart.web.layout;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import javax.servlet.jsp.PageContext;

/**
 *
 */
public final class LayoutHelper {

    private static final String PREFIX = LayoutHelper.class.getPackage().getName() + ".PREFIX";

    private static final String SUFFIX = LayoutHelper.class.getPackage().getName() + ".SUFFIX";

    private static final String VALUES = LayoutHelper.class.getPackage().getName() + ".VALUES";

    private static final String CONTENTS = LayoutHelper.class.getPackage().getName() + ".CONTENTS";

    private static final String STACK = LayoutHelper.class.getPackage().getName() + ".STACK";

    /**
     *
     */
    private static class Parameters {

        private final Map<String, String> contents;

        private final Map<String, Object> values;

        /**
         *
         * @return
         */
        public Map<String, String> getContents() {
            return contents;
        }

        /**
         *
         * @return
         */
        public Map<String, Object> getValues() {
            return values;
        }

        /**
         *
         * @param contents
         * @param values
         */
        public Parameters(final Map<String, String> contents, final Map<String, Object> values) {
            this.contents = contents;
            this.values = values;
        }
    }

    /**
     *
     * @param pageContext
     * @param path
     * @return
     */
    public static String resolvePath(final PageContext pageContext, final String path) {
        String prefix = pageContext.getServletContext().getInitParameter(PREFIX);
        String suffix = pageContext.getServletContext().getInitParameter(SUFFIX);
        StringBuilder builder = new StringBuilder(64);
        if ((prefix != null) && (prefix.length() > 0)) {
            builder.append(prefix);
        }
        builder.append(path);
        if ((suffix != null) && (suffix.length() > 0)) {
            builder.append(suffix);
        }
        return builder.toString();
    }

    /**
     *
     * @param pageContext
     */
    @SuppressWarnings("unchecked")
    public static void backupLayoutParameters(final PageContext pageContext) {
        Stack<Parameters> stack = (Stack<Parameters>)pageContext.getAttribute(STACK, PageContext.REQUEST_SCOPE);
        if (stack == null) {
            stack = new Stack<Parameters>();
            pageContext.setAttribute(STACK, stack, PageContext.REQUEST_SCOPE);
        }
        Map<String, String> contents = (Map<String, String>)pageContext.getAttribute(CONTENTS, PageContext.REQUEST_SCOPE);
        Map<String, Object> values = (Map<String, Object>)pageContext.getAttribute(VALUES, PageContext.REQUEST_SCOPE);
        stack.push(new Parameters(contents, values));
    }

    /**
     *
     * @param pageContext
     */
    @SuppressWarnings("unchecked")
    public static void restoreLayoutParameters(final PageContext pageContext) {
        Stack<Parameters> stack = (Stack<Parameters>)pageContext.getAttribute(STACK, PageContext.REQUEST_SCOPE);
        if ((stack != null) && (stack.size() > 0)) {
            Parameters parameters = stack.pop();
            pageContext.setAttribute(CONTENTS, parameters.getContents(), PageContext.REQUEST_SCOPE);
            pageContext.setAttribute(VALUES, parameters.getValues(), PageContext.REQUEST_SCOPE);
        }
    }

    /**
     *
     * @param pageContext
     * @param name
     * @param content
     */
    @SuppressWarnings("unchecked")
    public static void setContent(final PageContext pageContext, final String name, final String content) {
        Map<String, String> contents = (Map<String, String>)pageContext.getAttribute(CONTENTS, PageContext.REQUEST_SCOPE);
        if (contents == null) {
            contents = new HashMap<String, String>();
            pageContext.setAttribute(CONTENTS, contents, PageContext.REQUEST_SCOPE);
        }
        contents.put(name, content);
    }

    /**
     *
     * @param pageContext
     * @param name
     * @return
     */
    @SuppressWarnings("unchecked")
    public static String getContent(final PageContext pageContext, final String name) {
        Map<String, String> contents = (Map<String, String>)pageContext.getAttribute(CONTENTS, PageContext.REQUEST_SCOPE);
        if (contents == null) {
            return "";
        }
        return contents.containsKey(name) ? contents.get(name) : "";
    }

    /**
     *
     * @param pageContext
     * @param key
     * @param value
     */
    @SuppressWarnings("unchecked")
    public static void setValue(final PageContext pageContext, final String key, final Object value) {
        Map<String, Object> values = (Map<String, Object>)pageContext.getAttribute(VALUES, PageContext.REQUEST_SCOPE);
        if (values == null) {
            values = new HashMap<String, Object>();
            pageContext.setAttribute(VALUES, values, PageContext.REQUEST_SCOPE);
        }
        values.put(key, value);
    }

    /**
     *
     * @param pageContext
     * @param key
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Object getValue(final PageContext pageContext, final String key) {
        Map<String, Object> values = (Map<String, Object>)pageContext.getAttribute(VALUES, PageContext.REQUEST_SCOPE);
        if (values == null) {
            return null;
        }
        return values.containsKey(key) ? values.get(key) : null;
    }

    private LayoutHelper() {
    }
}
