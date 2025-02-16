package org.github.tung.utils;

import jakarta.json.*;
import jakarta.json.stream.JsonGenerator;
import org.github.tung.Main;
import org.github.tung.entity.Address;
import org.github.tung.entity.Person;
import org.github.tung.entity.PhoneNumber;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonUtil {
    public static void main(String[] args) {
        Person p = fromJson("json/person.json");
        System.out.println(p);
    }

    public static Person fromJson(String fileName) {
        Person p = null;
        try (JsonReader reader = Json.createReader(new FileReader(fileName))) {
            JsonObject jo = reader.readObject();
            if (jo != null) {
                p = new Person();
                String fName = jo.getString("firstName");
                p.setFirstName(fName);
                p.setLastName(jo.getString("lastName"));
                p.setAge(jo.getInt("age"));

                JsonObject joAddr = jo.getJsonObject("address");
                if (joAddr != null) {
                    Address address = new Address();
                    address.setStreetAddress(joAddr.getString("streetAddress"));
                    address.setCity(joAddr.getString("city"));
                    address.setState(joAddr.getString("state"));
                    address.setPostalCode(joAddr.getInt("postalCode"));
                    p.setAddress(address);
                }

                JsonArray jsPhone = jo.getJsonArray("phoneNumbers");
                if (jsPhone != null) {
                    List<PhoneNumber> phones = jsPhone.stream()
                            .map(jv -> (JsonObject) jv)
                            .map(joPhone -> {
                                PhoneNumber phoneNumber = new PhoneNumber();
                                phoneNumber.setType(joPhone.getString("type"));
                                phoneNumber.setNumber(joPhone.getString("number"));
                                return phoneNumber;
                            }).toList();
                    p.setPhoneNumbers(phones);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return p;
    }

    public static void toJsonAndWrite2File(List<Person> people, String s) {
        Map<String, String> conf = new HashMap<>();
        conf.put(JsonGenerator.PRETTY_PRINTING, ""); // Thêm cấu hình Pretty Printing
        JsonWriterFactory factory = Json.createWriterFactory(conf);
        try (JsonWriter writer = factory.createWriter(new FileWriter(s))) {
            JsonArrayBuilder jaBuilder = Json.createArrayBuilder();
            JsonObjectBuilder joBuilder = Json.createObjectBuilder();

            people.stream().map(person -> {
                joBuilder.add("firstName", person.getFirstName())
                        .add("lastName", person.getLastName())
                        .add("age", person.getAge())
                        .add("address", toJson(person.getAddress()));
                if (person.getPhoneNumbers() != null) {
                    joBuilder.add("phoneNumber", toJson(person.getPhoneNumbers()));
                }
                return joBuilder.build();
            }).toList().forEach(jo -> jaBuilder.add(jo));
            JsonArray ja = jaBuilder.build();
            writer.writeArray(ja);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static JsonObject toJson(Address address) {
        JsonObjectBuilder jo = Json.createObjectBuilder();
        return jo.add("streetAddress", address.getStreetAddress())
                .add("city", address.getCity())
                .add("state", address.getState())
                .add("postalCode", address.getPostalCode())
                .build();
    }

    private static JsonArray toJson(List<PhoneNumber> phoneNumbers) {
        JsonArrayBuilder jaBuilder = Json.createArrayBuilder();
        JsonObjectBuilder joBuilder = Json.createObjectBuilder();

        phoneNumbers.stream()
                .map(phone -> {
                    return joBuilder.add("type", phone.getType())
                            .add("number", phone.getNumber()).build();
                })
                .toList()
                .forEach(jo -> jaBuilder.add(jo));

        return jaBuilder.build();
    }
}
