package com.jt.controller;


import com.jt.pojo.Rights;
import com.jt.service.RightsService;
import com.jt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController  // identifies the controller class Return value is Jason
@RequestMapping("/rights") // Extraction of public parts
@CrossOrigin    //Cross-domain operations on front and back ends
public class RightsController {

    @Autowired
    private RightsService rightsService;

    /**
     * http://localhost:8091/rights/getRightsList
     */

    @GetMapping("getRightsList")
    public SysResult getRightsList(){
        List<Rights> rightsList = rightsService.findList();
        return SysResult.success(rightsList);
    }
}
