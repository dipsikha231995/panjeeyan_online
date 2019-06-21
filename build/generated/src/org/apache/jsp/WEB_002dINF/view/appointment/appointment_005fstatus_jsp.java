package org.apache.jsp.WEB_002dINF.view.appointment;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class appointment_005fstatus_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(2);
    _jspx_dependants.add("/WEB-INF/jspf/header.jspf");
    _jspx_dependants.add("/WEB-INF/jspf/footer.jspf");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_forEach_var_items;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_if_test;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_c_forEach_var_items = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_if_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_c_forEach_var_items.release();
    _jspx_tagPool_c_if_test.release();
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!doctype html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <title>e-Panjeeyan</title>\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width\">\n");
      out.write("<!--        <meta http-equiv=\"Content-Security-Policy\" content=\"default-src 'none'; script-src 'self' 'unsafe-inline'; connect-src 'self'; img-src 'self'; style-src 'self' 'unsafe-inline'; font-src 'self';\">-->\n");
      out.write("<!--        <meta http-equiv=\"X-XSS-Protection\" content=\"'1; mode=block' always\">-->\n");
      out.write("<!--        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8; X-Content-Type-Options=nosniff\" />-->\n");
      out.write("        <script src=\"js/jquery.confirm.js\" type=\"text/javascript\"></script>\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/epanjeeyan.css\"/>\n");
      out.write("        <link rel=\"stylesheet\" href=\"bs/css/bootstrap.css\"/>\n");
      out.write("        <link rel=\"stylesheet\" href=\"bs/css/datepicker.css\"/>\n");
      out.write("        <script src=\"js/jquery.min.js\" type=\"text/javascript\"></script>\n");
      out.write("        <script src=\"js/jquery.jqprint-0.3.js\" type=\"text/javascript\"></script>\n");
      out.write("        <script src=\"bs/js/bootstrap.min.js\" type=\"text/javascript\"></script>\n");
      out.write("        <script src=\"bs/js/bootstrap-datepicker.js\" type=\"text/javascript\"></script>\n");
      out.write("        <script src=\"bs/js/custom.js\" type=\"text/javascript\"></script>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("\n");
      out.write("        <div class=\"container transparent-bg\">\n");
      out.write("            <div class=\"row head-block\">\n");
      out.write("                <div class=\"col-sm-12\">\t\t\n");
      out.write("                    <div id=\"divLogo\">\n");
      out.write("                        <div class=\"col-sm-5\">\n");
      out.write("                        <div class=\"logo\" > <img src=\"resources/logo.png\" /> </div>\n");
      out.write("                            <h2 class=\"head-1\" id=\"divTagLine\"><strong>e-PANJEEYAN </strong></h2>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"col-sm-7\">\n");
      out.write("                            <h5 class=\"head-2\" id=\"divTagLine\"><strong>REVENUE AND DISASTER MANAGEMENT DEPARTMENT</strong></h5>\n");
      out.write("                            <h5 class='head-2' id=\"divTagLine\"><strong>Government of Assam</strong></h5>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div> \n");
      out.write(" \n");
      out.write("\n");
      out.write("\n");
      out.write("<a style=\"padding: 0px 6px;\" href=\"index.jsp\"><span class=\"glyphicon glyphicon-backward\"></span> Go Back</a>\n");
      out.write("<div class=\"panel panel-info\" style=\"margin-top: 10px;\" >\n");
      out.write("    <div class=\"panel-heading\">\n");
      out.write("        <h2 class=\"panel-title\">Check your appointment Status</h2>\n");
      out.write("    </div>\n");
      out.write("    <div class=\"panel-body\">\n");
      out.write("        ");
      if (_jspx_meth_c_if_0(_jspx_page_context))
        return;
      out.write("\n");
      out.write("        <form class=\"form-horizontal\" role=\"form\" action=\"appointment_status\" method=\"post\">\n");
      out.write("            <div class=\"form-group\">\n");
      out.write("                <label for=\"DocRefNo\" class=\"col-sm-4 control-label\">\n");
      out.write("                    Reference No <span class=\"glyphicon glyphicon-star\"></span>\n");
      out.write("                </label>\n");
      out.write("                <div class=\"col-sm-3\">\n");
      out.write("                    <input type=\"text\" class=\"form-control\" id=\"DocRefNo\" name=\"DocRefNo\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${docrefno}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" placeholder=\"\" data-validation=\"required custom length\" data-validation-length=\"max100\" data-validation-regexp=\"^([a-zA-Z +0-9~%.,:_\\-/@&()]+)$\" data-validation-error-msg-length=\"Maxixmum 100 characters allowed\"  data-validation-error-msg-required=\"Required field\" data-validation-error-msg-custom=\"Allowed characters are A to Z, a to z, 0 to 9, ~, %, ., :, _, -, (, ) or blank space\" >\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"form-group\">\n");
      out.write("                <label class=\"col-sm-4 control-label\" for=\"captcha\"> Security Question <span class=\"glyphicon glyphicon-star\"></span>&nbsp;&nbsp;");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${ran1}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write(" + ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${ran2}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write(" = </label>\n");
      out.write("                <div class=\"col-sm-1\">\n");
      out.write("                    <input id=\"captcha\" name=\"captcha\" type=\"text\" placeholder=\"\" class=\"form-control\" data-validation=\"number\" data-validation-error-msg=\"Invalid value for Security Question\">\n");
      out.write("                </div>\n");
      out.write("                <div class=\"captcha-text\"> << Specify correct result of addition in the given box</div>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"form-group\">\n");
      out.write("                <div class=\"col-sm-offset-4 col-sm-2\">\n");
      out.write("                    <button type=\"submit\" class=\"btn btn-success btn-sm\">Check Status</button>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </form>\n");
      out.write("        <div class=\"col-sm-offset-4 text-success\">");
      if (_jspx_meth_c_if_1(_jspx_page_context))
        return;
      out.write("</div>\n");
      out.write("        <div class=\"col-sm-offset-4 text-success \">");
      if (_jspx_meth_c_if_2(_jspx_page_context))
        return;
      out.write("</div>\n");
      out.write("    </div>\n");
      out.write("</div>\n");
      out.write("<script src=\"js/form-validator/jquery.form-validator.min.js\" type=\"text/javascript\"></script>\n");
      out.write("<script>$.validate({});</script>\n");
      out.write("\n");
      out.write("\n");
      out.write("\r\n");
      out.write("        \r\n");
      out.write("<!--  </body>\r\n");
      out.write("\r\n");
      out.write("  <div class=\"navbar navbar-verse navbar-fixed-bottom\">\r\n");
      out.write("    <div class=\"container\">\r\n");
      out.write("      <p class=\"navbar-text pull-right\"><font color=\"white\">© 2014 - National Informatics Center,Assam State Unit.  </font>\r\n");
      out.write("           \r\n");
      out.write("      </p>\r\n");
      out.write("    \r\n");
      out.write("    </div>\r\n");
      out.write("  </div>\r\n");
      out.write("      \r\n");
      out.write("</html>-->\r\n");
      out.write("\r\n");
      out.write("</div>\r\n");
      out.write("<div class=\"row-fluid\" align=\"center\">\r\n");
      out.write("    <div >\r\n");
      out.write("\r\n");
      out.write("        <h5 style=\"color: gray\" >Web application designed & developed by NIC, Assam</h4>\r\n");
      out.write("        <h5 style=\"color: gray\" >All rights reserved. Revenue & Disaster Management Department, Government of Assam</h5>\r\n");
      out.write("    </div>\r\n");
      out.write("</div> \r\n");
      out.write("\r\n");
      out.write("    ");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_c_if_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_0.setPageContext(_jspx_page_context);
    _jspx_th_c_if_0.setParent(null);
    _jspx_th_c_if_0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${errormsg ne null}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null)).booleanValue());
    int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
    if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n");
        out.write("            <div class=\"alert-danger\" style=\"margin: 10px 0;padding: 10px;\">\n");
        out.write("                ");
        if (_jspx_meth_c_forEach_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_0, _jspx_page_context))
          return true;
        out.write("\n");
        out.write("            </div>\n");
        out.write("        ");
        int evalDoAfterBody = _jspx_th_c_if_0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
      return true;
    }
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
    return false;
  }

  private boolean _jspx_meth_c_forEach_0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_forEach_0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _jspx_tagPool_c_forEach_var_items.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_forEach_0.setPageContext(_jspx_page_context);
    _jspx_th_c_forEach_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_0);
    _jspx_th_c_forEach_0.setVar("error");
    _jspx_th_c_forEach_0.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${errormsg}", java.lang.Object.class, (PageContext)_jspx_page_context, null));
    int[] _jspx_push_body_count_c_forEach_0 = new int[] { 0 };
    try {
      int _jspx_eval_c_forEach_0 = _jspx_th_c_forEach_0.doStartTag();
      if (_jspx_eval_c_forEach_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\n");
          out.write("                    <strong>>></strong> ");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${error}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("<br/>\n");
          out.write("                ");
          int evalDoAfterBody = _jspx_th_c_forEach_0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_forEach_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_forEach_0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_forEach_0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_forEach_0.doFinally();
      _jspx_tagPool_c_forEach_var_items.reuse(_jspx_th_c_forEach_0);
    }
    return false;
  }

  private boolean _jspx_meth_c_if_1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_1 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_1.setPageContext(_jspx_page_context);
    _jspx_th_c_if_1.setParent(null);
    _jspx_th_c_if_1.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${not empty appointment_fee_structure}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null)).booleanValue());
    int _jspx_eval_c_if_1 = _jspx_th_c_if_1.doStartTag();
    if (_jspx_eval_c_if_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${appointment_fee_structure}", java.lang.String.class, (PageContext)_jspx_page_context, null));
        int evalDoAfterBody = _jspx_th_c_if_1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
      return true;
    }
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
    return false;
  }

  private boolean _jspx_meth_c_if_2(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_2 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_2.setPageContext(_jspx_page_context);
    _jspx_th_c_if_2.setParent(null);
    _jspx_th_c_if_2.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${not empty appointment_error}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null)).booleanValue());
    int _jspx_eval_c_if_2 = _jspx_th_c_if_2.doStartTag();
    if (_jspx_eval_c_if_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${appointment_error}", java.lang.String.class, (PageContext)_jspx_page_context, null));
        int evalDoAfterBody = _jspx_th_c_if_2.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_2);
      return true;
    }
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_2);
    return false;
  }
}
