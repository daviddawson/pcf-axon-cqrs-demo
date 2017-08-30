package io.pivotal.catalog.events;

import io.muoncore.newton.NewtonEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by benwilcock on 18/04/2017.
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductAddedEvent implements NewtonEvent<String> {
    private String id;
    private String name;
}
