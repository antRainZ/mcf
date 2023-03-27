package org.antrain.app.config;

import org.antrain.app.wordfilter.FilterStopWord;
import org.antrain.app.wordfilter.WordContext;
import org.antrain.app.wordfilter.WordFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WordConfig {
    @Bean
    public WordContext wordContext() {
        return new WordContext();
    }

    @Bean
    public WordFilter wordFilter(WordContext wordContext) {
        return new WordFilter(wordContext);
    }

    @Bean
    public FilterStopWord filterStopWord() {
        return new FilterStopWord();
    }
}
