package crudsecurity.controller;

import crudsecurity.model.Role;
import crudsecurity.model.User;

import crudsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class UserController {

    final private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String printUserMenu(ModelMap model) {
        List<String> messages = new ArrayList<>();
        Map<String, String> menuMap = new LinkedHashMap<>();

        menuMap.put("Profile Info", "/user");                                                       // < security update
        messages.add("Hello! And welcome to Security CRUD!");                                       // < security update

        model.addAttribute("pageTitle", "User menu page");              // < security update
        model.addAttribute("messages", messages);
        model.addAttribute("menuMap", menuMap);

        return "index";
    }

    // security update :: >>>
    @GetMapping("/user")
    public String printUserInfo(ModelMap model) {
        model.addAttribute("pageTitle", "User profile info");
        model.addAttribute("tableTitle", "User profile info");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        model.addAttribute("user", userService.getUserByName(authentication.getName()));

        return "/user";
    }

    @GetMapping("/admin")
    public String printAdminMenu(ModelMap model) {
        List<String> messages = new ArrayList<>();
        Map<String, String> menuMap = new LinkedHashMap<>();

        menuMap.put("Profile Info", "/user");
        menuMap.put("Users list", "/admin/userlist");
        messages.add("Hello! And welcome to Security CRUD!");

        model.addAttribute("pageTitle", "Admin menu page");
        model.addAttribute("messages", messages);
        model.addAttribute("menuMap", menuMap);

        return "/admin/index";
    }
    // security update :: <<<

    @GetMapping("/admin/userlist")                                                                  // < security update
    public String printUserList(ModelMap model) {
        model.addAttribute("userList", userService.getUserList());
        model.addAttribute("userEditFormUrl", "/admin/useredit");       // < security update
        model.addAttribute("userCreateFormUrl", "/admin/usernew");      // < security update
        model.addAttribute("userDeleteActionUrl", "/admin/userdelete"); // < security update

        return "admin/userlist";                                                                    // < security update
    }

    @GetMapping(value = "/admin/usernew")                                                           // < security update
    public String printNewUserForm(ModelMap model) {
        model.addAttribute("pageTitle", "Create new user");
        model.addAttribute("formTitle", "New user data:");
        model.addAttribute("formAction", "/admin/usersave");            // < security update
        model.addAttribute("submitButtonText", "create");
        model.addAttribute("user", new User());

        return "admin/useredit";                                                                    // < security update
    }

    @GetMapping(value = "/admin/useredit" + "/{id}")                                                // < security update
    public String printEditUserForm(@PathVariable String id, ModelMap model) {
        model.addAttribute("pageTitle", "Edit user");
        model.addAttribute("formTitle", "User data:");
        model.addAttribute("formAction", "/admin/userupdate");          // < security update
        model.addAttribute("submitButtonText", "update");
        model.addAttribute("user", userService.getUserById(Integer.parseInt(id)));
        model.addAttribute("showUserId", "true");

        return "admin/useredit";                                                                    // < security update
    }

    @GetMapping(value = "/admin/userdelete" + "/{id}")                                              // < security update
    public String deleteUser(@PathVariable String id, ModelMap model) {
        userService.delete(userService.getUserById(Integer.parseInt(id)));
        return "redirect:" + "/admin/userlist";                                                     // < security update
    }

    @RequestMapping(value = "/admin/usersave", method = RequestMethod.POST)                         // < security update
    public String createNewUser(@ModelAttribute("user") User newUser, ModelMap model) {
        Role userRole = new Role();
        userRole.setRole("ROLE_USER");
        Set<Role> userRolesSet = new HashSet<>();
        userRolesSet.add(userRole);
        newUser.setRoles(userRolesSet);

        userService.add(newUser);
        return "redirect:" + "/admin/userlist";                                                     // < security update
    }

    @RequestMapping(value = "/admin/userupdate", method = RequestMethod.POST)                       // < security update
    public String updateUser(@ModelAttribute("user") User user, ModelMap model) {
        userService.update(user);
        return "redirect:" + "/admin/userlist";                                                     // < security update
    }

}
