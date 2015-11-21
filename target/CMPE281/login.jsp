<%--
  Created by IntelliJ IDEA.
  User: WU
  Date: 20/11/2015
  Time: 8:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <link href="./Test_files/css" rel="stylesheet" type="text/css">
    <title>Mobile Health Sensor, Group #1</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./Test_files/bootstrap-custom.css" type="text/css">
    <link rel="stylesheet" href="./Test_files/icomoon.css" type="text/css">
    <link rel="stylesheet" href="./Test_files/font-awesome.css" type="text/css">
    <link rel="stylesheet" href="./Test_files/tipsy.css" type="text/css">
    <link rel="stylesheet" href="./Test_files/docs.css" type="text/css">
    <link rel="stylesheet" href="./Test_files/pygments.css" type="text/css">

    <script type="text/javascript">
        var DOCUMENTATION_OPTIONS = {
            URL_ROOT:    '#',
            VERSION:     '3.0',
            COLLAPSE_INDEX: false,
            FILE_SUFFIX: '',
            HAS_SOURCE:  false
        };
    </script>

    <script type="text/javascript" src="./Test_files/jquery.js"></script>
    <script type="text/javascript" src="./Test_files/underscore.js"></script>
    <script type="text/javascript" src="./Test_files/doctools.js"></script>
    <script type="text/javascript" src="./Test_files/bootstrap.js"></script>
    <script type="text/javascript" src="./Test_files/jquery.tipsy.js"></script>
    <script type="text/javascript" src="./Test_files/jquery.cookie.js"></script>
    <script type="text/javascript" src="./Test_files/navbar.js"></script>

</head>
<body>
<header id="header-db" class="row" role="navigation">
    <div class="header-content">
        <a class="icon-menu expand-toc-icon pull-left" href="file:///Users/xiaofengli/Documents/CMPE_281/project_ui_temp/Test_files/Test.html"></a>
        <div class="logo pull-left">
            <h1>Health Sensor</h1>
        </div>

    </div>
</header>



<div class="content">
    <div class="main-column pull-left">
        <div class="document">
            <div class="documentwrapper">
                <div class="bodywrapper">
                    <div class="body">
                        <div class="section" style="width: 460px;">
                            <h1>Login</h1>
                            <div class="section" id="getting-started">

                                <form class="col-md-10 col-md-offset-1 col-xs-12 col-xs-offset-0" method="POST" action="<c:url value="/j_spring_security_check" />">

                                    <table>
                                        <tr>
                                            <td>User:</td>
                                            <td><input type='text' name='username' value=''></td>
                                        </tr>
                                        <tr>
                                            <td>Password:</td>
                                            <td><input type='password' name='password' /></td>
                                        </tr>
                                        <tr>
                                            <td colspan='2'><input name="submit" type="submit"
                                                                   value="submit" /></td>
                                        </tr>
                                    </table>

                                    <input type="hidden" name="${_csrf.parameterName}"
                                           value="${_csrf.token}" />
                                </form>

                            </div>
                        </div>
                        <div class="footer">
                            <div class="copyright">
                                <p>© Group #1, Health Mobile Sensor, 2015.</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body></html>