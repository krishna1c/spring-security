package org.springframework.security.config.method;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.aop.config.AbstractInterceptorDrivenBeanDefinitionDecorator;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionDecorator;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.access.intercept.aopalliance.MethodSecurityInterceptor;
import org.springframework.security.access.method.MapBasedMethodSecurityMetadataSource;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.Elements;
import org.springframework.util.StringUtils;
import org.springframework.util.xml.DomUtils;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * @author Luke Taylor
 * @author Ben Alex
 *
 */
public class InterceptMethodsBeanDefinitionDecorator implements BeanDefinitionDecorator {
    private BeanDefinitionDecorator delegate = new InternalInterceptMethodsBeanDefinitionDecorator();

    public BeanDefinitionHolder decorate(Node node, BeanDefinitionHolder definition, ParserContext parserContext) {
        MethodConfigUtils.registerDefaultMethodAccessManagerIfNecessary(parserContext);

        return delegate.decorate(node, definition, parserContext);
    }
}

/**
 * This is the real class which does the work. We need access to the ParserContext in order to do bean
 * registration.
 */
class InternalInterceptMethodsBeanDefinitionDecorator extends AbstractInterceptorDrivenBeanDefinitionDecorator {
    static final String ATT_METHOD = "method";
    static final String ATT_ACCESS = "access";
    private static final String ATT_ACCESS_MGR = "access-decision-manager-ref";

    protected BeanDefinition createInterceptorDefinition(Node node) {
        Element interceptMethodsElt = (Element)node;
        BeanDefinitionBuilder interceptor = BeanDefinitionBuilder.rootBeanDefinition(MethodSecurityInterceptor.class);

        // Default to autowiring to pick up after invocation mgr
        interceptor.setAutowireMode(RootBeanDefinition.AUTOWIRE_BY_TYPE);

        String accessManagerId = interceptMethodsElt.getAttribute(ATT_ACCESS_MGR);

        if (!StringUtils.hasText(accessManagerId)) {
            accessManagerId = BeanIds.METHOD_ACCESS_MANAGER;
        }

        interceptor.addPropertyValue("accessDecisionManager", new RuntimeBeanReference(accessManagerId));
        interceptor.addPropertyValue("authenticationManager", new RuntimeBeanReference(BeanIds.AUTHENTICATION_MANAGER));

        // Lookup parent bean information
        Element parent = (Element) node.getParentNode();
        String parentBeanClass = parent.getAttribute("class");
        parent = null;

        // Parse the included methods
        List<Element> methods = DomUtils.getChildElementsByTagName(interceptMethodsElt, Elements.PROTECT);
        Map<String, List<ConfigAttribute>> mappings = new LinkedHashMap<String, List<ConfigAttribute>>();

        for (Element protectmethodElt : methods) {
            String[] tokens = StringUtils.commaDelimitedListToStringArray(protectmethodElt.getAttribute(ATT_ACCESS));

            // Support inference of class names
            String methodName = protectmethodElt.getAttribute(ATT_METHOD);

            if (methodName.lastIndexOf(".") == -1) {
                if (parentBeanClass != null && !"".equals(parentBeanClass)) {
                    methodName = parentBeanClass + "." + methodName;
                }
            }

            mappings.put(methodName, SecurityConfig.createList(tokens));
        }

        BeanDefinition metadataSource = new RootBeanDefinition(MapBasedMethodSecurityMetadataSource.class);
        metadataSource.getConstructorArgumentValues().addGenericArgumentValue(mappings);
        interceptor.addPropertyValue("securityMetadataSource", metadataSource);

        return interceptor.getBeanDefinition();
    }
}
