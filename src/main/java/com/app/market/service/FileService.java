package com.app.market.service;

import java.io.FileNotFoundException;
import java.util.List;

public interface FileService {

    List<String[]> fileReader() throws FileNotFoundException;

}
