package zenmobile.mvc.controllers;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import zenmobile.beans.vo.AppVO;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value="/app/upload")
public class AppUploadController {

	@Autowired
	private HttpServletRequest request;
	
	@RequestMapping(method=RequestMethod.GET) 
	public String getUploadForm(Model model) {
		model.addAttribute("appvo", new AppVO());
		return "app/upload/uploadApp";
	}
	
	  @RequestMapping(method = RequestMethod.POST)
	  public String create(AppVO appVO, BindingResult result)
	  {
          InputStream inputStream = null;
          OutputStream outputStream = null;
          
	    if (result.hasErrors())
	    {
	      for(ObjectError error : result.getAllErrors())
	      {
	        System.err.println("Error: " + error.getCode() +  " - " + error.getDefaultMessage());
	      }
	      return "/app/upload";
	    }
	 
	    // Some type of file processing...
	    System.err.println("-------------------------------------------");
	    System.err.println("Test upload: " + appVO.getName());
	    System.err.println("Test upload 2: " + appVO.getFileData().getOriginalFilename());
	    System.err.println("-------------------------------------------");
	 
        try {
            MultipartFile file = appVO.getFileData();
            String fileName = null;

            if (file.getSize() > 0) {
                    inputStream = file.getInputStream();
                  //  if (file.getSize() > 10000) {
                  //          System.out.println("File Size:::" + file.getSize());
                  //          return "/uploadfile";
                  //  }
                    System.out.println("path::" + request.getRealPath(""));
                    fileName = request.getRealPath("") +  file.getOriginalFilename();
                    outputStream = new FileOutputStream(fileName);
                    System.out.println("fileName:" + file.getOriginalFilename());

                    int readBytes = 0;
                    byte[] buffer = new byte[10000];
                    while ((readBytes = inputStream.read(buffer, 0, 10000)) != -1) {
                            outputStream.write(buffer, 0, readBytes);
                    }
                    outputStream.close();
                    inputStream.close();
            }

		            // ..........................................
		           // session.setAttribute("uploadFile", file.getOriginalFilename());
		    } catch (Exception e) {
		            e.printStackTrace();
		    }
        
	    return "redirect:/user/";
	  }	
}
