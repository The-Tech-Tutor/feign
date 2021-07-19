package com.example.feign.client;

import com.example.feign.config.SampleClientConfiguration;
import com.example.feign.model.SampleRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "sampleclient",
        url = "${sample.client.url}",
        configuration = SampleClientConfiguration.class
)
public interface SampleClient {
    @RequestMapping(method = RequestMethod.GET,
            value = "/sample",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<Object> getSampleDataByIds(@RequestParam("ids") List<Long> ids);

    @RequestMapping(method = RequestMethod.DELETE,
            value = "/sample",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity deleteSampleDataById(@RequestParam("id") Long id);

    @RequestMapping(method = RequestMethod.PUT,
            value = "/sample",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<Object> updateData(@RequestParam("id") Long id, @RequestBody SampleRequest sampleRequest);

    @RequestMapping(method = RequestMethod.POST,
        value = "/sample",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<Object> saveNewData(SampleRequest sampleRequest);
}
