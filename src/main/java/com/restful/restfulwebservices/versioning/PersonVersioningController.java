package com.restful.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// example of Restful services versioning
@RestController
public class PersonVersioningController {

    // one way of versioning
    // called as uri versioning
    @GetMapping(path="/v1/person")
    public PersonV1 personV1(){
        return new PersonV1("Burhan Atique");
    }

    @GetMapping(path="/v2/person")
    public PersonV2 personV2(){
        return new PersonV2(new Name("Burhan", "Atique"));
    }


    //other way is to pass different params in url e.g url?version=1
    //called as request paramter versioning
        @GetMapping(path="/person/params",params = "version=1")
        public PersonV1 paramV1(){
            return new PersonV1("Burhan Atique");
        }

        @GetMapping(path="/person/params",params = "version=2")
        public PersonV2 paramV2(){
            return new PersonV2(new Name("Burhan", "Atique"));
        }



        // caching is not possible in header and mime type versioning as we urls will be same
        // where as for above 2 approaches caching is possible as urls are different
        //but uri is populated in above approaches as we have to make new urls everytime for new versions


        // other way is to pass different vales in the header of request
    // called as request header versioning
        @GetMapping(path="/person/header",headers = "X-API-VERSION=1")
        public PersonV1 headerV1(){
            return new PersonV1("Burhan Atique");
        }

        @GetMapping(path="/person/header",headers = "X-API-VERSION=2")
        public PersonV2 headerV2(){
            return new PersonV2(new Name("Burhan", "Atique"));
        }

    // last way is to pass different produces
    //produces check what is the output od the service. it is also called Accept header versioning or mime type versioning
    // or content negotiation
    // we give Accept=application/vnd.company.app-v1+json in the header
    @GetMapping(path="/person/produces",produces = "application/vnd.company.app-v1+json")
    public PersonV1 producesV1(){
        return new PersonV1("Burhan Atique");
    }

    @GetMapping(path="/person/produces",produces = "application/vnd.company.app-v2+json")
    public PersonV2 producesV2(){
        return new PersonV2(new Name("Burhan", "Atique"));
    }
}
