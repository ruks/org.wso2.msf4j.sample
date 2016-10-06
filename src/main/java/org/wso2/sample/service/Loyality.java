/*
 * Copyright (c) 2016, WSO2 Inc. (http://wso2.com) All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wso2.sample.service;

import com.google.gson.Gson;
import io.swagger.annotations.ApiParam;
import org.wso2.sample.bean.PointBean;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * This is the Microservice resource class.
 * See <a href="https://github.com/wso2/msf4j#getting-started">https://github.com/wso2/msf4j#getting-started</a>
 * for the usage of annotations.
 *
 * @since 0.9
 */
@Path("/service")
public class Loyality {
    private final Gson gson = new Gson();
    private static Map<String, PointBean> store = new HashMap<>();

    @GET
    @Path("/reward/{user}")
    @Produces("application/json")
    public Response get(@PathParam("user") String user) {
        System.out.println("GET invoked");
        PointBean obj = store.get(user);
        String jsonString = gson.toJson(obj);
        return Response.status(Response.Status.OK).entity(jsonString).build();
    }

    @POST
    @Path("/reward")
    @Consumes("application/json")
    @Produces("application/json")
    public String post(@ApiParam(value = "PointBean object", required = true) PointBean pointBean) {
        System.out.println("POST invoked");
        store.put(pointBean.getUser(), pointBean);
        return "{\"error\":\"false\"}";
    }

    @PUT
    @Path("/")
    public void put() {
        // TODO: Implementation for HTTP PUT request
        System.out.println("PUT invoked");
    }

    @DELETE
    @Path("/")
    public void delete() {
        // TODO: Implementation for HTTP DELETE request
        System.out.println("DELETE invoked");
    }
}
