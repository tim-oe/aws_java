package org.tec.aws.conf;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean(name = "objectMapper")
    public ObjectMapper objectMapper() {
        ObjectMapper om = new ObjectMapper();

        om.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //https://stackoverflow.com/questions/37164399/jackson-desrialize-when-jsonproperty-is-sometimes-array-and-sometimes-a-single-o
        om.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        om.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        //https://github.com/FasterXML/jackson-modules-java8
        om.registerModule(new Jdk8Module());
        om.registerModule(new ParameterNamesModule());
        om.registerModule(new JavaTimeModule());

        return om;
    }
}
