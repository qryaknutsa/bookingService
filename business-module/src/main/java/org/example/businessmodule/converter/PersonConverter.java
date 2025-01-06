package org.example.businessmodule.converter;


import org.example.businessmodule.dto.PersonWrite;
import org.example.businessmodule.enums.Country;
import org.example.businessmodule.enums.EyeColor;
import org.example.businessmodule.enums.HairColor;
import org.example.businessmodule.model.Person;

public class PersonConverter {

    public static Person toPerson(PersonWrite personWrite){
        Person person = new Person();
        if(personWrite.getEyeColor() != null) person.setEyeColor(EyeColor.valueOf(personWrite.getEyeColor()));
        person.setHeight(personWrite.getHeight());
        if(personWrite.getNationality() != null) person.setNationality(Country.valueOf(personWrite.getNationality()));
        person.setHairColor(HairColor.valueOf(personWrite.getHairColor()));
        person.setLocation(LocationConverter.toLocation(personWrite.getLocation()));
        return person;
    }


    public static PersonWrite toPersonWrite(Person person){
        PersonWrite personWrite = new PersonWrite();
        personWrite.setId(person.getId());
        if(person.getEyeColor() != null) personWrite.setEyeColor(person.getEyeColor().name());
        personWrite.setHeight(person.getHeight());
        if(person.getNationality() != null) personWrite.setNationality(person.getNationality().name());
        personWrite.setHairColor(person.getHairColor().name());
        personWrite.setLocation(LocationConverter.toLocationWrite(person.getLocation()));
        return personWrite;
    }
}
