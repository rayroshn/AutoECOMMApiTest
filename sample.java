public class sample {


    Response res = given().resassrued.baseuri().contentType("application/json").headers(Authorization:"abcd").when().post("api/user").body("\Request
    {
        \"name":"John",
            \"job":"QA Engineer"
    }).then.extract.asstring();

    String httpstatus=res.statucode();
    String name= res.name();
    int id = res.id();
}

