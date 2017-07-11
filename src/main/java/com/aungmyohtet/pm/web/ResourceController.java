package com.aungmyohtet.pm.web;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.aungmyohtet.pm.entity.Organization;
import com.aungmyohtet.pm.entity.Resource;
import com.aungmyohtet.pm.service.OrganizationService;
import com.aungmyohtet.pm.service.ResourceService;

@Controller
public class ResourceController {

    private static final String UPLOAD_DIRECTORY = "D:/mytemp/";

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private ResourceService resourceService;

    public void setResourceService(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @ModelAttribute("module")
    String module() {
        return "resources";
    }

    @RequestMapping(value = "/{organizationName}/resources/new", method = RequestMethod.GET)
    public String showOrganizationResourceForm(Model model, @PathVariable("organizationName") String organizationName) {

        model.addAttribute("organizationName", organizationName);
        model.addAttribute("resource", new Resource());
        return "organizationResourceForm";
    }

    @RequestMapping(value = "/{organizationName}/resources/new", method = RequestMethod.POST)
    public String addResource(@Validated @ModelAttribute Resource resource, BindingResult result, final @RequestParam("file") MultipartFile file, Model model,
            @PathVariable("organizationName") String organizationName, HttpServletRequest request) {

       /* if (result.hasErrors()) {
            return "organizationResourceForm";
        }*/
/*
        if(resource.getFile()==null)
        {
            model.addAttribute("Pl", attributeValue)
        }*/
        try {

            byte[] bytes = file.getBytes();
            Path path = Paths.get("D:/mytemp/" + file.getOriginalFilename());
            Files.write(path, bytes);

            Organization organization = organizationService.findByName(organizationName);

    //        Resource resource = new Resource();

            resource.setFilePath(path.toString());
            resource.setOrganization(organization);

            resourceService.save(resource);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/{organizationName}/resources";
    }

    @RequestMapping(value = "/{organizationName}/resources/{resourceId}/download", method = RequestMethod.GET)
    public void downloadResourceFile(Model model, @PathVariable("organizationName") String organizationName, @PathVariable("resourceId") String resourceId,
            HttpServletRequest request, HttpServletResponse response) throws IOException {

        Resource resource = resourceService.findById(Integer.parseInt(resourceId));
        Path file = Paths.get(resource.getFilePath());
        if (Files.exists(file)) {
            response.setContentType("application/pdf");
            response.addHeader("Content-Disposition", "attachment; filename=" + file);
            try {
                Files.copy(file, response.getOutputStream());
                response.getOutputStream().flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
