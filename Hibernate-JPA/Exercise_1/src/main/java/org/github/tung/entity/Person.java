package org.github.tung.entity;

import lombok.*;

import java.util.List;


@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private String firstName;
    private String lastName;
    private int age;
    private Address address;
    private List<PhoneNumber> phoneNumbers;
}
