package com.project.service;

import com.project.util.FixedData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class  FixedDataService {
    @Autowired
    private FixedData fixData;
    public List<String> getStatusType() {
        return fixData.getStatusType();
    }
    public List<String> getAssetType() {
        return fixData.getAssetType();
    }
    public List<String> getActionType(){
        return fixData.getActionType();
    }
}
