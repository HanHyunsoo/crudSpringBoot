package com.apress.springrecipes.ii.sequence.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@ComponentScan(
        includeFilters = {
                @ComponentScan.Filter(
                        type = FilterType.REGEX,
                        pattern = {"com.apress.springrecipes.ii.*Dao",
                                    "com.apress.springrecipes.ii.*Service"})
        },
        excludeFilters = {
                @ComponentScan.Filter(
                        type = FilterType.ANNOTATION,
                        classes = {org.springframework.stereotype.Controller.class})
        }
)
public class SequenceConfiguration {
}
