package com.mertoatakan.restaurantservice.config;

import com.mertoatakan.restaurantservice.errormessage.SolrClientErrorMessage;
import com.mertoatakan.restaurantservice.exceptions.SolrClientException;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;

@Configuration
@EnableSolrRepositories(basePackages = "com.mertoatakan.restaurantservice.repository")
public class SolrConfig {

    @Value("${spring.data.solr.host}")
    private String solrHost;

    @Bean
    public SolrClient solrClient() {
        return new HttpSolrClient.Builder(solrHost).build();
    }

    @Bean
    public SolrTemplate solrTemplate(SolrClient client) throws SolrClientException {
        try {
            return new SolrTemplate(client);
        } catch (Exception e) {
            // Log the exception or handle it as needed
            throw new SolrClientException(SolrClientErrorMessage.SOLR_CLIENT_ERROR_MESSAGE);
        }
    }
}