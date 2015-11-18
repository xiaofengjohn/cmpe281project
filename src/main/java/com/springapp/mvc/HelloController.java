package com.springapp.mvc;

import com.mongodb.client.MongoCollection;
import com.springapp.mvc.Authentication.AccountInfo;
import com.springapp.mvc.Authentication.AccountInfoDAO;
import com.springapp.mvc.Data.DataDAO;
import com.springapp.mvc.Data.DataPoint;
import com.springapp.mvc.Data.Usage;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/*")
public class HelloController {
    MongoCollection collection;
    AccountInfoDAO accountInfoDAO = new AccountInfoDAO();
    DataDAO dataDAO = new DataDAO();
    @RequestMapping(value = "register", method = RequestMethod.POST)
    @ResponseBody
    AccountInfo register(@RequestParam String name, @RequestParam String password) {
        AccountInfo accountInfo = new AccountInfo(name, password);
        if(accountInfoDAO.insert(accountInfo)) {
            return accountInfo;
        } else {
            return null;
        }
    }


    @RequestMapping(value = "/admin/deregister/{name}", method = RequestMethod.DELETE)
    void adminDeregister(@PathVariable String name) {
        accountInfoDAO.delete(name);
    }

    @RequestMapping(value = "/user/deregister", method = RequestMethod.DELETE)
    void Deregister(@PathVariable String name) {
        accountInfoDAO.delete(name);
        dataDAO.deleteByName(name);
    }

    @RequestMapping(value = "/user/uploaddata", method = RequestMethod.POST)
    @ResponseBody DataPoint upload(@RequestBody DataPoint dataPoint) {
        if(dataDAO.insert(dataPoint)) {
            return dataPoint;
        }
        return null;
    }

    @RequestMapping(value = "/user/getdata", method = RequestMethod.GET)
    @ResponseBody
    List<DataPoint> userGetData(@RequestBody DataPoint dataPoint, final HttpServletRequest request) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        List<DataPoint> result = dataDAO.findByName(name);
        return result;
    }

    @RequestMapping(value = "/user/getusage", method = RequestMethod.GET)
    @ResponseBody
    Usage userGetUsage(final HttpServletRequest request) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        List<DataPoint> dataPoints = dataDAO.findByName(name);
        int credit = accountInfoDAO.getAccountInfo(name).getCredit();
        Usage usage = new Usage(dataPoints.size(), credit);
        return usage;
    }

    @RequestMapping(value = "/user/topup", method = RequestMethod.POST)
    @ResponseBody
    Usage userGetUsage(final HttpServletRequest request, @RequestParam int credit) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        AccountInfo accountInfo = accountInfoDAO.getAccountInfo(name);
        accountInfoDAO.delete(accountInfo.getName());
        accountInfo.setCredit(accountInfo.getCredit() + credit);
        accountInfoDAO.insert(accountInfo);
        return userGetUsage(request);
    }

    @RequestMapping(value = "/admin/getusage", method = RequestMethod.GET)
    @ResponseBody
    Usage adminGetUsage() {
        List<String> allnames = accountInfoDAO.getAllName();

    }


	@RequestMapping(value = "", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		model.addAttribute("message", "Hello world!");
		return "hello";
	}

    @RequestMapping(value = "test", method = RequestMethod.GET)
    public String testLogin(ModelMap model) {

        AccountInfo accountInfo = accountInfoDAO.getAccountInfo("user1");
        if(accountInfo != null)
        model.addAttribute("message", accountInfo.getName() + accountInfo.getPassword());
        else
            model.addAttribute("message", "cannot find");
        return "hello";
    }

    @RequestMapping(value = "user/getname", method = RequestMethod.GET)
    public String testUserAuth(ModelMap model, final HttpServletRequest request) {
        model.addAttribute("message", SecurityContextHolder.getContext().getAuthentication().getName());
        return "hello";
    }
    @RequestMapping(value = "admin", method = RequestMethod.GET)
    public String testAdminAuth() {
        return "writeconfirm";
    }
}