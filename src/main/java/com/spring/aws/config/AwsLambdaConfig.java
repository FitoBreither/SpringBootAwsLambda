package com.spring.aws.config;

import com.spring.aws.domain.Character;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Configuration
public class AwsLambdaConfig {

    @Bean
    public Filter getFilter(){
        return new SecurityFilter();
    }
    @Bean(name = "saludar")
    public Supplier<String> greeting(){
        return () ->  "Hello World";
    }

    @Bean
    public Consumer<String> printParam(){
        return (param) -> {
            System.out.println(param);
        };
    }

    @Bean
    public Function<String, String> receiveParam(){
        return (param) -> {
            String name = param.toUpperCase();
            return name;
        };
    }

    //Generate a Json
    @Bean
    public Supplier<Map<String, Object>> createCharacter(){
        return () -> {
          Map<String, Object> character = new HashMap<>();
          character.put("name","Goku");
          character.put("healthPoints", 100);
          character.put("skill", "Kame Hame ha!");

          return character;
        };
    }

    //recibir un jason y retornar un String
    @Bean
    public Function<Map<String, Object>, String> receiveCharacter(){
        return (param) -> {
            param.forEach((key, value) -> System.out.println(key + " - " + value.toString()));
            return "personaje recibido";
        };
    }

    //Recibir un objeto y retornar un objeto
    @Bean
    public Function<Character, Character> receiveAnObject(){
        return (param) -> param;
    }

    //Recibir un Json y retornar un Json
    @Bean
    public Function<Map<String,Object>, Map<String, Object>> processCharacters(){
        return (param) -> {
            Map<String, Object> mapCharacter = param;

            mapCharacter.forEach((key,value) -> System.out.println(key + " - " + value.toString()));

            Map<String, Object> newCharacter = new HashMap<>();
            newCharacter.put("name", "Krillin");
            newCharacter.put("healthPoints", 50);
            newCharacter.put("skills", new String[] {"Ki en Zan!", "Kame Hame ha!"});
            return newCharacter;
        };
    }
}
