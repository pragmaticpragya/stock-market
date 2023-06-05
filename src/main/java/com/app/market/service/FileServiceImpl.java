package com.app.market.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileServiceImpl implements FileService{

    public String fileName;
    public String filePath;

    public FileServiceImpl(String filePath){
        this.filePath = filePath;
    }

    @Override
    public List<String[]> fileReader() throws FileNotFoundException {
        File file = new File(this.filePath);
        List<String[]> ordersList = new ArrayList<>();
        Scanner scanner = new Scanner(file);
        while(scanner.hasNextLine()) {
            String[] data = scanner.nextLine().split(" ");
            ordersList.add(data);
        }
        return ordersList;
    }
}
