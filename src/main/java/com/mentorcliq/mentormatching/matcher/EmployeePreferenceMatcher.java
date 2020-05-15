package com.mentorcliq.mentormatching.matcher;

import com.mentorcliq.mentormatching.model.EmployeeWithPreferences;
import com.mentorcliq.mentormatching.model.Pair;
import com.mentorcliq.mentormatching.model.Preference;
import org.springframework.stereotype.Service;

@Service
public class EmployeePreferenceMatcher<T extends EmployeeWithPreferences> implements EmployeeMatchingStrategy<Boolean, T> {

    @Override
    public Boolean calculateMatch(Pair<T> pair) {
        T e1 = pair.getFirst();
        T e2 = pair.getSecond();

        if (e1.getPreference().equals(Preference.NO_PREFERENCE) && e2.getPreference().equals(Preference.NO_PREFERENCE)) {
            return true;
        }

        if (e1.getLocation().equals(e2.getLocation())) {
            return (e1.getPreference().equals(Preference.YES) && e2.getPreference().equals(Preference.YES))
                    || (e1.getPreference().equals(Preference.YES) && e2.getPreference().equals(Preference.NO_PREFERENCE))
                    || (e1.getPreference().equals(Preference.NO_PREFERENCE) && e2.getPreference().equals(Preference.YES));
        } else {
            return (e1.getPreference().equals(Preference.NO) && e2.getPreference().equals(Preference.NO))
                    || (e1.getPreference().equals(Preference.NO) && e2.getPreference().equals(Preference.NO_PREFERENCE))
                    || (e1.getPreference().equals(Preference.NO_PREFERENCE) && e2.getPreference().equals(Preference.NO));
        }
    }
}
