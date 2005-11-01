<?php 
if ($_SERVER['HTTP_HOST']=='acegisecurity.sourceforge.net')
header('Location: http://acegisecurity.org');
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"><html><head><title>Acegi Security System for Spring - Acegi Security System for Spring</title><style type="text/css" media="all">
          @import url("./style/maven-base.css");
          
			    @import url("./style/maven-theme.css");</style><link rel="stylesheet" href="./style/print.css" type="text/css" media="print"></link><meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"></meta></head><body class="composite"><div id="banner"><a href="http://sourceforge.net/projects/acegisecurity" id="organizationLogo"><img alt="Acegi Technology Pty Limited" src="http://sourceforge.net/sflogo.php?group_id=104215&amp;type=5"></img></a><a href="http://acegisecurity.sourceforge.net/" id="projectLogo"><img alt="Acegi Security System for Spring" src="http://acegisecurity.sourceforge.net/logo.gif"></img></a><div class="clear"><hr></hr></div></div><div id="breadcrumbs"><div class="xleft">
                	Last published: 23 October 2005
                  | Doc for 0.9.0-SNAPSHOT</div><div class="xright">
        
        <a href="http://sourceforge.net/projects/acegisecurity" class="externalLink" title="External Link">Acegi Security on Sourceforge</a>
      </div><div class="clear"><hr></hr></div></div><div id="leftColumn"><div id="navcolumn"><div id="menuOverview"><h5>Overview</h5><ul><li class="none"><a href="index.html">Home</a></li><li class="none"><a href="building.html">Building with Maven</a></li><li class="none"><a href="downloads.html">Downloads</a></li></ul></div><div id="menuDocumentation"><h5>Documentation</h5><ul><li class="none"><a href="suggested.html">Suggested Steps</a></li><li class="none"><a href="reference.html">Reference Guide</a></li><li class="none"><a href="dbinit.txt">Sample SQL Schema</a></li><li class="none"><a href="faq.html">FAQ</a></li><li class="none"><a href="articles.html">External Web Articles</a></li><li class="none"><a href="powering.html">Products using Acegi</a></li><li class="none"><a href="standalone.html">Use without Spring</a></li><li class="none"><a href="upgrade/upgrade-070-080.html">Upgrading to 0.8.0</a></li><li class="none"><a href="upgrade/upgrade-06-070.html">Upgrading to 0.7.0</a></li><li class="none"><a href="upgrade/upgrade-05-06.html">Upgrading to 0.6</a></li><li class="none"><a href="upgrade/upgrade-04-05.html">Upgrading to 0.5</a></li><li class="none"><a href="upgrade/upgrade-03-04.html">Upgrading to 0.4</a></li><li class="none"><a href="multiproject/acegi-security/apidocs/index.html" class="newWindow" title="New Window" target="_blank">Core JavaDocs</a></li><li class="none"><a href="policies.html">Project Policies</a></li></ul></div><div id="menuProjects"><h5>Projects</h5><ul><li class="none"><a href="multiproject/acegi-security/index.html">Core Framework</a></li><li class="none"><a href="multiproject/acegi-security-cas/index.html">CAS Adapter</a></li><li class="none"><a href="multiproject/acegi-security-catalina/index.html">Catalina Adapter</a></li><li class="none"><a href="multiproject/acegi-security-jboss/index.html">JBoss Adapter</a></li><li class="none"><a href="multiproject/acegi-security-jetty/index.html">Jetty Adapter</a></li><li class="none"><a href="multiproject/acegi-security-resin/index.html">Resin Adapter</a></li></ul></div><div id="menuSamples"><h5>Samples</h5><ul><li class="none"><a href="multiproject/acegi-security-sample-contacts/index.html">Contacts</a></li><li class="none"><a href="multiproject/acegi-security-sample-attributes/index.html">Attributes</a></li></ul></div><div id="search"><h5>Search Acegi Security System for Spring</h5><form action="http://www.google.com/search" method="get"><a href="http://www.google.com" class="navlink"><img height="32" width="75" alt="Google" src="http://www.google.com/logos/Logo_25wht.gif"></img></a><input name="q" maxlength="255" size="15" type="text" value="" id="q"></input><input name="btnG" type="submit" value="Go" id="btnG"></input><input name="domains" type="hidden" value="http://acegisecurity.sourceforge.net"></input><input name="sitesearch" type="hidden" value="http://acegisecurity.sourceforge.net" id="search"></input></form></div><div id="menuProject_Documentation"><h5>Project Documentation</h5><ul><li class="none"><strong><a href="index.html">About Acegi Security System for Spring</a></strong></li><li class="none"><a href="downloads.html">Downloads</a></li><li class="collapsed"><a href="project-info.html">Project Info</a></li><li class="collapsed"><a href="maven-reports.html">Project Reports</a></li><li class="none"><a href="http://maven.apache.org/development-process.html" class="externalLink" title="External Link">Development Process</a></li></ul></div><div><ul><li class="none"><a href="http://www.springframework.org/" class="externalLink" title="External Link"><img title="Spring Framework" style="border: 1px" src="http://www.springframework.org/buttons/spring_white.png" alt="Spring Framework"></img></a></li><li class="none"><a href="http://www.cenqua.com/clover/" class="externalLink" title="External Link"><img title="Code Coverage by Clover" style="border: 1px" src="http://www.cenqua.com/clover/images/clovered1.gif" alt="Code Coverage by Clover"></img></a></li></ul></div><a href="http://maven.apache.org/" title="Built by Maven" id="poweredBy"><img alt="Built by Maven" src="./images/logos/maven-button-1.png"></img></a></div></div><div id="bodyColumn"><div class="contentBox"><div class="section"><center>
      </center><br></br><br></br><font face="Arial" size="-1">
      <center><b>
      <hr></hr>

      </b><center><b>Mission Statement</b></center>
      <hr></hr>
      <br></br>To provide comprehensive security services for <a href="http://www.springframework.org/" class="externalLink" title="External Link"><i>The Spring Framework</i></a>. 
      </center><br></br><b>
      <hr></hr>

      </b><center><b>Key Features</b></center>
      <hr></hr>
      <br></br>
      <ul>
        <li><b>It is ready NOW.</b> As explained in the reference guide, the API 
        is now quite stable. We also use the <a href="http://apr.apache.org/versioning.html" class="externalLink" title="External Link">Apache APR Project 
        Versioning Guidelines</a> so you can identify backward 
        compatibility.<br></br><br></br>
        </li><li><b>Fast results:</b> View our <a href="suggested.html">suggested steps</a>
        for the fastest way to develop complex, security-compliant applications.<br></br><br></br>
        </li><li><b>Enterprise-wide single sign on:</b> Using Yale University's open 
        source <a href="http://www.yale.edu/tp/auth/" class="externalLink" title="External Link">Central Authentication 
        Service</a> (CAS), the Acegi Security System for Spring can participate 
        in an enterprise-wide single sign on environment. You no longer need 
        every web application to have its own authentication database. Nor are 
        you restricted to single sign on across a single web container. Advanced 
        single sign on features like proxy support and forced refresh of logins 
        are supported by both CAS and Acegi Security.<br></br><br></br>
        </li><li><b>Reuses your Spring expertise:</b> We use Spring application 
        contexts for all configuration, which should help Spring developers get 
        up-to-speed nice and quickly.<br></br><br></br>
        </li><li><b>Domain object instance security:</b> In many applications it's 
        desirable to define Access Control Lists (ACLs) for individual domain 
        object instances. We provide a comprehensive ACL package with features 
        including integer bit masking, permission inheritence (including 
        blocking), a JDBC-backed ACL repository, caching and a pluggable, 
        interface-driven design.<br></br><br></br>
        </li><li><b>Non-intrusive setup:</b> The entire security system can operate 
        within a single web application using the provided filters. There is no 
        need to make special changes or deploy libraries to your Servlet or EJB 
        container.<br></br><br></br>
        </li><li><b>Full (but optional) container integration:</b> The credential 
        collection and authorization capabilities of your Servlet or EJB 
        container can be fully utilised via included "container adapters". We 
        currently support Catalina (Tomcat), Jetty, JBoss and Resin, with 
        additional containers easily added.<br></br><br></br>
        </li><li><b>Keeps your objects free of security code:</b> Many applications 
        need to secure data at the bean level based on any combination of 
        parameters (user, time of day, authorities held, method being invoked, 
        parameter on method being invoked....). This package gives you this 
        flexibility without adding security code to your Spring business 
        objects.<br></br><br></br>
        </li><li><b>After invocation security:</b> Acegi Security can not only protect
		methods from being invoked in the first place, but it can also
		deal with the Objects returned from the methods. Included implementations 
		of after invocation security can throw an exception or mutate the returned
		object based on ACLs.<br></br><br></br>
        </li><li><b>Secures your HTTP requests as well:</b> In addition to securing 
        your beans, the project also secures your HTTP requests. No longer is it 
        necessary to rely on web.xml security constraints. Best of all, your 
        HTTP requests can now be secured by your choice of regular expressions 
        or Apache Ant paths, along with pluggable authentication, authorization 
        and run-as replacement managers.<br></br><br></br>
        </li><li><b>Channel security:</b> The Acegi Security System for Spring can 
        automatically redirect requests across an appropriate transport channel. 
        Whilst flexible enough to support any of your "channel" requirements (eg 
        the remote user is a human, not a robot), a common channel security 
        feature is to ensure your secure pages will only be available over 
        HTTPS, and your public pages only over HTTP. Acegi Security also 
        supports unusual port combinations and pluggable transport decision 
        managers.<br></br><br></br>
        </li><li><b>Supports HTTP BASIC authentication:</b> Perfect for remoting 
        protocols or those web applications that prefer a simple browser pop-up 
        (rather than a form login), Acegi Security can directly process HTTP 
        BASIC authentication requests as per RFC 1945.<br></br><br></br>
        </li><li><b>Supports HTTP Digest authentication:</b> For greater security than
        offered by BASIC authentcation, Acegi Security also supports Digest Authentication
        (which never sends the user's password across the wire). Digest Authentication
        is widely supported by modern browsers. Acegi Security's implementation complies
        with both RFC 2617 and RFC 2069.<br></br><br></br>
        </li><li><b>Convenient security taglib:</b> Your JSP files can use our taglib 
        to ensure that protected content like links and messages are only 
        displayed to users holding the appropriate granted authorities. The taglib
		also fully integrates with Acegi Security's ACL services.<br></br><br></br>
        </li><li><b>Application context or attribute-based configuration:</b> You 
        select the method used to configure your security environment. The 
        project supports configuration via Spring application contexts as well 
        as Jakarta Commons Attributes.<br></br><br></br>
        </li><li><b>Various authentication backends:</b> We include the ability to 
        retrieve your user and granted authority definitions from either an XML 
        file or JDBC datasource. Alternatively, you can implement the 
        single-method DAO interface and obtain authentication details from 
        anywhere you like.<br></br><br></br>
        </li><li><b>Event support:</b> Building upon Spring's 
        <code>ApplicationEvent</code> services, you can write your own listeners 
        for authentication-related events, along with authorisation-related events.
		This enables you to implement account lockout and audit log systems, with
		complete decoupling from Acegi Security code.<br></br><br></br>
        </li><li><b>Easy integration with existing databases:</b> Our implementations 
        have been designed to make it very easy to use your existing 
        authentication schema and data (without modification). Of course,
		you can also provide your own Data Access Object if you wish.<br></br><br></br>
        </li><li><b>Caching:</b> Acegi Security integrates with Spring's <a href="http://ehcache.sourceforge.net/" class="externalLink" title="External Link">EHCACHE</a> factory. 
        This flexibility means your database (or other authentication 
        repository) is not repeatedly queried for authentication 
        information.<br></br><br></br>
        </li><li><b>Pluggable architecture:</b> Every critical aspect of the package 
        has been modelled using high cohesion, loose coupling, interface-driven 
        design principles. You can easily replace, customise or extend parts of 
        the package.<br></br><br></br>
        </li><li><b>Startup-time validation:</b> Every critical object dependency and 
        configuration parameter is validated at application context startup 
        time. Security configuration errors are therefore detected early and 
        corrected quickly.<br></br><br></br>
        </li><li><b>Remoting support:</b> Does your project use a rich client? Not a 
        problem. Acegi Security integrates with standard Spring remoting 
        protocols, because it automatically processes the HTTP BASIC 
        authentication headers they present. Add our BASIC authentication filter 
        to your web.xml and you're done.<br></br><br></br>
        </li><li><b>Advanced password encoding:</b> Of course, passwords in your 
        authentication repository need not be in plain text. We support both SHA 
        and MD5 encoding, and also pluggable "salt" providers to maximise 
        password security.<br></br><br></br>
        </li><li><b>Run-as replacement:</b> The security system fully supports 
        temporarily replacing the authenticated user for the duration of the web 
        request or bean invocation. This enables you to build public-facing 
        object tiers with different security configurations than your backend 
        objects.<br></br><br></br>
        </li><li><b>Transparent security propagation:</b> Acegi Security can automatically
		transfer its core authentication information from one machine to another,
		using a variety of protocols including RMI and Spring's HttpInvoker.<br></br><br></br>
        </li><li><b>Compatible with HttpServletRequest.getRemoteUser():</b> Even though
		Acegi Security can deliver authentication using a range of pluggable mechanisms
		(most of which require no web container configuration), we allow you to access
		the resulting Authentication object via the getRemoteUser() method.<br></br><br></br>
        </li><li><b>Unit tests:</b> A must-have of any quality security project, unit 
        tests are included. Our unit test coverage is very high, as shown in the
		<a href="multiproject/acegi-security/clover/index.html">coverage report</a>.<br></br><br></br>
        </li><li><b>Built by Maven:</b> This assists you in effectively reusing the Acegi
		Security artifacts in your own Maven-based projects.<br></br><br></br>
        </li><li><b>Supports your own unit tests:</b> We provide a number of classes 
        that assist with your own unit testing of secured business objects. For 
        example, you can change the authentication identity and its associated 
        granted authorities directly within your test methods.<br></br><br></br>
        </li><li><b>Peer reviewed:</b> Whilst nothing is ever completely secure, 
        using an open source security package leverages the continuous design 
        and code quality improvements that emerge from peer review.<br></br><br></br>
        </li><li><b>Thorough documentation:</b> All APIs are fully documented using 
        <a href="http://acegisecurity.sourceforge.net/multiproject/acegi-security/apidocs/index.html" class="externalLink" title="External Link">JavaDoc</a>, with a 40+ page 
		<a href="reference.html">Reference Guide</a> providing an easy-to-follow 
        introduction. More documentation is provided on this web site, as
		shown in the left hand navigation sidebar.<br></br><br></br>
        </li><li><b>Apache license.</b><br></br><br></br></li></ul><br></br><b>
      <hr></hr>

      </b><center><b>Project Resources</b></center>
      <hr></hr>
      <br></br>
      <center><a href="http://forum.springframework.org/" class="externalLink" title="External Link"><b>Support 
      Forums</b></a><br></br><br></br><a href="http://sourceforge.net/project/showfiles.php?group_id=104215" class="externalLink" title="External Link"><b>Downloads</b></a>
      </center></font></div></div></div><div class="clear"><hr></hr></div><div id="footer"><div class="xright">� 2004-2005, Acegi Technology Pty Limited</div><div class="clear"><hr></hr></div></div></body></html>