package acegifier.web;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.TransformerException;

import org.acegisecurity.util.FilterChainProxy;
import org.acegisecurity.util.InMemoryResource;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import acegifier.WebXmlConverter;

/**
 * Takes a submitted web.xml, applies the transformer to it and returns the resulting
 * modified web.xml and acegi-app-context.xml file contents.
 *
 * @author Luke Taylor
 * @version $Id$
 */
public class AcegifierController extends SimpleFormController {

    public AcegifierController() {
    }

    public ModelAndView onSubmit(
            HttpServletRequest request, HttpServletResponse response, Object command, BindException errors)
                throws Exception {

        AcegifierForm conversion = (AcegifierForm)command;
        WebXmlConverter converter = new WebXmlConverter();
        int nBeans = 0;
        Document newWebXml = null, acegiBeans = null;

        try {
            converter.setInput(conversion.getWebXml());
            converter.doConversion();
            newWebXml = converter.getNewWebXml();
            acegiBeans = converter.getAcegiBeans();
            nBeans = validateAcegiBeans(conversion, acegiBeans, errors);
        } catch (DocumentException de) {
            errors.rejectValue("webXml","webXmlDocError","There was a problem with your web.xml: " + de.getMessage());
        } catch (TransformerException te) {
            errors.rejectValue("webXml","transFailure","There was an error during the XSL transformation: " + te.getMessage());
        }

        if(errors.hasErrors()) {
            return showForm(request, response, errors);
        }

        Map model = new HashMap();
        model.put("webXml", prettyPrint(newWebXml));
        model.put("acegiBeansXml", prettyPrint(acegiBeans));
        model.put("nBeans", new Integer(nBeans));

        return new ModelAndView("acegificationResults", model);
    }

    /** Creates a formatted XML string from the supplied document */
    private String prettyPrint(Document document) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setTrimText(false);
        XMLWriter writer = new XMLWriter(output, format);
        writer.write(document);
        writer.flush();
        writer.close();
        return output.toString();
    }

    /**
     * Validates the acegi beans, based on the input form data, and returns the number
     * of spring beans defined in the document.
     */
    private int validateAcegiBeans(AcegifierForm conversion, Document beans, Errors errors) {
        DefaultListableBeanFactory bf = createBeanFactory(beans);

        //TODO: actually do some proper validation!

        try {
            bf.getBean("filterChainProxy", FilterChainProxy.class);
        } catch (BeansException be) {
            errors.rejectValue("webXml","beansInvalid","There was an error creating or accessing the bean factory " + be.getMessage());
        }
        return bf.getBeanDefinitionCount();
    }

    /** Creates a BeanFactory from the spring beans XML document */
    private DefaultListableBeanFactory createBeanFactory(Document beans) {
        DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanReader = new XmlBeanDefinitionReader(bf);
        beanReader.loadBeanDefinitions(new InMemoryResource(beans.asXML().getBytes()));

        return bf;
    }

}