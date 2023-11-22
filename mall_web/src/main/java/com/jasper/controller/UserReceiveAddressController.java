package com.jasper.controller;

import com.jasper.pojo.UserReceiveAddress;
import com.jasper.service.UserReceiveAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("webService/userAddress")
public class UserReceiveAddressController {

    private final UserReceiveAddressService receiveAddressService;
    @PostMapping("/addAddress")
    private Boolean addAddress(@RequestBody UserReceiveAddress receiveAddress){
        return receiveAddressService.addAddress(receiveAddress);
    }

    @DeleteMapping("/deleteAddress/{id}")
    private Boolean deleteAddress(@PathVariable Long id){
        return receiveAddressService.deleteAddress(id);
    }
}
