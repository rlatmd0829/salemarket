package com.salemarket.salemarket.controller;

import com.salemarket.salemarket.service.HeartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HeartController {

    private final HeartService heartService;

}
