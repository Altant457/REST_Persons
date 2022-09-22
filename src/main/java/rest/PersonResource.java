package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.PersonDTO;
import dtos.PersonsDTO;
import errorhandling.PersonNotFoundException;
import facades.PersonFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;

@Path("persons")
public class PersonResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final PersonFacade FACADE = PersonFacade.getInstance(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces("application/json")
    public String getAll() {
        PersonsDTO personList = FACADE.getAllPersons();
        return GSON.toJson(personList);
    }
    @GET
    @Path("{id}")
    @Produces("application/json")
    public String getById(@PathParam("id") int id) throws PersonNotFoundException {
        try {
            PersonDTO personDTO = FACADE.getPerson(id);
            return GSON.toJson(personDTO);
        } catch (Exception e) {
            throw new PersonNotFoundException("No person with provided id found");
        }
    }
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public String addPerson(String input) {
        PersonDTO dto = GSON.fromJson(input, PersonDTO.class);
        PersonDTO newDto = FACADE.addPerson(dto.getfName(), dto.getlName(), dto.getPhone());
        String returnVal = GSON.toJson(newDto);
        returnVal = "{" +returnVal.substring(returnVal.indexOf("\"fName"));
        return returnVal;
    }
    @DELETE
    @Path("{id}")
    @Produces("application/json")
    public String deletePerson(@PathParam("id") int id) throws PersonNotFoundException {
        try {
            if (FACADE.deletePerson(id)) {
                return "{\"status\": \"removed\"}";
            } else {
                return "{\"status\": \"failed\"}";
            }
        } catch (Exception e) {
            throw new PersonNotFoundException("Could not delete, provided id does not exist");
        }
    }
    @PUT
    @Path("{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public String  editPerson(String person, @PathParam("id") int id) {
        PersonDTO dto = GSON.fromJson(person, PersonDTO.class);
        dto.setId(id);
        PersonDTO newDto = FACADE.editPerson(dto);
        return GSON.toJson(newDto);
    }
}

