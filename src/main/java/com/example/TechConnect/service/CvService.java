package com.example.TechConnect.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.example.TechConnect.model.CurriculumVitae;

@Service
public class CvService {
    private final List<CurriculumVitae> cvList = new ArrayList<>();

    public void addCv(CurriculumVitae cv) {
        cvList.add(cv);
    }

    public List<CurriculumVitae> getAllCv() {
        return cvList;
    }
}
