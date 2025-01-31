package com.doar_sangue.services.rules;

import com.doar_sangue.entities.Donor;
import com.doar_sangue.utils.DonorUtils;

public class FemaleDonorRules implements DonorRules {
    @Override
    public boolean canDonate(Donor donor) {
        return DonorUtils.calcAge(donor.getDataNasc()) >= 16 && DonorUtils.calcAge(donor.getDataNasc()) <= 65 && donor.getPeso() > 50;
    }
}
