package com.example.feign.controller;

import com.example.feign.client.SampleClient;
import com.example.feign.model.SampleRequest;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/sample")
@RequiredArgsConstructor
public class SampleController {

    private final SampleClient sampleClient;

    @GetMapping
    public ResponseEntity getSampleDataByIds(@RequestParam("ids") List<Long> ids) {
        try {
            ResponseEntity responseEntity = sampleClient.getSampleDataByIds(ids);

            return new ResponseEntity(responseEntity.getBody(), responseEntity.getStatusCode());
        }catch (FeignException feignException) {
            return new ResponseEntity(feignException.responseBody(), HttpStatus.valueOf(feignException.status()));
        }
    }

    @DeleteMapping
    public ResponseEntity deleteSampleDataById(@RequestParam("id") Long id) {
        try {
            ResponseEntity responseEntity = sampleClient.deleteSampleDataById(id);

            return new ResponseEntity(responseEntity.getStatusCode());
        }catch (FeignException feignException) {
            return new ResponseEntity(feignException.responseBody(), HttpStatus.valueOf(feignException.status()));
        }
    }

    @PutMapping
    public ResponseEntity updateData(@RequestParam("id") Long id, @RequestBody SampleRequest sampleRequest) {
        try {
            ResponseEntity responseEntity = sampleClient.updateData(id, sampleRequest);

            return new ResponseEntity(responseEntity.getBody(), responseEntity.getStatusCode());
        }catch (FeignException feignException) {
            return new ResponseEntity(feignException.responseBody(), HttpStatus.valueOf(feignException.status()));
        }
    }

    @PostMapping
    public ResponseEntity saveNewData(@RequestBody SampleRequest sampleRequest) {
        try {
            ResponseEntity responseEntity = sampleClient.saveNewData(sampleRequest);

            return new ResponseEntity(responseEntity.getBody(), responseEntity.getStatusCode());
        }catch (FeignException feignException) {
            return new ResponseEntity(feignException.responseBody(), HttpStatus.valueOf(feignException.status()));
        }
    }
}
