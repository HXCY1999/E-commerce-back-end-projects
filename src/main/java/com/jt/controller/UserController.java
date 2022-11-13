package com.jt.controller;

import com.jt.pojo.ItemCat;
import com.jt.pojo.User;
import com.jt.service.UserService;
import com.jt.vo.PageResult;
import com.jt.vo.SysResult;
import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController  // identifies the controller class Return value is Jason
@RequestMapping("/rights") // Extraction of public parts
@CrossOrigin    // Cross-domain operations on front and back ends
public class UserController {


    @Autowired
    private UserService userService;

    @GetMapping("/findAll")
    public List<User> findAll(){

        return userService.findAll();
    }
    /**
     * http://localhost:8091/user/login
     */
    @PostMapping("/login")
    public SysResult login(@RequestBody User user){
       String token = userService.login(user);
       if (token != null) {
           return SysResult.success();
       } else{
           return SysResult.fail();
       }
    }
    /**
     * user table display
     * request path: /user/list
     * request type: GET
     * request variable: The backend uses the PageResult object to receive
     * request case : http://localhost:8091/user/list?query= query key word &pageNum=1&pageSize=10
     */
    @GetMapping("list")
    public SysResult getUserList(PageResult pageResult){
        pageResult = userService.findByPage(pageResult);
        return  SysResult.success(pageResult);
    }
    /**
     * http://localhost:8091/user/status/1/false
     *                                  {id}{status}
     */
    @PutMapping("status/{id}/{status}")
    @Transactional
    public SysResult changeUserStatus(User user){
        userService.changeUserStatus(user);
        return SysResult.success();
    }
    /**
     *  /user/addUser
     */
    @PostMapping("addUser")
    public  SysResult addUser(@RequestBody User user){
        userService.addUser(user);
        return SysResult.success();
    }
    /**
     * request URL: http://localhost:8091/user/1
     * request type: GET
     */
    @GetMapping("{id}")
    public SysResult findUser(@PathVariable Integer id){
       User user = userService.findUser(id);
        return SysResult.success(user);
    }
    /**
     * request URL: http://localhost:8091/user/updateUser
     * request method: OPTIONS
     */
    @PutMapping("updateUser")
    public SysResult updateUser(@RequestBody User user){
        userService.updateUser(user);
        return SysResult.success();
    }
    /**
     * request URL: http://localhost:8091/user/{id}
     */
    @DeleteMapping("{id}")
    public SysResult deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
        return SysResult.success();
    }


}
