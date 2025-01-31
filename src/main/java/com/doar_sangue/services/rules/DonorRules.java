package com.doar_sangue.services.rules;

import com.doar_sangue.entities.Donor;

public interface DonorRules {
    boolean canDonate(Donor donor);
}