package org.graniteds.tutorial.data.entities;

import javax.persistence.*;
import lombok.*;
import org.granite.tide.data.DataPublishListener;

import java.io.Serializable;

/**
 *
 * @author oliver.guenther
 */
@Getter
@EqualsAndHashCode(of = "id")
@ToString
@Entity
@EntityListeners({DataPublishListener.class})
public class Box implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    @Setter
    private String name;

    public Box() {
    }

    public Box(String name) {
        this.name = name;
    }

}
