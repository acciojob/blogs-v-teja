package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService {
    @Autowired
    ImageRepository imageRepository2;

    @Autowired
    BlogRepository blogRepository;

    public Image createAndReturn(Blog blog, String description, String dimensions){
        //create an image based on given parameters and add it to the imageList of given blog
        Image newImage = new Image(description,dimensions,blog);
        newImage.setBlog(blog);
        List<Image> currImageList = blog.getImageList();
        if(currImageList==null){
            currImageList = new ArrayList<>();
        }
        currImageList.add(newImage);
        blog.setImageList(currImageList);
        imageRepository2.save(newImage);
        blogRepository.save(blog);

        return newImage;
    }

    public void deleteImage(Image image){
        try{
            imageRepository2.delete(image);
        }catch (Exception e){
            System.out.println("deleteImage Failed");
        }
    }

    public Image findById(int id) {
        return imageRepository2.findById(id).get();
    }

    public int countImagesInScreen(Image image, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        //In case the image is null, return 0
        int count =0;
        try{
            if(image==null){
                return 0;
            }

            String imageDimension = image.getDimensions();
            String[] imageParts = imageDimension.split("X");
            String part1 = imageParts[0];
            String part2 = imageParts[1];


            String[] screenParts = screenDimensions.split("X");
            String partS1 = screenParts[0];
            String partS2 = screenParts[1];

            int length = Integer.parseInt(partS1)/Integer.parseInt(part1);
            int breadth = Integer.parseInt(partS2)/Integer.parseInt(part2);

            count = length*breadth;

        }catch (Exception e){
            System.out.println(e);
        }
        return count;
    }
}
