package com.example.spm.service;

import com.example.spm.dto.MedicineDTO;
import com.example.spm.model.Medicine;

import java.util.List;


public interface IMedicineService {

    public MedicineDTO createMedicine(MedicineDTO medicineDto);
    public List<MedicineDTO> getAllMedicines();
    public List<MedicineDTO> getMedicinesByEmergencyCase(String emergencyCaseName);
    public List<Medicine> getMedicinesEntityByEmergencyCase(String emergencyCaseName);
    public List<MedicineDTO> getUserMedicines(String username);
    public List<MedicineDTO> getMedicinesNotToUseWithEmergencyCase(String emergencyCaseName);
    public List<MedicineDTO> getAllowedMedicinesByEmergencyCase(String emergencyCaseName);

}
