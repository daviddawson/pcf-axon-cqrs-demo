package io.pivotal.catalog.events;

import io.muoncore.newton.NewtonEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProductAddedEvent implements NewtonEvent<String> {
    private String id;
    private String name;
}
