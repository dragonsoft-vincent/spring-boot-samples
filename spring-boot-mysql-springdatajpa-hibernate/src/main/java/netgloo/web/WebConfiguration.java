package netgloo.web;

import lombok.Data;
import org.apache.catalina.connector.Connector;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.File;

/**
 * Web全局配置
 *
 * @author vincentchen
 * @date 16/12/26.
 */
@Configuration
@PropertySource("classpath:/tomcat.https.properties")
@EnableConfigurationProperties(WebConfiguration.TomcatSslConnectorProperties.class)
public class WebConfiguration extends WebMvcConfigurerAdapter{

    @ConfigurationProperties(prefix = "custom.tomcat.https")
    @Data
    public static class TomcatSslConnectorProperties {
        protected final Log logger = LogFactory.getLog(getClass());

        private Integer port;
        private Boolean ssl= true;
        private Boolean secure = true;
        private String scheme = "https";
        private File keystore;
        private String keystorePassword;


        public void configureConnector(Connector connector) {
            if (port != null)
                connector.setPort(port);
            if (secure != null)
                connector.setSecure(secure);
            if (scheme != null)
                connector.setScheme(scheme);
            if (ssl != null)
                connector.setProperty("SSLEnabled", ssl.toString());
            if (keystore != null && keystore.exists()) {
                connector.setProperty("keystoreFile",
                        keystore.getAbsolutePath());
                connector.setProperty("keystorePass",
                        keystorePassword);
            } else {
                logger.error("keystore not found");
            }
        }
    }

    @Bean
    public EmbeddedServletContainerFactory servletContainerFactory(TomcatSslConnectorProperties properties) {
        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
        tomcat.addAdditionalTomcatConnectors(createSslConnector(properties));
        return tomcat;
    }

    private Connector createSslConnector(TomcatSslConnectorProperties properties) {
        Connector connector = new Connector();
        properties.configureConnector(connector);
        return connector;
    }


    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseSuffixPatternMatch(false).setUseTrailingSlashMatch(true);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/internal/**", "/statics/**").addResourceLocations("classpath:/");
    }
}
