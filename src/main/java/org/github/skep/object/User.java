package org.github.skep.object;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class User {
    private int id;
    private String username;
    private String name;
    private int age;
    private String gender;
}
