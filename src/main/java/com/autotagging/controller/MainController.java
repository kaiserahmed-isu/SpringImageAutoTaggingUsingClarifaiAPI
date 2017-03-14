package com.autotagging.controller;


import com.autotagging.service.ImageTagging;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Kaiser Ahmed on 3/14/2017.
 */
@Controller
public class MainController {

    // http://localhost:8080/index
    @RequestMapping(value="/index", method= RequestMethod.GET)
    @ResponseBody
    public String index() {

        // You can change the Image URL accordingly.
        String imageUrl = "http://www.dream-wallpaper.com/free-wallpaper/nature-wallpaper/dream-homes-1-wallpaper/1280x800/free-wallpaper-9.jpg";
       // String imageUrl = "https://samples.clarifai.com/demo-vid-1.mp4";

        // List of Recognized Result from Image
        List<String> resultList = ImageTagging.findTags(imageUrl);

        // Iteration of Result
        for(String result : resultList) {
            System.out.println(result);
        }

        return "Result: "+ resultList.toString();
    }


}
